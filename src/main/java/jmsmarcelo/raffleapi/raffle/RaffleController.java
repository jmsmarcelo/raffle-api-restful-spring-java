package jmsmarcelo.raffleapi.raffle;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raffles")
@SecurityRequirement(name = "bearer-key")
@EnableMethodSecurity(securedEnabled = true)
public class RaffleController {
    private final RaffleService raffleService;

    public RaffleController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Raffle> add(@RequestBody @Valid Raffle raffle) {
        return ResponseEntity.ok(raffleService.create(raffle));
    }
    @GetMapping
    public ResponseEntity<List<Raffle>> getAll() {
        return ResponseEntity.ok(raffleService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Raffle> getById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(raffleService.findById(id));
    }
    @PutMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Raffle> set(@RequestBody @Valid Raffle raffle) {
        return ResponseEntity.ok(raffleService.update(raffle));
    }
}
