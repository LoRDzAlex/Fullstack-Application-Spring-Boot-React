package ch.wiss.unternehmensliste.exception.couldnotbeupdated;

public class JobCouldNotBeUpdatedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while updating a Job
     *
     * @param id
     *
     * @return Exception Message
     */
    public JobCouldNotBeUpdatedException(int id) {
        super("The Job with id '" + id + "' could not be updated.");
    }
}