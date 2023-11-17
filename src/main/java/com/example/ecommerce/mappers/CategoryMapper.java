package com.example.ecommerce.mappers;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.Entities.Category;

public class CategoryMapper {
  public static CategoryDto mapEntityToDto(Category category){
    return new CategoryDto(
      category.getId(),
      category.getCategoryName(),
      category.getSubCategories()
    );
  }
  public static Category mapDtoToEntity(CategoryDto categoryDto)
  {
    Category category=new Category();
    category.setId(categoryDto.id());
    category.setCategoryName(categoryDto.categoryName());
  return category;
  }
}

