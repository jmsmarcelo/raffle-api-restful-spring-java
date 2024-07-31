package jmsmarcelo.raffleapi.raffle;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/raffles")
public class RaffleController {
    private final RaffleService raffleService;

    public RaffleController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }

    @PostMapping
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
    public ResponseEntity<Raffle> set(@RequestBody @Valid Raffle raffle) {
        return ResponseEntity.ok(raffleService.update(raffle));
    }
}
