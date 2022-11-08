package ch.wiss.unternehmensliste.exception.couldnotbesaved;

public class ContactCouldNotBeSavedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while saving a Contact
     *
     * @param contactName
     *
     * @return Exception Message
     */
    public ContactCouldNotBeSavedException(String contactName) {
        super("The Contact with name '" + contactName + "' could not be saved.");
    }

    /**
     * Handles an Exception that accuses while saving a Contact
     *
     *
     * @return Exception Message
     */
    public ContactCouldNotBeSavedException() {
        super("The ContactAccount could not be saved.");
    }
}
