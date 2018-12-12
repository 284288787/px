/**create by liuhua at 2016年6月8日 上午11:40:25**/
package com.star.framework.jdbc.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeUtils {
	private static Logger logger = LoggerFactory.getLogger(SomeUtils.class);

	public static void setProperty(Object targetObject, String propertyName, Object propertyValue) {
		try {
			Field field = targetObject.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			if ((propertyValue instanceof BigDecimal)) {
				if ((field.getType().isAssignableFrom(Integer.class)) || (field.getType().isAssignableFrom(Integer.TYPE))) {
					field.set(targetObject, Integer.valueOf(((BigDecimal) propertyValue).intValue()));
				}
				if ((field.getType().isAssignableFrom(Long.class)) || (field.getType().isAssignableFrom(Long.TYPE))) {
					field.set(targetObject, Long.valueOf(((BigDecimal) propertyValue).longValue()));
				}
				if ((field.getType().isAssignableFrom(Double.class)) || (field.getType().isAssignableFrom(Double.TYPE))) {
					field.set(targetObject, Double.valueOf(((BigDecimal) propertyValue).doubleValue()));
				}
				if ((field.getType().isAssignableFrom(Float.class)) || (field.getType().isAssignableFrom(Float.TYPE))) {
					field.set(targetObject, Float.valueOf(((BigDecimal) propertyValue).floatValue()));
				}
				if ((field.getType().isAssignableFrom(Byte.class)) || (field.getType().isAssignableFrom(Byte.TYPE))) {
					field.set(targetObject, Byte.valueOf(((BigDecimal) propertyValue).byteValue()));
				}

			} else if ((field.getType().isAssignableFrom(Integer.class)) || (field.getType().isAssignableFrom(Integer.TYPE))) {
				field.set(targetObject, Integer.valueOf(Integer.parseInt(propertyValue.toString())));
			} else {
				field.set(targetObject, propertyValue);
			}
		} catch (SecurityException e) {
			logger.warn(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			logger.warn(e.getMessage(), e);
		} catch (NoSuchFieldException e) {
			logger.warn(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.warn(e.getMessage(), e);
		}
	}
}
