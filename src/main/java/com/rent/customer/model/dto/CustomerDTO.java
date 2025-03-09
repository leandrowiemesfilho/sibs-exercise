package com.rent.customer.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

/**
 * The `CustomerDTO` class is a Data Transfer Object (DTO) that represents customer information.
 * It is used to transfer customer data between layers of the application, such as between the
 * controller and service layers. This class includes validation annotations to ensure the
 * integrity of the data.
 */
public class CustomerDTO {
    private UUID id;

    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;

    @Email
    @NotNull
    @NotBlank
    private String email;

    /**
     * Retrieves the unique identifier of the customer.
     *
     * @return the unique identifier (UUID) of the customer.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the customer.
     *
     * @param id the unique identifier (UUID) to set for the customer.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Retrieves the first name of the customer.
     *
     * @return the first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer.
     *
     * @param firstName the first name to set for the customer. Must not be null or blank.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the customer.
     *
     * @return the last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer.
     *
     * @param lastName the last name to set for the customer. Must not be null or blank.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the email address of the customer.
     *
     * @return the email address of the customer.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the customer.
     *
     * @param email the email address to set for the customer. Must not be null, blank, and must be a valid email format.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
