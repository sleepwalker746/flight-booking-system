package bg.arsenii.svishtovproject221728.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    private String middleName;

    @NotBlank
    private String lastName;

    @Column(unique = true)
    @NotBlank
    private String egn;

    private String address;
    private String phone;
    private String passportNumber;

    @OneToMany(mappedBy = "passenger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
}
