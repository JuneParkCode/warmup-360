package org.kernel360.precourse.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Constraint(validatedBy = YearMonthValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface YearMonth {
	String pattern() default "yyyyMM";

	String message() default "잘못된 형식의 년월입니다.";

	Class<?>[] groups() default {};

	Class<?>[] payload() default {};
}
