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
import org.mockito.quality.Strictness;

import jakarta.validation.ConstraintValidatorContext;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class YearMonthValidatorTest {

	private YearMonthValidator yearMonthValidator = new YearMonthValidator();
	private final String defaultPattern = "yyyyMM";

	@Mock
	private YearMonth yearMonthAnnotation;
	@Mock
	private ConstraintValidatorContext constraintValidatorContext;

	@BeforeEach
	void setUp() {
		yearMonthValidator = new YearMonthValidator();
	}

	@Nested
	@DisplayName("Common")
	public class CommonTest {

		@Test
		@DisplayName("입력 문자열로 null이 들어온 경우")
		void isValid_nullString() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn(defaultPattern);

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid(null, constraintValidatorContext);

			// then
			assertEquals(false, result);
		}

		@Test
		@DisplayName("pattern 이 유효하지 않은 경우")
		void isValid_invalidPattern() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn("abcde");

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("20210101", constraintValidatorContext);

			// then
			assertEquals(false, result);
		}

		@Test
		@DisplayName("pattern이 빈 문자열인 경우")
		void isValid_emptyPattern() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn("");

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("20210101", constraintValidatorContext);

			// then
			assertEquals(false, result);
		}
	}

	@Nested
	@DisplayName("Default Pattern")
	public class DefaultPatternTest {

		@Test
		@DisplayName("입력 문자열로 빈 문자열이 들어온 경우")
		void isValid_blankString() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn(defaultPattern);

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("", constraintValidatorContext);

			// then
			assertEquals(false, result);
		}

		@Test
		@DisplayName("유효한 입력 - 일반")
		void isValid() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn(defaultPattern);

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("202101", constraintValidatorContext);

			// then
			assertEquals(true, result);
		}

		@Test
		@DisplayName("유효한 입력 - 1900년 이전")
		void isValid_InvalidYear() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn(defaultPattern);

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("100101", constraintValidatorContext);

			// then
			assertTrue(result);
		}

		@Test
		@DisplayName("유효하지 않은 입력 - 날 포함")
		void isValid_Invalid() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn(defaultPattern);

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("20210101", constraintValidatorContext);

			// then
			assertFalse(result);
		}

		@Test
		@DisplayName("유효하지 않은 입력 - 없는 달")
		void isValid_InvalidMonth() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn(defaultPattern);

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("202113", constraintValidatorContext);

			// then
			assertFalse(result);
		}

	}

	@Nested
	@DisplayName("Custom Pattern")
	public class CustomPattern_Test {

		@Test
		@DisplayName("pattern이 yy-MM인 경우 - OK")
		void isValid_yyMM() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn("yy-MM");

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("21-01", constraintValidatorContext);

			// then
			assertEquals(true, result);
		}

		@Test
		@DisplayName("pattern이 yy-MM인 경우 - NG")
		void isValid_yyMM_Invalid() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn("yy-MM");

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("2021-01", constraintValidatorContext);

			// then
			assertEquals(false, result);
		}

		@Test
		@DisplayName("pattern이 yyyy-MM인 경우 - OK")
		void isValid_yyyyMM() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn("yyyy-MM");

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("2021-01", constraintValidatorContext);

			// then
			assertEquals(true, result);
		}

		@Test
		@DisplayName("pattern이 yyyy-MM인 경우 - NG")
		void isValid_yyyyMM_Invalid() {
			// given
			given(yearMonthAnnotation.pattern()).willReturn("yyyy-MM");

			// when
			yearMonthValidator.initialize(yearMonthAnnotation);
			final boolean result = yearMonthValidator.isValid("21-01", constraintValidatorContext);

			// then
			assertEquals(false, result);
		}
	}

}
