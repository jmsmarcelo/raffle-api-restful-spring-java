package jmsmarcelo.raffleapi.service;

import jmsmarcelo.raffleapi.model.Raffle;
import jmsmarcelo.raffleapi.model.RaffleStatus;
import jmsmarcelo.raffleapi.model.Ticket;
import jmsmarcelo.raffleapi.model.TicketStatus;
import jmsmarcelo.raffleapi.repository.RaffleRepository;
import jmsmarcelo.raffleapi.repository.TicketRepository;
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

    public Ticket drawTicket(Raffle raffle) {
        raffle = raffleRepository.findById(raffle.getId()).orElse(raffle);
        List<Ticket> tickets = ticketRepository.findAllByRaffleIdAndRaffleStatusAndStatus(raffle.getId(), RaffleStatus.UPCOMING, TicketStatus.PARTICIPATING);

        Random rand = new Random();
        Ticket drawnTicket = tickets.get(rand.nextInt(tickets.size()));

        drawnTicket.setStatus(TicketStatus.WINNER);
        raffle.setStatus(RaffleStatus.COMPLETED);

        raffleRepository.save(raffle);
        return ticketRepository.save(drawnTicket);
    }
}
