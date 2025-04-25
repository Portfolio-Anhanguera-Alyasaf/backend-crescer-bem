package com.anhanguera.crescerbem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tb_child_vaccine")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChildVaccine extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "child_vaccine_id")
    private UUID childVaccineId;

    @ManyToOne
    @JoinColumn(name = "children_id")
    private Children child;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    @Column(name = "applied")
    private boolean applied;

    public ChildVaccine(Children child, Vaccine vaccine, boolean applied) {
        this.child = child;
        this.vaccine = vaccine;
        this.applied = applied;
    }
}
