package com.example.nextbackend.controller;
import com.example.nextbackend.dto.FamilyResponse;
import com.example.nextbackend.model.CustomerDetails;
import com.example.nextbackend.model.Family;
import com.example.nextbackend.model.FamilyMember;
import com.example.nextbackend.model.Plan;
import com.example.nextbackend.service.CustomerServiceImplementation;
import com.example.nextbackend.service.FamilyMemberServiceImplementation;
import com.example.nextbackend.service.FamilyServiceImplementation;
import com.example.nextbackend.service.PlanServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private CustomerServiceImplementation customerServiceImplementation;

    @Autowired
    private FamilyServiceImplementation familyServiceImplementation;



    @PostMapping("/createCustomer")
    public Map<String, Object> createUser(@RequestBody CustomerDetails customer_details) {
//        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));

        Map<String, Object> response = new HashMap<String, Object>();

        Family family = familyServiceImplementation.getByusername(customer_details.getFamily().getUsername());
        if (family==null){
            response.put("error", false);
        }else{
            response.put("error", true);
            response.put("msg", "username already exists");
            return response;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String result = encoder.encode(customer_details.getFamily().getPassword());
        customer_details.getFamily().setPassword(result);
        response.put("userDetail", customerServiceImplementation.createCustomer(customer_details));
        return response;
    }

//    @PostMapping("/putFamily")
//    public Family createFamily(@RequestBody Family family) {
////        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));
//
//        return familyServiceImple.createFamily(family);
//    }

    @GetMapping("/checkUsername/{username}")
    public FamilyResponse CheckUsernamw(@PathVariable String username) {
//        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));
         FamilyResponse familyResponse = new FamilyResponse();
         Family family = familyServiceImplementation.getByusername(username);
         if (family!=null){
             familyResponse.available = false;
         }else{
             familyResponse.available = true;
         }
         return familyResponse;
    }


    @PostMapping("/login")
    public Map<String, Object> getFamilyByUserName(@RequestBody Map<String, String> json) {
//        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));

       Map<String, Object> map = new HashMap<String,Object>();

        Family fam =  familyServiceImplementation.getByusername(json.get("username"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        if(fam == null) map.put("error","UserName doesn't exist!!");
        else if(encoder.matches(json.get("password"), fam.getPassword())){
            map.put("data", customerServiceImplementation.findCustomersByFamilyId(fam.getFamily_id()));
        }else{
            map.put("error", "Invalid Password!!");
        }

        return map;
    }




    @GetMapping("/getByID/{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable long id){
        return ResponseEntity.ok(customerServiceImplementation.getCustomerByID(id));
    }

    @PutMapping("/updateByID/{id}")
    public ResponseEntity<Object> updateUserByID(@PathVariable long id , @RequestBody CustomerDetails customer){
        return ResponseEntity.ok(customerServiceImplementation.updateCustomerByID(id , customer));
    }



    @DeleteMapping("/deleteByID/{id}")
    public ResponseEntity<CustomerDetails> deleteUserByID(@PathVariable long id){
        return new ResponseEntity<>(customerServiceImplementation.deleteCustomerByID(id) , HttpStatus.NO_CONTENT);
    }


}
