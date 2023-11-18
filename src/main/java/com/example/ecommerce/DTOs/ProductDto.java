package com.example.ecommerce.DTOs;

import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Subcategory;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.List;

public record ProductDto (
Long id,
  String sku,
  String productImage,
  String productName,
  String shortDescription,
  String longDescription,
  Double price,
  Double discountPrice,

  List<String> options,
  Boolean active,
  String subcategoryName
//SubcategoryDto subcategory

) {
}
