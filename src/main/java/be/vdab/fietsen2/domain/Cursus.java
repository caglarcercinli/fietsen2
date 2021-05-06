package be.vdab.fietsen2.domain;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Cursus {
    @Id
    @Column(columnDefinition = "binary(16)")
    private UUID id;
    private String naam;

    public Cursus(String naam) {
        this.naam = naam;
        id=UUID.randomUUID();
    }

    protected Cursus() {
    }

    public UUID getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }
}
