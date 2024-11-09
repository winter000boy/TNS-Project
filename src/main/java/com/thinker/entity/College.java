package com.thinker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "colleges")
public class College {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "College name is required")
    @Column(name = "college_name", nullable = false)
    private String collegeName;

    @NotBlank(message = "Location is required")
    @Column(nullable = false)
    private String location;

    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Invalid phone number")
    @Column(name = "contact_number")
    private String contactNumber;

    @Email(message = "Invalid email address")
    @Column(unique = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private User collegeAdmin;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    private List<Student> students;

    @OneToMany(mappedBy = "college", cascade = CascadeType.ALL)
    private List<Certificate> certificates;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}