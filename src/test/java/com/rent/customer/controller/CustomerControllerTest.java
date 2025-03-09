package com.rent.customer.controller;

import com.rent.customer.model.dto.CustomerDTO;
import com.rent.customer.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link CustomerController} class.
 * These tests verify the behavior of the controller's endpoints.
 */
class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the {@link CustomerController#findAll()} method.
     * Verifies that the method returns a list of customers with HTTP status {@code FOUND}.
     */
    @Test
    void testFindAll() {
        final List<CustomerDTO> customers = Collections.singletonList(new CustomerDTO());

        when(customerService.findAll()).thenReturn(customers);

        final ResponseEntity<List<CustomerDTO>> response = customerController.findAll();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(customers, response.getBody());
        verify(customerService, times(1)).findAll();
    }

    /**
     * Tests the {@link CustomerController#findById(UUID)} method.
     * Verifies that the method returns a customer with HTTP status {@code FOUND}.
     */
    @Test
    void testFindById() {
        final UUID customerId = UUID.randomUUID();
        final CustomerDTO customer = new CustomerDTO();

        when(customerService.findById(customerId)).thenReturn(customer);

        final ResponseEntity<CustomerDTO> response = customerController.findById(customerId);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(customer, response.getBody());
        verify(customerService, times(1)).findById(customerId);
    }

    /**
     * Tests the {@link CustomerController#create(CustomerDTO)} method.
     * Verifies that the method returns the created customer's ID with HTTP status {@code CREATED}.
     */
    @Test
    void testCreate() {
        final CustomerDTO customerDTO = new CustomerDTO();
        final UUID customerId = UUID.randomUUID();

        when(customerService.create(customerDTO)).thenReturn(customerId);

        final ResponseEntity<UUID> response = customerController.create(customerDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(customerId, response.getBody());
        verify(customerService, times(1)).create(customerDTO);
    }

    /**
     * Tests the {@link CustomerController#update(CustomerDTO)} method.
     * Verifies that the method returns HTTP status {@code OK}.
     */
    @Test
    void testUpdate() {
        final CustomerDTO customerDTO = new CustomerDTO();

        final ResponseEntity<Void> response = customerController.update(customerDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).update(customerDTO);
    }

    /**
     * Tests the {@link CustomerController#delete(UUID)} method.
     * Verifies that the method returns HTTP status {@code OK}.
     */
    @Test
    void testDelete() {
        final UUID customerId = UUID.randomUUID();

        final ResponseEntity<Void> response = customerController.delete(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(customerService, times(1)).delete(customerId);
    }
}
