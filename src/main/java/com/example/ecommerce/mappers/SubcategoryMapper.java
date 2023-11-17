package com.example.ecommerce.mappers;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Subcategory;

public class SubcategoryMapper {

        public static SubcategoryDto mapEntityToDto(Subcategory subcategory){
            return new SubcategoryDto(
                    subcategory.getId(),
                    subcategory.getSubCategoryName(),
                    CategoryMapper.mapEntityToDto(subcategory.getCategory())
            );

        }
        public static Subcategory mapDtoToEntity(SubcategoryDto subcategoryDto) {
            Subcategory subcategory = new Subcategory();
            subcategory.setId(subcategoryDto.id());
            subcategory.setSubCategoryName(subcategoryDto.subCategoryName());
            return subcategory;
        }
}
