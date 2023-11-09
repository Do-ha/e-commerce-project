package com.example.ecommerce.Repositories;

import com.example.ecommerce.Entities.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<Subcategory,Long> {
}
