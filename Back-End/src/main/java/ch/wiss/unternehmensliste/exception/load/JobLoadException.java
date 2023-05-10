package ch.wiss.unternehmensliste.exception.load;

public class JobLoadException extends RuntimeException {
    /**
     * Diese Exception wird geworfen, wenn die Jobs nicht geladen werden können
     *
     *
     * @return Exception Message
     */
    public JobLoadException() {
        super("Jobs could not be loaded.");
    }
}
