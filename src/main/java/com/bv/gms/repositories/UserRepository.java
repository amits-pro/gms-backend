package com.bv.gms.repositories;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bv.gms.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
	User findByEmail(String email);
    
    @Query("SELECT e FROM User e WHERE e.userId = :userId")
    Optional<User> findByUserId(String userId);

    @Query("SELECT e FROM User e WHERE e.department = :grievanceType")
    List<User> getUserMappedToGrievanceType(String grievanceType);

    
}
