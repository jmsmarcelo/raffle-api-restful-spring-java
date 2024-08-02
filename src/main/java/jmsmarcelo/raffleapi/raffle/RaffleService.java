package jmsmarcelo.raffleapi.raffle;

import jmsmarcelo.raffleapi.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaffleService {
    private final RaffleRepository raffleRepository;

    public RaffleService(RaffleRepository raffleRepository) {
        this.raffleRepository = raffleRepository;
    }

    public Raffle create(Raffle raffle) {
        if(raffleRepository.existsByName(raffle.getName()))
            throw new ValidationException(
                    HttpStatus.CONFLICT, "field::name", "error::there is a raffle with this name");

        return raffleRepository.save(raffle);
    }
    public List<Raffle> findAll() {
        List<Raffle> raffles = raffleRepository.findAll();

        if(raffles.isEmpty())
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "field::raffle", "error::there is no raffles");

        return raffles;
    }
    public Raffle findById(Long id) {
        if(!raffleRepository.existsById(id))
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "path::/raffles/" +id, "error::there is no raffle with this id");

        return raffleRepository.findById(id).orElse(new Raffle());
    }
    public Raffle update(Raffle raffle) {
        if(raffle.getId() == null)
            throw new ValidationException(
                    HttpStatus.BAD_REQUEST, "field::id", "error::");
        if(raffleRepository.existsByNameAndIdNot(raffle.getName(), raffle.getId()))
                throw new ValidationException(
                    HttpStatus.CONFLICT, "field::name", "error::there is a raffle with this name");
        if(!raffleRepository.existsById(raffle.getId()))
            throw new ValidationException(
                    HttpStatus.NOT_FOUND, "field::id", "error::this raffle does not exist");

        return raffleRepository.save(raffle);
    }
}
