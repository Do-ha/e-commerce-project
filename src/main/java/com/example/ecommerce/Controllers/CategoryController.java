package com.example.ecommerce.Controllers;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Product;
import com.example.ecommerce.Repositories.CategoryRepository;
import com.example.ecommerce.Services.CategoryService;
import com.example.ecommerce.mappers.CategoryMapper;
import com.example.ecommerce.mappers.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class CategoryController {
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit
    ) {
        return ResponseEntity.ok(categoryService.getAllCategories(page, limit));
    }
    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @GetMapping("/categories/search")
    public ResponseEntity<List<CategoryDto>> searchCategories(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "query", required = false) String query) {
        return ResponseEntity.ok(categoryService.searchCategories(page, limit, query));
    }
    @DeleteMapping("/categories/{id}")
    public CategoryDto deleteCategory(@PathVariable("id") Long id){
        return this.categoryService.DeleteCategory(id);
    }
}