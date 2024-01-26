package org.sgcp.springboot.app.services;

import org.sgcp.springboot.app.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

  List<Product> findAll();

  Optional<Product> findById(Long id);

  Product save(Product product);

  Optional<Product> update(Product product, Long id);

  Optional<Product> delete(Long id);

  boolean existsBySku(String sku);

}
