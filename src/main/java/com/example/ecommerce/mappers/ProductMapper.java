package com.example.ecommerce.mappers;

import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.Entities.Product;

public class ProductMapper {
  public static ProductDto mapProductToDTO(Product product) {
    return new ProductDto(
      product.getId(),
      product.getSku(),
      product.getProductName(),
      product.getProductImage(),
      product.getShortDescription(),
      product.getLongDescription(),
      product.getPrice(),
      product.getDiscountPrice(),
      product.getOptions(),
      product.getActive(),
//      product.getSubcategory().getSubCategoryName()
      (product.getSubcategory() != null) ? product.getSubcategory().getSubCategoryName() : null

    );
  }
}
