package com.anhanguera.crescerbem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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
    private LocalDateTime birthday;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinTable(
            name = "tb_child_consultations",
            joinColumns = @JoinColumn(name = "children_id"),
            inverseJoinColumns = @JoinColumn(name = "consultation_id")
    )
    private Set<Consultation> consultations = new HashSet<>();

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ChildVaccine> childVaccines = new HashSet<>();

    public Children(String name, LocalDateTime birthday, double weight, double height, User user) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
        this.user = user;
    }

    public Children(String name, LocalDateTime birthday, double weight, double height) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.height = height;
    }
}
