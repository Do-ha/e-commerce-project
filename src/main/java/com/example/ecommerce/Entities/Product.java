package com.example.ecommerce.Entities;

import jakarta.persistence.*;
import jakarta.websocket.Decoder;
import jakarta.websocket.Encoder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.Text;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sku;
    private String productImage;
    @Column(unique = true)
    private String productName;
    private String shortDescription;
    private String longDescription;
    private Double price;
    private Double discountPrice;
  private List<String> options;
  private boolean duplicated;
    private Boolean active;
    //aded this
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "product_order",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    @ManyToOne(fetch = FetchType.EAGER)
    private  Subcategory subcategory;
    @ManyToOne(fetch = FetchType.EAGER)
    Category category;

}
