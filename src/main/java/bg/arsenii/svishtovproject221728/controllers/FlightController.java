package bg.arsenii.svishtovproject221728.controllers;


import bg.arsenii.svishtovproject221728.entities.Flight;
import bg.arsenii.svishtovproject221728.services.FlightService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("flights", flightService.list());
        return "flights/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flights/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Flight flight, BindingResult br) {
        if (br.hasErrors()) return "flights/form";
        flightService.create(flight);
        return "redirect:/flights";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String flightNumber, Model model) {
        model.addAttribute("flights", flightService.findByFlightNumber(flightNumber));
        model.addAttribute("flightNumber", flightNumber);
        return "flights/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        flightService.delete(id);
        return "redirect:/flights";
    }
}