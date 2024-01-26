package org.sgcp.springboot.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.sgcp.springboot.app.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IsExistsDbValidation implements ConstraintValidator<IsExistsDb, String> {

  @Autowired
  private ProductService productService;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    return !productService.existsBySku(value);
  }
}
