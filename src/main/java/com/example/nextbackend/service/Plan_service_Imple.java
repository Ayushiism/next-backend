package com.example.nextbackend.service;
import com.example.nextbackend.exception.ResourceNotFoundException;
import com.example.nextbackend.model.IncludeItems;
import com.example.nextbackend.model.Plan;
import com.example.nextbackend.repository.Plan_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Plan_service_Imple implements Plan_service {

    @Autowired
    private Plan_repository plan_repository;
    @Override
    public List<Plan> allPlans() {
        return plan_repository.findAll();
    }

    @Override
    public Plan plansById(long id) {
        Plan pk = plan_repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not Exit with id "+id));
        return pk;
    }

    @Override
    public Plan putPlans(Plan plans) {
        return plan_repository.save(plans);
    }

    @Override
    public  Plan putDetails(long pid, String details) {
        Plan plan = plansById(pid);
        IncludeItems item = new IncludeItems();
        item.setInfo(details);
//        item.setPackage(Package);
        plan.getIncludeItems().add(item);
        return plan_repository.save(plan);
    }
}
