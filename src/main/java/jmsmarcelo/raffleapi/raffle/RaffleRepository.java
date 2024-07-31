package jmsmarcelo.raffleapi.raffle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Long> {
    Boolean existsByIdAndStatus(Long id, RaffleStatus status);
    Boolean existsByName(String name);
    Boolean existsByNameAndIdNot(String name, Long id);
}
