package ch.wiss.unternehmensliste.model;

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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JobApplicationRepositoryTest {


    @Autowired JobApplicationRepository jobApplicationRepository;

    @Autowired CompanyRepository companyRepository;
    @Autowired ContactRepository contactRepository;

    /**
     * JUnit test for saving a JobApplication
     */
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveJobApplicationTest(){
        this.companyRepository.save(new Company("Microsoft", "www.microsoft.com", "Zürich"));
        this.contactRepository.save(new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com"));
        JobApplication jobApplication = new JobApplication("Applikationsentwickler", "microsoftstrasse", 8000, "nicht Kontaktiert", contactRepository.findById(1), companyRepository.findById(1), LocalDateTime.now());
        jobApplicationRepository.save(jobApplication);

        Assertions.assertThat(jobApplication.getId()).isGreaterThan(0);
    }

    /**
     * JUnit test for getting a JobApplication
     */
    @Test
    @Order(2)
    public void getJobApplicationTest(){

        JobApplication jobApplication = jobApplicationRepository.findById(1);

        Assertions.assertThat(jobApplication.getId()).isEqualTo(1);
    }

    /**
     * JUnit test for getting a List of the JobApplication
     */
    @Test
    @Order(3)
    public void getListOfJobApplicationsTest(){
        List<JobApplication> jobApplications = (List<JobApplication>) jobApplicationRepository.findAll();

        Assertions.assertThat(jobApplications.size()).isGreaterThan(0);
    }

    /**
     * JUnit test for updating a JobApplication
     */
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateJobApplicationTest(){

        JobApplication jobApplication = jobApplicationRepository.findById(1);

        jobApplication.setAddress("Musterstrasse 23");

        JobApplication jobApplicationUpdated = jobApplicationRepository.save(jobApplication);

        Assertions.assertThat(jobApplicationUpdated.getAddress()).isEqualTo("Musterstrasse 23");
    }

    /**
     * JUnit test for deleting a JobApplication
     */
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteJobApplicationTest(){

        JobApplication jobApplication = jobApplicationRepository.findById(1);

        jobApplicationRepository.delete(jobApplication);

        JobApplication jobApplication1 = null;

        Optional<JobApplication> optionalJobApplication = Optional.ofNullable(jobApplicationRepository.findByAddress("Musterstrasse 23"));

        if(optionalJobApplication.isPresent()){
            jobApplication1 = optionalJobApplication.get();
        }

        Assertions.assertThat(jobApplication1).isNull();
    }
}