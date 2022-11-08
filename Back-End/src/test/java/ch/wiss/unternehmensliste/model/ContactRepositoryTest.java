package ch.wiss.unternehmensliste.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactRepositoryTest {

    @Autowired ContactRepository contactRepository;

    /**
     * JUnit test for saving a Contact
     */
    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveContactTest(){
        Contact contact = new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com");
        contactRepository.save(contact);

        Assertions.assertThat(contact.getId()).isGreaterThan(0);
    }

    /**
     * JUnit test for getting a Contact
     */
    @Test
    @Order(2)
    public void getContactTest(){

        Contact contact = contactRepository.findById(1);

        Assertions.assertThat(contact.getId()).isEqualTo(1);
    }

    /**
     * JUnit test for getting a List of the Contact
     */
    @Test
    @Order(3)
    public void getListOfContactsTest(){
        List<Contact> contacts = (List<Contact>) contactRepository.findAll();

        Assertions.assertThat(contacts.size()).isGreaterThan(0);
    }

    /**
     * JUnit test for updating a Contact
     */
    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateContactTest(){

        Contact contact = contactRepository.findById(1);

        contact.setEmail("maxmuster@gmail.com");

        Contact contactUpdated = contactRepository.save(contact);

        Assertions.assertThat(contactUpdated.getEmail()).isEqualTo("maxmuster@gmail.com");
    }

    /**
     * JUnit test for deleting a Contact
     */
    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteContactTest(){

        Contact contact = contactRepository.findById(1);

        contactRepository.delete(contact);

        Contact contact1 = null;

        Optional<Contact> optionalContact = Optional.ofNullable(contactRepository.findByEmail("maxmuster@gmail.com"));

        if(optionalContact.isPresent()){
            contact1 = optionalContact.get();
        }

        Assertions.assertThat(contact1).isNull();
    }
}