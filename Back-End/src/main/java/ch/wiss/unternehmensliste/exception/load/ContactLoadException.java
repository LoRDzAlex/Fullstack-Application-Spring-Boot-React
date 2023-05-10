package ch.wiss.unternehmensliste.exception.load;

public class ContactLoadException extends RuntimeException {
    /**
     * Diese Exception wird geworfen, wenn die Kontakte nicht geladen werden können
     *
     *
     * @return Exception Message
     */
    public ContactLoadException() {
        super("Contacts could not be loaded.");
    }
}
