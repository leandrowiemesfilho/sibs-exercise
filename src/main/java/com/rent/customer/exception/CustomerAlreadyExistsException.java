package com.rent.customer.exception;

/**
 * A static error message template indicating that a customer with the specified email already exists.
 */
public class CustomerAlreadyExistsException extends RuntimeException {

    private static final String USER_ALREADY_EXISTS = "Customer with e-mail: %s already exists";

    /**
     * Constructs a new `CustomerAlreadyExistsException` with a formatted error message that includes the
     * specified email address.
     *
     * @param email the email address of the customer that already exists.
     */
    public CustomerAlreadyExistsException(String email) {
        super(String.format(USER_ALREADY_EXISTS, email));
    }
}
