package com.example.nextbackend.service;

import com.example.nextbackend.exception.ResourceNotFoundException;
import com.example.nextbackend.model.CustomerDetails;
import com.example.nextbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CustomerDetails createCustomer(CustomerDetails user)
    {
//        Family fam = new Family();
//        fam.setUsername(user.get);
        return customerRepository.save(user);
    }

    @Override
    public CustomerDetails getCustomerByID(long id) {
        CustomerDetails customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not Exit with id "+id));
        return customer;
    }

    @Override
    public CustomerDetails updateCustomerByID(long id, CustomerDetails customer) {
        CustomerDetails customerToUpdate = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not Exit with id "+id));
        customerToUpdate.setFirst_name(customer.getFirst_name());
        customerToUpdate.setLast_name(customer.getLast_name());
        customerToUpdate.setDob(customer.getDob());
        customerToUpdate.setGender(customer.getGender());
        customerRepository.save(customerToUpdate);

        return customerToUpdate;
    }

    @Override
    public CustomerDetails deleteCustomerByID(long id) {
        CustomerDetails customer = customerRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Customer not Exit with id "+id));
        customerRepository.delete(customer);

        return customer;
    }

    @Override
    public List<CustomerDetails> findCustomersByFamilyId(long family_id){

        try {
            return new ResponseEntity<List<CustomerDetails>>(customerRepository.findCustomersByFamilyId(family_id), HttpStatus.OK).getBody();
        }
        catch(Exception e){
            throw new ResourceNotFoundException("Username does not exist");
        }
    }
}
