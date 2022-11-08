
package ch.wiss.unternehmensliste.service;

import ch.wiss.unternehmensliste.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Loading TestData into Database
 */
@Component
public class DataBaseLoader implements CommandLineRunner {

    private final JobApplicationRepository jobApplicationRepository;
    private final CompanyRepository companyRepository;
    private final ContactRepository contactRepository;

    @Autowired
    public DataBaseLoader(JobApplicationRepository jobApplicationRepository, CompanyRepository companyRepository, ContactRepository contactRepository){
        this.companyRepository = companyRepository;
        this.contactRepository = contactRepository;
        this.jobApplicationRepository = jobApplicationRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
/*
        this.companyRepository.save(new Company("Microsoft", "www.microsoft.com", "Zürich"));
        this.contactRepository.save(new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com"));
        this.jobApplicationRepository.save(new JobApplication("Applikationsentwickler", "microsoftstrasse", 8000, "nicht Kontaktiert", contactRepository.findById(1), companyRepository.findById(1), LocalDateTime.now()));
        this.companyRepository.save(new Company("Google", "www.google.com", "Zürich"));
        this.contactRepository.save(new Contact("male", "Google Contact", "0715398321", "google@gmail.com"));
        this.jobApplicationRepository.save(new JobApplication("Applikationsentwickler", "googlestrasse", 8000, "nicht Kontaktiert", contactRepository.findById(2), companyRepository.findById(2), LocalDateTime.now()));
        this.companyRepository.save(new Company("Ergon Informatik", "www.ergoninformatik.com", "Zürich"));
        this.contactRepository.save(new Contact("male", "Ergon Informatik Contact", "0715393321", "ergon@ergon.com"));
        this.jobApplicationRepository.save(new JobApplication("Applikationsentwickler", "ergonstrasse", 8000, "nicht Kontaktiert", contactRepository.findById(3), companyRepository.findById(3), LocalDateTime.now()));
*/
    }
}
