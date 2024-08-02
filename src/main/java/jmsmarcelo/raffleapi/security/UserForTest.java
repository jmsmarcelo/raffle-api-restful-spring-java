package jmsmarcelo.raffleapi.security;

import jmsmarcelo.raffleapi.security.user.Role;
import jmsmarcelo.raffleapi.security.user.User;
import jmsmarcelo.raffleapi.security.user.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
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
