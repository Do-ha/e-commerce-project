package com.example.ecommerce.Repositories;

import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Subcategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubCategoryRepository extends JpaRepository<Subcategory,Long> {
    @Query("select s from Subcategory s where s.subCategoryName like %:query%")
    Page<Subcategory> findBySubCategoriesName(@Param("query") String query, Pageable pageable);
}
