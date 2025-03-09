package com.rent.customer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

/**
 * The `Customer` class is an entity that represents a customer in the database.
 * It is mapped to the `T_CUSTOMER` table and contains fields for customer details such as
 * ID, first name, last name, and email. This class is used by JPA (Java Persistence API)
 * to persist and retrieve customer data.
 */
@Entity
@Table(name = "T_CUSTOMER")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CUSTOMER_ID", unique = true, nullable = false)
    private UUID id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
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
