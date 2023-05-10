package ch.wiss.unternehmensliste.exception.notfound;

public class CompanyNotFoundException extends RuntimeException {
    /**
     * Diese Exception wird geworfen, wenn ein Unternehmen nicht gefunden werden kann
     *
     * @param companyId
     *
     * @return Exception Message
     */
    public CompanyNotFoundException(int companyId) {
        super("The company with id '" + companyId + "' could not be found.");
    }
}
