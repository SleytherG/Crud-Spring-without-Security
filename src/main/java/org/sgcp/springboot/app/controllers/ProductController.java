package org.sgcp.springboot.app.controllers;

import jakarta.validation.Valid;
import org.sgcp.springboot.app.entities.Product;
import org.sgcp.springboot.app.services.ProductService;
import org.sgcp.springboot.app.validation.ProductValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  @Autowired
  private ProductService productService;

//  @Autowired
//  private ProductValidation productValidation;

  @GetMapping
  public List<Product> list() {
    return productService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> view(@PathVariable Long id) {
    Optional<Product> productOptional = productService.findById(id);
    if (productOptional.isPresent()) {
      return ResponseEntity.ok().body(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping
//  public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
  public ResponseEntity<?> create(@Valid @RequestBody Product product, BindingResult result) {
//    productValidation.validate(product, result);
    if ( result.hasFieldErrors() ) {
      return validation(result);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> update(@Valid @RequestBody Product product, BindingResult result, @PathVariable Long id) {
//    productValidation.validate(product, result);
    if ( result.hasFieldErrors() ) {
      return validation(result);
    }

    Optional<Product> productOptional = productService.update(product, id);
    if ( productOptional.isPresent() ) {
      return ResponseEntity.status(HttpStatus.CREATED).body(productOptional.get());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable Long id) {
    Optional<Product> productOptional = productService.delete(id);
    if ( productOptional.isPresent() ) {
      return ResponseEntity.ok(productOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  private ResponseEntity<?> validation(BindingResult result) {
    Map<String, String> errors = new HashMap<>();
    result.getFieldErrors().forEach(fieldError -> {
      errors.put(fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage());
    });
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
  }

}
