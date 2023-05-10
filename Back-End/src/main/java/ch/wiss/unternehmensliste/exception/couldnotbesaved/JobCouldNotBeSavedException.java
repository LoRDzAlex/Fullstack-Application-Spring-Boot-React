package ch.wiss.unternehmensliste.exception.couldnotbesaved;

public class JobCouldNotBeSavedException extends RuntimeException{
    /**
     * Diese Exception wird geworfen, wenn ein Job nicht gespeichert werden kann
     *
     * @param jobName
     *
     * @return Exception Message
     */
    public JobCouldNotBeSavedException(String jobName) {
        super("The Job with name '" + jobName + "' could not be saved.");
    }

    /**
     * Diese Exception wird geworfen, wenn ein Job nicht gespeichert werden kann
     *
     *
     * @return Exception Message
     */
    public JobCouldNotBeSavedException() {
        super("The JobAccount could not be saved.");
    }
}
