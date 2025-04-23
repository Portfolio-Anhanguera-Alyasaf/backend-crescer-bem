package com.anhanguera.crescerbem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_vaccines")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vaccine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "vaccine_id")
    private UUID vaccineId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}
