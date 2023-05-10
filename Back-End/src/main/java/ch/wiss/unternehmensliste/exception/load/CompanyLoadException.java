package ch.wiss.unternehmensliste.exception.load;

public class CompanyLoadException extends RuntimeException {
    /**
     * Diese Exception wird geworfen, wenn die Unternehmen nicht geladen werden können
     *
     *
     * @return Exception Message
     */
    public CompanyLoadException() {
        super("Companys could not be loaded.");
    }
}
