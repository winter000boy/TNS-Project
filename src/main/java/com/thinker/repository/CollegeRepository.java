package com.thinker.repository;

import com.thinker.entity.College;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {
    College findByCollegeName(String collegeName);
    College findByEmail(String email);
}