package be.vdab.fietsen2.repositories;

import be.vdab.fietsen2.domain.Docent;
import be.vdab.fietsen2.projections.AantalDocentenPerWedde;
import be.vdab.fietsen2.projections.IdEnEmailAdres;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DocentRepository {
    Optional<Docent> findById(long id);
    void create(Docent docent);
    void delete(long id);
    List<Docent> findAll();
    List<Docent> findByWeddeBetween(BigDecimal van,BigDecimal tot);
    List<String> findEmailAdressen();
    List<IdEnEmailAdres> findIdsEnEmailAdressen();
    BigDecimal findGrootsteWedde();
    List<AantalDocentenPerWedde> findAantalDocentenPerWedde();

}
