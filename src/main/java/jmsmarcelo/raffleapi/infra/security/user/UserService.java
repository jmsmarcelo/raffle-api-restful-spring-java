package jmsmarcelo.raffleapi.infra.security.user;

import jmsmarcelo.raffleapi.infra.security.TokenData;
import jmsmarcelo.raffleapi.infra.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
    public TokenData getTokenData(UserData userData) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userData.username(), userData.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        return new TokenData(tokenService.generateToken(user));
    }
}
