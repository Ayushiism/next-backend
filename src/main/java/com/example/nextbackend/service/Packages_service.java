package com.example.nextbackend.service;

import com.example.nextbackend.model.Packages;

import java.util.List;

public interface Packages_service {
    List<Packages> allPlans();
    Packages plansById(long id);

    Packages putPlans(Packages plans);

    Packages putDetails(long pid, String details);
}
