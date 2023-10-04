package com.example.nextbackend.service;

import com.example.nextbackend.exception.ResourceNotFoundException;
import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.model.Packages;
import com.example.nextbackend.repository.Packages_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Packages_service_Imple implements com.example.nextbackend.service.Packages_service{

    @Autowired
    private Packages_repository packagesRepository;
    @Override
    public List<Packages> allPlans() {
        return packagesRepository.findAll();
    }

    @Override
    public Packages plansById(long id) {
        Packages packages = packagesRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not Exit with id "+id));
        return packages;
    }

    @Override
    public Packages putPlans(Packages plans) {
        return packagesRepository.save(plans);
    }
}
