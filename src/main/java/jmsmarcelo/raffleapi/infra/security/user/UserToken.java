package jmsmarcelo.raffleapi.infra.security.user;

import jmsmarcelo.raffleapi.infra.security.TokenData;
import jmsmarcelo.raffleapi.infra.security.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserToken {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public UserToken(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public TokenData getTokenData(UserData userData) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(userData.username(), userData.password());
        var authentication = authenticationManager.authenticate(authenticationToken);
        User user = (User) authentication.getPrincipal();
        return new TokenData(tokenService.generateToken(user));
    }
}
