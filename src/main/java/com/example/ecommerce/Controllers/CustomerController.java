package com.example.ecommerce.Controllers;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.CustomerDto;
import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Entities.Customer;
import com.example.ecommerce.Entities.Product;
import com.example.ecommerce.Repositories.CustomerRepository;
import com.example.ecommerce.Services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class CustomerController {
    @PostMapping("/customers")
    public ResponseEntity<?>addcustomer(@RequestBody CustomerDto customerDto){
        return ResponseEntity.ok(customerService.addcustomer(customerDto));
    }

    private CustomerService customerService;

    @GetMapping("/customers")
    public ResponseEntity<List<CustomerDto>> getALLCustomers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit)

    {
        return ResponseEntity.ok(customerService.getAllCustomers(page, limit));
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
    @GetMapping("/customers/search")
    public ResponseEntity<List<CustomerDto>> searchCustomers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "query", required = false) String query) {
        return ResponseEntity.ok(customerService.searchCustomers(page , limit, query));
    }
    @DeleteMapping("/customers/{id}")
    public CustomerDto deleteCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto){
        return this.customerService.DeleteCustomer(id, customerDto);
    }
    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        CustomerDto updatedCustomerDto = this.customerService.updateCustomer(customerDto, id);

        if (updatedCustomerDto != null) {
            // Product updated successfully
            return ResponseEntity.ok(updatedCustomerDto);
        } else {
            // Product not found, return a 404 response or handle accordingly
            return ResponseEntity.notFound().build();
        }}
}
