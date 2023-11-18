package com.example.ecommerce.Controllers;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Repositories.SubCategoryRepository;
import com.example.ecommerce.Services.SubcategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class Subcategorycontroller {
private SubCategoryRepository subCategoryRepository;
private SubcategoryService subcategoryService;
  @PostMapping("/subcategories")
  public ResponseEntity<?>addSubcategory(@RequestBody SubcategoryDto subcategoryDto){
    return ResponseEntity.ok(subcategoryService.addsubcategory(subcategoryDto));
  }

    @GetMapping("/subcategories")
    public ResponseEntity<List<SubcategoryDto>> getALLSubcategory(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit)

     {
        return ResponseEntity.ok(subcategoryService.getAllSubcategories(page, limit));
    }
    @GetMapping("/subcategories/{id}")
    public ResponseEntity<SubcategoryDto> getSubcategoryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(subcategoryService.getSubcategoryById(id));
    }
    @GetMapping("/subcategories/search")
    public ResponseEntity<List<SubcategoryDto>> searchSubcategories(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "query", required = false) String query) {
        return ResponseEntity.ok(subcategoryService.searchSubcategories(page , limit, query));
    }
    @DeleteMapping("/subcategories/{id}")
    public SubcategoryDto deleteSubcategory(@PathVariable("id") Long id){
        return this.subcategoryService.DeleteSubcategory(id);
    }
}
