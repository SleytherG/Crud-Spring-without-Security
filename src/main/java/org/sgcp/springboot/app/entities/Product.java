package org.sgcp.springboot.app.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.sgcp.springboot.app.validation.IsExistsDb;
import org.sgcp.springboot.app.validation.IsRequired;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

//  @NotEmpty(message = "{NotEmpty.product.name}")
  @IsRequired(message = "{IsRequired.product.name}")
  @Size(min = 3, max = 20)
  private String name;

  @Min(value = 500, message = "{Min.product.price}")
  @NotNull(message = "{NotNull.product.price}")
  private Integer price;

//  @NotBlank(message = "{NotBlank.product.description}")
  @IsRequired
  private String description;

  @IsExistsDb
  @IsRequired
  private String sku;

}
