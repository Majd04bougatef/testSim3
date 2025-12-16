package tn.esprit.sim3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Sim3Application {

    public static void main(String[] args) {
        SpringApplication.run(Sim3Application.class, args);
    }

}
