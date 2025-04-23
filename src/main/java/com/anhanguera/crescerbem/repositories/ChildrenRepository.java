package com.anhanguera.crescerbem.repositories;

import com.anhanguera.crescerbem.entities.Children;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChildrenRepository extends JpaRepository<Children, UUID> {
}
