package ch.wiss.unternehmensliste.model;

import jakarta.persistence.*;

/**
 * Die Role Klasse ist eine Entität und wird verwendet um, die
 * Daten der Rollen in der Datenbank zu speichern.
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {
    }
    public Role(Integer id, ERole name) {
        Id = id;
        this.name = name;
    }
    public Role(ERole name) {
        this.name = name;
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public enum ERole {
        ROLE_USER,
        ROLE_ADMIN,
        ROLE_COMPANY
    }
}
