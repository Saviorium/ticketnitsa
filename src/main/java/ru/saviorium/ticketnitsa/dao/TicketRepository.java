package ru.saviorium.ticketnitsa.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.saviorium.ticketnitsa.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {}
