package com.example.nextbackend.repository;


import com.example.nextbackend.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Family_repository extends JpaRepository<Family, Long> {
    Family findByusername(String username);
}
