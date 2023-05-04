package ch.wiss.unternehmensliste.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "job")
public class JobApplication {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Integer id;

    @NotNull
    @Size(min = 3, max = 50, message = "job name should be between 3 and 50 characters")
    private String jobName;
    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 30, message = "address should be between 5 and 30 characters")
    private String address;

    @NotNull
    @Range(min = 100, max = 99999, message = "plz should be between 3 and 10 characters")
    private Integer zip;

    @NotNull
    @Size(min = 3, max = 20, message = "status should be between 3 and 50 characters")
    private String status;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "contact_id")
    private @ManyToOne Contact contact;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "company_id")
    private @OneToOne Company company;

    private LocalDate changed;

    @NotNull
    private LocalDateTime created;

    public JobApplication(String jobName, String address, Integer zip, String status, Contact contact, Company company, LocalDateTime created) {
        this.jobName = jobName;
        this.address = address;
        this.zip = zip;
        this.status = status;
        this.contact = contact;
        this.company = company;
        this.created = created;
    }

    public JobApplication(String jobName, String address, Integer zip, String status, LocalDateTime created) {
        this.jobName = jobName;
        this.address = address;
        this.zip = zip;
        this.status = status;
        this.created = created;
    }

    public JobApplication() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobApplication that = (JobApplication) o;
        return Objects.equals(id, that.id) && Objects.equals(address, that.address) && Objects.equals(zip, that.zip) && Objects.equals(contact, that.contact) && Objects.equals(company, that.company) && Objects.equals(status, that.status) && Objects.equals(jobName, that.jobName) && Objects.equals(changed, that.changed) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, zip, contact, company, status, jobName, changed, created);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public LocalDate getChanged() {
        return changed;
    }

    public void setChanged(LocalDate changed) {
        this.changed = changed;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
