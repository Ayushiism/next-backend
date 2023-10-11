package com.example.nextbackend.repository;

import com.example.nextbackend.model.CustomerDetails;
import com.example.nextbackend.model.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Long>  {
    @Query("SELECT u FROM CustomerDetails u WHERE u.family.family_id=?1")
    List<CustomerDetails> findCustomersByFamilyId(long family_id);
}