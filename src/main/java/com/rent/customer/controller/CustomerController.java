package com.rent.customer.controller;

import com.rent.customer.model.dto.CustomerDTO;
import com.rent.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * The `CustomerController` class is a Spring REST controller that handles HTTP requests related to customer operations.
 * It provides endpoints for retrieving, creating, updating, and deleting customer records.
 * This controller maps all requests to the base path "/customer".
 */
@RestController
@RequestMapping("customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    /**
     * Constructs a new `CustomerController` with the specified `CustomerService`.
     *
     * @param customerService the service responsible for handling customer-related business logic.
     */
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a `ResponseEntity` containing a list of `CustomerDTO` objects and an HTTP status of `FOUND` (302).
     */
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll() {
        logger.debug("Find all customers");

        final List<CustomerDTO> customerDTOList = customerService.findAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(customerDTOList);
    }

    /**
     * Retrieves a specific customer by their unique identifier.
     *
     * @param customerId the unique identifier of the customer to retrieve.
     * @return a `ResponseEntity` containing the `CustomerDTO` object and an HTTP status of `FOUND` (302).
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable("customerId") UUID customerId) {
        logger.debug("Find customer by id: {}", customerId);

        final CustomerDTO customerDTO = customerService.findById(customerId);
        return ResponseEntity.status(HttpStatus.FOUND).body(customerDTO);
    }

    /**
     * Creates a new customer record.
     *
     * @param customerDTO the `CustomerDTO` object containing the customer data to be created.
     * @return a `ResponseEntity` containing the unique identifier of the newly created customer and an HTTP status of `CREATED` (201).
     */
    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody @Valid CustomerDTO customerDTO) {
        logger.debug("Create customer: {}", customerDTO);

        final UUID customerId = customerService.create(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerId);
    }

    /**
     * Updates an existing customer record.
     *
     * @param customerDTO the `CustomerDTO` object containing the updated customer data.
     * @return a `ResponseEntity` with an HTTP status of `OK` (200) indicating the update was successful.
     */
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid CustomerDTO customerDTO) {
        customerService.update(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Deletes a customer record by their unique identifier.
     *
     * @param customerId the unique identifier of the customer to delete.
     * @return a `ResponseEntity` with an HTTP status of `OK` (200) indicating the deletion was successful.
     */
    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable("customerId") UUID customerId) {
        customerService.delete(customerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}