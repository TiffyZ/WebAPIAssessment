package com.example.webapiassessment.service;

import com.example.webapiassessment.domain.Customer;
import com.example.webapiassessment.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testGetCustomerById() throws ChangeSetPersister.NotFoundException {
        // Arrange
        long customerId = 1L;
        Customer customer = new Customer(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        // Act
        Customer result = customerService.getCustomerById(customerId);

        // Assert
        assertEquals(customer, result);
        verify(customerRepository, times(1)).findById(customerId);
    }
}
