package org.example.tekstBrukergrensesnitt;

public class MenyValg {
    private final String infoTekst;
    private final Runnable action;
    public MenyValg(String infoTekst, Runnable action) {
        this.infoTekst=infoTekst;
        this.action = action;
    }
    public String getInfoTekst() {
        return infoTekst;
    }
    public void execute(){
        action.run();
    }
}
