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
import ru.saviorium.ticketnitsa.model.Project;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepo;

    @GetMapping("/create")
    public String projectCreateForm(Model model) {
        model.addAttribute(new Project());
        return "createProjectForm";
    }

    @PostMapping("/create")
    public String saveProject(@Valid Project project, Errors errors) {
        log.info("Create ticket: " + project);
        if (errors.hasErrors()) {
            return "createTicketForm";
        }
        projectRepo.save(project);

        return "redirect:/ticket/showall/" + project.getId();
    }

    @GetMapping("/all")
    public String showAll(Model model) {
        List<Project> projects = projectRepo.findAll();
        model.addAttribute("projects", projects);
        return "projectsView";
    }
}
