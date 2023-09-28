package com.example.nextbackend.controller;



import com.example.nextbackend.dto.FamilyResponse;
import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.model.Family;
import com.example.nextbackend.service.Customer_service_Imple;
import com.example.nextbackend.service.Family_service_Imple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    private Customer_service_Imple customer_service_imple;
    @Autowired
    private Family_service_Imple familyServiceImple;

    @GetMapping("/getAllCustomers")
    public List<Customer_details> getAllUsers(){
        return customer_service_imple.getAllCustomers();
    }

    @GetMapping("/getCustomersByPage")
    public List<Customer_details> getCustomerByPage(@RequestParam(value = "pageNumber" , defaultValue = "0" , required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize" , defaultValue = "5" , required = false) Integer pageSize){
        return customer_service_imple.getCustomersByPage(pageNumber , pageSize);
    }

    @PostMapping("/putCustomer")
    public Customer_details createUser(@RequestBody Customer_details customer_details) {
//        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        String result = encoder.encode(customer_details.getFamily().getPassword());
        customer_details.getFamily().setPassword(result);
        return customer_service_imple.createCustomer(customer_details);
    }
    @PostMapping("/putFamily")
    public Family createFamily(@RequestBody Family family) {
//        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));

        return familyServiceImple.createFamily(family);
    }

    @GetMapping("/getFamilyByUserName/{username}")
    public FamilyResponse getFamilyByUserName(@PathVariable String username) {
//        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));
         FamilyResponse familyResponse = new FamilyResponse();
         Family family = familyServiceImple.getByusername(username);
         if (family!=null){
             familyResponse.familyEntity = family;
             familyResponse.error = false;
         }else{
             familyResponse.familyEntity = null;
             familyResponse.error = true;
         }
         return familyResponse;
    }


    @GetMapping("/login")
    public Map<String, Object> getFamilyByUserName(@RequestBody Map<String, String> json) {
//        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));

       Map<String, Object> map = new HashMap<String,Object>();

        Family fam =  familyServiceImple.getByusername(json.get("username"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

        if(fam == null) map.put("error","UserName doesn't exist!!");
        else if(encoder.matches(json.get("password"), fam.getPassword())){
            map.put("data", customer_service_imple.findCustomersByFamilyId(fam.getFamily_id()));
        }else{
            map.put("error", "Invalid Password!!");
        }

        return map;
    }

    @PostMapping("/addFamilyMember")
    public Map<String, Object> putFamilyMember(@RequestBody Map<String, String> json){

        Map<String, Object> map = new HashMap<String,Object>();
        Family fam =  familyServiceImple.getByusername(json.get("username"));

        if(fam == null) map.put("error","UserName doesn't exist!!");
        else{
            Customer_details customer = new Customer_details();
            customer.setFamily(fam);
            customer.setDob(json.get("dob"));
            customer.setGender(json.get("gender"));
            customer.setId_type(json.get("id_type"));
            customer.setId_number(json.get("id_number"));
            customer.setFirst_name(json.get("first_name"));
            customer.setLast_name(json.get("last_name"));

            map.put("data" ,customer_service_imple.createCustomer(customer));
            map.put("message", "User added Successfully!!");
        }
        return map;
    }


    @GetMapping("/getByID/{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable long id){
        return ResponseEntity.ok(customer_service_imple.getCustomerByID(id));
    }

    @PutMapping("/updateByID/{id}")
    public ResponseEntity<Object> updateUserByID(@PathVariable long id , @RequestBody Customer_details customer){
        return ResponseEntity.ok(customer_service_imple.updateCustomerByID(id , customer));
    }

    @PutMapping("/updatePlanByID/{id}/{plan}")
    public ResponseEntity<Object> updatePlanByID(@PathVariable long id , @PathVariable String plan){
        return ResponseEntity.ok(familyServiceImple.updateCustomerByID(id , plan));
    }


    @DeleteMapping("/deleteByID/{id}")
    public ResponseEntity<Customer_details> deleteUserByID(@PathVariable long id){
        return new ResponseEntity<>(customer_service_imple.deleteCustomerByID(id) , HttpStatus.NO_CONTENT);
    }
}
