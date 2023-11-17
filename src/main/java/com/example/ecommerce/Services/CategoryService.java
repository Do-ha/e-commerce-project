package com.example.ecommerce.Services;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Product;
import com.example.ecommerce.Repositories.CategoryRepository;
import com.example.ecommerce.mappers.CategoryMapper;
import com.example.ecommerce.mappers.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

  private CategoryRepository categoryRepository;

  public CategoryDto addCategory(CategoryDto categoryDto) {

    return CategoryMapper.mapEntityToDto(categoryRepository.save(CategoryMapper.mapDtoToEntity(categoryDto)));

}

  public CategoryDto updateCategory(CategoryDto updatedCategoryDto, Long id) {
    Optional<Category> categoryToUpdateOptional = this.categoryRepository.findById(id);

    if (!categoryToUpdateOptional.isPresent()) {
      return null;
    }

    Category categoryToUpdate = categoryToUpdateOptional.get();

    if (updatedCategoryDto.categoryName() != null) {
      categoryToUpdate.setCategoryName(updatedCategoryDto.categoryName());
    }
//    if (updatedCategoryDto.active() != null) {
//      productToUpdate.setActive(!updatedProductDto.active());
//    }
//    if (updatedCategoryDto.subCategories()!=null){
//      categoryToUpdate.setSubCategories(updatedCategoryDto.subCategories());
//
//    }


    Category updatedCategory = this.categoryRepository.save(categoryToUpdate);

    // Map the updated product to a ProductDto and return it
    return CategoryMapper.mapEntityToDto(updatedCategory);
  }

  public List<CategoryDto> getAllCategories(  int page, int limit) {
    // Use PageRequest for pagination
    Pageable pageable = PageRequest.of(page, limit);

    // Use findAll with pageable directly
    Page<Category> categoryPage = categoryRepository.findAll(pageable);
      return categoryPage.getContent().stream()
        .map(CategoryMapper::mapEntityToDto)
        .collect(Collectors.toList());


  }


}
