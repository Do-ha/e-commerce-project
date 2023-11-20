package com.example.ecommerce.mappers;

import com.example.ecommerce.DTOs.CustomerDto;
import com.example.ecommerce.Entities.Customer;

public class CustomerMapper {
    public static CustomerDto mapEntityToDto(Customer customer){
        return new CustomerDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getCreationDate(),
                customer.getLastLogIn(),
                customer.getValidAccount(),
                customer.getActive()
        );

    }
    public static Customer mapDtoToEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.id());
        customer.setFirstName(customerDto.firstName());
        customer.setLastName(customerDto.lastName());
        customer.setEmail(customerDto.email());
        customer.setCreationDate(customerDto.creationDate());
        customer.setLastLogIn(customerDto.lastLogIn());
        customer.setValidAccount(customerDto.validAccount());
        customer.setActive(customerDto.active());


        return customer;
    }
}
