package org.sgcp.springboot.app.services.impl;

import org.sgcp.springboot.app.entities.Product;
import org.sgcp.springboot.app.repositories.ProductRepository;
import org.sgcp.springboot.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  private ProductRepository productRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Product> findAll() {
    return (List<Product>) productRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<Product> findById(Long id) {
    return productRepository.findById(id);
  }

  @Override
  @Transactional
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public Optional<Product> update(Product product, Long id) {
    Optional<Product> productOptional = productRepository.findById(id);
    if( productOptional.isPresent() ) {
      Product productDB = productOptional.orElseThrow();

      productDB.setSku(product.getSku());
      productDB.setName(product.getName());
      productDB.setDescription(product.getDescription());
      productDB.setPrice(product.getPrice());
      return Optional.of(productRepository.save(productDB));
    }
    return productOptional;
  }

  @Override
  @Transactional
  public Optional<Product> delete(Long id) {
    Optional<Product> productOptional = productRepository.findById(id);
    productOptional.ifPresent(productDB -> productRepository.delete(productDB));
    return productOptional;
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsBySku(String sku) {
    return productRepository.existsBySku(sku);
  }

}
