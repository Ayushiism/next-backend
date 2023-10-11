package com.example.nextbackend.service;

import com.example.nextbackend.model.FamilyMember;

public interface FamilyMemberService {
    FamilyMember createFamilyMember(FamilyMember fm);
    FamilyMember deleteFamilyMember(long id);
    FamilyMember getFamilyMemberById(long id);
}
