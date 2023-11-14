package com.example.ecommerce.Controllers;

import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.DTOs.SubcategoryDto;
import com.example.ecommerce.Entities.Category;
import com.example.ecommerce.Entities.Product;
import com.example.ecommerce.Entities.Subcategory;
import com.example.ecommerce.Repositories.ProductRepository;
import com.example.ecommerce.Repositories.SubCategoryRepository;
import com.example.ecommerce.Services.ProductService;
import com.example.ecommerce.mappers.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;




@RequestMapping("/v1")
@RestController
@AllArgsConstructor
public class ProductController {

  private ProductRepository productRepository;
  private ProductService productService;
  @GetMapping("/products")

  public ResponseEntity<List<ProductDto>> getAllProducts(

    @RequestParam(name = "page", defaultValue = "1") int page,
    @RequestParam(name = "limit", defaultValue = "10") int limit
  ) {
    return ResponseEntity.ok(productService.getAllProducts(page, limit));
  }


@GetMapping("/products/{id}")
public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
    return ResponseEntity.ok(productService.getProductById(id));
}

  @PutMapping("/products/{id}")
  public Product updateProduct (@PathVariable("id") Long id, @RequestBody Product product){
    return this.productService.update(product, id);
  }
@DeleteMapping("/products/{id}")
  public ProductDto deleteProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
    return this.productService.DeleteProduct(productDto,id);
}
@PostMapping("/products")
  public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto ){
    return ResponseEntity.ok(productService.addProduct(productDto));
}
  @GetMapping("/search")
  public ResponseEntity<List<ProductDto>> searchProducts(
    @RequestParam(name = "page", defaultValue = "1") int page,
    @RequestParam(name = "limit", defaultValue = "10") int limit,
    @RequestParam(name = "query", required = false) String query) {
return ResponseEntity.ok(productService.searchProducts(page, limit, query));

  }




}
