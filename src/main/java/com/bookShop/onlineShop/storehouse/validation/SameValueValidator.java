package com.bookShop.onlineShop.storehouse.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ObjectUtils;

public class SameValueValidator implements ConstraintValidator<SameValue, Object> {

	private String firstValue;
	private String secondValue;
	private String message;
	
	public void initialize(SameValue sameValue) {
		this.firstValue = sameValue.firstValue();
		this.secondValue = sameValue.secondValue();
		this.message = sameValue.message();
	}
	
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(value);
		Object stValue = beanWrapper.getPropertyValue(firstValue);
		Object ndValue = beanWrapper.getPropertyValue(secondValue);
		if (ObjectUtils.nullSafeEquals(stValue, ndValue)) {
			return true;
		} else {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addPropertyNode(secondValue).addConstraintViolation();
			return false;
		}
	}
}
