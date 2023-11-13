package com.example.ecommerce.DTOs;

import com.example.ecommerce.Entities.Product;

import java.util.Date;
import java.util.List;

public record OrderDto(

  String customerId,

  Double cartTotalPrice,
  List<Product> products


) {
}
