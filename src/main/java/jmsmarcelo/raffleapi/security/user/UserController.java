package jmsmarcelo.raffleapi.security.user;

import jakarta.validation.Valid;
import jmsmarcelo.raffleapi.security.TokenData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    private final UserSecurity userSecurity;

    public UserController(UserSecurity userSecurity) {
        this.userSecurity = userSecurity;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenData> login(@RequestBody @Valid UserData userData) {
        return ResponseEntity.ok(userSecurity.getTokenData(userData));
    }
}
