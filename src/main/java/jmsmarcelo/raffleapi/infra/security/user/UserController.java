package jmsmarcelo.raffleapi.infra.security.user;

import jakarta.validation.Valid;
import jmsmarcelo.raffleapi.infra.security.TokenData;
import jmsmarcelo.raffleapi.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    private final UserToken userToken;

    public UserController(UserToken userToken) {
        this.userToken = userToken;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenData> login(@RequestBody @Valid UserData userData) {
        return ResponseEntity.ok(userToken.getTokenData(userData));
    }
}
