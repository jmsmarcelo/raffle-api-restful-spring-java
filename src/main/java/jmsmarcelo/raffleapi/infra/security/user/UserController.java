package jmsmarcelo.raffleapi.infra.security.user;

import jakarta.validation.Valid;
import jmsmarcelo.raffleapi.infra.security.TokenData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenData> login(@RequestBody @Valid UserData userData) {
        return ResponseEntity.ok(userService.getTokenData(userData));
    }
}
