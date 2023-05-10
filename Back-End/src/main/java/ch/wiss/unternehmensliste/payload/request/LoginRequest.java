package ch.wiss.unternehmensliste.payload.request;

import jakarta.validation.constraints.NotBlank;

/**
 * Die LoginRequest Klasse wird verwendet, um die Daten des Users als JSON-Objekt zu erhalten.
 * Dies wird verwendet, um dem User die Daten zu senden, wenn er sich einloggt.
 */
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
