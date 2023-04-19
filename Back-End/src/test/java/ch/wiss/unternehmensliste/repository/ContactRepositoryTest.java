package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/*
    TODO; kommentieren
 */
@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    /**
     * JUnit test for saving a Contact
     */
    @Test
    public void saveContactTest(){
        Contact contact = new Contact("male", "Microsoft Contact", "0715398421", "microsoft@microsoft.com");
        contactRepository.save(contact);
        assertTrue(contact.getId() > 0);
        int contactId = contact.getId();
        Contact result = contactRepository.findById(contactId);

        assertEquals(contactId, result.getId());
    }

    /**
     * JUnit test for getting a Contact
     */
    @Test
    public void getContactTest(){


        Contact contact = new Contact("female", "BMT Contact", "0715394796", "bmt@bmt.com");
        contactRepository.save(contact);
        assertTrue(contact.getId() > 0);
        int contactId = contact.getId();

        contact = contactRepository.findById(contactId);

        assertEquals(contactId, contact.getId());
    }

    /**
     * JUnit test for getting a List of the Contact
     */
    @Test
    public void getListOfContactsTest(){

        Contact contact = new Contact("female", "google", "0715391283", "google@google.com");
        contactRepository.save(contact);
        assertTrue(contact.getId() > 0);
        List<Contact> contacts = (List<Contact>) contactRepository.findAll();
        assertTrue(contacts.size() > 0);
    }

    /**
     * JUnit test for updating a Contact
     */
    @Test
    public void updateContactTest(){

        Contact contact = new Contact("female", "google", "0715391283", "google@google.com");
        contactRepository.save(contact);
        assertTrue(contact.getId() > 0);

        contact.setEmail("maxmuster@gmail.com");

        Contact contactUpdated = contactRepository.save(contact);

        assertTrue(contactUpdated.getEmail().equals("maxmuster@gmail.com"));
    }

    /**
     * JUnit test for deleting a Contact
     */
    @Test
    public void deleteContactTest(){

        Contact contact = new Contact("female", "google", "0715391283", "google@google.com");
        contactRepository.save(contact);
        assertTrue(contact.getId() > 0);
        int contactId = contact.getId();

        contactRepository.delete(contact);

        assertFalse(contactRepository.existsById(contactId));

        }
}