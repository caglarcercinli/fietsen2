package be.vdab.fietsen2.repositories;

import be.vdab.fietsen2.domain.Docent;

import java.util.Optional;

public interface DocentRepository {
    Optional<Docent> findById(long id);
    void create(Docent docent);
    void delete(long id);
}
