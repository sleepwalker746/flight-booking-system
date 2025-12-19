package bg.arsenii.svishtovproject221728.controllers;

import bg.arsenii.svishtovproject221728.entities.Reservation;
import bg.arsenii.svishtovproject221728.services.FlightService;
import bg.arsenii.svishtovproject221728.services.PassengerService;
import bg.arsenii.svishtovproject221728.services.ReservationService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final ReservationService reservationService;
    private final PassengerService passengerService;
    private final FlightService flightService;


    public ReservationController(ReservationService reservationService, PassengerService passengerService, FlightService flightService) {
        this.reservationService = reservationService;
        this.passengerService = passengerService;
        this.flightService = flightService;
    }


    @GetMapping
    public String list(Model model) {
        model.addAttribute("reservations", reservationService.list());
        return "reservations/list";
    }


    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("flights", flightService.list());
        model.addAttribute("passengers", passengerService.list());
        return "reservations/form";
    }


    @PostMapping
    public String create(@Valid @ModelAttribute Reservation reservation, BindingResult br,
                         @RequestParam Long flightId, @RequestParam Long passengerId, @RequestParam double paidAmount) {
        if (br.hasErrors()) return "reservations/form";
        reservationService.create(flightId, passengerId, paidAmount);
        return "redirect:/reservations";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) Long reservationId, Model model) {
        model.addAttribute("reservations", reservationService.searchByReservationNumber(reservationId));
        model.addAttribute("reservationId", reservationId);
        return "reservations/list";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Reservation existing = reservationService.list().stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
        if (existing != null) {
            reservationService.findBetween(existing.getCreatedAt().minusYears(10), existing.getCreatedAt().plusYears(10)); // placeholder logic
        }
        return "redirect:/reservations";
    }
}
