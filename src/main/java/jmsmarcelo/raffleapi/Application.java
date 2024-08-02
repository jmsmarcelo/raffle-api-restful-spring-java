package jmsmarcelo.raffleapi;

import jmsmarcelo.raffleapi.security.SecurityConfiguration;
import jmsmarcelo.raffleapi.security.UserForTest;
import jmsmarcelo.raffleapi.security.user.Role;
import jmsmarcelo.raffleapi.security.user.User;
import jmsmarcelo.raffleapi.security.user.UserRepository;
import jmsmarcelo.raffleapi.security.user.UserService;
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
