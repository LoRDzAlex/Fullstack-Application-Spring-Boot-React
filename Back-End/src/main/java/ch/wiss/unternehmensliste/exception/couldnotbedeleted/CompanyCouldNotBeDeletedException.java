package ch.wiss.unternehmensliste.exception.couldnotbedeleted;
/**
 * Exceptionklasse für Unternehmen, die nicht gelöscht werden können
 *
 */


public class CompanyCouldNotBeDeletedException extends RuntimeException{
    /**
     * Exception wird geworfen, wenn ein Unternehmen nicht gelöscht werden kann
     *
     * @param id
     *
     * @return Exception Message
     */
    public CompanyCouldNotBeDeletedException(int id) {
        super("The Company with id '" + id + "' could not be deleted.");
    }
}
