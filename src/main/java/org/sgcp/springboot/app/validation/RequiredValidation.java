package org.sgcp.springboot.app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

public class RequiredValidation implements ConstraintValidator<IsRequired, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
//    return value != null && !value.isEmpty() && !value.isBlank();
    return StringUtils.hasText(value);
  }

}
