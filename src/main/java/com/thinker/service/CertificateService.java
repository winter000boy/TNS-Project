package com.example.demo.service;

import com.example.demo.entity.Certificate;
import com.example.demo.repository.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository certificateRepository;

    public List<Certificate> getAllCertificates() {
        return certificateRepository.findAll();
    }

    public Certificate saveCertificate(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    public Optional<Certificate> findById(Long id) {
        return certificateRepository.findById(id);
    }

    public void deleteById(Long id) {
        certificateRepository.deleteById(id);
    }
}
