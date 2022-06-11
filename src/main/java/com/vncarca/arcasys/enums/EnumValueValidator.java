/**
* Created by Roy Morocho.
* User: steve
* Date: 11 jun 2022
* Time: 11:32:28
*/
package com.vncarca.arcasys.enums;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<Enum, String> {
	private Enum annotation;

	@Override
	public void initialize(Enum annotation) {
		this.annotation = annotation;
	}

	@Override
	public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext) {
		boolean result = false;

		Object[] enumValues = this.annotation.enumClass().getEnumConstants();

		if (enumValues != null) {
			for (Object enumValue : enumValues) {
				if (valueForValidation.equals(enumValue.toString())
						|| (this.annotation.ignoreCase() && valueForValidation.equalsIgnoreCase(enumValue.toString()))) {
					result = true;
					break;
				}
			}
		}

		return result;
	}
}