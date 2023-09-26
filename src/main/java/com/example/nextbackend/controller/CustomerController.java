package com.example.nextbackend.controller;



import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.model.Family;
import com.example.nextbackend.service.Customer_service_Imple;
import com.example.nextbackend.service.Family_service_Imple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        return customer_service_imple.createCustomer(customer_details);
    }
    @PostMapping("/putFamily")
    public Family createFamily(@RequestBody Family family) {
//        customer_details.setDob(new SimpleDateFormat("yyyy/MM/dd").parse(String.valueOf(customer_details.getDob())));

        return familyServiceImple.createFamily(family);
    }


    @GetMapping("/getByID/{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable long id){
        return ResponseEntity.ok(customer_service_imple.getCustomerByID(id));
    }

    @PutMapping("/updateByID/{id}")
    public ResponseEntity<Object> updateUserByID(@PathVariable long id , @RequestBody Customer_details customer){
        return ResponseEntity.ok(customer_service_imple.updateCustomerByID(id , customer));
    }


    @DeleteMapping("/deleteByID/{id}")
    public ResponseEntity<Customer_details> deleteUserByID(@PathVariable long id){
        return new ResponseEntity<>(customer_service_imple.deleteCustomerByID(id) , HttpStatus.NO_CONTENT);
    }
}
