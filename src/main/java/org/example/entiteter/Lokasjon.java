package org.example.entiteter;

import jakarta.persistence.*;
import org.example.entiteter.brukerEntiteter.Medarbeider;

import java.util.ArrayList;
import java.util.List;

@Entity
//@Table(schema = "dat109oblig2iterativt",name = "lokasjon")
@Table(name = "lokasjon")
public class Lokasjon extends Entitet {
    private String adresse;
    private String telefonnummer;
    public Lokasjon(){}
    public Lokasjon(String adresse, String telefonnummer) {
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
    }

    @ManyToOne
    @JoinColumn(name = "bilutleiefirma_id")
    private Bilutleiefirma bilutleiefirma;
    @OneToMany(mappedBy = "lokasjon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Medarbeider> medarbeidere = new ArrayList<>();
    @OneToMany(mappedBy = "lokasjon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Bil> biler = new ArrayList<>();

    public Bilutleiefirma getBilutleiefirma() {
        return bilutleiefirma;
    }

    public void setBilutleiefirma(Bilutleiefirma bilutleiefirma) {
        this.bilutleiefirma = bilutleiefirma;
    }

    public List<Medarbeider> getMedarbeidere() {
        return medarbeidere;
    }

    public List<Bil> getBiler() {
        return biler;
    }

    @Override
    public String toString(){
        return this.getId() + " " + this.telefonnummer + this.adresse;
    }
}
