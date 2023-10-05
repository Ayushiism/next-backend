package com.example.nextbackend.service;

import com.example.nextbackend.model.Plan;

import java.util.List;

public interface Plan_service {
    List<Plan> allPlans();
    Plan plansById(long id);

    Plan putPlans(Plan plans);

    Plan putDetails(long pid, String details);
}
