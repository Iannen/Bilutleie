package org.example;


import org.example.entiteter.Bilutleiefirma;
import org.example.entiteter.Lokasjon;
import org.example.entiteter.brukerEntiteter.Administrator;
import org.example.entiteter.brukerEntiteter.Medarbeider;
import org.example.repositories.BilutleiefirmaRepo;
import org.example.service.DatabaseService;
import org.example.tekstBrukergrensesnitt.Brukergrensesnitt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        //initstuffs
        ConfigurableApplicationContext context = SpringApplication.run(Main.class, args);
        Bilutleiefirma firma = populate(context);

        DatabaseService databaseService = context.getBean(DatabaseService.class);
        databaseService.setBilutleiefirma(firma);
        new Brukergrensesnitt(databaseService);

        //terminate the program
        context.close();
    }

    private static Bilutleiefirma populate(ConfigurableApplicationContext context){
        Bilutleiefirma bilutleiefirma = new Bilutleiefirma();
        Administrator defaultAdmin = new Administrator("Admin","pass","Karl Roger");
        defaultAdmin.setBilutleiefirma(bilutleiefirma);
        bilutleiefirma.getAdministratorer().add(defaultAdmin);

        Lokasjon lokasjon = new Lokasjon("Laksbakkveien 8", "55555555");
        lokasjon.setBilutleiefirma(bilutleiefirma);
        bilutleiefirma.getLokasjoner().add(lokasjon);

        Medarbeider tim = new Medarbeider("Timmuser", "pass", "Timmy Teigen");
        tim.setLokasjon(lokasjon);
        lokasjon.getMedarbeidere().add(tim);
        
        


        BilutleiefirmaRepo firmaRepo = context.getBean(BilutleiefirmaRepo.class);
        firmaRepo.save(bilutleiefirma);
        return bilutleiefirma;
    }
}