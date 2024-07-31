package jmsmarcelo.raffleapi.raffledraw;

import jmsmarcelo.raffleapi.ticket.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/raffle-draw")
public class RaffleDrawController {
    private final RaffleDrawService raffleDrawService;

    public RaffleDrawController(RaffleDrawService raffleDrawService) {
        this.raffleDrawService = raffleDrawService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> drawTicket(@PathVariable Long id) {
        return ResponseEntity.ok(raffleDrawService.drawTicket(id));
    }
}
