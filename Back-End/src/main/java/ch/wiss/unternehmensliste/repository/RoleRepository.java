package ch.wiss.unternehmensliste.repository;

import ch.wiss.unternehmensliste.model.Role;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Override
    Optional<Role> findById(Long aLong);

    Optional<Role> findByName(Role.ERole name);

    @Override
    <S extends Role> Optional<S> findOne(Example<S> example);
}
