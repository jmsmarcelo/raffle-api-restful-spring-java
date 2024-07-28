package jmsmarcelo.raffleapi.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity(name = "raffles")
public class Raffle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Date drawDate;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private String award;
    @Column(nullable = false)
    private RaffleStatus status = RaffleStatus.PENDING;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Ticket> tickets;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Date getDrawDate() {
        return drawDate;
    }
    public void setDrawDate(Date drawDate) {
        this.drawDate = drawDate;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAward() {
        return award;
    }
    public void setAward(String award) {
        this.award = award;
    }

    public RaffleStatus getStatus() {
        return status;
    }
    public void setStatus(RaffleStatus status) {
        this.status = status;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
