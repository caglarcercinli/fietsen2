package be.vdab.fietsen2.repositories;

import be.vdab.fietsen2.domain.Docent;
import be.vdab.fietsen2.projections.AantalDocentenPerWedde;
import be.vdab.fietsen2.projections.IdEnEmailAdres;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaDocentRepository implements DocentRepository {
    private final EntityManager manager;

    JpaDocentRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public Optional<Docent> findById(long id) {
        return Optional.ofNullable(manager.find(Docent.class, id));
    }

    @Override
    public void create(Docent docent) {
        manager.persist(docent);
    }

    @Override
    public void delete(long id) {
        findById(id).ifPresent(
                docent -> manager.remove(docent)
        );
    }

    @Override
    public List<Docent> findAll() {
        return manager.createQuery("select d from Docent d order by d.wedde",
                Docent.class).getResultList();
    }

    @Override
    public List<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot) {
        return manager.createNamedQuery("Docent.findByWeddeBetween", Docent.class)
                .setParameter("van", van)
                .setParameter("tot", tot)
                .setHint("javax.persistence.loadgraph",
                        manager.createEntityGraph("Docent.metCampus"))
                .getResultList();
    }

    @Override
    public List<String> findEmailAdressen() {
        return manager.createQuery("select d.emailAdres from Docent d", String.class)
                .getResultList();
    }

    @Override
    public List<IdEnEmailAdres> findIdsEnEmailAdressen() {
        return manager.createQuery("select new be.vdab.fietsen2.projections.IdEnEmailAdres(d.id, d.emailAdres)" +
                "from Docent d", IdEnEmailAdres.class).getResultList();
    }

    @Override
    public BigDecimal findGrootsteWedde() {
        return manager.createQuery("select max(d.wedde) from Docent d", BigDecimal.class)
                .getSingleResult();
    }

    @Override
    public List<AantalDocentenPerWedde> findAantalDocentenPerWedde() {
        return manager.createQuery(
                "select new be.vdab.fietsen2.projections.AantalDocentenPerWedde(" +
                        "d.wedde,count(d)) from Docent d group by d.wedde",
                AantalDocentenPerWedde.class)
                .getResultList();
    }

    @Override
    public int algemeneOpslag(BigDecimal percentage) {
        return manager.createNamedQuery("Docent.algemeneOpslag")
                .setParameter("percentage", percentage)
                .executeUpdate();
    }

    @Override
    public Optional<Docent> findByIdWithLock(long id) {
        return Optional.ofNullable(
                manager.find(Docent.class,id, LockModeType.PESSIMISTIC_WRITE)
        );
    }
}
