package com.example.nextbackend.service;

import com.example.nextbackend.exception.ResourceNotFoundException;
import com.example.nextbackend.model.Family;
import com.example.nextbackend.model.FamilyMember;
import com.example.nextbackend.model.Plan;
import com.example.nextbackend.repository.Family_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class Family_service_Imple implements Family_service{

    @Autowired
    private Family_repository family_repository;

    @Autowired
    private Plan_service_Imple plan_service_Imple;

    @Override
    public Family createFamily(Family family) {
        return family_repository.save(family);
    }

    @Override
    public Family getByusername(String username) {
        return new ResponseEntity<Family>(family_repository.findByusername(username) , HttpStatus.OK).getBody();
    }

    @Override
    public Family updateCustomerByID(long id, long pId) {
        Plan plan = plan_service_Imple.plansById(pId);
        Family familyUpdate = family_repository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User not Exit with id"+id));
                familyUpdate.setPlan(plan);
                family_repository.save(familyUpdate);
        return familyUpdate;
    }

    @Override
    public Family addFamilyMemberToFamily(FamilyMember fm, String username) {

        Family familyUpdate = family_repository.findByusername(username);

        familyUpdate.getFamily_members().add(fm);
        family_repository.save(familyUpdate);
        return familyUpdate;
    }
}
