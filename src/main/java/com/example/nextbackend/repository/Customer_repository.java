package com.example.nextbackend.repository;


import com.example.nextbackend.model.Customer_details;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Customer_repository extends JpaRepository<Customer_details, Long> {
}
