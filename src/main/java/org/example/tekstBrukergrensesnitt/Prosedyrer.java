package org.example.tekstBrukergrensesnitt;

import org.example.entiteter.Bil;
import org.example.entiteter.Entitet;
import org.example.entiteter.brukerEntiteter.Kunde;
import org.example.entiteter.Lokasjon;
import org.example.entiteter.brukerEntiteter.Medarbeider;

import java.util.List;
import java.util.Scanner;

public class Prosedyrer {
    private final static Scanner scanner = new Scanner(System.in);

    public static String beOmPaalogging(){
        System.out.println("Vennligst returner brukernavn");
        return scanner.nextLine();
    }

    public static String beOmPassord() {
        System.out.println("Vennligst returner passord");
        return scanner.nextLine();
    }
    public static String beOmNavn() {
        System.out.println("Vennligst returner navn");
        return scanner.nextLine();
    }
    public static Lokasjon skapLokasjon() {
        System.out.println("Vennligst oppgi lokasjonens adresse");
        String adresse=scanner.nextLine();
        System.out.println("Vennligst oppgi lokasjonens telefonnummer");
        String telefonnummer=scanner.nextLine();
        System.out.println("Ny lokasjon:\tAdresse: "+adresse+", Telefonnummer: "+telefonnummer);
        System.out.println("Vennligst returner '1' for å bekrefte, '2' for å avbryte eller hvasomhelst for å forsøke igjen");
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> new Lokasjon(adresse, telefonnummer);
            case "2" -> null;
            default -> skapLokasjon();
        };
    }
    public static Medarbeider skapMedarbeider() {
        String navn = beOmNavn();
        String brukernavn = beOmPaalogging();
        String passord=beOmPassord();
        System.out.println("Ny medarbeider:\tNavn: "+navn+", brukernavn: "+brukernavn+", passord: "+passord);
        System.out.println("Vennligst returner '1' for å bekrefte, '2' for å avbryte eller hvasomhelst for å forsøke igjen");
        String input = scanner.nextLine();
        return switch (input){
            case "1"->new Medarbeider(brukernavn,passord,navn);
            case "2"->null;
            default->skapMedarbeider();
        };
    }
    public static Kunde skapKunde() {
        String navn = beOmNavn();
        String brukernavn = beOmPaalogging();
        String passord=beOmPassord();
        System.out.println("Ny kunde:\tNavn: "+navn+", brukernavn: "+brukernavn+", passord: "+passord);
        System.out.println("Vennligst returner '1' for å bekrefte, '2' for å avbryte eller hvasomhelst for å forsøke igjen");
        String input = scanner.nextLine();
        return switch (input){
            case "1"->new Kunde(brukernavn, passord, navn);
            case "2"->null;
            default->skapKunde();
        };
    }
    public static Bil skapBil() {
        String navn = beOmNavn();
        return new Bil();
    }

    public static <T extends Entitet> T velgEntity(List<T> entiteter) {
        System.out.println("Vennligst velg ut en av følgende");
        for (T entity:entiteter)
            System.out.println("\t"+entity.getId());
        String input = scanner.nextLine();
        long id;
        try {
             id=Long.parseLong(input);
        }catch(Exception e) {
            System.out.println("'"+input+"' er ikke et tall");
            return null;
        }
        return entiteter.stream().filter(a->a.getId()==id).toList().get(0);
    }
}
