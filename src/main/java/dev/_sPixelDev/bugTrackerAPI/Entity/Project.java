package dev._sPixelDev.bugTrackerAPI.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Integer projectId;

    @Getter
    @Setter
    private String projectTitle;

    @Getter
    @Setter
    @NonNull
    @CreatedDate
    private LocalDate dateCreated;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "projects_devs",
            joinColumns = {
                    @JoinColumn(name = "dev_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id")
            }
    )
    private List<Developers> devs;
}
