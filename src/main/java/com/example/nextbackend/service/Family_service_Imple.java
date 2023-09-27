package com.example.nextbackend.service;

import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.model.Family;
import com.example.nextbackend.repository.Customer_repository;
import com.example.nextbackend.repository.Family_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Family_service_Imple implements Family_service{

    @Autowired
    private Family_repository family_repository;


    @Override
    public Family createFamily(Family family) {
        return family_repository.save(family);
    }

    @Override
    public Family getByusername(String username) {
        return new ResponseEntity<Family>(family_repository.findByusername(username) , HttpStatus.OK).getBody();
    }
}
