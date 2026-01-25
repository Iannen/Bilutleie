package org.example.entiteter.brukerEntiteter;

import jakarta.persistence.MappedSuperclass;
import org.example.entiteter.Entitet;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.example.security.SecurityUtils.encodePassword;

@MappedSuperclass
public class Bruker extends Entitet {

    private String brukernavn;
    private String salt;
    private String saltedPassword;
    private String navn;
    public Bruker(){}

    public Bruker(String brukernavn, String passord, String navn) {
        this.brukernavn = brukernavn;
        this.salt= BCrypt.gensalt();
        this.saltedPassword = encodePassword(passord,salt);
        this.navn = navn;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }

    public void setSaltedPassword(String saltedPassword) {
        this.saltedPassword = saltedPassword;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
