package com.example.ecommerce.mappers;

import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.Entities.Product;
import com.example.ecommerce.Entities.Subcategory;

public class ProductMapper {
  public static ProductDto mapEntityToDTO(Product product) {
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
  public static Product mapDtoToEntity(ProductDto productDto) {
    Product product = new Product();

    // Set properties from DTO to entity
    product.setId(productDto.id());

    product.setSku(productDto.sku());
    product.setProductName(productDto.productName());
    product.setProductImage(productDto.productImage());
    product.setShortDescription(productDto.shortDescription());
    product.setLongDescription(productDto.longDescription());
    product.setPrice(productDto.price());
    product.setDiscountPrice(productDto.discountPrice());
    product.setOptions(productDto.options());
    product.setActive(productDto.active());

    // Assuming that 'subcategory' is a property in your Product class
    if (productDto.subcategoryName() != null) {
      Subcategory subcategory = new Subcategory();
      subcategory.setSubCategoryName(productDto.subcategoryName());
      product.setSubcategory(subcategory);
    }

    return product;
  }
}
