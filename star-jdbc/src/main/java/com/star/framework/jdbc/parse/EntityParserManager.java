/**create by liuhua at 2016年6月8日 上午11:16:50**/
package com.star.framework.jdbc.parse;

import java.util.HashMap;
import java.util.Map;

public class EntityParserManager {
	private static Map<Class<? extends Object>, EntityParser> cache = new HashMap<>();

	public static EntityParser getEntityParser(Class<? extends Object> clazz) {
		EntityParser sqlParser = (EntityParser) cache.get(clazz);
		if (sqlParser == null) {
			sqlParser = new EntityParser(clazz);
			synchronized (cache) {
				if (cache.get(clazz) == null) {
					cache.put(clazz, sqlParser);
				}
			}
		}
		return sqlParser;
	}
}
