package ch.wiss.unternehmensliste.controller;

import ch.wiss.unternehmensliste.model.User;
import ch.wiss.unternehmensliste.payload.response.MessageResponse;
import ch.wiss.unternehmensliste.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class UserController {
    @Autowired
    UserRepository userRepository;

    /**
     * Gibt den öffentlichen Inhalt zurück.
     *
     * @return öffentlicher Inhalt
     */
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    /**
     * Gibt die Kontodetails des authentifizierten Benutzers zurück.
     *
     * @param userDetails Benutzerdetails
     * @return Kontodetails des Benutzers
     */
    @GetMapping("/account")
    @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<?> getAccountDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userDetails);
    }

    /**
     * Gibt alle Benutzer zurück.
     *
     * @return alle Benutzer
     */
    @GetMapping("/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers() {
        Iterable<User> users = null;
        try{
            users = userRepository.findAll();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
        return ResponseEntity.ok(users);
    }

    /**
     * Löscht einen Benutzer anhand der ID.
     *
     * @param id Benutzer-ID
     * @return erfolgreiche Löschungsnachricht
     */
    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        try{
            userRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    /**
     * Aktualisiert einen Benutzer anhand von Benutzername oder E-Mail-Adresse.
     *
     * @param username Benutzername
     * @param email E-Mail-Adresse
     * @return erfolgreiche Aktualisierungsnachricht
     */
    @PatchMapping("/user")
    public ResponseEntity<?> updateUser(@RequestParam(required = false) String username, @RequestParam(required = false) String email) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

            if (email != null & !user.getEmail().equals(email)) {
                user.setEmail(email);
            }
            if (username != null && !user.getUsername().equals(username)) {
                user.setUsername(username);
            }

            userRepository.save(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }

    /**
     * Gibt den User Inhalt zurück.
     *
     * @return user Inhalt
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }
    /**
     * Gibt den Firmen Inhalt zurück.
     *
     * @return firmen Inhalt
     */
    @GetMapping("/company")
    @PreAuthorize("hasRole('COMPANY')")
    public String companyAccess() {
        return "Company Content.";
    }
    /**
     * Gibt den Admin Inhalt zurück.
     *
     * @return admin Inhalt
     */
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
