package bg.arsenii.svishtovproject221728.controllers;

import bg.arsenii.svishtovproject221728.entities.Passenger;
import bg.arsenii.svishtovproject221728.services.PassengerService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengerController {
    private final PassengerService passengerService;


    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }


    @GetMapping
    public String list(Model model) {
        model.addAttribute("passengers", passengerService.list());
        return "passengers/list";
    }


    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("passenger", new Passenger());
        return "passengers/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Passenger passenger, BindingResult br) {
        if (br.hasErrors()) return "passengers/form";
        passengerService.create(passenger);
        return "redirect:/passengers";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("passengers", passengerService.searchByName(name));
        model.addAttribute("name", name);
        return "passengers/list";
    }

    @GetMapping("/egn")
    public String searchByEgn(@RequestParam String egn, Model model) {
        model.addAttribute("passenger", passengerService.findByEgn(egn));
        return "passengers/details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        passengerService.delete(id);
        return "redirect:/passengers";
    }
}
