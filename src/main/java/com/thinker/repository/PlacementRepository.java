package com.thinker.repository;

import com.thinker.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlacementRepository extends JpaRepository<Placement, Long> {
    List<Placement> findByCollegeId(Long collegeId);
    List<Placement> findByYear(Integer year);
    List<Placement> findByCompany(String company);
}