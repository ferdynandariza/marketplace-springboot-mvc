package io.github.ferdynandariza.marketplace.validation;

import io.github.ferdynandariza.marketplace.service.ShipmentService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueShipmentNameValidator implements ConstraintValidator<UniqueShipmentName, String> {

    @Autowired
    ShipmentService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return service.isShipmentNameValid(value);
    }
}
