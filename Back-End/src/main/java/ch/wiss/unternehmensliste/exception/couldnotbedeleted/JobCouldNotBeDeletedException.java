package ch.wiss.unternehmensliste.exception.couldnotbedeleted;

public class JobCouldNotBeDeletedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while deleting a Job
     *
     * @param id
     *
     * @return Exception Message
     */
    public JobCouldNotBeDeletedException(int id) {
        super("The Job with id '" + id + "' could not be deleted.");
    }
}
