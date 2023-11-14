package com.example.ecommerce.Services;

import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.Entities.Product;
import com.example.ecommerce.Repositories.ProductRepository;
import com.example.ecommerce.mappers.ProductMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

  private ProductRepository productRepository;
  public ProductDto addProduct(ProductDto productDto) {
    return ProductMapper.mapEntityToDTO(productRepository.save(ProductMapper.mapDtoToEntity(productDto)));


  }
  public ProductDto getProductById(Long id) {
    Optional<Product> productOptional = productRepository.findById(id);

    if (productOptional.isPresent()) {
      ProductDto productDto = ProductMapper.mapEntityToDTO(productOptional.get());
      return productDto ;
    }
    else {
      throw new EntityNotFoundException("Product not found");
    }
  }

  public Product update(Product product, Long id  ) {
    Optional<Product> productTopatchOptional = this.productRepository.findById(id);

    if (!productTopatchOptional.isPresent()) {
      return null;
    }
    Product productTopatch = productTopatchOptional.get();
    if (product.getProductName()!=null){
      productTopatch.setProductName(product.getProductName());
    }
    if (product.getActive()!=null){
      productTopatch.setActive(!product.getActive());

    }
    if (product.getProductImage()!=null){
      productTopatch.setProductImage(product.getProductImage());

    }
    if (product.getDiscountPrice()!=null){
      productTopatch.setDiscountPrice(product.getDiscountPrice());

    }
    if (product.getLongDescription()!=null){
      productTopatch.setLongDescription(product.getLongDescription());

    }
    if (product.getShortDescription()!=null){
      productTopatch.setShortDescription(product.getShortDescription());

    }
    if (product.getPrice()!=null){
      productTopatch.setPrice(product.getPrice());

    }
    if (product.getSku()!=null){
      productTopatch.setSku(product.getSku());

    }
    if (product.getOptions()!=null){
      productTopatch.setOptions(product.getOptions());

    }

    if (product.getSubcategory()!=null){
      productTopatch.setSubcategory(product.getSubcategory());

    }
    if (product.getOrders()!=null){
      productTopatch.setOrders(product.getOrders());

    }

    return this.productRepository.save(productTopatch);
  }

  public ProductDto DeleteProduct(ProductDto productDto , Long id){
    Optional<Product> productTodeleteOptional = this.productRepository.findById(id);


    if (!productTodeleteOptional.isPresent()) {
      return null;
    }
    Product productTodelete=productTodeleteOptional.get();
    this.productRepository.delete(productTodelete);
    return ProductMapper.mapEntityToDTO(productTodelete);
  }
//  }    return ProductMapper.mapProductToDTO(productRepository.save(ProductMapper.mapDtoToEntity(productDto)));


  public List<ProductDto> getAllProducts(
    @RequestParam(name = "page", defaultValue = "1") int page,
    @RequestParam(name = "limit", defaultValue = "10") int limit
  ) {
    // Use PageRequest for pagination
     Pageable pageable = PageRequest.of(page, limit);

    // Use findAll with pageable directly
    Page<Product> productPage = productRepository.findAll(pageable);
    if(productPage.hasContent()){
    return productPage.getContent().stream()
      .map(ProductMapper::mapEntityToDTO)
      .collect(Collectors.toList());
  }
  else{ throw new EntityNotFoundException("Product not found");
  }


}
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
    if (productPage.hasContent()){
    return productPage.getContent().stream()
      .map(ProductMapper::mapEntityToDTO)
      .collect(Collectors.toList());}
    else{
      throw new EntityNotFoundException("Product not found");
    }
  }


}
