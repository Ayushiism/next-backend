package com.example.nextbackend.controller;

import com.example.nextbackend.model.Family;
import com.example.nextbackend.model.FamilyMember;
import com.example.nextbackend.service.FamilyMemberServiceImplementation;
import com.example.nextbackend.service.FamilyServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("api/family")
public class FamilyController {

    @Autowired
    private FamilyServiceImplementation familyServiceImplementation;
    @Autowired
    private FamilyMemberServiceImplementation familyMemberServiceImplementation;
    @PostMapping("/addFamilyMember")
    public Map<String, Object> putFamilyMember(@RequestBody Map<String, String> json) {
        Map<String, Object> map = new HashMap<String,Object>();
        Family fam =  familyServiceImplementation.getByusername(json.get("username"));

        if(fam == null) map.put("error","UserName doesn't exist!!");
        else{
            FamilyMember fm = new FamilyMember();

            fm.setDob(json.get("dob"));
            fm.setGender(json.get("gender"));
            fm.setFirst_name(json.get("first_name"));
            fm.setLast_name(json.get("last_name"));
            map.put("data" ,familyServiceImplementation.addFamilyMemberToFamily(fm, json.get("username")));
            map.put("message", "User added Successfully!!");
        }
        return map;
    }

    @PutMapping("/updatePlanByID/{id}/{pid}")
    public ResponseEntity<Object> updatePlanByID(@PathVariable long id , @PathVariable long pid){
        return ResponseEntity.ok(familyServiceImplementation.updateCustomerByID(id , pid));
    }

    @DeleteMapping("/deleteFamilyMemberByID/{id}")
    public ResponseEntity<FamilyMember> deleteFamilyMemberByID(@PathVariable long id){
        return new ResponseEntity<>(familyMemberServiceImplementation.deleteFamilyMember(id) , HttpStatus.NO_CONTENT);
    }
}
