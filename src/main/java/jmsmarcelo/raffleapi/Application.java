package jmsmarcelo.raffleapi;

import jmsmarcelo.raffleapi.infra.security.UserForTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    @Autowired
    UserForTest userForTest;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
