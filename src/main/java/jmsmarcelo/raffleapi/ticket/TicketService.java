package jmsmarcelo.raffleapi.ticket;

import jmsmarcelo.raffleapi.customer.CustomerRepository;
import jmsmarcelo.raffleapi.exception.ValidationException;
import jmsmarcelo.raffleapi.raffle.RaffleRepository;
import jmsmarcelo.raffleapi.raffle.RaffleStatus;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final RaffleRepository raffleRepository;
    private final CustomerRepository customerRepository;

    public TicketService(TicketRepository ticketRepository, RaffleRepository raffleRepository, CustomerRepository customerRepository) {
        this.ticketRepository = ticketRepository;
        this.raffleRepository = raffleRepository;
        this.customerRepository = customerRepository;
    }

    public Ticket create(Ticket ticket) {
        if(ticket.getRaffle().getId() == null)
            throw new ValidationException(
                    HttpStatus.BAD_REQUEST, "field::raffle", "error::The given id must not be null");
        if(!raffleRepository.existsByIdAndStatus(ticket.getRaffle().getId(), RaffleStatus.AVAILABLE))
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "field::raffle id", "error::does not exist or the status is not AVAILABLE");
        if(ticket.getCustomer().getCpf() == null || ticket.getCustomer().getCpf().isEmpty())
            throw new ValidationException(
                    HttpStatus.BAD_REQUEST, "field::customer", "error::The given cpf must not be null or empty");
        if(!customerRepository.existsById(ticket.getCustomer().getCpf()))
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "field::customer", "error::cpf does not exist");

        ticket.setRaffle(raffleRepository.findById(ticket.getRaffle().getId()).orElse(ticket.getRaffle()));
        ticket.setCustomer(customerRepository.findById(ticket.getCustomer().getCpf()).orElse(ticket.getCustomer()));
        return ticketRepository.save(ticket);
    }
    public Ticket findById(Long id) {
        if(!ticketRepository.existsById(id))
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "path::", "error::ticket does not exist");

        return ticketRepository.findById(id).orElse(null);
    }
    public List<Ticket> findAllByRaffleId(Long raffleId) {
        List<Ticket> tickets = ticketRepository.findAllByRaffleId(raffleId);

        if(tickets.isEmpty())
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "error::there are no tickets for the selected raffle");

        return tickets;
    }
    public List<Ticket> findAllByCustomerCpf(String customerCpf) {
        List<Ticket> tickets = ticketRepository.findAllByCustomerCpf(customerCpf);

        if(tickets.isEmpty())
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "error::There are no tickets for the selected cpf");

        return tickets;
    }
    public List<Ticket> findAllByRaffleIdAndCustomerCpf(Long raffleId, String customerCpf) {
        List<Ticket> tickets = ticketRepository.findAllByRaffleIdAndCustomerCpf(raffleId, customerCpf);

        if(tickets.isEmpty())
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "error::There are no tickets for the selected id/cpf");

        return tickets;
    }
    public Ticket update(Ticket ticket) {
        if(!ticketRepository.existsById(ticket.getId()))
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "path::", "error::ticket does not exist");

        return ticketRepository.save(ticket);
    }
}
