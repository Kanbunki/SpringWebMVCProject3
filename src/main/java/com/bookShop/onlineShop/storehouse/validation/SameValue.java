package com.bookShop.onlineShop.storehouse.validation;

import java.lang.annotation.*;
import javax.validation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {SameValueValidator.class})
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface SameValue {

	String message() default "{com.bookShop.onlineShop.storehouse.validation.SamValueVlidator.message}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String firstValue();
	String secondValue();
	
	@Target({TYPE, ANNOTATION_TYPE})
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		SameValue[] value();
	}
}
