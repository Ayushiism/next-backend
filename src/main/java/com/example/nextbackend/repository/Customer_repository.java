package com.example.nextbackend.repository;


import com.example.nextbackend.model.Customer_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface Customer_repository extends JpaRepository<Customer_details, Long> {

    @Query("SELECT u FROM Customer_details u WHERE u.family.family_id=?1")
    List<Customer_details> findCustomersByFamilyId(long family_id);
}
