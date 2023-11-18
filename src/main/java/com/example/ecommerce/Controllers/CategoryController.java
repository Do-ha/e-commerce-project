package com.example.ecommerce.Controllers;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Repositories.CategoryRepository;
import com.example.ecommerce.Services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class CategoryController {
  private CategoryService categoryService;
  private CategoryRepository categoryRepository;
  @PostMapping("/categories")
  public ResponseEntity<?>addCategory(@RequestBody CategoryDto categoryDto){
    return ResponseEntity.ok(categoryService.addCategory(categoryDto));
  }
  @PutMapping("/categories/{id}")
  public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long id, @RequestBody CategoryDto categoryDto) {
    CategoryDto updatedCategoryDto = this.categoryService.updateCategory(categoryDto, id);

    if (updatedCategoryDto != null) {
      // Product updated successfully
      return ResponseEntity.ok(updatedCategoryDto);
    } else {
      // Product not found, return a 404 response or handle accordingly
      return ResponseEntity.notFound().build();
    }
  }

  @GetMapping("/categories")
  public ResponseEntity<List<CategoryDto>> getAllCategories( @RequestParam(name = "page") int page,
    @RequestParam(name = "limit",defaultValue = "10") int limit) {

    List<CategoryDto> allCategories = categoryService.getAllCategories(page, limit);

    return ResponseEntity.ok(allCategories);}
@GetMapping("/categories/{id}")
  public ResponseEntity<CategoryDto>getCategoryById(@PathVariable("id") Long id){
    return ResponseEntity.ok(this.categoryService.getCategoryById(id));

}

  @GetMapping("/categories/search")
  public ResponseEntity<List<CategoryDto>>searchCategory(
    @RequestParam(name = "page",defaultValue = "1")int page,
  @RequestParam(name="query" )String query,
  @RequestParam(name="limit" , defaultValue = "10")int limit){
    return ResponseEntity.ok(categoryService.searchCategories(page,limit,query));
  }
  @DeleteMapping("/categories/{id}")
  public CategoryDto deleteCategory(@PathVariable("id") Long id){
    return this.categoryService.DeleteCategory(id);
  }
}




