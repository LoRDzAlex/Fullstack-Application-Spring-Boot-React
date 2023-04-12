package ch.wiss.unternehmensliste.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "contact")
public class Contact {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @Id Integer id;

    @NotNull
    @Size(min = 3, max = 6, message = "gender should be between 3 and 6 characters")
    private String gender;

    @NotNull
    @Size(min = 3, max = 50, message = "name should be between 3 and 50 characters")
    private String contactName;

    @NotNull
    @Column(unique = true)
    @Size(min = 10, max = 15, message = "telephone number should be between 5 and 30 characters")
    private String tel;
    //nicht vorhanden standard value

    @NotNull
    @Column(unique = true)
    @Size(min = 5, max = 50, message = "enter valid email address")
    private String email;

    public Contact(String gender, String contactName, String tel, String email) {
        this.gender = gender;
        this.contactName = contactName;
        this.tel = tel;
        this.email = email;
    }

    public Contact() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(gender, contact.gender) && Objects.equals(contactName, contact.contactName) && Objects.equals(tel, contact.tel) && Objects.equals(email, contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, contactName, tel, email);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
