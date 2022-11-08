package ch.wiss.unternehmensliste.exception.notfound;

public class CompanyNotFoundException extends RuntimeException {
    /**
     * Handles an Exception that accuses while searching an CompanyAccount with the ID
     *
     * @param companyId
     *
     * @return Exception Message
     */
    public CompanyNotFoundException(int companyId) {
        super("The company with id '" + companyId + "' could not be found.");
    }
}
