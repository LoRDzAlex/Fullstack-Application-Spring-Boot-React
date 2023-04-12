package ch.wiss.unternehmensliste.controller;

import ch.wiss.unternehmensliste.model.Role;
import ch.wiss.unternehmensliste.model.User;
import ch.wiss.unternehmensliste.payload.request.LoginRequest;
import ch.wiss.unternehmensliste.payload.request.SignupRequest;
import ch.wiss.unternehmensliste.payload.response.JwtResponse;
import ch.wiss.unternehmensliste.payload.response.MessageResponse;
import ch.wiss.unternehmensliste.repository.RoleRepository;
import ch.wiss.unternehmensliste.repository.UserRepository;
import ch.wiss.unternehmensliste.security.jwt.JwtUtils;
import ch.wiss.unternehmensliste.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Transactional
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * Ein neuer Benutzer wird eingeloggt
     * @param loginRequest
     * @return ResponseEntity
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        // Authentifizierung des Benutzers
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        // Setzen der Authentifizierung im Security Context Holder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generieren eines JWT-Tokens
        String jwt = jwtUtils.generateJwtToken(authentication);
        // Extrahieren der Benutzerdetails aus dem Security Context Holder
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        // Rückgabe des JWT-Tokens und der Benutzerdetails
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }
    /**
     * Registrieren eines neuen Benutzers
     * @param signUpRequest
     * @return ResponseEntity
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // Prüfen, ob der Benutzername oder die E-Mail-Adresse bereits existieren
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }
        // Prüfen, ob die E-Mail-Adresse bereits existiert
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Erstellen eines neuen Benutzers
        User user = new User(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail());
        // Setzen der Benutzerrollen
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        // Prüfen, ob die Benutzerrolle angegeben wurde
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(Role.ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    // Prüfen, ob die Benutzerrolle "admin" oder "company" ist
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(Role.ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "company" -> {
                        Role companyRole = roleRepository.findByName(Role.ERole.ROLE_COMPANY)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(companyRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(Role.ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        // Speichern des Benutzers in der Datenbank
        user.setRoles(roles);
        userRepository.save(user);
        // Rückgabe einer Erfolgsmeldung
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
