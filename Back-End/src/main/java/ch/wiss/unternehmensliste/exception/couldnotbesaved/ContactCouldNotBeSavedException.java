package ch.wiss.unternehmensliste.exception.couldnotbesaved;

public class ContactCouldNotBeSavedException extends RuntimeException{
    /**
     * Diese Exception wird geworfen, wenn ein Kontakt nicht gespeichert werden kann
     *
     * @param contactName
     *
     * @return Exception Message
     */
    public ContactCouldNotBeSavedException(String contactName) {
        super("The Contact with name '" + contactName + "' could not be saved.");
    }

    /**
     * Diese Exception wird geworfen, wenn ein Kontakt nicht gespeichert werden kann
     *
     *
     * @return Exception Message
     */
    public ContactCouldNotBeSavedException() {
        super("The ContactAccount could not be saved.");
    }
}
