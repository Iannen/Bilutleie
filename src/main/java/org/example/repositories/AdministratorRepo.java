package org.example.repositories;

import org.example.entiteter.brukerEntiteter.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepo extends JpaRepository<Administrator,Long> {
    Administrator findByBrukernavn(String brukernavn);
}
