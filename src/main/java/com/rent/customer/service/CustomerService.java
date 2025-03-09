package com.rent.customer.service;

import com.rent.customer.exception.CustomerAlreadyExistsException;
import com.rent.customer.exception.CustomerNotFoundException;
import com.rent.customer.mapper.CustomerMapper;
import com.rent.customer.model.Customer;
import com.rent.customer.model.dto.CustomerDTO;
import com.rent.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The `CustomerService` class is a service layer component responsible for handling business logic
 * related to customer operations. It interacts with the `CustomerRepository` to perform CRUD operations
 * on customer entities and uses `CustomerMapper` to convert between entity and DTO objects.
 */
@Service
public class CustomerService {

    private final CustomerRepository repository;

    /**
     * Constructs a new `CustomerService` with the specified `CustomerRepository`.
     *
     * @param repository the repository responsible for data access operations on customer entities.
     */
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a list of all customers.
     *
     * @return a list of `CustomerDTO` objects representing all customers.
     */
    public List<CustomerDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(CustomerMapper::toCustomerDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a specific customer by their unique identifier.
     *
     * @param id the unique identifier of the customer to retrieve.
     * @return a `CustomerDTO` object representing the customer.
     * @throws CustomerNotFoundException if no customer is found with the specified ID.
     */
    public CustomerDTO findById(UUID id) {
        final Customer customer = repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(id)
                );

        return CustomerMapper.toCustomerDTO(customer);
    }

    /**
     * Creates a new customer record.
     *
     * @param customerDTO the `CustomerDTO` object containing the customer data to be created.
     * @return the unique identifier (UUID) of the newly created customer.
     * @throws CustomerAlreadyExistsException if a customer with the same email already exists.
     */
    public UUID create(CustomerDTO customerDTO) {
        this.validateCustomer(customerDTO);

        final Customer customer = CustomerMapper.toCustomer(customerDTO);

        repository.save(customer);

        return customer.getId();
    }

    /**
     * Validates that a customer with the same email does not already exist.
     *
     * @param customerDTO the `CustomerDTO` object to validate.
     * @throws CustomerAlreadyExistsException if a customer with the same email already exists.
     */
    private void validateCustomer(CustomerDTO customerDTO) {
        this.repository.findByEmail((customerDTO.getEmail()))
                .ifPresent(customer -> {
                    throw new CustomerAlreadyExistsException(
                            "Customer with e-mail: " + customer.getEmail() + " already exists"
                    );
                });
    }

    /**
     * Updates an existing customer record.
     *
     * @param customerDTO the `CustomerDTO` object containing the updated customer data.
     * @throws CustomerNotFoundException if no customer is found with the specified ID.
     */
    public void update(CustomerDTO customerDTO) {
        final Customer customer = this.repository.findById(customerDTO.getId())
                .orElseThrow(() ->
                        new CustomerNotFoundException(customerDTO.getId())
                );

        if (!customerDTO.getEmail().equals(customer.getEmail())) {
            validateCustomer(customerDTO);
        }

        mergeCustomer(customer, customerDTO);

        this.repository.save(customer);
    }

    /**
     * Deletes a customer record by their unique identifier.
     *
     * @param id the unique identifier of the customer to delete.
     * @throws CustomerNotFoundException if no customer is found with the specified ID.
     */
    public void delete(UUID id) {
        final Customer customer = this.repository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(id)
                );

        this.repository.delete(customer);
    }

    private static void mergeCustomer(Customer customer, CustomerDTO customerDTO) {
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
    }
}
