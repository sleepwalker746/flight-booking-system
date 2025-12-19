package bg.arsenii.svishtovproject221728.repositories;

import bg.arsenii.svishtovproject221728.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.OffsetDateTime;
import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCreatedAtBetween(OffsetDateTime from, OffsetDateTime to);
    List<Reservation> findByFlightId(Long flightId);
}
