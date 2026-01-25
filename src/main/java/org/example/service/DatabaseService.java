package org.example.service;

import org.example.entiteter.*;
import org.example.entiteter.brukerEntiteter.Administrator;
import org.example.entiteter.brukerEntiteter.Kunde;
import org.example.entiteter.brukerEntiteter.Medarbeider;
import org.example.entiteter.brukerEntiteter.Bruker;
import org.example.repositories.*;
import org.example.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatabaseService {
    private final BilutleiefirmaRepo bilutleiefirmaRepo;
    private final AdministratorRepo administratorRepo;
    private final KundeRepo kundeRepo;
    private final LokasjonRepo lokasjonRepo;
    private final MedarbeiderRepo medarbeiderRepo;
    private final BilRepo bilrepo;
    private final Bilutleiefirma bilutleiefirma;

    @Autowired
    public DatabaseService(BilutleiefirmaRepo bilutleiefirmaRepo, AdministratorRepo administratorRepo, KundeRepo kundeRepo, LokasjonRepo lokasjonRepo, MedarbeiderRepo medarbeiderRepo, BilRepo bilrepo){
        this.bilutleiefirmaRepo=bilutleiefirmaRepo;
        this.administratorRepo=administratorRepo;
        this.kundeRepo=kundeRepo;
        this.lokasjonRepo=lokasjonRepo;
        this.medarbeiderRepo=medarbeiderRepo;
        this.bilrepo = bilrepo;
        this.bilutleiefirma = bilutleiefirmaRepo.findById((long)1).orElseGet(()->{
            Bilutleiefirma bilutleiefirma = new Bilutleiefirma();
            Administrator defaultAdmin = new Administrator("Admin","pass","Karl Roger");
            defaultAdmin.setBilutleiefirma(bilutleiefirma);
            bilutleiefirma.getAdministratorer().add(defaultAdmin);
            bilutleiefirmaRepo.save(bilutleiefirma);
            return bilutleiefirma;
        });

    }
    public void addAdministrator(Administrator administrator){
        bilutleiefirma.getAdministratorer().add(administrator);
        administrator.setBilutleiefirma(bilutleiefirma);
        bilutleiefirmaRepo.save(bilutleiefirma);
    }
    public void addKunde(Kunde kunde){
        bilutleiefirma.getKunder().add(kunde);
        kunde.setBilutleiefirma(bilutleiefirma);
        bilutleiefirmaRepo.save(bilutleiefirma);
    }
    public void addMedarbeider(Lokasjon lokasjon, Medarbeider medarbeider) {
        lokasjon.getMedarbeidere().add(medarbeider);
        medarbeider.setLokasjon(lokasjon);
        lokasjonRepo.save(lokasjon);
    }
    public void addLokasjon(Lokasjon lokasjon){
        bilutleiefirma.getLokasjoner().add(lokasjon);
        lokasjon.setBilutleiefirma(bilutleiefirma);
        bilutleiefirmaRepo.save(bilutleiefirma);
    }
    public void addBil(Lokasjon lokasjon,Bil bil){
        lokasjon.getBiler().add(bil);
        bil.setLokasjon(lokasjon);
        bilutleiefirmaRepo.save(bilutleiefirma);
    }
    public <T extends Bruker> T login(Class<T> brukerClass, String brukernavn, String plaintekstPass) {
        T bruker;
        if (Administrator.class.isAssignableFrom(brukerClass)) {
            bruker = brukerClass.cast(administratorRepo.findByBrukernavn(brukernavn));
        } else if (Kunde.class.isAssignableFrom(brukerClass)) {
            bruker = brukerClass.cast(kundeRepo.findByBrukernavn(brukernavn));
        } else if (Medarbeider.class.isAssignableFrom(brukerClass)) {
            bruker = brukerClass.cast(medarbeiderRepo.findByBrukernavn(brukernavn));
        } else {
            throw new IllegalArgumentException("Unsupported bruker class: " + brukerClass.getName());
        }
        if (bruker!=null && SecurityUtils.matches(plaintekstPass,bruker.getSaltedPassword(),bruker.getSalt()))
            return bruker;
        return null;
    }
    @SuppressWarnings("unchecked")
    public <T extends Entitet> List<T> hentEntiteter(Class<T> entityClass) {
        List<T> entities = null;
        if (Lokasjon.class.isAssignableFrom(entityClass)) {
            entities = (List<T>) lokasjonRepo.findAll();
        } else if (Administrator.class.isAssignableFrom(entityClass)) {
            entities = (List<T>) administratorRepo.findAll();
        } else if (Kunde.class.isAssignableFrom(entityClass)) {
            entities = (List<T>) kundeRepo.findAll();
        } else if (Medarbeider.class.isAssignableFrom(entityClass)) {
            entities = (List<T>) medarbeiderRepo.findAll();
        } else if (Bilutleiefirma.class.isAssignableFrom(entityClass)) {
            entities = (List<T>) bilutleiefirmaRepo.findAll();
        } else if (Bil.class.isAssignableFrom(entityClass))
            entities = (List<T>) bilrepo.findAll();
        return entities;
    }
    public <T extends Entitet> T hentEntitet(Class<T> entityClass, Long id) {
        if (Administrator.class.isAssignableFrom(entityClass)) {
            return entityClass.cast(administratorRepo.findById(id).orElse(null));
        } else if (Kunde.class.isAssignableFrom(entityClass)) {
            return entityClass.cast(kundeRepo.findById(id).orElse(null));
        } else if (Medarbeider.class.isAssignableFrom(entityClass)) {
            return entityClass.cast(medarbeiderRepo.findById(id).orElse(null));
        } else if (Bilutleiefirma.class.isAssignableFrom(entityClass)) {
            return entityClass.cast(bilutleiefirmaRepo.findById(id).orElse(null));
        } else if (Lokasjon.class.isAssignableFrom(entityClass)) {
            return entityClass.cast(lokasjonRepo.findById(id).orElse(null));
        } else if (Bil.class.isAssignableFrom(entityClass)){
            return entityClass.cast(bilrepo.findById(id).orElse(null));
        }

        else {
            throw new IllegalArgumentException("Unsupported entity class: " + entityClass.getName());
        }
    }
    public Bilutleiefirma getBilutleiefirma(){
        return this.bilutleiefirma;
    }
}
