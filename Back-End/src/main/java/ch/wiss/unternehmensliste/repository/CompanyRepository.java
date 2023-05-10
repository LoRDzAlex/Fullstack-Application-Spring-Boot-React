package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Company;
import org.springframework.data.repository.CrudRepository;

/**
 * Das CompanyRepository Interface ist eine Erweiterung des CrudRepository Interfaces.
 * Es wird verwendet, um die Datenbankabfragen für die Company-Objekte zu definieren.
 */
public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Company findById(int id);

    Iterable<Company> findAllByCompanyName(String companyName);

    Iterable<Company> findAllByCanton(String canton);
}
