package org.example.entiteter.brukerEntiteter;

import org.example.entiteter.Bilutleiefirma;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
//@Table(schema = "dat109oblig2iterativt",name = "admin")
@Table(name = "admin")
public class Administrator extends Bruker {

    public Administrator(){}
    public Administrator(String brukernavn, String passord, String navn) {
        super(brukernavn,passord,navn);
    }

    @ManyToOne
    @JoinColumn(name = "bilutleiefirma_id")
    private Bilutleiefirma bilutleiefirma;

    public Bilutleiefirma getBilutleiefirma() {
        return bilutleiefirma;
    }

    public void setBilutleiefirma(Bilutleiefirma bilutleiefirma) {
        this.bilutleiefirma = bilutleiefirma;
    }    
}
