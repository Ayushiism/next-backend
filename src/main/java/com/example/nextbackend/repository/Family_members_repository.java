package com.example.nextbackend.repository;

import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.model.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Family_members_repository extends JpaRepository<FamilyMember, Long>{

    @Query("SELECT u FROM FamilyMember u WHERE u.family.family_id=?1")
    List<Customer_details> findFamilyMembersByFamilyId(long family_id);
}
