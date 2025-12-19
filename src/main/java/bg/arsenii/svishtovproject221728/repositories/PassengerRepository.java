package bg.arsenii.svishtovproject221728.repositories;

import bg.arsenii.svishtovproject221728.entities.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;


public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByEgn(String egn);
    List<Passenger> findByFirstNameContainingIgnoreCase(String firstName);
}
