package jmsmarcelo.raffleapi.raffledraw;

import jmsmarcelo.raffleapi.exception.ValidationException;
import jmsmarcelo.raffleapi.raffle.RaffleRepository;
import jmsmarcelo.raffleapi.raffle.RaffleStatus;
import jmsmarcelo.raffleapi.ticket.Ticket;
import jmsmarcelo.raffleapi.ticket.TicketRepository;
import jmsmarcelo.raffleapi.ticket.TicketStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class RaffleDrawService {
    private final RaffleRepository raffleRepository;
    private final TicketRepository ticketRepository;

    public RaffleDrawService(TicketRepository ticketRepository, RaffleRepository raffleRepository) {
        this.raffleRepository = raffleRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket drawTicket(Long id) {
        if(!raffleRepository.existsById(id))
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "path::/raffle-draw/" + id, "error::this raffle id does not exist or the status is not AVAILABLE");

        List<Ticket> tickets = ticketRepository.findAllByRaffleIdAndRaffleStatusAndStatus(id,
                RaffleStatus.AVAILABLE, TicketStatus.PARTICIPATING);

        if(tickets.isEmpty())
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "path::/raffle-draw/" + id, "error::there are no tickets to raffle id");

        Random rand = new Random();
        Ticket drawnTicket = tickets.get(rand.nextInt(tickets.size()));
        drawnTicket.setStatus(TicketStatus.WINNER);
        drawnTicket.getRaffle().setStatus(RaffleStatus.COMPLETED);
        return ticketRepository.save(drawnTicket);
    }
}
