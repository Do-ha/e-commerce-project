package com.example.ecommerce.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@Data
public class HttpResponseWithData <T>extends HttpResponse{
  private T Data;
  @Builder(builderMethodName = "builderWithData")
  public HttpResponseWithData(String message,String status, T data) {
    super(status,message);
    Data = data;
  }
}
