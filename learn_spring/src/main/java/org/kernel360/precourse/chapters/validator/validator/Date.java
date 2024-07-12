package org.kernel360.precourse.chapters.validator.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Date {
	String pattern() default "yyyyMMdd";

	String message() default "잘못된 형식의 날짜입니다.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
