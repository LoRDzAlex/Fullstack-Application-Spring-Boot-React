package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
    TODO; kommentieren
 */

@DataJpaTest
class CompanyRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    CompanyRepository companyRepository;



    /**
     * JUnit test for saving a Company
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
     * JUnit test for getting a Company
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
     * JUnit test for getting a List of the Company
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
     * JUnit test for updating a Company
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
     * JUnit test for deleting a Company
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