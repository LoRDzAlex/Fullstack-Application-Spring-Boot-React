package ch.wiss.unternehmensliste.model;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface JobApplicationRepository extends CrudRepository<JobApplication, Integer> {
    JobApplication findByAddress(String address);
    JobApplication findById(int id);
    Iterable<JobApplication> findAllByZip(int zip);
    Iterable<JobApplication> findAllByStatus(String status);
    Iterable<JobApplication> findAllByJobName(String jobName);
    Iterable<JobApplication> findAllByChanged(LocalDateTime changed);
}
