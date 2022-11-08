package ch.wiss.unternehmensliste.exception.load;

public class JobLoadException extends RuntimeException {
    /**
     * Handles an Exception that accuses while loading a Job
     *
     *
     * @return Exception Message
     */
    public JobLoadException() {
        super("Jobs could not be loaded.");
    }
}
