package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.JobApplication;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

/**
 * Das JobApplicationRepository Interface ist eine Erweiterung des CrudRepository Interfaces.
 * Es wird verwendet, um die Datenbankabfragen für die JobApplication-Objekte zu definieren.
 */
public interface JobApplicationRepository extends CrudRepository<JobApplication, Integer> {
    JobApplication findByAddress(String address);

    JobApplication findById(int id);

    Iterable<JobApplication> findAllByZip(int zip);

    Iterable<JobApplication> findAllByStatus(String status);

    Iterable<JobApplication> findAllByJobName(String jobName);

    Iterable<JobApplication> deleteAllByCompany_CompanyName(String companyName);
    Iterable<JobApplication> findAllByChanged(LocalDateTime changed);
}
