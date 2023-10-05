package com.example.nextbackend.service;

import com.example.nextbackend.repository.IncludeItems_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncludeItems_sevice_Imple implements com.example.nextbackend.service.IncludeItems_service{
    @Autowired
    private IncludeItems_repository includeItemsRepository;
    @Autowired
    private Plan_service_Imple packages_service_imple;

}
