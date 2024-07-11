package org.kernel360.precourse.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

	private String pattern;

	@Override
	public void initialize(YearMonth constraintAnnotation) {
		this.pattern = constraintAnnotation.pattern();
	}

	@Override
	public boolean isValid(String input, ConstraintValidatorContext constraintValidatorContext) {
		if (input == null || input.trim().isEmpty()) {
			return false;
		}
		final String withDayValue = input + "01";
		final String withDayPattern = this.pattern + "dd";

		try {
			LocalDate.parse(withDayValue, DateTimeFormatter.ofPattern(withDayPattern));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
