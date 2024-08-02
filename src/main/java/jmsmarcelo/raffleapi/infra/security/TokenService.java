package jmsmarcelo.raffleapi.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jmsmarcelo.raffleapi.infra.exception.ValidationException;
import jmsmarcelo.raffleapi.infra.security.user.User;
import jmsmarcelo.raffleapi.infra.security.user.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    @Value("${JWT_SECRET:12345678}")
    private String secret;

    public String generateToken(User user) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("Raffle.api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(expirationDate())
                    .sign(algorithm);
        } catch(Exception e) {
            throw new ValidationException(HttpStatus.BAD_REQUEST, "error::error generating token", "message::" + e.getMessage());
        }
    }
    public String getSubject(String token) {
        try {
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Raffle.api").build()
                    .verify(token).getSubject();
        } catch(Exception e) {
            throw new ValidationException(HttpStatus.BAD_REQUEST, "error:invalid or expired token", "message::" + e.getMessage());
        }
    }

    private Instant expirationDate() {
        return Instant.now().plusSeconds(3600 * 24 * 30);
    }
}
