package ch.wiss.unternehmensliste.exception.couldnotbesaved;

public class JobCouldNotBeSavedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while saving a Job
     *
     * @param jobName
     *
     * @return Exception Message
     */
    public JobCouldNotBeSavedException(String jobName) {
        super("The Job with name '" + jobName + "' could not be saved.");
    }

    /**
     * Handles an Exception that accuses while saving a Job
     *
     *
     * @return Exception Message
     */
    public JobCouldNotBeSavedException() {
        super("The JobAccount could not be saved.");
    }
}
