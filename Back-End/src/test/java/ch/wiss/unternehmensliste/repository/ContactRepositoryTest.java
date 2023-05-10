package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/*
    Hier sind die JUnit Tests für die ContactRepository, die testen die Grundfunktionen der CRUDRepository
 */
@DataJpaTest
class ContactRepositoryTest {

    @Autowired
    ContactRepository contactRepository;

    /**
     * Um eine Contact zu speichern
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
     * um eine Contact zu finden
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
     * um eine Liste von Contacts zu finden
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
     * Um eine Contact zu speichern
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
     * Um eine Contact zu löschen
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