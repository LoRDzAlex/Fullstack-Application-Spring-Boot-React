package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Company;
import ch.wiss.unternehmensliste.model.Contact;
import ch.wiss.unternehmensliste.model.JobApplication;
import ch.wiss.unternehmensliste.repository.CompanyRepository;
import ch.wiss.unternehmensliste.repository.ContactRepository;
import ch.wiss.unternehmensliste.repository.JobApplicationRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.assertj.core.api.Assertions;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


/*
    TODO; kommentieren
 */
@DataJpaTest
class JobApplicationRepositoryTest {


    @Autowired
    JobApplicationRepository jobApplicationRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    ContactRepository contactRepository;

    /**
     * JUnit test for saving a JobApplication
     */
    @Test
    public void saveJobApplicationTest(){
        Company company = new Company( "Microsoft", "www.microsoft.com", "Zürich");
        companyRepository.save(company);
        assertTrue(company.getId() > 0);
        int companyId = company.getId();
        Contact contact = new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com");
        contactRepository.save(contact);
        assertTrue(contact.getId() > 0);
        int contactId = contact.getId();
        JobApplication jobApplication = new JobApplication("Applikationsentwickler", "microsoftstrasse", 8000, "nicht Kontaktiert", contact, company, LocalDateTime.now());
        jobApplicationRepository.save(jobApplication);

        assertTrue(jobApplication.getId() > 0);
    }

    /**
     * JUnit test for getting a JobApplication
     */
    @Test
    public void getJobApplicationTest(){
        Company company = new Company( "Microsoft", "www.microsoft.com", "Zürich");
        companyRepository.save(company);
        assertTrue(company.getId() > 0);
        Contact contact = new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com");
        contactRepository.save(contact);
        assertTrue(contact.getId() > 0);
        JobApplication jobApplication = new JobApplication("Applikationsentwickler", "microsoftstrasse", 8000, "nicht Kontaktiert", contact, company, LocalDateTime.now());
        jobApplicationRepository.save(jobApplication);
        assertTrue(jobApplication.getId() > 0);
        int jobApplicationId = jobApplication.getId();
        JobApplication result = jobApplicationRepository.findById(jobApplicationId);

        assertEquals(jobApplicationId, result.getId());
    }

    /**
     * JUnit test for getting a List of the JobApplication
     */
    @Test
    public void getListOfJobApplicationsTest(){
            Company company = new Company( "Microsoft", "www.microsoft.com", "Zürich");
            companyRepository.save(company);
            assertTrue(company.getId() > 0);
            Contact contact = new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com");
            contactRepository.save(contact);
            assertTrue(contact.getId() > 0);
            JobApplication jobApplication = new JobApplication("Applikationsentwickler", "microsoftstrasse", 8000, "nicht Kontaktiert", contact, company, LocalDateTime.now());
            jobApplicationRepository.save(jobApplication);
            assertTrue(jobApplication.getId() > 0);
        List<JobApplication> jobApplications = (List<JobApplication>) jobApplicationRepository.findAll();

        assertTrue(jobApplications.size() > 0);
    }

    /**
     * JUnit test for updating a JobApplication
     */
    @Test
    public void updateJobApplicationTest(){

            Company company = new Company( "Microsoft", "www.microsoft.com", "Zürich");
            companyRepository.save(company);
            assertTrue(company.getId() > 0);
            Contact contact = new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com");
            contactRepository.save(contact);
            assertTrue(contact.getId() > 0);
            JobApplication jobApplication = new JobApplication("Applikationsentwickler", "microsoftstrasse", 8000, "nicht Kontaktiert", contact, company, LocalDateTime.now());
            jobApplicationRepository.save(jobApplication);
            assertTrue(jobApplication.getId() > 0);

        jobApplication.setAddress("Musterstrasse 23");

        JobApplication jobApplicationUpdated = jobApplicationRepository.save(jobApplication);

        assertTrue(jobApplication.getAddress().equals("Musterstrasse 23"));
    }

    /**
     * JUnit test for deleting a JobApplication
     */
    @Test
    public void deleteJobApplicationTest(){

        Company company = new Company( "Microsoft", "www.microsoft.com", "Zürich");
        companyRepository.save(company);
        assertTrue(company.getId() > 0);
        Contact contact = new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com");
        contactRepository.save(contact);
        assertTrue(contact.getId() > 0);
        JobApplication jobApplication = new JobApplication("Applikationsentwickler", "microsoftstrasse", 8000, "nicht Kontaktiert", contact, company, LocalDateTime.now());
        jobApplicationRepository.save(jobApplication);
        assertTrue(jobApplication.getId() > 0);
        int jobApplicationId = jobApplication.getId();

        jobApplicationRepository.delete(jobApplication);

        assertFalse(jobApplicationRepository.existsById(jobApplicationId));
    }
}