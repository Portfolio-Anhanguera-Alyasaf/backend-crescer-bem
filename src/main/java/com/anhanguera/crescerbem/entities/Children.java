package com.anhanguera.crescerbem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_children")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Children extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "children_id")
    private UUID childrenId;

    @Column(name = "name")
    private String name;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "consultation_id")
    private Set<Consultation> consultations = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "child")
    private Set<ChildVaccine> childVaccines = new HashSet<>();

    @PostPersist
    public void postPersist() {
        this.setCreatedBy(this.getUser().getEmail());
    }

    @PostUpdate
    public void postUpdate() {
        this.setUpdatedBy(this.getUser().getEmail());
    }
}
