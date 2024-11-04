package com.bv.gms.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.bv.gms.entities.Grievance;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
	
    @Query("SELECT e FROM Grievance e WHERE e.raisedBy.id = :raisedBy ORDER BY e.updatedOn")
    List<Grievance> getByUserId(Long raisedBy);

    
    @Query("SELECT e FROM Grievance e WHERE e.assignedTo.id = :assignedToId")
    List<Grievance> getAssignedToUserId(@Param("assignedToId") Long assignedToId);
}

