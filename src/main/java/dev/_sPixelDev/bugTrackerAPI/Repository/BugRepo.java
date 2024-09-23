package dev._sPixelDev.bugTrackerAPI.Repository;

import dev._sPixelDev.bugTrackerAPI.Entity.Bugs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepo extends JpaRepository<Bugs, Integer> {
}
