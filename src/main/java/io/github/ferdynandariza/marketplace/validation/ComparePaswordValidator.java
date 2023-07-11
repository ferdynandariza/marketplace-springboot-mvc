package io.github.ferdynandariza.marketplace.validation;

import io.github.ferdynandariza.marketplace.dto.account.RegisterDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ComparePaswordValidator implements ConstraintValidator<ComparePassword, RegisterDTO> {

    @Override
    public boolean isValid(RegisterDTO value, ConstraintValidatorContext context) {
        var password = value.getPassword();
        var passwordConfirmation = value.getPasswordConfirmation();
        return password.equals(passwordConfirmation);
    }

}
