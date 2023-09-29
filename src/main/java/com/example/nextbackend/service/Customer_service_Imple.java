package com.example.nextbackend.service;

import com.example.nextbackend.exception.ResourceNotFoundException;


import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.model.Family;
import com.example.nextbackend.repository.Customer_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Customer_service_Imple implements com.example.nextbackend.service.Customer_service {

    @Autowired
    private Customer_repository customer_repository;




    @Override
    public Customer_details createCustomer(Customer_details user)
    {
//        Family fam = new Family();
//        fam.setUsername(user.get);
        return customer_repository.save(user);
    }

    @Override
    public Customer_details getCustomerByID(long id) {
         Customer_details customer = customer_repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not Exit with id "+id));
        return customer;
    }

    @Override
    public Customer_details updateCustomerByID(long id, Customer_details customer) {
        Customer_details customerToUpdate = customer_repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not Exit with id "+id));
        customerToUpdate.setFirst_name(customer.getFirst_name());
        customerToUpdate.setLast_name(customer.getLast_name());
        customerToUpdate.setDob(customer.getDob());
        customerToUpdate.setGender(customer.getGender());
        customer_repository.save(customerToUpdate);

        return customerToUpdate;
    }

    @Override
    public Customer_details deleteCustomerByID(long id) {
        Customer_details customer = customer_repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not Exit with id "+id));
        customer_repository.delete(customer);

        return customer;
    }

    @Override
    public List<Customer_details> findCustomersByFamilyId(long family_id){

        try {
            return new ResponseEntity<List<Customer_details>>(customer_repository.findCustomersByFamilyId(family_id), HttpStatus.OK).getBody();
        }
        catch(Exception e){
            throw new ResourceNotFoundException("Username does not exist");
        }
    }
}
