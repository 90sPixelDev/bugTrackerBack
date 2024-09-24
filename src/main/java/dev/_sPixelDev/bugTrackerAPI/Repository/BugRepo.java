package dev._sPixelDev.bugTrackerAPI.Repository;

import dev._sPixelDev.bugTrackerAPI.Entity.Bugs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BugRepo extends JpaRepository<Bugs, Integer> {

    @Query(value = "SELECT bug_id, bug_name, bug_description, priority, time_created, projects.project_id FROM bugs LEFT JOIN projects ON bugs.project_id = projects.project_id", nativeQuery = true)
    public Page<Bugs> getAllBugsListQry (Pageable pageOptions);
}
