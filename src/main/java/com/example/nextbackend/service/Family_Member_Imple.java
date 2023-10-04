package com.example.nextbackend.service;

import com.example.nextbackend.exception.ResourceNotFoundException;
import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.model.FamilyMember;
import com.example.nextbackend.repository.Family_members_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Family_Member_Imple implements Family_Member_service{

    @Autowired
    private Family_members_repository family_member_repository;

    @Override
    public FamilyMember createFamilyMember(FamilyMember fm){
        return family_member_repository.save(fm);
    }

    @Override
    public FamilyMember deleteFamilyMember(long id){
        FamilyMember fm = family_member_repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid ID: "+id));
        family_member_repository.delete(fm);
        return fm;
    }

    @Override
    public FamilyMember getFamilyMemberById(long id){
        return family_member_repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid Family Member Id: "+id));
    }


}
