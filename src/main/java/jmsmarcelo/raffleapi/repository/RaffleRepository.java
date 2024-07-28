package jmsmarcelo.raffleapi.repository;

import jmsmarcelo.raffleapi.model.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Long> {
}
