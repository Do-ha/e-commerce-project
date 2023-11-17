package com.example.ecommerce.Repositories;

import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select c from Category c where c.categoryName like %:query%")
    Page<Category> findByCategorytName(@Param("query") String query, Pageable pageable);

}
