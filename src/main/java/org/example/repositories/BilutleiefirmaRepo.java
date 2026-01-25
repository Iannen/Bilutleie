package org.example.repositories;

import org.example.entiteter.Bilutleiefirma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BilutleiefirmaRepo extends JpaRepository<Bilutleiefirma,Long> {}
