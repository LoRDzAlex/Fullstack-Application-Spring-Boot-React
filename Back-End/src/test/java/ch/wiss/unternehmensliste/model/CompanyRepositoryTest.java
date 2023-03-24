package ch.wiss.unternehmensliste.model;

import ch.wiss.unternehmensliste.repository.CompanyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;

    /**
     * JUnit test for saving a Company
     */
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCompanyTest(){
        Company company = new Company("Microsoft", "www.microsoft.com", "Zürich");
        companyRepository.save(company);

        Assertions.assertThat(company.getId()).isGreaterThan(0);
    }

    /**
     * JUnit test for getting a Company
     */
    @Test
    @Order(2)
    public void getCompanyTest(){

        Company company = companyRepository.findById(1);

        Assertions.assertThat(company.getId()).isEqualTo(1);
    }

    /**
     * JUnit test for getting a List of the Company
     */
    @Test
    @Order(3)
    public void getListOfCompanysTest(){
        List<Company> companies = (List<Company>) companyRepository.findAll();

        Assertions.assertThat(companies.size()).isGreaterThan(0);
    }

    /**
     * JUnit test for updating a Company
     */
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCompanyTest(){

        Company company = companyRepository.findById(1);

        company.setCompanyName("MaxMusterCompany");

        Company companyUpdated = companyRepository.save(company);

        Assertions.assertThat(companyUpdated.getCompanyName()).isEqualTo("MaxMusterCompany");
    }

    /**
     * JUnit test for deleting a Company
     */
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteCompanyTest(){

        Company company = companyRepository.findById(1);

        companyRepository.delete(company);

        Company company1 = null;

        Optional<Company> optionalCompany = Optional.ofNullable(companyRepository.findById(1));

        if(optionalCompany.isPresent()){
            company1 = optionalCompany.get();
        }

        Assertions.assertThat(company1).isNull();
    }
}