package ch.wiss.unternehmensliste.exception.couldnotbeupdated;

public class CompanyCouldNotBeUpdatedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while updating a Company
     *
     * @param id
     *
     * @return Exception Message
     */
    public CompanyCouldNotBeUpdatedException(int id) {
        super("The Company with id '" + id + "' could not be updated.");
    }
}
