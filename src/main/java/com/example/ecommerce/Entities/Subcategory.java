package com.example.ecommerce.Entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"subCategories"})
    private Category category;

    @OneToMany
    private List<Product> product;

}
