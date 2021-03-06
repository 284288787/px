/**create by liuhua at 2016年6月8日 上午11:19:03**/
package com.star.framework.jdbc.parse;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class ValueParser {
	private static Logger logger = LoggerFactory.getLogger(ValueParser.class);

	public static Map<String, Object> parser(Object entity) {
		Map<String, Object> values = new HashMap<>();
		Method[] methods = entity.getClass().getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Column.class)) {
				Column column = (Column) method.getAnnotation(Column.class);
				PropertyDescriptor descriptor = BeanUtils.findPropertyForMethod(method);
				String key = descriptor.getName();
				Object value = null;
				try {
					value = method.invoke(entity, new Object[0]);
					if ((value instanceof Date))
						value = dateFormat(column, (Date) value);
				} catch (Exception e) {
					logger.debug("reflect error.[" + method + "]", e);
				}
				if ((column.table() != null) && (!"".equals(column.table()))) {
					values.put("ROUTETABLE", column.table());
				}
				values.put(key, value);
			}
		}

		return values;
	}

	private static Object dateFormat(Column column, Date date) {
		if ((date != null) && (!"".equals(column.columnDefinition()))) {
			SimpleDateFormat format = new SimpleDateFormat(column.columnDefinition());
			return format.format(date);
		}
		return date;
	}
}
