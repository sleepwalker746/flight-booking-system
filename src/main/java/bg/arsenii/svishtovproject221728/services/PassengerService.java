package bg.arsenii.svishtovproject221728.services;

import bg.arsenii.svishtovproject221728.entities.Passenger;
import bg.arsenii.svishtovproject221728.exceptions.NotFoundException;
import bg.arsenii.svishtovproject221728.repositories.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;


    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }


    public Passenger create(Passenger passenger) {
    passengerRepository.findByEgn(passenger.getEgn()).ifPresent(p ->{
        throw new IllegalArgumentException("Passenger with such EGN already exists");
    });
        return passengerRepository.save(passenger);
    }


    public List<Passenger> list() {
        return passengerRepository.findAll();
    }


    public Passenger findByEgn(String egn) {
        return passengerRepository.findByEgn(egn)
                .orElseThrow(() -> new NotFoundException("Passenger with such EGN not found"));
    }


    public void delete(Long id) {
        passengerRepository.deleteById(id);
    }


    public List<Passenger> searchByName(String name) {
        if (name == null || name.isBlank()) {
            return passengerRepository.findAll();
        }
        return passengerRepository.findByFirstNameContainingIgnoreCase(name);
    }
}
