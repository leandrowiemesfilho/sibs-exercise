package com.rent.customer.repository;

import com.rent.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * The `CustomerRepository` interface is a Spring Data JPA repository responsible for data access operations
 * on `Customer` entities. It extends `JpaRepository`, providing standard CRUD operations and additional
 * query methods for interacting with the `T_CUSTOMER` table in the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    /**
     * Retrieves a customer by their email address.
     *
     * @param email the email address of the customer to retrieve.
     * @return an `Optional` containing the `Customer` entity if found, or an empty `Optional` if no customer
     *         with the specified email exists.
     */
    Optional<Customer> findByEmail(String email);
}
