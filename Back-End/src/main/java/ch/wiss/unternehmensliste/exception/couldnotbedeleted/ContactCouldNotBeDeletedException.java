package ch.wiss.unternehmensliste.exception.couldnotbedeleted;

public class ContactCouldNotBeDeletedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while deleting a Contact
     *
     * @param id
     *
     * @return Exception Message
     */
    public ContactCouldNotBeDeletedException(int id) {
        super("The Contact with id '" + id + "' could not be deleted.");
    }
}
