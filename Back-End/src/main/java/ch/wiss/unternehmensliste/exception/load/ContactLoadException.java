package ch.wiss.unternehmensliste.exception.load;

public class ContactLoadException extends RuntimeException {
    /**
     * Handles an Exception that accuses while loading an ContactAccount
     *
     *
     * @return Exception Message
     */
    public ContactLoadException() {
        super("Contacts could not be loaded.");
    }
}
