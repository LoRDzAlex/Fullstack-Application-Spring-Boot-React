package ch.wiss.unternehmensliste.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/job")
@CrossOrigin("localhost:3000")
public class JobController {

    /**
     * Verbinde das ContactRepository
     * @param contactRepository
     * @return verbundenes ContactRepository
     */
    private ContactRepository contactRepository;
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    /**
     * Verbinde das CompanyRepository
     * @param companyRepository
     * @return verbundenes CompanyRepository
     */
    private CompanyRepository companyRepository;
    @Autowired
    public void setCompanyRepository(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }
    /**
     * Verbinde das JobApplicationRepository
     * @param jobApplicationRepository
     * @return verbundenes JobApplicationRepository
     */
    private JobApplicationRepository jobApplicationRepository;
    @Autowired
    public void setJobApplicationRepository(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    /**
     * Liste alle vorhandenen JobApplications auf
     *
     * @return alle vorhandenen JobApplications
     * @throws JobLoadException wenn etwas schiefläuft
     */
    @GetMapping("")
    public ResponseEntity<Iterable<JobApplication>> getAllJobApplications(){
        Iterable<JobApplication> jobApplications = null;

        try{
            jobApplications = jobApplicationRepository.findAll();
        }catch (Exception ex){
            throw new JobLoadException();
        }
        return ResponseEntity.ok(jobApplications);
    }

    /**
     * Liste eine bestimmte JobApplication nach ID auf
     *
     * @param id
     *
     * @return bestimmte JobApplication
     * @throws JobNotFoundException wenn etwas schiefläuft
     */
    @GetMapping(path = "/id")
    public ResponseEntity<JobApplication> getJobApplicationById(@RequestParam int id){
        JobApplication jobapplication = null;

        try{
            jobapplication = jobApplicationRepository.findById(id);
        }catch (Exception ex){
            throw new JobNotFoundException(id);
        }
        return ResponseEntity.ok(jobapplication);
    }

    /**
     * Füge neue JobApplication && neue Firma && neuen Kontakt hinzu
     *
     * @param jobName
     * @param address
     * @param zip
     * @param status
     * @param companyName
     * @param website
     * @param canton
     * @param gender
     * @param fullname
     * @param tel
     * @param email
     * @return neue JobApplication, neue Firma und neuer Kontakt
     * @throws JobCouldNotBeSavedException wenn etwas schiefläuft
     */
    @PostMapping(path = "")
    public ResponseEntity<String> addJobApplicationwithForeignData(@RequestParam String jobName,
                                                    @RequestParam String address,
                                                    @RequestParam int zip,
                                                    @RequestParam String status,
                                                    @RequestParam String companyName,
                                                    @RequestParam String website,
                                                    @RequestParam String canton,
                                                    @RequestParam String gender,
                                                    @RequestParam String fullname,
                                                    @RequestParam String tel,
                                                    @RequestParam String email){



        LocalDateTime created = LocalDateTime.now();

            Contact contact = new Contact(gender, fullname, tel, email);
            Company company = new Company(companyName, website, canton);
            companyRepository.save(company);
            contactRepository.save(contact);
            JobApplication jobApplication = new JobApplication(jobName, address, zip, status, contact, company, created);
            jobApplicationRepository.save(jobApplication);

        return ResponseEntity.ok("Saved: "+jobName);
    }

    /**
     * Fügt eine neue JobApplication hinzu.
     *
     * @param jobName
     * @param address
     * @param zip
     * @param status
     * @param contact_id
     * @param company_id
     * @return neue JobApplication
     * @throws JobCouldNotBeSavedException wenn etwas schiefläuft
     */
    @PostMapping(path = "/add")
    public ResponseEntity<String> addJobApplication(@RequestParam String jobName,
                                                    @RequestParam String address,
                                                    @RequestParam int zip,
                                                    @RequestParam String status,
                                                    @RequestParam int contact_id,
                                                    @RequestParam int company_id){
        LocalDateTime created = LocalDateTime.now();
        try{
            Contact contact = contactRepository.findById(contact_id);
            Company company = companyRepository.findById(company_id);
            JobApplication jobApplication = new JobApplication(jobName, address, zip, status, contact, company, created);
            jobApplicationRepository.save(jobApplication);
        }catch (Exception ex){
            throw new JobCouldNotBeSavedException();
        }
        return ResponseEntity.ok("Saved: "+jobName);
    }

    /**
     * Löscht eine JobApplication anhand ihrer ID.
     *
     * @param id
     * @return gelöschte JobApplication
     * @throws JobCouldNotBeDeletedException wenn etwas schiefläuft
     */
    @DeleteMapping(path = "")
    public ResponseEntity<String> deleteJobApplication(@RequestParam int id){
        try{
            jobApplicationRepository.deleteById(id);
        }catch (Exception ex){
            throw new JobCouldNotBeDeletedException(id);
        }
        return ResponseEntity.ok("Deleted");
    }

    /**
     * Aktualisiert eine JobApplication anhand ihrer ID.
     *
     * @param id
     * @param jobName
     * @param address
     * @param zip
     * @return aktualisierte JobApplication
     * @throws JobCouldNotBeUpdatedException wenn etwas schiefläuft
     */
    @PutMapping(path = "")
    public ResponseEntity<String> updateJobApplication(@RequestParam int id,
                                                       @RequestParam String jobName,
                                                       @RequestParam String address,
                                                       @RequestParam int zip){
        JobApplication j = jobApplicationRepository.findById(id);
        try{
            j.setJobName(jobName);
            j.setAddress(address);
            j.setZip(zip);
            j.setChanged(LocalDate.now());
            j.setCreated(j.getCreated());
            jobApplicationRepository.save(j);
        }catch (Exception ex){
            throw new JobCouldNotBeUpdatedException(id);
        }
        return ResponseEntity.ok("Updated id: "+ id);
    }
    /**
     * Aktualisiert eine JobApplication anhand ihrer ID und optionaler Parameter.
     *
     * @param id
     * @param jobName
     * @param address
     * @param zip
     * @return aktualisierte JobApplication
     * @throws JobCouldNotBeUpdatedException wenn etwas schiefläuft
     */
    @PatchMapping(path = "")
    public ResponseEntity<String> updateJobApplication(@RequestParam int id,
                                                       @RequestParam(required = false) String jobName,
                                                       @RequestParam(required = false) String address,
                                                       @RequestParam(required = false) Integer zip){
        JobApplication j = jobApplicationRepository.findById(id);
        if (j == null) {
            throw new JobCouldNotBeUpdatedException(id);
        }
        if (jobName != null && !jobName.equals(j.getJobName())) {
            j.setJobName(jobName);
        }
        if (address != null && !address.equals(j.getAddress())) {
            j.setAddress(address);
        }
        if (zip != null && !zip.equals(j.getZip())) {
            j.setZip(zip);
        }
        j.setChanged(LocalDate.now());
        jobApplicationRepository.save(j);
        return ResponseEntity.ok("Updated id: "+ j.getId());
    }

    /**
     * Löscht mehrere JobApplications anhand des Firmennamens.
     *
     * @param companyName
     * @return gelöschte JobApplications
     */
    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteMultipleJobsByCompanyName(@RequestParam String companyName){
        try{
            jobApplicationRepository.deleteAllByCompany_CompanyName(companyName);
        }catch (Exception ex){
            return ResponseEntity.badRequest().body("Could not delete");
        }
        return ResponseEntity.ok("Deleted");
    }
}
