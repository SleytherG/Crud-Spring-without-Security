package org.sgcp.springboot.app.repositories;

import org.sgcp.springboot.app.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

  boolean existsBySku(String sku);

}
