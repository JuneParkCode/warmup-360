package org.kernel360.precourse.validator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import jakarta.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = org.mockito.quality.Strictness.STRICT_STUBS)
class DateValidatorTest {

	private DateValidator dateValidator;
	private final String defaultPattern = "yyyyMMdd";

	@Mock
	private Date dateAnnotation;

	@Mock
	private ConstraintValidatorContext constraintValidatorContext;

	@BeforeEach
	void setUp() {
		dateValidator = new DateValidator();
	}

	@Nested
	@DisplayName("Common Test")
	class CommonTest {
		@Test
		@DisplayName("입력 문자열로 null이 들어온 경우")
		void isValid_nullString() {
			// given
			given(dateAnnotation.pattern()).willReturn(defaultPattern);
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid(null, constraintValidatorContext);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("pattern 이 유효하지 않은 경우")
		void isValid_invalidPattern() {
			// given
			given(dateAnnotation.pattern()).willReturn("abcde");
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid("20210101", constraintValidatorContext);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("pattern 이 null 인 경우")
		void isValid_nullPattern() {
			// given
			given(dateAnnotation.pattern()).willReturn(null);
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid("20210101", constraintValidatorContext);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("입력 문자열로 빈 문자열이 들어온 경우")
		void isValid_emptyString() {
			// given
			given(dateAnnotation.pattern()).willReturn(defaultPattern);
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid("", constraintValidatorContext);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("입력 문자열로 공백이 들어온 경우")
		void isValid_blankString() {
			// given
			given(dateAnnotation.pattern()).willReturn(defaultPattern);
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid("   ", constraintValidatorContext);

			// then
			assertFalse(result);
		}
	}

	@Nested
	@DisplayName("Default pattern Test")
	class DefaultPatternTest {
		@BeforeEach
		void setUp() {
			given(dateAnnotation.pattern()).willReturn(defaultPattern);
			dateValidator.initialize(dateAnnotation);
		}

		@Test
		@DisplayName("유효한 입력")
		void isValid_validInput() {
			// given
			// when
			final boolean result = dateValidator.isValid("20210101", constraintValidatorContext);

			// then
			assertTrue(result);
		}

		@Test
		@DisplayName("유효한 입력 - 1900년대 이전")
		void isValid_validInputBefore1900() {
			// given
			// when
			final boolean result = dateValidator.isValid("00100101", constraintValidatorContext);

			// then
			assertTrue(result);
		}

		@Test
		@DisplayName("유효하지 않은 입력 - 시간이 포함된 입력")
		void isValid_withTimeInput() {
			// given
			// when
			final boolean result = dateValidator.isValid("20210101 12:34:56", constraintValidatorContext);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("유효하지 않은 입력 - Day 가 없는 경우")
		void isValid_invalidInput() {
			// given
			// when
			final boolean result = dateValidator.isValid("202101", constraintValidatorContext);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("유효하지 않은 입력 - 형식이 잘못된 경우")
		void isValid_notDateInput() {
			// given
			// when
			final boolean result = dateValidator.isValid("2021-01-01", constraintValidatorContext);

			// then
			assertFalse(result);
		}

	}

	@Nested
	@DisplayName("Custom pattern Test")
	class CustomPatternTest {
		@Test
		@DisplayName("pattern 이 yy-MM-dd 인 경우 - OK")
		void isValid_yyMMdd() {
			// given
			given(dateAnnotation.pattern()).willReturn("yy-MM-dd");
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid("21-01-01", constraintValidatorContext);

			// then
			assertTrue(result);
		}

		@Test
		@DisplayName("pattern 이 yyyy-MM-dd 인 경우 - OK")
		void isValid_yyyyMMdd() {
			// given
			given(dateAnnotation.pattern()).willReturn("yyyy-MM-dd");
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid("2021-01-01", constraintValidatorContext);

			// then
			assertTrue(result);
		}

		@Test
		@DisplayName("pattern 이 yyyy-MM-dd 인 경우 - NG")
		void isValid_yyyyMMdd_Invalid() {
			// given
			given(dateAnnotation.pattern()).willReturn("yyyy-MM-dd");
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid("21-01-01", constraintValidatorContext);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("pattern 이 yy-MM-dd 인 경우 - NG")
		void isValid_yyMMdd_Invalid() {
			// given
			given(dateAnnotation.pattern()).willReturn("yy-MM-dd");
			dateValidator.initialize(dateAnnotation);

			// when
			final boolean result = dateValidator.isValid("2021-01-01", constraintValidatorContext);

			// then
			assertFalse(result);
		}

	}
}
