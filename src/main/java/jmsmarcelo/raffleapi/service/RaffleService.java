package jmsmarcelo.raffleapi.service;

import jmsmarcelo.raffleapi.model.Raffle;
import jmsmarcelo.raffleapi.repository.RaffleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaffleService {
    private final RaffleRepository raffleRepository;

    public RaffleService(RaffleRepository raffleRepository) {
        this.raffleRepository = raffleRepository;
    }

    public Raffle create(Raffle raffle) {
        return raffleRepository.save(raffle);
    }
    public List<Raffle> findAll() {
        return raffleRepository.findAll();
    }
    public Raffle findById(Long id) {
        return raffleRepository.findById(id).orElse(null);
    }
    public Raffle update(Raffle raffle) {
        return raffleRepository.save(raffle);
    }
}
