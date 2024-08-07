package jmsmarcelo.raffleapi.ticket;

import jmsmarcelo.raffleapi.raffle.RaffleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAllByRaffleId(Long raffleId);
    List<Ticket> findAllByCustomerCpf(String customerCpf);
    List<Ticket> findAllByRaffleIdAndCustomerCpf(Long raffleId, String customerCpf);
    List<Ticket> findAllByRaffleIdAndRaffleStatusAndStatus(Long raffleId, RaffleStatus raffleStatus, TicketStatus status);
}
