package com.thinker.service;

import com.thinker.entity.College;
import com.thinker.repository.CollegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    public College saveCollege(College college) {
        return collegeRepository.save(college);
    }

    public Optional<College> findById(Long id) {
        return collegeRepository.findById(id);
    }

    public void deleteCollege(Long id) {
        collegeRepository.deleteById(id);
    }
}