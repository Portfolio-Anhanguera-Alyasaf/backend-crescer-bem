package com.anhanguera.crescerbem.repositories;

import com.anhanguera.crescerbem.entities.ChildVaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChildVaccineRepository extends JpaRepository<ChildVaccine, UUID> {
    @Query("SELECT cv FROM ChildVaccine cv WHERE cv.child.childrenId = :idChild")
    Optional<ChildVaccine> findByChildId(@Param("idChild") UUID idChild);
}
