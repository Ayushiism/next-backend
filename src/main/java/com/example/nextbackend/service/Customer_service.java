package com.example.nextbackend.service;



import com.example.nextbackend.model.Customer_details;

import java.util.List;

public interface Customer_service {
    List<Customer_details> getAllCustomers();
    List<Customer_details> getCustomersByPage(Integer pageNumber , Integer pageSize);
    Customer_details createCustomer( Customer_details user);
    Customer_details getCustomerByID(long id);
    Customer_details updateCustomerByID(long id , Customer_details user);
    Customer_details deleteCustomerByID( long id);
    List<Customer_details> findCustomersByFamilyId(long family_id);
}
