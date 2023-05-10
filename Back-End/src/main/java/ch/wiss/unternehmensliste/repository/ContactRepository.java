package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Contact;
import org.springframework.data.repository.CrudRepository;

/**
 * Das ContactRepository Interface ist eine Erweiterung des CrudRepository Interfaces.
 * Es wird verwendet, um die Datenbankabfragen für die Contact-Objekte zu definieren.
 */

public interface ContactRepository extends CrudRepository<Contact, Integer> {
    Contact findById(int id);

    Contact findByEmail(String email);

    Contact findByGender(String gender);
}
