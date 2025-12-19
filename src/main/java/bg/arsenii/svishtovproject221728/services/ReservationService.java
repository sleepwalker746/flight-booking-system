package bg.arsenii.svishtovproject221728.services;

import bg.arsenii.svishtovproject221728.entities.Flight;
import bg.arsenii.svishtovproject221728.entities.Passenger;
import bg.arsenii.svishtovproject221728.entities.Reservation;
import bg.arsenii.svishtovproject221728.exceptions.NotFoundException;
import bg.arsenii.svishtovproject221728.repositories.FlightRepository;
import bg.arsenii.svishtovproject221728.repositories.PassengerRepository;
import bg.arsenii.svishtovproject221728.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;


    public ReservationService(ReservationRepository reservationRepository, FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.reservationRepository = reservationRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }


    public Reservation create(Long flightId, Long passengerId, double paidAmount) {

        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new NotFoundException("Flight not found"));
        Passenger passenger = passengerRepository.findById(passengerId).orElseThrow(() -> new NotFoundException("Passenger not found"));

        long booked = reservationRepository.findByFlightId(flight.getId()).size();
        if (booked >= flight.getTotalSeats()) {
            throw new IllegalStateException("No seats available");
        }

        Reservation r = Reservation.builder()
                .reservationCode(generateCode())
                .flight(flight)
                .passenger(passenger)
                .createdAt(OffsetDateTime.now())
                .paidAmount(paidAmount)
                .build();

        return reservationRepository.save(r);
    }


    public List<Reservation> list() {
        return reservationRepository.findAll();
    }

    public List<Reservation> searchByReservationNumber(Long id) {
        if (id == null) return reservationRepository.findAll();
        return reservationRepository.findById(id).map(List::of).orElse(List.of());
    }

    public List<Reservation> findBetween(OffsetDateTime from, OffsetDateTime to) {
        return reservationRepository.findByCreatedAtBetween(from, to);
    }


    private String generateCode() {
        return UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }
}
