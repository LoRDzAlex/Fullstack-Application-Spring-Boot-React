package ch.wiss.unternehmensliste.exception.couldnotbedeleted;

public class CompanyCouldNotBeDeletedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while deleting a Company
     *
     * @param id
     *
     * @return Exception Message
     */
    public CompanyCouldNotBeDeletedException(int id) {
        super("The Company with id '" + id + "' could not be deleted.");
    }
}
