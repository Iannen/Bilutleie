package org.example.entiteter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dat109oblig2iterativt",name = "bil")
public class Bil extends Entitet {
    @ManyToOne
    @JoinColumn(name = "lokasjon_id")
    private Lokasjon lokasjon;

    public Lokasjon getLokasjon() {
        return lokasjon;
    }

    public void setLokasjon(Lokasjon lokasjon) {
        this.lokasjon = lokasjon;
    }
}
