package com.example.ecommerce.Repositories;

import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.Entities.Product;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
  @Query("select p from Product p where p.productName like %:query% or p.shortDescription like %:query%")
  Page<Product> findByProductName(@Param("query") String query, Pageable pageable);


}


//  Page<Product>findByCategoryNameContains(String query, Pageable pageable);
//
//  @Query("select p from Product p where p.CategoryName like :x")
//  Page<Product>chercher(@Param("x") String keyword, Pageable pageable);}
