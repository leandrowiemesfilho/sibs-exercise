package com.rent.customer.exception;

import java.util.UUID;

/**
 * The `CustomerNotFoundException` class is a custom runtime exception that is thrown when a customer
 * with a specified ID is not found in the system. This exception is typically used in service or
 * controller layers to indicate that a requested customer resource does not exist.
 */
public class CustomerNotFoundException extends RuntimeException {

    private static final String USER_NOT_FOUND = "Customer with id %s not found";

    /**
     * Constructs a new `CustomerNotFoundException` with a formatted error message that includes the
     * specified customer ID.
     *
     * @param id the unique identifier (UUID) of the customer that was not found.
     */
    public CustomerNotFoundException(UUID id) {
        super(String.format(USER_NOT_FOUND, id));
    }
}