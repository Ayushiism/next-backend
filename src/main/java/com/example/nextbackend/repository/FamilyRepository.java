package com.example.nextbackend.repository;

import com.example.nextbackend.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {
    Family findByusername(String username);
}
