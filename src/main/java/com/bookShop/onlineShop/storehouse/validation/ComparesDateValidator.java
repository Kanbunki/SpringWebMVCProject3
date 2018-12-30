package com.bookShop.onlineShop.storehouse.validation;

import javax.validation.*;

import org.springframework.beans.*;
import org.springframework.util.*;

public class ComparesDateValidator implements ConstraintValidator<ComparesDate, Object> {

	private String oldDate;
	private String newDate;
	private String message;
	
	public void initialize(ComparesDate constraintAnnotation) {
		this.oldDate = constraintAnnotation.oldDate();
		this.newDate = constraintAnnotation.newDate();
		this.message = constraintAnnotation.message();
	}
	
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		Object oldDateValue = beanWrapper.getPropertyValue(oldDate);
		Object newDateValue = beanWrapper.getPropertyValue(newDate);
		if (ObjectUtils.nullSafeToString(newDateValue).compareTo(ObjectUtils.nullSafeToString(oldDateValue)) >= 0) {
			
			return true;
		} else {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(newDate).addConstraintViolation();
			return false;
		}
	}
}
