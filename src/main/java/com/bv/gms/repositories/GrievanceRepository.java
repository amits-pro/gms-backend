package com.bv.gms.repositories;

import java.time.LocalDate;
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

    @Query("SELECT g.raisedOn, COUNT(g) as grievanceCount, g.grievanceType, g.status FROM Grievance g "
    		+ " WHERE g.raisedOn BETWEEN :fromDate AND :toDate GROUP BY g.raisedOn, g.grievanceType, g.status ORDER BY g.raisedOn")
    List<Object[]> findGrievancesDataInDateRange(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);


    @Query("SELECT g.raisedOn, COUNT(g) as grievanceCount, g.grievanceType, g.status FROM Grievance g "
    		+ " GROUP BY g.raisedOn, g.grievanceType, g.status ORDER BY g.raisedOn")
    List<Object[]> findOverallGrievancesData();

    
    
    @Query("SELECT g.raisedOn, COUNT(g) as grievanceCount, g.grievanceType, g.status FROM Grievance g "
    		+ " WHERE g.raisedOn BETWEEN :fromDate AND :toDate AND g.grievanceType = :grievanceType GROUP BY g.raisedOn, g.grievanceType, g.status ORDER BY g.raisedOn")
    List<Object[]> findGrievancesDataInDateRangeByDepartment(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate, String grievanceType);


    @Query("SELECT g.raisedOn, COUNT(g) as grievanceCount, g.grievanceType, g.status FROM Grievance g "
    		+ " WHERE g.grievanceType = :grievanceType GROUP BY g.raisedOn, g.grievanceType, g.status ORDER BY g.raisedOn")
    List<Object[]> findOverallGrievancesDataByDepartment(String grievanceType);

    @Query("SELECT e FROM Grievance e WHERE e.grievanceType = :grievanceType")
    List<Grievance> findGrievancesByGrievanceType(String grievanceType);

    @Query("SELECT g.raisedOn, COUNT(g) as grievanceCount, g.grievanceType, g.status FROM Grievance g "
    		+ "WHERE g.raisedBy.id = :userId GROUP BY g.raisedOn, g.grievanceType, g.status ORDER BY g.raisedOn")
    List<Object[]> findOverallGrievancesDataForUser(Long userId);

    
}

