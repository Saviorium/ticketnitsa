package ru.saviorium.ticketnitsa.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//FIXME: random ids - should start at 1 for each project
    private long id;
    @NotNull
    @NotEmpty
    private String project;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInProject;
    private Date createdAt;
    private Status status;
    private String name;
    private String description;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }

    public enum Status {
        NEW, IN_PROGRESS, WAITING, CLOSED
    }
}
