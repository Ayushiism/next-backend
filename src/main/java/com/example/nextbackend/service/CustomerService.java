package com.example.nextbackend.service;

import com.example.nextbackend.model.CustomerDetails;
import java.util.List;
public interface CustomerService {
    CustomerDetails createCustomer(CustomerDetails user);
    CustomerDetails getCustomerByID(long id);
    CustomerDetails updateCustomerByID(long id , CustomerDetails user);
    CustomerDetails deleteCustomerByID(long id);
    List<CustomerDetails> findCustomersByFamilyId(long family_id);
}


