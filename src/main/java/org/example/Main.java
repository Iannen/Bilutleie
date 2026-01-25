package org.example;


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
        DatabaseService databaseService = context.getBean(DatabaseService.class);

        Brukergrensesnitt brukergrensesnitt = new Brukergrensesnitt(databaseService);

        //terminate the program
        context.close();
    }
}