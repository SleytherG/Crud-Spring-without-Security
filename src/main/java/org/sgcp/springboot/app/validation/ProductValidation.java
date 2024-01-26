package org.sgcp.springboot.app.validation;

import org.sgcp.springboot.app.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProductValidation implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return Product.class.isAssignableFrom(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    Product product = (Product) target;
    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "es requerido!");
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotBlank.product.description");
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotNull.product.price");
//    ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "Min.product.price");

    if ( product.getDescription() == null || product.getDescription().isBlank() ) {
      errors.rejectValue("description", null, "es requerido, por favor");
    }

    if ( product.getPrice() == null ) {
//      errors.rejectValue("price", "NotNull.product.price");
      errors.rejectValue("price", null, "no puede ser nulo, ok!");
    } else if ( product.getPrice() < 500 ) {
//      errors.rejectValue("price", "Min.product.price");
      errors.rejectValue("price", null, "debe ser un valor numerico mayor o igual que 500!");
    }

  }
}
