package com.example.nextbackend.service;

import com.example.customerdetails.exception.ResourceNotFoundException;


import com.example.nextbackend.model.Customer_details;
import com.example.nextbackend.repository.Customer_repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Customer_service_Imple implements com.example.customerdetails.service.Customer_service {

    @Autowired
    private Customer_repository customer_repository;

    @Override
    public List<Customer_details> getAllCustomers() {
        return customer_repository.findAll();
    }

    @Override
    public List<Customer_details> getCustomersByPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber , pageSize);
        Page<Customer_details> page = customer_repository.findAll(pageable);
        return page.getContent();
    }

    @Override
    public Customer_details createCustomer(Customer_details user) {
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
}
