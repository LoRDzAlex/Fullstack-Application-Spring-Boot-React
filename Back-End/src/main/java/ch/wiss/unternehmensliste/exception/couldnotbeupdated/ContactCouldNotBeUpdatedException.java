package ch.wiss.unternehmensliste.exception.couldnotbeupdated;

public class ContactCouldNotBeUpdatedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while updating a Contact
     *
     * @param id
     *
     * @return Exception Message
     */
    public ContactCouldNotBeUpdatedException(int id) {
        super("The Contact with id '" + id + "' could not be updated.");
    }
}
