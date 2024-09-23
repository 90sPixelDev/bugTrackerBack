package dev._sPixelDev.bugTrackerAPI.Repository;

import dev._sPixelDev.bugTrackerAPI.Entity.Developers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepo extends JpaRepository<Developers, Integer> {

    @Query(value = "SELECT * FROM developers", nativeQuery = true)
    public Page<Developers> getAllDevsOptimized(Pageable pageOptions);

    @Query(value = "SELECT * FROM developers WHERE dev_id = :devId", nativeQuery = true)
    public Developers getDevById(@Param("devId")int devId);
}
