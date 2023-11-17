package com.example.ecommerce.Entities;

import com.example.ecommerce.Repositories.CategoryRepository;
import com.example.ecommerce.Repositories.SubCategoryRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private Boolean active = false;
    @OneToMany(mappedBy = "category")
    private List<Subcategory> subCategories;
    @OneToMany(fetch = FetchType.EAGER)
  private List<Product>products;




}
