package org.example.tekstBrukergrensesnitt;

import org.example.entiteter.Bil;
import org.example.entiteter.Bilutleiefirma;
import org.example.entiteter.Lokasjon;
import org.example.entiteter.brukerEntiteter.Administrator;
import org.example.entiteter.brukerEntiteter.Kunde;
import org.example.entiteter.brukerEntiteter.Medarbeider;
import org.example.service.DatabaseService;

public class Brukergrensesnitt {

    private final DatabaseService databaseService;

    public Brukergrensesnitt(DatabaseService databaseService) {
        this.databaseService = databaseService;
        setUpAndStart();
    }
    private void setUpAndStart() {
        //deklarere menyobjekter
        Meny<Bilutleiefirma> loginMeny = new Meny<>("Hovedmeny - vennligst velg en av følgende");
        Meny<Administrator> adminMeny = new Meny<>("Adminmeny -- vennligst velg en av følgende");
        Meny<Kunde> kundeMeny = new Meny<>("Kundemeny -- vennligst velg en av følgende");
        Meny<Medarbeider> medarbeiderMeny = new Meny<>("Medarbeidermeny -- vennligst velg en av følgende");

        //Gi hovemeny logikk
        loginMeny.addValg("1", "Logg inn som administrator", () -> {
                    String brukernavn = Prosedyrer.beOmPaalogging();
                    String plaintekstPass = Prosedyrer.beOmPassord();
                    Administrator admin = databaseService.login(Administrator.class, brukernavn, plaintekstPass);
                    if (admin == null)
                        System.out.println("Pålogging mislykkes");
                    else
                        adminMeny.run(admin);
                })
                .addValg("2", "Logg inn som medarbeider", () -> {
                    String brukernavn = Prosedyrer.beOmPaalogging();
                    String plaintekstPass = Prosedyrer.beOmPassord();
                    Medarbeider medarbeider = databaseService.login(Medarbeider.class, brukernavn, plaintekstPass);
                    if (medarbeider == null)
                        System.out.println("Pålogging mislykkes");
                    else
                        medarbeiderMeny.run(medarbeider);
                })
                .addValg("3", "Logg inn som kunde", () -> {
                    String brukernavn = Prosedyrer.beOmPaalogging();
                    String plaintekstPass = Prosedyrer.beOmPassord();
                    Kunde kunde = databaseService.login(Kunde.class, brukernavn, plaintekstPass);
                    if (kunde == null)
                        System.out.println("Pålogging mislykkes");
                    else
                        kundeMeny.run(kunde);
                })
                .addValg("4","Registrer ny kunde",()->{
                    Kunde kunde = Prosedyrer.skapKunde();
                    if (kunde!=null){
                        databaseService.addKunde(kunde);
                        kundeMeny.run(kunde);
                    }
                });

        //Gi adminmeny logikk
        adminMeny
                .addValg("1", "Registrer en ny medarbeider på en lokasjon", () -> {
                    Lokasjon lokasjon = Prosedyrer.velgEntity(databaseService.hentEntiteter(Lokasjon.class));
                    if (lokasjon!=null){
                        Medarbeider medarbeider =Prosedyrer.skapMedarbeider();
                        if (medarbeider != null)
                            databaseService.addMedarbeider(lokasjon, medarbeider);
                    }
                })
                .addValg("2", "Opprette et utleiekontor til firmaet", () -> {
                    Lokasjon lokasjon = Prosedyrer.skapLokasjon();
                    if (lokasjon != null)
                        databaseService.addLokasjon(lokasjon);
                })
                .addValg("3","Registrer en ny bil på en lokasjon",()->{
                    Lokasjon lokasjon = Prosedyrer.velgEntity(databaseService.hentEntiteter(Lokasjon.class));
                    if (lokasjon!=null){
                        Bil bil =Prosedyrer.skapBil();
                        if (bil != null)
                            databaseService.addBil(lokasjon, bil);
                    }
                });

        //Gi kundemeny logikk
        kundeMeny
                .addValg("1", "Lei en bil fra en lokasjon", () ->{
                    Lokasjon lokasjon = Prosedyrer.velgEntity(databaseService.hentEntiteter(Lokasjon.class));
                    if (lokasjon!=null){
                        Bil bil = Prosedyrer.velgEntity(databaseService.hentEntiteter(Bil.class));
                        //persiste et nytt utleie
                    }
                });

        //Gi medarbeidermeny logikk
        medarbeiderMeny
                .addValg("1","Legg til ny kunde",()->{
                    Kunde kunde = Prosedyrer.skapKunde();
                    databaseService.addKunde(kunde);
                })
                .addValg("2","Lei ut en bil til en kunde",()->{
                    Lokasjon lokasjon = medarbeiderMeny.getMenyobjekt().getLokasjon();
                    Bil bil = Prosedyrer.velgEntity(databaseService.hentEntiteter(Bil.class));
                    //persiste et nytt utleie
                });
        loginMeny.run(databaseService.getBilutleiefirma());
    }
}
