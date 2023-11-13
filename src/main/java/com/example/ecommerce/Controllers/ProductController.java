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

  public List<ProductDto> getAllProducts(
    @RequestParam(name = "page", defaultValue = "1") int page,
    @RequestParam(name = "limit", defaultValue = "10") int limit
  ) {
    // Use PageRequest for pagination
    Pageable pageable = PageRequest.of(page , limit);

    // Use findAll with pageable directly
    Page<Product> productPage = productRepository.findAll(pageable);
    return productPage.getContent().stream()
      .map(ProductMapper::mapProductToDTO)
      .collect(Collectors.toList());


}


  @GetMapping("/products/{id}")
  public Optional<Product> getProductById(@PathVariable("id") Long id){
    return this.productRepository.findById(id);
  }
  @PutMapping("/products/{id}")
  public Product updateProduct (@PathVariable("id") Long id, @RequestBody Product product){
    return this.productService.update(product, id);
  }
@DeleteMapping("/products/{id}")
  public Product deleteProduct(@PathVariable("id") Long id, @RequestBody Product product){
    return this.productService.DeleteProduct(product,id);
}
@PostMapping("/products")
  public ResponseEntity<Product> addProduct(@RequestBody Product product ){
     this.productRepository.save(product);
     return new ResponseEntity<>(product, HttpStatus.CREATED);
}
  @GetMapping("/search")
  public List<ProductDto> searchProducts(
    @RequestParam(name = "page", defaultValue = "1") int page,
    @RequestParam(name = "limit", defaultValue = "10") int limit,
    @RequestParam(name = "query", required = false) String query) {

    // Use PageRequest for pagination
    Pageable pageable = PageRequest.of(page , limit); // Adjust page to be zero-indexed

    // Use findAll or findByProductName with pageable directly
    Page<Product> productPage;
    if (query != null && !query.isEmpty()) {
      productPage = productRepository.findByProductName(query, pageable);
    } else {
      productPage = productRepository.findAll(pageable);
    }

    // Transform products to a List of DTOs using stream and map
    return productPage.getContent().stream()
      .map(ProductMapper::mapProductToDTO)
      .collect(Collectors.toList());
  }




}
