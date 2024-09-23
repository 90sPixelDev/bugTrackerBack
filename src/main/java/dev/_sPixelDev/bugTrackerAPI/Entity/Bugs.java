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
    private Integer bug_id;

    private String bug_name;

    private String bug_description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project_id;

    private Enum priority;
}
