package be.vdab.fietsen2.domain;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "campussen")
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    @Embedded
    private Adres adres;
    @ElementCollection
    @CollectionTable(name = "campussentelefoonnrs",
            joinColumns = @JoinColumn(name = "campusId"))
    @OrderBy("fax")
    private Set<TelefoonNr> telefoonNrs;
    @OneToMany
    @JoinColumn(name = "campusId")
    @OrderBy("voornaam, familienaam")
    private Set<Docent> docenten;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Campus)) return false;
        Campus campus = (Campus) o;
        return Objects.equals(naam.toUpperCase(), campus.naam.toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam.toUpperCase());
    }

    public Campus(String naam, Adres adres) {
        this.naam = naam;
        this.adres = adres;
        this.telefoonNrs = new LinkedHashSet<>();
        this.docenten = new LinkedHashSet<>();
    }

    protected Campus() {
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Adres getAdres() {
        return adres;
    }

    public Set<TelefoonNr> getTelefoonNrs() {
        return Collections.unmodifiableSet(telefoonNrs);
    }

    public Set<Docent> getDocenten() {
        return Collections.unmodifiableSet(docenten);
    }
    public boolean add(Docent docent) {
        if (docent == null) {
            throw new NullPointerException();
        }
        return docenten.add(docent);
    }
}
