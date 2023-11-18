package com.example.ecommerce.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class People {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  @Column(unique = true)
  private String email;
  private String password;
  @CreationTimestamp
  private Date creationDate;
  private Date lastLogIn;
  private Boolean active=true;

}
