package com.anhanguera.crescerbem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_consultations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consultation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "consultation_id")
    private UUID consultationId;

    @Column(name = "doctor_name")
    private String doctorName;

    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "date_consult")
    private LocalDateTime dateConsult;


    public Consultation(String doctorName, String hospitalName, LocalDateTime dateConsult) {
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.dateConsult = dateConsult;
    }
}
