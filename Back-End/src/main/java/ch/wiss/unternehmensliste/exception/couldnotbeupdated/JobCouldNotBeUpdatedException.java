package ch.wiss.unternehmensliste.exception.couldnotbeupdated;

public class JobCouldNotBeUpdatedException extends RuntimeException{
    /**
     * Diese Exception wird geworfen, wenn ein Job nicht aktualisiert werden kann
     *
     * @param id
     *
     * @return Exception Message
     */
    public JobCouldNotBeUpdatedException(int id) {
        super("The Job with id '" + id + "' could not be updated.");
    }
}