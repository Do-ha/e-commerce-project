package com.example.ecommerce.Repositories;

import com.example.ecommerce.Entities.Customer;
import com.example.ecommerce.Entities.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository <Customer,Long> {
    @Query("select t from Customer t where t.lastName like %:query% or t.email like %:query%")
    Page<Customer> findBylastNameAndEmail(@Param("query") String query, Pageable pageable);
}
