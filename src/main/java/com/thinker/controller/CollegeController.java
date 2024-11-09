package com.thinker.controller;

import com.thinker.entity.College;
import com.thinker.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/colleges")
@CrossOrigin(origins = "*")
public class CollegeController {

    private static final Logger logger = LoggerFactory.getLogger(CollegeController.class);

    @Autowired
    private CollegeService collegeService;

    @GetMapping
    public ResponseEntity<List<College>> getAllColleges() {
        logger.info("Fetching all colleges");
        return ResponseEntity.ok(collegeService.getAllColleges());
    }

    @PostMapping
    public ResponseEntity<College> createCollege(@RequestBody College college) {
        logger.info("Creating new college: {}", college.getCollegeName());
        return ResponseEntity.ok(collegeService.saveCollege(college));
    }

    @GetMapping("/{id}")
    public ResponseEntity<College> getCollegeById(@PathVariable Long id) {
        logger.info("Fetching college with id: {}", id);
        return collegeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<College> updateCollege(@PathVariable Long id, @RequestBody College collegeDetails) {
        logger.info("Updating college with id: {}", id);
        return collegeService.findById(id)
                .map(college -> {
                    college.setCollegeName(collegeDetails.getCollegeName());
                    college.setLocation(collegeDetails.getLocation());
                    college.setContactNumber(collegeDetails.getContactNumber());
                    college.setEmail(collegeDetails.getEmail());
                    return ResponseEntity.ok(collegeService.saveCollege(college));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCollege(@PathVariable Long id) {
        logger.info("Deleting college with id: {}", id);
        return collegeService.findById(id)
                .map(college -> {
                    collegeService.deleteCollege(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}