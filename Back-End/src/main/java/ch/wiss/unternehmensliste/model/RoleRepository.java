package ch.wiss.unternehmensliste.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Override
    Optional<Role> findById(Long aLong);
    Optional<Role> findByName(Role.ERole name);
}
