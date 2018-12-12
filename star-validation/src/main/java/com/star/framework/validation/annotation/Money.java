/**create by liuhua at 2016年6月3日 下午3:23:46**/
package com.star.framework.validation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.star.framework.validation.validator.MoneyValidator;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MoneyValidator.class)
public @interface Money {
	String message() default "金额错误，xxxx.xx";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	double min() default 0.0;
	double max() default 99999999999999999.99;
}
