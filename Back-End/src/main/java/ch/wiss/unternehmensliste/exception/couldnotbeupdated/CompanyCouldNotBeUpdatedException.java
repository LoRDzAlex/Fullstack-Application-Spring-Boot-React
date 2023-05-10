package ch.wiss.unternehmensliste.exception.couldnotbeupdated;

public class CompanyCouldNotBeUpdatedException extends RuntimeException{
    /**
     * Diese Exception wird geworfen, wenn ein Unternehmen nicht aktualisiert werden kann
     *
     * @param id
     *
     * @return Exception Message
     */
    public CompanyCouldNotBeUpdatedException(int id) {
        super("The Company with id '" + id + "' could not be updated.");
    }
}
