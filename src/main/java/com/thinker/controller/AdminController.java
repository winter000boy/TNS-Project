package com.thinker.controller;

import com.thinker.entity.Admin;
import com.thinker.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admins")
@CrossOrigin(origins = "*")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping
    public ResponseEntity<List<Admin>> getAllAdmins() {
        logger.info("Fetching all admins");
        return ResponseEntity.ok(adminService.getAllAdmins());
    }

    @PostMapping
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        logger.info("Creating new admin: {}", admin.getName());
        return ResponseEntity.ok(adminService.saveAdmin(admin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long id) {
        logger.info("Fetching admin with id: {}", id);
        return adminService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long id, @RequestBody Admin adminDetails) {
        logger.info("Updating admin with id: {}", id);
        return adminService.findById(id)
                .map(admin -> {
                    admin.setName(adminDetails.getName());
                    admin.setEmail(adminDetails.getEmail());
                    admin.setUsername(adminDetails.getUsername());
                    return ResponseEntity.ok(adminService.saveAdmin(admin));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Long id) {
        logger.info("Deleting admin with id: {}", id);
        return adminService.findById(id)
                .map(admin -> {
                    adminService.deleteAdmin(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}