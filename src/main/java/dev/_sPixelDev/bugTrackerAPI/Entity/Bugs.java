package dev._sPixelDev.bugTrackerAPI.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "bugs")
@Getter
@Setter
public class Bugs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bugId;

    @Getter
    @Setter
    private String bugName;

    @Getter
    @Setter
    private String bugDescription;

    @Getter
    @Setter
    private LocalDateTime timeCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project projectId;

    private Enum priority;
}
