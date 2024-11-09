package com.thinker.controller;

import com.thinker.entity.Certificate;
import com.thinker.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/v1/certificates")
@CrossOrigin(origins = "*")
public class CertificateController {

    private static final Logger logger = LoggerFactory.getLogger(CertificateController.class);

    @Autowired
    private CertificateService certificateService;

    @GetMapping
    public ResponseEntity<List<Certificate>> getAllCertificates() {
        logger.info("Fetching all certificates");
        return ResponseEntity.ok(certificateService.getAllCertificates());
    }

    @PostMapping
    public ResponseEntity<Certificate> createCertificate(@RequestBody Certificate certificate) {
        logger.info("Creating new certificate for year: {}", certificate.getYear());
        return ResponseEntity.ok(certificateService.saveCertificate(certificate));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> getCertificateById(@PathVariable Long id) {
        logger.info("Fetching certificate with id: {}", id);
        return certificateService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Certificate> updateCertificate(@PathVariable Long id, @RequestBody Certificate certificateDetails) {
        logger.info("Updating certificate with id: {}", id);
        return certificateService.findById(id)
                .map(certificate -> {
                    certificate.setYear(certificateDetails.getYear());
                    certificate.setName(certificateDetails.getName());
                    certificate.setDescription(certificateDetails.getDescription());
                    return ResponseEntity.ok(certificateService.saveCertificate(certificate));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCertificate(@PathVariable Long id) {
        logger.info("Deleting certificate with id: {}", id);
        return certificateService.findById(id)
                .map(certificate -> {
                    certificateService.deleteCertificate(id);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}