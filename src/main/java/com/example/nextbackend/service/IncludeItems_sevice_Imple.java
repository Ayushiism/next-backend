package com.example.nextbackend.service;

import com.example.nextbackend.exception.ResourceNotFoundException;
import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.model.IncludeItems;
import com.example.nextbackend.model.Packages;
import com.example.nextbackend.repository.IncludeItems_repository;
import com.example.nextbackend.repository.Packages_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncludeItems_sevice_Imple implements com.example.nextbackend.service.IncludeItems_service{
    @Autowired
    private IncludeItems_repository includeItemsRepository;
    @Autowired
    private Packages_service_Imple packages_service_imple;

}
