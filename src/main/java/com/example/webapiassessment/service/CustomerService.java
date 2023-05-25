package com.example.webapiassessment.service;

import com.example.webapiassessment.domain.Customer;
import com.example.webapiassessment.repository.CustomerRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Retrieve a specific customer by the ID.
     *
     * @param customerId The ID of the customer.
     * @return The retrieved customer.
     */
    public Customer getCustomerById(Long customerId) throws ChangeSetPersister.NotFoundException {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
    }
}
