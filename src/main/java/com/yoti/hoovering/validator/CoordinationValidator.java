package com.yoti.hoovering.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * Validates coordination to be positive
 */
public class CoordinationValidator implements ConstraintValidator<ValidateCoordination, List<Integer>> {

    @Override
    public boolean isValid(List<Integer> list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && list.size() == 2 && list.get(0) >= 0 && list.get(1) >= 0;
    }
}
