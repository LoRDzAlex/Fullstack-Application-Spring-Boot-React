package ch.wiss.unternehmensliste.exception.couldnotbesaved;

public class CompanyCouldNotBeSavedException extends RuntimeException{
    /**
     * Handles an Exception that accuses while saving a Company
     *
     * @param companyName
     *
     * @return Exception Message
     */
    public CompanyCouldNotBeSavedException(String companyName) {
        super("The Company with name '" + companyName + "' could not be saved.");
    }

    /**
     * Handles an Exception that accuses while saving a Company
     *
     *
     * @return Exception Message
     */
    public CompanyCouldNotBeSavedException() {
        super("The CompanyAccount could not be saved.");
    }
}
