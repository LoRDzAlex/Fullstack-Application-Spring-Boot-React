package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Das UserRepository Interface ist eine Erweiterung des JpaRepository Interfaces.
 * Es wird verwendet, um die Datenbankabfragen für die User-Objekte zu definieren.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Override
    Optional<User> findById(Long aLong);

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
