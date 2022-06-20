/**
* Created by Roy Morocho.
* User: steve
* Date: 11 jun 2022
* Time: 11:31:52
*/
package com.vncarca.arcasys.enums;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { EnumValueValidator.class })
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
		ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Enum {
	String regexp();

	String message() default "contine valor no válido, debe ser {regexp}";

	public abstract Class<?>[] groups() default {};

	public abstract Class<? extends Payload>[] payload() default {};

	public abstract Class<? extends java.lang.Enum<?>> enumClass();

	public abstract boolean ignoreCase() default false;
}