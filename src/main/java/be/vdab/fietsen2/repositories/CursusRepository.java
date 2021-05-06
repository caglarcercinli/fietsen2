package be.vdab.fietsen2.repositories;

import be.vdab.fietsen2.domain.Cursus;

import java.util.Optional;
import java.util.UUID;

public interface CursusRepository {
    Optional<Cursus> findById(UUID id);
    void create(Cursus cursus);
}
