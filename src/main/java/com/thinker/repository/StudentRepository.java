package com.thinker.repository;

import com.thinker.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByCollegeId(Long collegeId);
    Student findByRollNo(Long rollNo);
    Student findByEmail(String email);
}