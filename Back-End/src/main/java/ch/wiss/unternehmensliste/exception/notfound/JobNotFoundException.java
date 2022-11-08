package ch.wiss.unternehmensliste.exception.notfound;

public class JobNotFoundException extends RuntimeException {
    /**
     * Handles an Exception that accuses while searching a Job with the ID
     *
     * @param jobId
     *
     * @return Exception Message
     */
    public JobNotFoundException(int jobId) {
        super("The job with id '" + jobId + "' could not be found.");
    }
}
