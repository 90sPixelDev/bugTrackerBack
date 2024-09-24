package dev._sPixelDev.bugTrackerAPI.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "developers")
@Getter
@Setter
public class Developers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer devId;

    private String devName;

    @ManyToMany(mappedBy = "devs", fetch = FetchType.LAZY)
    private List<Project> projects;
}
