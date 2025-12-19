package bg.arsenii.svishtovproject221728.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String flightNumber;

    private String airline;
    private String destination;
    private OffsetDateTime departure;
    private double price;
    private int totalSeats;

    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
}
