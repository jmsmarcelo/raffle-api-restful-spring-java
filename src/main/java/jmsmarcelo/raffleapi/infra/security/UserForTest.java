package jmsmarcelo.raffleapi.infra.security;

import jmsmarcelo.raffleapi.infra.security.user.Role;
import jmsmarcelo.raffleapi.infra.security.user.User;
import jmsmarcelo.raffleapi.infra.security.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserForTest {
    UserRepository userRepository;
    public UserForTest(UserRepository userRepository) {
        this.userRepository = userRepository;

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User adminTest = new User("adminTest", bCryptPasswordEncoder.encode("12345678"), Role.ROLE_ADMIN);
        userRepository.save(adminTest);
        User userTest = new User("userTest", bCryptPasswordEncoder.encode("12345678"), Role.ROLE_USER);
        userRepository.save(userTest);
    }
}
