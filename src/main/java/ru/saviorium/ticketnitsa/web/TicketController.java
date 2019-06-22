package ru.saviorium.ticketnitsa.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.saviorium.ticketnitsa.dao.ProjectRepository;
import ru.saviorium.ticketnitsa.dao.TicketRepository;
import ru.saviorium.ticketnitsa.model.Project;
import ru.saviorium.ticketnitsa.model.Ticket;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepo;
    @Autowired
    private ProjectRepository projectRepo;

    @GetMapping("/create")
    public String createTicketForm(Model model) {
        List<Project> projects = new LinkedList<>(projectRepo.findAll());
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

        return "redirect:/ticket/showall/" + ticket.getProject();
    }

    @GetMapping("/showall/{projectId}")
    public String showTicketsByProjectId(@PathVariable String projectId, Model model) {
        List<Ticket> tickets = ticketRepo.findByProject(projectId);
        model.addAttribute("projectid", projectId);
        model.addAttribute("tickets", tickets);
        return "ticketsListView";
    }
}
