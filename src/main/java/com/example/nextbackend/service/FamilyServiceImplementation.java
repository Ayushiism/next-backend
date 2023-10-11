package com.example.nextbackend.service;

import com.example.nextbackend.exception.ResourceNotFoundException;
import com.example.nextbackend.model.Family;
import com.example.nextbackend.model.FamilyMember;
import com.example.nextbackend.model.Plan;
import com.example.nextbackend.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class FamilyServiceImplementation implements FamilyService{

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private PlanServiceImplementation planServiceImplementation;

    @Override
    public Family createFamily(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Family getByusername(String username) {
        return new ResponseEntity<Family>(familyRepository.findByusername(username) , HttpStatus.OK).getBody();
    }

    @Override
    public Family updateCustomerByID(long id, long pId) {
        Plan plan = planServiceImplementation.plansById(pId);
        Family familyUpdate = familyRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User not Exit with id"+id));
        familyUpdate.setPlan(plan);
        familyRepository.save(familyUpdate);
        return familyUpdate;
    }

    @Override
    public Family addFamilyMemberToFamily(FamilyMember fm, String username) {

        Family familyUpdate = familyRepository.findByusername(username);

        familyUpdate.getFamily_members().add(fm);
        familyRepository.save(familyUpdate);
        return familyUpdate;
    }
}


