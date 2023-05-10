package ch.wiss.unternehmensliste.exception.notfound;

public class ContactNotFoundException extends RuntimeException {
    /**
     * Diese Exception wird geworfen, wenn ein Kontakt nicht gefunden werden kann
     *
     * @param contactId
     *
     * @return Exception Message
     */
    public ContactNotFoundException(int contactId) {
        super("The contact with id '" + contactId + "' could not be found.");
    }
    /**
     * Diese Exception wird geworfen, wenn ein Kontakt nicht gefunden werden kann
     *
     * @param email
     *
     * @return Exception Message
     */
    public ContactNotFoundException(String email) {
        super("The contact with email '" + email + "' could not be found.");
    }
}
