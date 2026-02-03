package org.example.entiteter.brukerEntiteter;

import jakarta.persistence.*;
import org.example.entiteter.Lokasjon;

@Entity
//@Table(schema = "dat109oblig2iterativt",name = "medarbeider")
@Table(name = "medarbeider")
public class Medarbeider extends Bruker {

    public Medarbeider(String brukernavn, String passord, String navn) {
        super(brukernavn,passord,navn);
    }

    public Medarbeider() {}

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
