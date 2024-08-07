package jmsmarcelo.raffleapi.ticket;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
@SecurityRequirement(name = "bearer-key")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody @Valid Ticket ticket) {
        return ResponseEntity.ok(ticketService.create(ticket));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> get(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findById(id));
    }
    @GetMapping("/raffle-{id}")
    public ResponseEntity<List<Ticket>> getAllByRaffleId(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.findAllByRaffleId(id));
    }
    @GetMapping("customer-{cpf}")
    public ResponseEntity<List<Ticket>> getAllByCustomerCPf(@PathVariable String cpf) {
        return ResponseEntity.ok(ticketService.findAllByCustomerCpf(cpf));
    }
    @GetMapping("raffle_{id}/customer-{cpf}")
    public ResponseEntity<List<Ticket>> getAllByRaffleIdAndCustomerCPf(@PathVariable Long id, @PathVariable String cpf) {
        return ResponseEntity.ok(ticketService.findAllByRaffleIdAndCustomerCpf(id, cpf));
    }
    @PutMapping
    public ResponseEntity<Ticket> set(@RequestBody @Valid Ticket ticket) {
        return ResponseEntity.ok(ticketService.update(ticket));
    }
}
