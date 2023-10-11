package com.example.nextbackend.controller;

import com.example.nextbackend.model.IncludeItems;
import com.example.nextbackend.model.Plan;
import com.example.nextbackend.service.PlanServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("api/plan")
public class PlanController {

    @Autowired
    private PlanServiceImplementation planServiceImplementation;

    @PostMapping("/createPlans")
    public Plan addPlans(@RequestBody Plan plans) {
        return planServiceImplementation.createPlans(plans);
    }

    @GetMapping("/allPlans")
    public List<Plan> getallPans() {
        return planServiceImplementation.allPlans();
    }

    @GetMapping("/PlanById/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable long id) {
        return ResponseEntity.ok(planServiceImplementation.plansById(id));
    }

    @PostMapping("/putDetails")
    public ResponseEntity<Plan> putDetails(@RequestBody Map<String, String> json) {

        return ResponseEntity.ok(planServiceImplementation.putDetails(Long.parseLong(json.get("id")), json.get("info")));
    }
    @DeleteMapping("/deleteDetails/{details_id}")
    public ResponseEntity<IncludeItems> deleteDetails(@PathVariable long details_id) {
        return ResponseEntity.ok(planServiceImplementation.deleteDetails(details_id));
    }
}
