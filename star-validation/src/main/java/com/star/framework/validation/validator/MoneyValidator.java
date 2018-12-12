/**create by liuhua at 2016年6月3日 下午3:24:49**/
package com.star.framework.validation.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.star.framework.validation.annotation.Money;

public class MoneyValidator implements ConstraintValidator<Money, Object> {
	private String moneyReg = "^\\d+(\\.\\d{1,2})?$";
	private Pattern moneyPattern = Pattern.compile(moneyReg);
	
	double min;
	double max;
	
	
	public void initialize(Money money) {
		this.min = money.min();
		this.max = money.max();
	}

	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		if (null == obj) {
			return true;
		}
		try{
			Double value = null;
			if (obj instanceof Double) {
				value = (Double) obj;
			}else if(obj instanceof String){
				value = Double.parseDouble(obj.toString());
			}else{
				return false;
			}
			boolean bool = moneyPattern.matcher(value.toString()).matches();
			if (bool && value >= min && value <= max) {
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}

}
