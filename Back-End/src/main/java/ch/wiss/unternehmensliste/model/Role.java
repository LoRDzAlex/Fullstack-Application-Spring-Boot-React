package ch.wiss.unternehmensliste.model;

import javax.persistence.*;

@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    public Role() {
    }

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public enum ERole {ROLE_USER, ROLE_ADMINISTRATOR, ROLE_FIRMA}

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
}