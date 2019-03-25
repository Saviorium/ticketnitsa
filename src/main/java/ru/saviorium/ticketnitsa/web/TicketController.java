package ru.saviorium.ticketnitsa.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.saviorium.ticketnitsa.dao.TicketRepository;
import ru.saviorium.ticketnitsa.model.Project;
import ru.saviorium.ticketnitsa.model.Ticket;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepo;

    @GetMapping("/create")
    public String createTicketForm(Model model) {
        List<Project> projects = Arrays.asList(
                new Project("JAVA", "Java dev", ""),
                new Project("MUSIC", "Learning music", ""),
                new Project("BUY", "Purchases", "")
        );

        model.addAttribute("projects", projects);
        model.addAttribute("ticket", new Ticket());
        return "createTicketForm";
    }

    @PostMapping("/create")
    public String saveTicket(@Valid Ticket ticket, Errors errors) {
        log.info("Create ticket: " + ticket);
        if (errors.hasErrors()) {
            return "createTicketForm";
        }
        ticketRepo.save(ticket);

        return "redirect:/";
    }
}
