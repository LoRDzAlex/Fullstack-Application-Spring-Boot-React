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
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/account")
    @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<?> getAccountDetails(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(userDetails);
    }

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


    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('COMPANY') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/company")
    @PreAuthorize("hasRole('COMPANY')")
    public String companyAccess() {
        return "Company Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
