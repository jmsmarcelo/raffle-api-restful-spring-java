package jmsmarcelo.raffleapi.raffle;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity(name = "raffles")
public class Raffle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    @NotNull
    private Date drawDate;
    @Column(nullable = false)
    @NotNull
    private Double price;
    @NotBlank
    @Column(nullable = false)
    private String award;
    @NotNull
    @Column(nullable = false)
    private RaffleStatus status = RaffleStatus.PENDING;

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
}
