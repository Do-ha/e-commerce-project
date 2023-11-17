package com.example.ecommerce.Controllers;

import com.example.ecommerce.DTOs.CategoryDto;
import com.example.ecommerce.DTOs.HttpResponse;
import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Services.SubcategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/v1")
public class SubcategoryController {
  private SubcategoryService subcategoryService;
  @PostMapping("/subcategories")
  public ResponseEntity<?>addSubcategory(@RequestBody SubcategoryDto subcategoryDto){

    return ResponseEntity.ok(subcategoryService.addSubcategory(subcategoryDto));
  }

  @PutMapping("/subcategories/{id}")
  public ResponseEntity<?> updatesubcategory(@PathVariable("id") Long id, @RequestBody SubcategoryDto subcategoryDto) {
    SubcategoryDto updatedsubcategoryDto = this.subcategoryService.updateSubcategory(subcategoryDto, id);

    if (updatedsubcategoryDto != null) {
      // Product updated successfully
      return ResponseEntity.ok(HttpResponse.builder().status("200").message("product created successfully").build());
    } else {
      throw new EntityNotFoundException("Subcategory already exists");
    }
  }}


//  @PostMapping("/subcategories")
//  public ResponseEntity<?> addSubcategories(@RequestBody SubcategoryDto subcategoryDto ){
//
//    if(subcategoryService.addSubcategory(subcategoryDto)!=null){
//    return ResponseEntity.ok(HttpResponse.builder().status("200").message("subcategory created successfully").build());
//  }
//    return ResponseEntity.badRequest(HttpResponse.builder().status(HttpStatus.BAD_REQUEST.toString()).message("subcategory name already exists").build());
//
//}}}
