package org.example.entiteter.brukerEntiteter;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.example.entiteter.Bilutleiefirma;

@Entity
@Table(schema = "dat109oblig2iterativt",name = "admin")
public class Administrator extends Bruker {

    @ManyToOne
    @JoinColumn(name = "bilutleiefirma_id")
    private Bilutleiefirma bilutleiefirma;

    public Administrator(){}
    public Administrator(String brukernavn, String passord, String navn) {
        super(brukernavn,passord,navn);
    }

    public Bilutleiefirma getBilutleiefirma() {
        return bilutleiefirma;
    }

    public void setBilutleiefirma(Bilutleiefirma bilutleiefirma) {
        this.bilutleiefirma = bilutleiefirma;
    }

}
