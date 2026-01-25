package org.example.repositories;

import org.example.entiteter.brukerEntiteter.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KundeRepo extends JpaRepository<Kunde,Long> {
    Kunde findByBrukernavn(String brukernavn);
}
