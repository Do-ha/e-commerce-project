package com.example.ecommerce.Services;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.CustomerDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Customer;
import com.example.ecommerce.Entities.Subcategory;
import com.example.ecommerce.Repositories.CustomerRepository;
import com.example.ecommerce.mappers.CategoryMapper;
import com.example.ecommerce.mappers.CustomerMapper;
import com.example.ecommerce.mappers.SubcategoryMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    public CustomerDto addcustomer(CustomerDto customerDto){
        Customer customer = customerRepository.save(CustomerMapper.mapDtoToEntity(customerDto));
        return CustomerMapper.mapEntityToDto(customer);
    }

    public List<CustomerDto> getAllCustomers(
            int page,
           int limit)

    {
        // Use PageRequest for pagination
        Pageable pageable = PageRequest.of(page, limit);

        // Use findAll with pageable directly
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        if(customerPage.hasContent()){
            return customerPage.getContent().stream()
                    .map(CustomerMapper::mapEntityToDto)
                    .collect(Collectors.toList());
        }
        else{
            return Collections.emptyList();
        }
    }
    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (customerOptional.isPresent()) {
            CustomerDto customerDto = CustomerMapper.mapEntityToDto(customerOptional.get());
            return customerDto ;
        }
        else {
            throw new EntityNotFoundException("Customer not found");
        }}

    public List<CustomerDto> searchCustomers(int page,int limit,String query) {

        // Use PageRequest for pagination
        Pageable pageable = PageRequest.of(page , limit); // Adjust page to be zero-indexed

        // Use findAll or findByProductName with pageable directly
        Page<Customer> customerPage = customerRepository.findBylastNameAndEmail(query, pageable);

        return customerPage.getContent().stream()
                .map(CustomerMapper::mapEntityToDto)
                .collect(Collectors.toList());

    }
    public CustomerDto DeleteCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> customerTodeleteOptional = this.customerRepository.findById(id);


        if (customerTodeleteOptional.isEmpty()) {
            return null;
        }
        Customer customerTodelete = customerTodeleteOptional.get();
            this.customerRepository.delete(customerTodelete);
            return CustomerMapper.mapEntityToDto(customerTodelete);
        }
    public CustomerDto updateCustomer(CustomerDto updatedCustomerDto, Long id) {
        Optional<Customer> CustomerToUpdateOptional = this.customerRepository.findById(id);

        if (CustomerToUpdateOptional.isEmpty()) {

            return null;
        }
        Customer customerToUpdate = CustomerToUpdateOptional.get();

        if (updatedCustomerDto.firstName() != null) {
            customerToUpdate.setFirstName(updatedCustomerDto.firstName());
        }
        if (updatedCustomerDto.lastName() != null) {
            customerToUpdate.setLastName(updatedCustomerDto.lastName());
        }
        if (updatedCustomerDto.email() != null) {
            customerToUpdate.setEmail(updatedCustomerDto.email());
        }
        if (updatedCustomerDto.active() != null) {
            customerToUpdate.setActive(updatedCustomerDto.active());
        }
        Customer updatedUser = this.customerRepository.save(customerToUpdate);

        // Map the updated product to a ProductDto and return it
        return CustomerMapper.mapEntityToDto(updatedUser);
    }
}
