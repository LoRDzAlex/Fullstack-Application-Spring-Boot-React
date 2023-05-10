package ch.wiss.unternehmensliste.exception.couldnotbeupdated;

public class ContactCouldNotBeUpdatedException extends RuntimeException{
    /**
     * Diese Exception wird geworfen, wenn ein Kontakt nicht aktualisiert werden kann
     *
     * @param id
     *
     * @return Exception Message
     */
    public ContactCouldNotBeUpdatedException(int id) {
        super("The Contact with id '" + id + "' could not be updated.");
    }
}
