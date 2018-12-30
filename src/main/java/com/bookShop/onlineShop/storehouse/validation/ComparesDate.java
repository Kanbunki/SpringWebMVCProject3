package com.bookShop.onlineShop.storehouse.validation;

import java.lang.annotation.*;
import javax.validation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {ComparesDateValidator.class})
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface ComparesDate {

	String message() default "{com.bookShop.onlineShop.storehouse.validation.ComparesDateValidator.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String oldDate();
	String newDate();
	
	@Target({TYPE, ANNOTATION_TYPE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		ComparesDate[] value();
	}
}
