package com.example.ecommerce.Entities;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subCategoryName;
//    @Column(name = "categoryId")
    private Boolean active;
    @ManyToOne
    private Category category;

    @OneToMany
    private List<Product> product;

}