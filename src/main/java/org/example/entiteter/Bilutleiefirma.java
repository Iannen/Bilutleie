package org.example.entiteter;

import jakarta.persistence.*;
import org.example.entiteter.brukerEntiteter.Administrator;
import org.example.entiteter.brukerEntiteter.Kunde;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "dat109oblig2iterativt",name = "bilutleiefirma")
public class Bilutleiefirma extends Entitet {
    @OneToMany(mappedBy = "bilutleiefirma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Kunde> kunder = new ArrayList<>();
    @OneToMany(mappedBy = "bilutleiefirma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Lokasjon> lokasjoner = new ArrayList<>();
    @OneToMany(mappedBy = "bilutleiefirma", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Administrator> administratorer = new ArrayList<>();

    public List<Kunde> getKunder() {
        return kunder;
    }

    public List<Lokasjon> getLokasjoner() {
        return lokasjoner;
    }

    public List<Administrator> getAdministratorer() {
        return administratorer;
    }
}
