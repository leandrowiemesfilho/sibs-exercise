package com.rent.customer.handler;

import com.rent.customer.exception.CustomerAlreadyExistsException;
import com.rent.customer.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the {@link GlobalExceptionHandler} class.
 * These tests verify the exception handling logic.
 */
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    /**
     * Tests the {@link GlobalExceptionHandler#handleCustomerNotFound(CustomerNotFoundException)} method.
     * Verifies that the method returns a {@code 404 Not Found} response.
     */
    @Test
    void testHandleCustomerNotFound() {
        final CustomerNotFoundException exception = new CustomerNotFoundException(UUID.randomUUID());
        final ResponseEntity<String> response = exceptionHandler.handleCustomerNotFound(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Tests the {@link GlobalExceptionHandler#handleCustomerAlreadyExists(CustomerAlreadyExistsException)} method.
     * Verifies that the method returns a {@code 208 Already Reported} response.
     */
    @Test
    void testHandleCustomerAlreadyExists() {
        final CustomerAlreadyExistsException exception = new CustomerAlreadyExistsException("test@example.com");
        final ResponseEntity<String> response = exceptionHandler.handleCustomerAlreadyExists(exception);

        assertEquals(HttpStatus.ALREADY_REPORTED, response.getStatusCode());
    }
}

