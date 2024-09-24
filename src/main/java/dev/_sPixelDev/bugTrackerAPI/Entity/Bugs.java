package dev._sPixelDev.bugTrackerAPI.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "bugs")
public class Bugs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
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

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project", nullable = false)
    private Project project;

    @Getter
    @Setter
    @NonNull
    private String priority;
}
