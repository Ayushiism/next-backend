package com.example.nextbackend.service;

import com.example.nextbackend.exception.ResourceNotFoundException;
import com.example.nextbackend.model.CustomerDetails;
import com.example.nextbackend.model.IncludeItems;
import com.example.nextbackend.model.Plan;
import com.example.nextbackend.repository.IncludeItemsRepository;
import com.example.nextbackend.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImplementation implements PlanService {

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private IncludeItemsRepository includeItemsRepository;
    @Override
    public List<Plan> allPlans() {
        return planRepository.findAll();
    }

    @Override
    public Plan plansById(long id) {
        Plan pk = planRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not Exit with id "+id));
        return pk;
    }

    @Override
    public Plan createPlans(Plan plans) {
        return planRepository.save(plans);
    }

    @Override
    public  Plan putDetails(long pid, String details) {
        Plan plan = plansById(pid);
        IncludeItems item = new IncludeItems();
        item.setInfo(details);
//        item.setPackage(Package);
        plan.getIncludeItems().add(item);
        return planRepository.save(plan);
    }

    @Override
    public IncludeItems deleteDetails(long details_id){
        IncludeItems includedItem = includeItemsRepository.findById(details_id).orElseThrow(() ->
                new ResourceNotFoundException("Invalid details id: " + details_id));


        includeItemsRepository.delete(includedItem);

        return includedItem;
    }
}
