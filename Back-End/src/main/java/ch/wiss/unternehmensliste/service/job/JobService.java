/*
package ch.wiss.unternehmensliste.service.job;

import ch.wiss.unternehmensliste.exception.couldnotbedeleted.JobCouldNotBeDeletedException;
import ch.wiss.unternehmensliste.exception.couldnotbesaved.JobCouldNotBeSavedException;
import ch.wiss.unternehmensliste.exception.couldnotbeupdated.JobCouldNotBeUpdatedException;
import ch.wiss.unternehmensliste.exception.load.JobLoadException;
import ch.wiss.unternehmensliste.exception.notfound.JobNotFoundException;
import ch.wiss.unternehmensliste.model.Company;
import ch.wiss.unternehmensliste.model.Contact;
import ch.wiss.unternehmensliste.model.JobApplication;
import ch.wiss.unternehmensliste.repository.CompanyRepository;
import ch.wiss.unternehmensliste.repository.ContactRepository;
import ch.wiss.unternehmensliste.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class JobService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    public Iterable<JobApplication> getAllJobApplications() {
        try {
            return jobApplicationRepository.findAll();
        } catch (Exception ex) {
            throw new JobLoadException();
        }
    }

    public JobApplication getJobApplicationById(int id) {
        try {
            return jobApplicationRepository.findById(id);
        } catch (Exception ex) {
            throw new JobNotFoundException(id);
        }
    }

    public JobApplication addJobApplicationWithForeignData(String jobName, String address, int zip, String status,
                                                           String companyName, String website, String canton, String gender, String fullname, String tel, String email) {
        LocalDateTime created = LocalDateTime.now();
        try {
            Contact contact = new Contact(gender, fullname, tel, email);
            Company company = new Company(companyName, website, canton);
            companyRepository.save(company);
            contactRepository.save(contact);
            JobApplication jobApplication = new JobApplication(jobName, address, zip, status, contact, company, created);
            return jobApplicationRepository.save(jobApplication);
        } catch (Exception ex) {
            throw new JobCouldNotBeSavedException();
        }
    }

    public JobApplication addJobApplication(String jobName, String address, int zip, String status,
                                            int contact_id, int company_id) {
        LocalDateTime created = LocalDateTime.now();
        try {
            Contact contact = contactRepository.findById(contact_id);
            Company company = companyRepository.findById(company_id);
            JobApplication jobApplication = new JobApplication(jobName, address, zip, status, contact, company, created);
            return jobApplicationRepository.save(jobApplication);
        } catch (Exception ex) {
            throw new JobCouldNotBeSavedException();
        }
    }

    public void deleteJobApplication(int id) {
        try {
            jobApplicationRepository.deleteById(id);
        } catch (Exception ex) {
            throw new JobCouldNotBeDeletedException(id);
        }
    }

    public JobApplication updateJobApplication(int id, String jobName, String address, int zip, String status,
                                               int contact_id, int company_id) {
        try {
            JobApplication jobApplication = jobApplicationRepository.findById(id);
            if (jobApplication == null) {
                throw new JobNotFoundException(id);
            }
            Contact contact = contactRepository.findById(contact_id);
            Company company = companyRepository.findById(company_id);
            jobApplication.setJobName(jobName);
            jobApplication.setAddress(address);
            jobApplication.setZip(zip);
            jobApplication.setStatus(status);
            jobApplication.setContact(contact);
            jobApplication.setCompany(company);
            jobApplication.setChanged(LocalDate.now());
            return jobApplicationRepository.save(jobApplication);
        } catch (Exception ex) {
            throw new JobCouldNotBeUpdatedException(id);
        }
    }

}
*/