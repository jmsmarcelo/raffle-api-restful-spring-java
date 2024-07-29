package jmsmarcelo.raffleapi.controller;

import jmsmarcelo.raffleapi.model.Raffle;
import jmsmarcelo.raffleapi.model.Ticket;
import jmsmarcelo.raffleapi.service.RaffleDrawService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raffle-draw")
public class RaffleDrawController {
    private final RaffleDrawService raffleDrawService;

    public RaffleDrawController(RaffleDrawService raffleDrawService) {
        this.raffleDrawService = raffleDrawService;
    }

    @PostMapping
    public ResponseEntity<Ticket> drawTicket(@RequestBody Raffle raffle) {
        return ResponseEntity.ok(raffleDrawService.drawTicket(raffle));
    }
}
