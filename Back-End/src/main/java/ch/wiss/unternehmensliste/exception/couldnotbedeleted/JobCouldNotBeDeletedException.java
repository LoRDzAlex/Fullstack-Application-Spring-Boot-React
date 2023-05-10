package ch.wiss.unternehmensliste.exception.couldnotbedeleted;

public class JobCouldNotBeDeletedException extends RuntimeException{
    /**
     * Diese Exception wird geworfen, wenn ein Job nicht gelöscht werden kann
     *
     * @param id
     *
     * @return Exception Message
     */
    public JobCouldNotBeDeletedException(int id) {
        super("The Job with id '" + id + "' could not be deleted.");
    }
}
