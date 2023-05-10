package ch.wiss.unternehmensliste.exception.notfound;

public class JobNotFoundException extends RuntimeException {
    /**
     * Diese Exception wird geworfen, wenn ein Job nicht gefunden werden kann
     *
     * @param jobId
     *
     * @return Exception Message
     */
    public JobNotFoundException(int jobId) {
        super("The job with id '" + jobId + "' could not be found.");
    }
}
