package org.kernel360.precourse.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<Date, String> {

	private String pattern;

	@Override
	public void initialize(Date constraintAnnotation) {
		this.pattern = constraintAnnotation.pattern();
	}

	@Override
	public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
		if (input == null || input.trim().isEmpty()) {
			return false;
		}

		try {
			LocalDate.parse(input, DateTimeFormatter.ofPattern(this.pattern));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
