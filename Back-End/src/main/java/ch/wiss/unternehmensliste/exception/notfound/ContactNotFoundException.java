package ch.wiss.unternehmensliste.exception.notfound;

public class ContactNotFoundException extends RuntimeException {
    /**
     * Handles an Exception that accuses while searching an Contact with the ID
     *
     * @param contactId
     *
     * @return Exception Message
     */
    public ContactNotFoundException(int contactId) {
        super("The contact with id '" + contactId + "' could not be found.");
    }
    /**
     * Handles an Exception that accuses while searching an Contact with the Email
     *
     * @param email
     *
     * @return Exception Message
     */
    public ContactNotFoundException(String email) {
        super("The contact with email '" + email + "' could not be found.");
    }
}
