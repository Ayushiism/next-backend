package com.example.nextbackend.service;

import com.example.nextbackend.model.Family;
import com.example.nextbackend.model.FamilyMember;

public interface FamilyService {
    Family createFamily(Family family);
    Family getByusername(String username);
    Family updateCustomerByID(long id ,long pid);
    Family addFamilyMemberToFamily(FamilyMember fm, String username);
}
