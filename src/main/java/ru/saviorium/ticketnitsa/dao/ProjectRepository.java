package ru.saviorium.ticketnitsa.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.saviorium.ticketnitsa.model.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, String> {}
