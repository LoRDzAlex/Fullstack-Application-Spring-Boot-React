package ch.wiss.unternehmensliste.controller;

import ch.wiss.unternehmensliste.exception.couldnotbedeleted.ContactCouldNotBeDeletedException;
import ch.wiss.unternehmensliste.exception.couldnotbesaved.ContactCouldNotBeSavedException;
import ch.wiss.unternehmensliste.exception.couldnotbeupdated.ContactCouldNotBeUpdatedException;
import ch.wiss.unternehmensliste.exception.load.ContactLoadException;
import ch.wiss.unternehmensliste.exception.notfound.ContactNotFoundException;
import ch.wiss.unternehmensliste.model.Contact;
import ch.wiss.unternehmensliste.repository.ContactRepository;
import org.mariadb.jdbc.message.client.ResetPacket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
@CrossOrigin("*")
public class ContactController {

    /**
     * Wire ContactRepository
     * @param contactRepository
     * @return wired ContactRepository
     */
    private ContactRepository contactRepository;
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * List all existing Contact
     *
     * @return all existing Contact
     * @throws ContactLoadException if something went wrong
     */
    @GetMapping("")
    public ResponseEntity<Iterable<Contact>> getAllContacts(){
        Iterable<Contact> contacts = null;

        try{
            contacts = contactRepository.findAll();
        }catch (Exception ex){
            throw new ContactLoadException();
        }
        return ResponseEntity.ok(contacts);
    }

    /**
     * List specific Contact by ID
     *
     * @param id
     *
     * @return specific Contact
     * @throws ContactNotFoundException if something went wrong
     */
    @GetMapping(path = "/id")
    public ResponseEntity<Contact> getContactById(@RequestParam int id){
        Contact contact = null;

        try{
            contact = contactRepository.findById(id);
        }catch (Exception ex){
            throw new ContactNotFoundException(id);
        }
        return ResponseEntity.ok(contact);
    }

    /**
     * List specific Contact by Email
     *
     * @param email
     *
     * @return specific Contact
     * @throws ContactNotFoundException if something went wrong
     */
    @GetMapping(path = "/email")
    public ResponseEntity<Contact> getContactByEmail(@RequestParam String email) {
        Contact contact = null;

        try {
            contact = contactRepository.findByEmail(email);
        } catch (Exception ex) {
            throw new ContactNotFoundException(email);
        }

        return ResponseEntity.ok(contact);
    }

    /**
     * Creates new Contact
     *
     * @param contactName
     * @param gender
     * @param tel
     * @param email
     * @return new Contact
     * @throws ContactCouldNotBeSavedException if something went wrong
     */
    @PostMapping(path = "")
    public ResponseEntity<String> createContact(
            @RequestParam String contactName,
            @RequestParam String gender,
            @RequestParam String tel,
            @RequestParam String email
    ){
        try {
            Contact contact = new Contact(contactName, gender, tel, email);
            contactRepository.save(contact);
        }catch (Exception ex){
            throw new ContactCouldNotBeSavedException(contactName);
        }
        return ResponseEntity.ok("Saved " + contactName);
    }

    /**
     * Deletes Contact by ID
     *
     * @param id
     * @return deleted Contact
     * @throws ContactCouldNotBeDeletedException if something went wrong
     */
    @DeleteMapping(path = "")
    public ResponseEntity<String> deleteContact(@RequestParam int id){
        try{
            contactRepository.deleteById(id);
        }catch (Exception ex){
            throw new ContactCouldNotBeDeletedException(id);
        }
        return ResponseEntity.ok("Deleted");
    }

    /**
     * Updates Contact by ID
     *
     * @param id
     * @param contactName
     * @param gender
     * @param tel
     * @param email
     * @return updated Contact
     * @throws ContactCouldNotBeUpdatedException if something went wrong
     */
    @PutMapping(path = "")
    public ResponseEntity<String> updateContact(@RequestParam int id,
                                                @RequestParam String contactName,
                                                @RequestParam String gender,
                                                @RequestParam String tel,
                                                @RequestParam String email){
        Contact c = contactRepository.findById(id);
        try{
            c.setContactName(contactName);
            c.setGender(gender);
            c.setTel(tel);
            c.setEmail(email);
            contactRepository.save(c);
        }catch (Exception ex){
            throw new ContactCouldNotBeUpdatedException(id);
        }
        return ResponseEntity.ok("Updated "+ contactName);
    }
}
