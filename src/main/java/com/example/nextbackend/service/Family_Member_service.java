package com.example.nextbackend.service;

import com.example.nextbackend.model.FamilyMember;

public interface Family_Member_service {

    FamilyMember createFamilyMember(FamilyMember fm);
    FamilyMember deleteFamilyMember(long id);
    FamilyMember getFamilyMemberById(long id);

}
