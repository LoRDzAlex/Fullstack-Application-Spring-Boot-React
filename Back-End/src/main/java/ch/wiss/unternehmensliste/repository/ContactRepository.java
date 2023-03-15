package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Contact;
import org.springframework.data.repository.CrudRepository;


public interface ContactRepository extends CrudRepository<Contact, Integer> {
    Contact findById(int id);

    Contact findByEmail(String email);

    Contact findByGender(String gender);
}
