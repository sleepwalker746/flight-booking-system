package bg.arsenii.svishtovproject221728.repositories;

import bg.arsenii.svishtovproject221728.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findByFlightNumber(String flightNumber);
}
