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
    private int dev_id;

    private String dev_name;

    @ManyToMany
    private List<Project> projects;

    @OneToMany(mappedBy = "bug_id")
    private List<Bugs> bugs_assigned;

}
