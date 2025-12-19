package bg.arsenii.svishtovproject221728.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String reservationCode;

    @ManyToOne(optional = false)
    private Flight flight;

    @ManyToOne(optional = false)
    private Passenger passenger;

    private OffsetDateTime createdAt;

    private double paidAmount;
}
