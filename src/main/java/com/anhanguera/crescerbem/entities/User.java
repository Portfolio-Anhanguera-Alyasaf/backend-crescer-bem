package com.anhanguera.crescerbem.entities;

import com.anhanguera.crescerbem.entities.enums.Kind;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "children_number")
    private int childrenNumber;

    @Column(name = "kind")
    @Enumerated(EnumType.STRING)
    private Kind kind;

    public User(String email, String password, String name, int childrenNumber, Kind kind) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.childrenNumber = childrenNumber;
        this.kind = kind;
    }

    public User(String email, String name, int childrenNumber, Kind kind) {
        this.email = email;
        this.name = name;
        this.childrenNumber = childrenNumber;
        this.kind = kind;
    }

    @PrePersist
    public void prePersist() {
        this.setCreatedBy(this.getEmail());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdatedBy(this.getEmail());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }
}
