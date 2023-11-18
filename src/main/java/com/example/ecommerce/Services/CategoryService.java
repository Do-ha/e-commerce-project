package com.example.ecommerce.Services;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Product;
import com.example.ecommerce.Repositories.CategoryRepository;
import com.example.ecommerce.Repositories.ProductRepository;
import com.example.ecommerce.mappers.CategoryMapper;
import com.example.ecommerce.mappers.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
  public CategoryDto addCategory(CategoryDto categoryDto){
    return CategoryMapper.mapEntityToDto(categoryRepository.save(CategoryMapper.mapDtoToEntity(categoryDto)));
  }


  public CategoryDto updateCategory(CategoryDto updatedCategoryDto, Long id) {
    Optional<Category> categoryToUpdateOptional = this.categoryRepository.findById(id);

    if (!categoryToUpdateOptional.isPresent()) {
      // Product not found, handle accordingly (return null or throw an exception)
      return null;
    }

    Category categoryToUpdate = categoryToUpdateOptional.get();

    // Update attributes based on the provided ProductDto
    if (updatedCategoryDto.categoryName() != null) {
      categoryToUpdate.setCategoryName(updatedCategoryDto.categoryName());
    }
//    if (updatedCategoryDto.getSubCategories() != null) {
//      // Correct the update for subCategories
//      categoryToUpdate.setSubCategories(updatedCategoryDto.getSubCategories());
//    }

    Category updatedCategory = this.categoryRepository.save(categoryToUpdate);

    // Map the updated product to a ProductDto and return it
    return CategoryMapper.mapEntityToDto(updatedCategory);
  }
    public List<CategoryDto> getAllCategories(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit) {

        {
            // Use PageRequest for pagination
            Pageable pageable = PageRequest.of(page, limit);

            // Use findAll with pageable directly
            Page<Category> categoryPage = categoryRepository.findAll(pageable);
            if(categoryPage.hasContent()){
                return categoryPage.getContent().stream()
                        .map(CategoryMapper::mapEntityToDto)
                        .collect(Collectors.toList());
            }
            else{ throw new EntityNotFoundException("Category not found");
    }
}}

    public CategoryDto getCategoryById(Long id) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);

        if (categoryOptional.isPresent()) {
            CategoryDto categoryDto = CategoryMapper.mapEntityToDto(categoryOptional.get());
            return categoryDto ;
        }
        else {
            throw new EntityNotFoundException("Category not found");
        }}
    public List<CategoryDto> searchCategories(int page,int limit,String query) {

        // Use PageRequest for pagination
        Pageable pageable = PageRequest.of(page , limit); // Adjust page to be zero-indexed

        // Use findAll or findByProductName with pageable directly
        Page<Category> categoryPage;
        if (query != null || !query.isEmpty()) {
            System.out.println(query);
            categoryPage = categoryRepository.findByCategorytName(query, pageable);
        } else {
            System.out.println("non");
            categoryPage = categoryRepository.findAll(pageable);
        }

        return categoryPage.getContent().stream()
                .map(CategoryMapper::mapEntityToDto)
                .collect(Collectors.toList());

}
    public CategoryDto DeleteCategory( Long id) {
        Optional<Category> categoryTodeleteOptional = this.categoryRepository.findById(id);


        if (categoryTodeleteOptional.isEmpty()) {
           throw new EntityNotFoundException("Category with id " + id +" not found");
        }
        Category categoryTodelete = categoryTodeleteOptional.get();
        if (categoryTodelete.getSubCategories().isEmpty()) {
            this.categoryRepository.delete(categoryTodelete);
            return CategoryMapper.mapEntityToDto(categoryTodelete);
        }
        else {
            throw new EntityNotFoundException("Category with id " + id + " has attached subcategories and cannot be deleted.");
        }
    }}
