package com.rent.customer.service;

import com.rent.customer.exception.CustomerAlreadyExistsException;
import com.rent.customer.exception.CustomerNotFoundException;
import com.rent.customer.model.Customer;
import com.rent.customer.model.dto.CustomerDTO;
import com.rent.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link CustomerService} class.
 * These tests verify the business logic for customer operations.
 */
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link CustomerService#findById(UUID)} method.
     * Verifies that the method returns the correct customer when the customer exists.
     */
    @Test
    void testFindById() {
        final UUID customerId = UUID.randomUUID();
        final Customer customer = new Customer();

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        final CustomerDTO result = customerService.findById(customerId);

        // Assert
        assertNotNull(result);
        verify(customerRepository, times(1)).findById(customerId);
    }

    /**
     * Tests the {@link CustomerService#findById(UUID)} method.
     * Verifies that the method throws a {@link CustomerNotFoundException} when the customer does not exist.
     */
    @Test
    void testFindByIdNotFound() {
        final UUID customerId = UUID.randomUUID();

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.findById(customerId));
        verify(customerRepository, times(1)).findById(customerId);
    }

    /**
     * Tests the {@link CustomerService#create(CustomerDTO)} method.
     * Verifies that the method throws a {@link CustomerAlreadyExistsException} when a customer with the same email exists.
     */
    @Test
    void testCreateAlreadyExists() {
        final CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setEmail("test@example.com");

        when(customerRepository.findByEmail(customerDTO.getEmail())).thenReturn(Optional.of(new Customer()));

        assertThrows(CustomerAlreadyExistsException.class, () -> customerService.create(customerDTO));
        verify(customerRepository, times(1)).findByEmail(customerDTO.getEmail());
    }
}
