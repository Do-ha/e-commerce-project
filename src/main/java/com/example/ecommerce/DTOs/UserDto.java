package com.example.ecommerce.DTOs;

import com.example.ecommerce.Entities.Category;

import java.util.Date;
import java.util.Locale;

public record UserDto(

   String userName,
   String password,
   Category category


) {
}
