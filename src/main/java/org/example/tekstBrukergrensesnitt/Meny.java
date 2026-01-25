package org.example.tekstBrukergrensesnitt;


import org.example.entiteter.Entitet;

import java.util.*;

public class Meny <T extends Entitet>  {
    final static Scanner scanner = new Scanner(System.in);
    private final String headerMsg;
    private final Map<String,MenyValg> valg = new HashMap<>();
    private T menyobjekt;

    public Meny(String headerMsg){
        this.headerMsg=headerMsg;
    }
//    public void run() {
//        boolean running = true;
//        while (running){
//            System.out.println("**************************\n"+headerMsg);
//            for (Map.Entry<String, MenyValg> entry : valg.entrySet()) {
//                String id=entry.getKey();
//                MenyValg valg=entry.getValue();
//                System.out.println("\t"+id+". "+valg.getInfoTekst());
//            }
//            System.out.println("Returner '0' for å avslutte");
//            String input = scanner.nextLine();
//            if (valg.containsKey(input))
//                valg.get(input).execute();
//            else if (input.equals("0"))
//                running=false;
//            else
//                System.out.println(input+" er ikke et gyldig valg");
//        }
//    }
    public void run(T menyobjekt) {
        this.menyobjekt=menyobjekt;
        boolean running = true;
        while (running){
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
}