/**create by liuhua at 2016年6月16日 下午2:20:06**/
package com.star.framework.jdbc.dao.rowmapper;

import java.util.Date;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;

public class RowMapperFactory<T> {
	private Class<T> requiredType;

	public RowMapperFactory(Class<T> requiredType) {
		this.requiredType = requiredType;
	}

	public RowMapper<T> getRowMapper() {
		if ((this.requiredType.equals(String.class)) || (Number.class.isAssignableFrom(this.requiredType)) || (this.requiredType.equals(Date.class))) {
			return new SingleColumnRowMapper<T>(this.requiredType);
		}
		return new BeanPropertyRowMapper<T>(this.requiredType);
	}
}