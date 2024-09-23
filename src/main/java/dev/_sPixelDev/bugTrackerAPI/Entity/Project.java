package dev._sPixelDev.bugTrackerAPI.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "projects")
@EntityListeners(AuditingEntityListener.class)
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
    @OneToMany(fetch = FetchType.LAZY)
    private List<Developers> developers;
}
