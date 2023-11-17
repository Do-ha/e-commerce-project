package com.example.ecommerce.mappers;

import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Entities.Subcategory;

public class SubcategoryMapper {
  public static SubcategoryDto mapEntityToDto (Subcategory subcategory){
    return new SubcategoryDto(
      subcategory.getId(),
      subcategory.getSubCategoryName(),
//      subcategory.getCategory(),
      subcategory.getActive()

    );

  }
  public static Subcategory mapDtoToEntity (SubcategoryDto subcategoryDto) {
    Subcategory subcategory = new Subcategory();
    subcategory.setId(subcategoryDto.id());
    subcategory.setSubCategoryName(subcategoryDto.subCategoryName());
//    subcategory.setCategory(subcategoryDto.category());
    System.out.println(subcategory);
    return subcategory;
  }
}
