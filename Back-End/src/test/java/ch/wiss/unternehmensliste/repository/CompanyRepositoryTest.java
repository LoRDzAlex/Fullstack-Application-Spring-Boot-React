package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    Hier sind die JUnit Tests für die CompanyRepository, die testen die Grundfunktionen der CRUDRepository
 */

@DataJpaTest
class CompanyRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    CompanyRepository companyRepository;



    /**
     * um eine Company zu speichern
     */

    @Test
    public void saveCompanyTest(){
        Company company = new Company( "Microsoft", "www.microsoft.com", "Zürich");
        companyRepository.save(company);
        assertTrue(company.getId() > 0);
        int companyId = company.getId();
        Company result = companyRepository.findById(companyId);

        assertEquals(companyId, result.getId());
    }

    /**
     * Um eine Company zu finden
     */
    @Test
    public void getCompanyTest(){

        Company company = new Company("Amazon", "www.amazon.com", "Aarau");
        companyRepository.save(company);
        assertTrue(company.getId() > 0);
        int companyId = company.getId();
        Company result = companyRepository.findById(companyId);

        assertEquals(companyId, result.getId());
    }

    /**
     * Um eine Liste von Companies zu finden
     */
    @Test
    public void getListOfCompanysTest(){
        Company company = new Company("Github", "www.github.com", "St.Gallen");
        companyRepository.save(company);
        assertTrue(company.getId() > 0);
        List<Company> companies = (List<Company>) companyRepository.findAll();

        assertTrue(companies.size() > 0);
    }

    /**
     * Um eine Company zu aktualisieren
     */
    @Test
    public void updateCompanyTest(){
        Company company = new Company("Kantonsspital", "www.kssg.com", "St.Gallen");
        companyRepository.save(company);
        assertTrue(company.getId() > 0);
        company.setCompanyName("MaxMusterCompany");

        Company companyUpdated = companyRepository.save(company);

        assertTrue(companyUpdated.getCompanyName().equals("MaxMusterCompany"));
    }

    /**
     * Um eine Company zu löschen
     */
    @Test
    public void deleteCompanyTest(){

        Company company = new Company("BMT AG", "www.bmt.com", "Zürich");
        companyRepository.save(company);
        assertTrue(company.getId() > 0);
        int companyId = company.getId();
        companyRepository.delete(company);

        assertFalse(companyRepository.existsById(company.getId()));
    }


}