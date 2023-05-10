package ch.wiss.unternehmensliste.exception.couldnotbedeleted;

public class ContactCouldNotBeDeletedException extends RuntimeException{
    /**
     * Diese Exception wird geworfen, wenn ein Kontakt nicht gelöscht werden kann
     *
     * @param id
     *
     * @return Exception Message
     */
    public ContactCouldNotBeDeletedException(int id) {
        super("The Contact with id '" + id + "' could not be deleted.");
    }
}
