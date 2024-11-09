package com.thinker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "college_id", nullable = false)
    private College college;

    @NotNull(message = "Roll number is required")
    @Column(name = "roll_no", unique = true, nullable = false)
    private Long rollNo;

    @NotBlank(message = "Qualification is required")
    @Column(nullable = false)
    private String qualification;

    @NotBlank(message = "Course is required")
    @Column(nullable = false)
    private String course;

    @NotNull(message = "Year is required")
    @Min(value = 1, message = "Year must be positive")
    @Max(value = 4, message = "Year cannot be more than 4")
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;

    @Column(name = "hall_ticket_no", unique = true)
    private Long hallTicketNo;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^\\+?[1-9][0-9]{7,14}$", message = "Invalid phone number")
    @Column(name = "phone_number")
    private String phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}