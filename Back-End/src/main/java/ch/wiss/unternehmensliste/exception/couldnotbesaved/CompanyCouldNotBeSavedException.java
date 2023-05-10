package ch.wiss.unternehmensliste.exception.couldnotbesaved;

public class CompanyCouldNotBeSavedException extends RuntimeException{
    /**
     * Diese Exception wird geworfen, wenn ein Unternehmen nicht gespeichert werden kann
     *
     * @param companyName
     *
     * @return Exception Message
     */
    public CompanyCouldNotBeSavedException(String companyName) {
        super("The Company with name '" + companyName + "' could not be saved.");
    }

    /**
     * Diese Exception wird geworfen, wenn ein Unternehmen nicht gespeichert werden kann
     *
     *
     * @return Exception Message
     */
    public CompanyCouldNotBeSavedException() {
        super("The CompanyAccount could not be saved.");
    }
}
