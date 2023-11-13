package com.example.ecommerce.Services;

import com.example.ecommerce.DTOs.ProductDto;
import com.example.ecommerce.Entities.Product;
import com.example.ecommerce.Repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

  private ProductRepository productRepository;

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

  public Product DeleteProduct(Product product , Long id){
    Optional<Product> productTodeleteOptional = this.productRepository.findById(id);

    if (!productTodeleteOptional.isPresent()) {
      return null;
    }
    Product productTodelete=productTodeleteOptional.get();
    this.productRepository.delete(productTodelete);
    return  productTodelete;
  }



}
