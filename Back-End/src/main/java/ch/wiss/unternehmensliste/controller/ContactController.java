package ch.wiss.unternehmensliste.controller;

import ch.wiss.unternehmensliste.exception.couldnotbedeleted.ContactCouldNotBeDeletedException;
import ch.wiss.unternehmensliste.exception.couldnotbesaved.ContactCouldNotBeSavedException;
import ch.wiss.unternehmensliste.exception.couldnotbeupdated.ContactCouldNotBeUpdatedException;
import ch.wiss.unternehmensliste.exception.load.ContactLoadException;
import ch.wiss.unternehmensliste.exception.notfound.ContactNotFoundException;
import ch.wiss.unternehmensliste.model.Contact;
import ch.wiss.unternehmensliste.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/contact")
@CrossOrigin("localhost:3000")
public class ContactController {

    /**
     * Verbindet das ContactRepository
     * @param contactRepository
     * @return verbundenes ContactRepository
     */
    private ContactRepository contactRepository;
    @Autowired
    public void setContactRepository(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Listet alle vorhandenen Kontakte auf
     *
     * @return alle vorhandenen Kontakte
     * @throws ContactLoadException wenn etwas schiefläuft
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
     * Listet einen spezifischen Kontakt anhand seiner ID auf
     *
     * @param id
     *
     * @return spezifischer Kontakt
     * @throws ContactNotFoundException wenn etwas schiefläuft
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
     * Listet einen spezifischen Kontakt anhand seiner E-Mail-Adresse auf
     *
     * @param email
     *
     * @return spezifischer Kontakt
     * @throws ContactNotFoundException wenn etwas schiefläuft
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
     * Erstellt einen neuen Kontakt
     *
     * @param contactName
     * @param gender
     * @param tel
     * @param email
     * @return neuer Kontakt
     * @throws ContactCouldNotBeSavedException wenn etwas schiefläuft
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
     * Löscht einen Kontakt anhand seiner ID
     *
     * @param id
     * @return gelöschter Kontakt
     * @throws ContactCouldNotBeDeletedException wenn etwas schiefläuft
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
     * Updated einen Kontakt anhand der ID
     *
     * @param id
     * @param contactName
     * @param gender
     * @param tel
     * @param email
     * @return updated Kontakt
     * @throws ContactCouldNotBeUpdatedException wenn etwas schiefläuft
     */
    @PutMapping(path = "")
    public ResponseEntity<String> updateContactOld(@RequestParam int id,
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

    /**
     * Updated einen Kontakt anhand der ID
     * @param id
     * @param contactName
     * @param gender
     * @param tel
     * @param email
     * @return updated Kontakt
     * @throws ContactCouldNotBeUpdatedException wenn etwas schiefläuft
     */
    @PatchMapping(path = "")
    public ResponseEntity<String> updateContact(@RequestParam int id,
                                                @RequestParam(required = false) String contactName,
                                                @RequestParam(required = false) String gender,
                                                @RequestParam(required = false) String tel,
                                                @RequestParam(required = false) String email){
        Contact c = contactRepository.findById(id);
        if (c == null) {
            throw new ContactCouldNotBeUpdatedException(id);
        }
        if (contactName != null && !contactName.equals(c.getContactName())) {
            c.setContactName(contactName);
        }
        if (gender != null && !gender.equals(c.getGender())) {
            c.setGender(gender);
        }
        if (tel != null && !tel.equals(c.getTel())) {
            c.setTel(tel);
        }
        if (email != null && !email.equals(c.getEmail())) {
            c.setEmail(email);
        }
        contactRepository.save(c);
        return ResponseEntity.ok("Updated "+ c.getContactName());
    }
}
