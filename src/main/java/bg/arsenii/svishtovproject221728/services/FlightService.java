package bg.arsenii.svishtovproject221728.services;

import bg.arsenii.svishtovproject221728.entities.Flight;
import bg.arsenii.svishtovproject221728.exceptions.NotFoundException;
import bg.arsenii.svishtovproject221728.repositories.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;


    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    public Flight create(Flight f) {
        return flightRepository.save(f);
    }

    public Flight findByFlightNumber(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber).
                orElseThrow(() -> new NotFoundException("Flight number not found"));
    }

    public List<Flight> list() {
        return flightRepository.findAll();
    }

    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
