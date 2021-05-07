package be.vdab.fietsen2.repositories;

import be.vdab.fietsen2.domain.Campus;

import java.util.Optional;

public interface CampusRepository {
    void create(Campus campus);
    Optional<Campus> findById(long id);
}
