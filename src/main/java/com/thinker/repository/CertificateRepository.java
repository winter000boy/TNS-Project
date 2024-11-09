package com.thinker.repository;

import com.thinker.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    List<Certificate> findByCollegeId(Long collegeId);
    List<Certificate> findByYear(Integer year);
}