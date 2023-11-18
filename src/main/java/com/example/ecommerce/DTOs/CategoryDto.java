package com.example.ecommerce.DTOs;

import com.example.ecommerce.Entities.Subcategory;

import java.util.List;

public record CategoryDto(

  Long id,
  String categoryName,
  Boolean active
//  List<Subcategory> subCategories
) {
}
