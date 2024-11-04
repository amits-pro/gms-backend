package com.bv.gms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.bv.gms.entities.GrievanceHistory;

public interface GrievanceHistoryRepository extends JpaRepository<GrievanceHistory, Long> {
	
    @Query("SELECT e FROM GrievanceHistory e WHERE e.grievanceId = :grievanceId ORDER BY e.modifiedOn")
	List<GrievanceHistory> getByGrievanceId(Long grievanceId);

}

