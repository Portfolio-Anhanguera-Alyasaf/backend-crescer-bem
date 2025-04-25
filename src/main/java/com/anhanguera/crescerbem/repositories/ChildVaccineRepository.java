package com.anhanguera.crescerbem.repositories;

import com.anhanguera.crescerbem.entities.ChildVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChildVaccineRepository extends JpaRepository<ChildVaccine, UUID> {
}
