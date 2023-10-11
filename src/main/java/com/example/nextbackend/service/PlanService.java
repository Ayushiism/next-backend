package com.example.nextbackend.service;

import com.example.nextbackend.model.IncludeItems;
import com.example.nextbackend.model.Plan;

import java.util.List;

public interface PlanService {
    List<Plan> allPlans();
    Plan plansById(long id);

    Plan createPlans(Plan plans);

    Plan putDetails(long pid, String details);

    IncludeItems deleteDetails(long details_id);
}
