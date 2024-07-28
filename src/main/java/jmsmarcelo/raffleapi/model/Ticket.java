package jmsmarcelo.raffleapi.model;

import jakarta.persistence.*;

@Entity(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Raffle raffle;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
    @Column(nullable = false)
    private TicketStatus status = TicketStatus.PARTICIPATING;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Raffle getRaffle() {
        return raffle;
    }
    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
    }

    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TicketStatus getStatus() {
        return status;
    }
    public void setStatus(TicketStatus status) {
        this.status = status;
    }
}
