package jmsmarcelo.raffleapi.service;

import jmsmarcelo.raffleapi.model.Ticket;
import jmsmarcelo.raffleapi.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket create(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
    public Ticket findById(Long id) {
        return ticketRepository.findById(id).orElse(null);
    }
    public List<Ticket> findAllByRaffleId(Long raffleId) {
        return ticketRepository.findAllByRaffleId(raffleId);
    }
    public List<Ticket> findAllByCustomerCpf(String customerCpf) {
        return ticketRepository.findAllByCustomerCpf(customerCpf);
    }
    public List<Ticket> findAllByRaffleIdAndCustomerCpf(Long raffleId, String customerCpf) {
        return ticketRepository.findAllByRaffleIdAndCustomerCpf(raffleId, customerCpf);
    }
    public Ticket update(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
