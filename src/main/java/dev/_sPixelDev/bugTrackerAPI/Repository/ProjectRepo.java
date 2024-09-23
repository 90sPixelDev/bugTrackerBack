package dev._sPixelDev.bugTrackerAPI.Repository;

import dev._sPixelDev.bugTrackerAPI.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Integer> {
}
