package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Company findById(int id);

    Iterable<Company> findAllByCompanyName(String companyName);

    Iterable<Company> findAllByCanton(String canton);
}
