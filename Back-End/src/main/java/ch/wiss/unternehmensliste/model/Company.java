package ch.wiss.unternehmensliste.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
@Table(name = "company")
public class Company {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Integer id;

    @NotNull
    @Size(min = 5, max = 30, message = "companyName should be between 5 and 30 characters")
    private String companyName;

    @NotNull
    @Size(min = 3, max = 100, message = "website should have at least 8 characters")
    private String website;

    @NotNull
    @Size(min = 5, max = 50, message = "enter valid canton")
    private String canton;

    public Company(String companyName, String website, String canton) {
        this.companyName = companyName;
        this.website = website;
        this.canton = canton;
    }

    public Company() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(companyName, company.companyName) && Objects.equals(website, company.website) && Objects.equals(canton, company.canton);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, website, canton);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }
}
