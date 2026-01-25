package org.example.repositories;

import org.example.entiteter.brukerEntiteter.Medarbeider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedarbeiderRepo extends JpaRepository<Medarbeider,Long> {
    Medarbeider findByBrukernavn(String brukernavn);
}
