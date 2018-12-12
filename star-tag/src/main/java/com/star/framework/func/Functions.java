/**create by liuhua at 2016年11月30日 下午5:44:30**/
package com.star.framework.func;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

public class Functions {
	private static Gson gson = new GsonBuilder().setLongSerializationPolicy(LongSerializationPolicy.STRING).setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	@SuppressWarnings("serial")
	private static Map<Integer, String> week = new HashMap<Integer, String>(){{
		put(1, "周一");
		put(2, "周二");
		put(3, "周三");
		put(4, "周四");
		put(5, "周五");
		put(6, "周六");
		put(7, "周日");
	}};
	
	/**
	 * 将json字符串转换成map
	 * @author liuhua
	 *
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, Object> jsonToMap(String jsonStr){
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		return gson.fromJson(jsonStr, type);
	}
	
	/**
	 * 将json数组字符串转换成list
	 * @author liuhua
	 *
	 * @param jsonArrayStr
	 * @return
	 */
	public static List<Map<String, Object>> jsonArrayToMap(String jsonArrayStr){
		Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
		List<Map<String, Object>> list = gson.fromJson(jsonArrayStr, type);
		return list;
	}
	
	/**
	 * 从json字符串里获取key代表的值
	 * @author liuhua
	 *
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static Object getValueFromJson(String jsonStr, String key){
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		Map<String, Object> map = gson.fromJson(jsonStr, type);
		if (null != map) {
			return map.get(key);
		}
		return null;
	}
	
	public static List<Map<String, Object>> toListMap(List<Object> list){
		String temp = gson.toJson(list);
		Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
		return gson.fromJson(temp, type);
	}
	
	public static Map<String, Object> toMap(Object object){
		String temp = gson.toJson(object);
		Type type = new TypeToken<Map<String, Object>>(){}.getType();
		return gson.fromJson(temp, type);
	}
	
	public static String toString(Object object){
		return gson.toJson(object);
	}
	
	public static String getWeekName(Integer week){
		return Functions.week.get(week);
	}
	
	public static String concat(String a, String b){
	  return a + b;
	}
}
