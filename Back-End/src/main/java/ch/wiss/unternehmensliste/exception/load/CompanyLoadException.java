package ch.wiss.unternehmensliste.exception.load;

public class CompanyLoadException extends RuntimeException {
    /**
     * Handles an Exception that accuses while loading an CompanyAccount
     *
     *
     * @return Exception Message
     */
    public CompanyLoadException() {
        super("Companys could not be loaded.");
    }
}
