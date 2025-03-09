package com.rent.customer.mapper;

import com.rent.customer.model.Customer;
import com.rent.customer.model.dto.CustomerDTO;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public static Customer toCustomer(CustomerDTO customerDTO) {
        final Customer customer = new Customer();

        customer.setId(customerDTO.getId());
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());

        return customer;
    }

    public static CustomerDTO toCustomerDTO(Customer customer) {
        final CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(customer.getId());
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());

        return customerDTO;
    }
}
