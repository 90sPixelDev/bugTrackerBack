package dev._sPixelDev.bugTrackerAPI.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bugs")
@Getter
@Setter
public class Bugs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bug_id;

    private String bug_name;

    private String bug_description;

    @ManyToOne()
    private int project_id;

    private Enum priority;
}
