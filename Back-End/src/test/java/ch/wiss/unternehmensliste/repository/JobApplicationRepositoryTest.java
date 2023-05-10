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
    Hier sind die JUnit Tests für die JobApplicationRepository, die testen die Grundfunktionen der CRUDRepository
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
     * um eine JobApplication zu speichern
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
     * um eine JobApplication zu finden
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
     * um eine Liste von JobApplications zu finden
     * */
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
     * um eine JobApplication zu aktualisieren
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
     * um eine JobApplication zu löschen
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