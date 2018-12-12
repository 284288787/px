/**create by liuhua at 2017年5月20日 下午2:17:54**/
package com.star.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import net.sf.cglib.core.Converter;

/**
 * bean操作
 * 使用了cglib库
 * @author liuhua
 *
 */
public class CglibBeanUtils {

	private static Map<String, BeanCopier> beanCopiers = new HashMap<String, BeanCopier>();
	
	/**
	 * 将 source 的属性值 复制到 target对应的属性里
	 * 属性类型 属性名称 应该完全一致
	 * @param source
	 * @param target
	 */
	public static void copy(Object source, Object target){
		String key = getKey(source.getClass(), target.getClass());
		BeanCopier beanCopier = beanCopiers.get(key);
		if (null == beanCopier) {
			beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
			beanCopiers.put(key, beanCopier);
		}
		beanCopier.copy(source, target, null);
	}
	
	/**
	 * 将 source 的属性值 复制到 target对应的属性里
	 * 属性名称 应该完全一致
	 * 属性类型 可以不同，由converter自定义转换
	 * @param source
	 * @param target
	 * @param converter
	 */
	public static void copy(Object source, Object target, Converter converter){
		String key = getKey(source.getClass(), target.getClass());
		BeanCopier beanCopier = beanCopiers.get(key);
		if (null == beanCopier) {
			beanCopier = BeanCopier.create(source.getClass(), target.getClass(), true);
			beanCopiers.put(key, beanCopier);
		}
		beanCopier.copy(source, target, converter);
	}
	
	/**
	 * 将 source的键值对，复制到target的属性里
	 * 属性名称 应该完全一致
	 * @param source
	 * @param target
	 */
	public static <T> void copy(Map<String, Object> source, T target){
		BeanMap bm = BeanMap.create(target);
		bm.putAll(source);
	}
	
	/**
	 * 将source的所有属性 添加到target里
	 * @param source
	 * @param target
	 */
	public static void addToMap(Object source, Map<String, Object> target){
		addToMap(source, target, null);
	}
	
	public static void addToMap(Object source, Map<String, Object> target, String dateTimePattern) {
		if (null == target) {
			throw new RuntimeException("参数map不能为空");
		}
		if (null != source) {
			BeanMap bm = BeanMap.create(source);
			for (Object key : bm.keySet()) {
				Object value = bm.get(key);
				if (value instanceof Date && StringUtils.isNotBlank(dateTimePattern)) {
					SimpleDateFormat sdf = new SimpleDateFormat(dateTimePattern);
					value = sdf.format(value);
				}
				target.put(key.toString(), value);  
			}
		}
	}
	
	/**
	 * 将bean 转换成 BeanMap
	 * @param bean
	 * @return
	 */
	public static BeanMap toBeanMap(Object bean){
		BeanMap bm = BeanMap.create(bean);
		return bm;
	}
	
	private static String getKey(Class<? extends Object> class1, Class<? extends Object> class2){
		return class1.getName() + "_" + class2.getName();
	}
}
