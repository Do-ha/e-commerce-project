package com.example.ecommerce.Services;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Subcategory;
import com.example.ecommerce.Repositories.SubCategoryRepository;
import com.example.ecommerce.mappers.CategoryMapper;
import com.example.ecommerce.mappers.SubcategoryMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SubcategoryService {
  SubCategoryRepository subcategoryRepository;
  public SubcategoryDto addSubcategory(SubcategoryDto subcategoryDto) {

    return SubcategoryMapper.mapEntityToDto(subcategoryRepository.save(SubcategoryMapper.mapDtoToEntity(subcategoryDto)));

  }

  public SubcategoryDto updateSubcategory(SubcategoryDto updatedSubcategoryDto, Long id) {
    Optional<Subcategory> subcategoryToUpdateOptional = this.subcategoryRepository.findById(id);

    if (!subcategoryToUpdateOptional.isPresent()) {
      return null;
    }

    Subcategory subcategoryToUpdate = subcategoryToUpdateOptional.get();

    if (updatedSubcategoryDto.subCategoryName() != null) {
      subcategoryToUpdate.setSubCategoryName(updatedSubcategoryDto.subCategoryName());
    }
//    if (updatedCategoryDto.active() != null) {
//      productToUpdate.setActive(!updatedProductDto.active());
//    }
//    if (updatedCategoryDto.subCategories()!=null){
//      categoryToUpdate.setSubCategories(updatedCategoryDto.subCategories());
//
//    }


    Subcategory updatedSubcategory = this.subcategoryRepository.save(subcategoryToUpdate);

    // Map the updated product to a ProductDto and return it
    return SubcategoryMapper.mapEntityToDto(updatedSubcategory);
  }
}
