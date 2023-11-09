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
    private String productName;
    private Long subCategoryId;
    private String shortDescription;
    private String longDescription;
    private Double price;
    private Double discountPrice;
//    private Array<> options;
    private Boolean active;
    @ManyToMany
    @JoinTable(
            name = "product_order",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> orders;

    @ManyToOne
    private  Subcategory subcategory;

}
