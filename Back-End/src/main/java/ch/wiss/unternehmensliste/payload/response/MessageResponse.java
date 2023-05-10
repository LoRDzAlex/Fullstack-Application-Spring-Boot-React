package ch.wiss.unternehmensliste.payload.response;

/**
 * Die MessageResponse Klasse wird verwendet, um eine Nachricht als JSON-Objekt zurückzugeben.
 * Dies wird verwendet, um dem User eine Nachricht zu senden, wenn er sich registriert oder ein Passwort zurücksetzt.
 */
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
