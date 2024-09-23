package dev._sPixelDev.bugTrackerAPI.Repository;

import dev._sPixelDev.bugTrackerAPI.Entity.Developers;
import lombok.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DeveloperRepo extends JpaRepository<Developers, Integer> {
}
