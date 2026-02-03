package org.example.tekstBrukergrensesnitt;


import org.example.entiteter.Entitet;
import org.example.entiteter.brukerEntiteter.Bruker;

import java.io.IOException;
import java.util.*;

public class Meny <T extends Entitet>  {
    final static Scanner scanner = new Scanner(System.in);
    private final String headerMsg;
    private final Map<String,MenyValg> valg = new HashMap<>();
    private T menyobjekt;

    public Meny(String headerMsg){
        this.headerMsg=headerMsg;
    }

    public void run(T menyobjekt) {
        this.menyobjekt=menyobjekt;
        boolean running = true;
        while (running){
            clearConsole();
            if (menyobjekt instanceof Bruker bruker) {
                System.out.println("Velkommen: " + bruker.getNavn());
            }
            System.out.println("**************************\n"+headerMsg);
            for (Map.Entry<String, MenyValg> entry : valg.entrySet()) {
                String id=entry.getKey();
                MenyValg valg=entry.getValue();
                System.out.println("\t"+id+". "+valg.getInfoTekst());
            }
            System.out.println("Returner '0' for å avslutte");
            String input = scanner.nextLine();
            if (valg.containsKey(input))
                valg.get(input).execute();
            else if (input.equals("0"))
                running=false;
            else
                System.out.println(input+" er ikke et gyldig valg");
        }
    }


    public Meny<T> addValg(String id, String infoTekst,Runnable action) {
        this.valg.put(id,new MenyValg(infoTekst,action));
        return this;
    }

    public T getMenyobjekt() {
        return menyobjekt;
    }

    static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("\n");
    }
}