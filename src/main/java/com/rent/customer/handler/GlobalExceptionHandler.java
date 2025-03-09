package com.rent.customer.handler;

import com.rent.customer.exception.CustomerAlreadyExistsException;
import com.rent.customer.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * The `GlobalExceptionHandler` class is a global exception handler for the application.
 * It handles specific exceptions thrown by controllers and returns appropriate HTTP responses
 * with error details. This class is annotated with `@RestControllerAdvice`, making it a
 * centralized component for exception handling across all controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles `CustomerNotFoundException` and returns a `404 Not Found` response with the exception message.
     *
     * @param exception the `CustomerNotFoundException` instance containing the error details.
     * @return a `ResponseEntity` with a `404 Not Found` status and the exception message as the response body.
     */
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleCustomerNotFound(final CustomerNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    /**
     * Handles `CustomerAlreadyExistsException` and returns a `208 Already Reported` response with the exception message.
     *
     * @param exception the `CustomerAlreadyExistsException` instance containing the error details.
     * @return a `ResponseEntity` with a `208 Already Reported` status and the exception message as the response body.
     */
    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<String> handleCustomerAlreadyExists(final CustomerAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                .body(exception.getMessage());
    }

    /**
     * Handles `MethodArgumentNotValidException` and returns a `400 Bad Request` response with validation error details.
     * This exception is thrown when request validation fails (e.g., due to invalid input data).
     *
     * @param exception the `MethodArgumentNotValidException` instance containing the validation errors.
     * @return a `ResponseEntity` with a `400 Bad Request` status and a map of field errors in the response body.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        final StringBuilder errors = new StringBuilder();

        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    final String fieldName = ((FieldError) error).getField();
                    final String errorMessage = error.getDefaultMessage();
                    errors.append("The field");
                    errors.append(fieldName);
                    errors.append(" ");
                    errors.append(errorMessage);
                });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors.toString());
    }
}
