package jmsmarcelo.raffleapi.security.user;

import jmsmarcelo.raffleapi.security.TokenData;
import jmsmarcelo.raffleapi.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
public class UserSecurity {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserService userService;


    public UserSecurity(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService, UserService userService1) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService1;
    }

    public TokenData getTokenData(UserData userData) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(userData.username(), userData.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        return new TokenData(tokenService.generateToken(user));
    }
}
