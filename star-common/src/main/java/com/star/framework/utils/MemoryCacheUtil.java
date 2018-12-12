/**create by liuhua at 2017年12月20日 上午9:21:44**/
package com.star.framework.utils;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class MemoryCacheUtil {
	private final static Logger logger = LoggerFactory.getLogger(MemoryCacheUtil.class);
	
	public enum CacheType{
		reg_pic_code(3000);
		
		//该类型的缓存key的数量
		private Integer size;
		
		private CacheType(Integer size){
			this.size = size;
		}

		public Integer getSize() {
			return size;
		}

		public void setSize(Integer size) {
			this.size = size;
		}
	}
	
	private static Cache<CacheType, Cache<Object, Object>> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
	
	public static <T> void put(CacheType cacheType, Object key, T object) {
		put(cacheType, 1000 * 60 * 10, key, object);
	}
	
	public static <T> void put(CacheType cacheType, long timeMillis, Object key, T object) {
		try {
			Cache<Object, Object> map = cache.get(cacheType, new Callable<Cache<Object, Object>>(){
				public Cache<Object, Object> call() throws Exception {
					return CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).maximumSize(cacheType.getSize()).build();
				}
			});
			map.put(key, object);			
		} catch (Exception e) {
			logger.error("", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(CacheType cacheType, Object key, TypeReference<T> typeReference){
		try {
			Cache<Object, Object> map = cache.getIfPresent(cacheType);
			if (null == map || map.size() == 0) {
				cache.invalidate(cacheType);
				return null;
			}			
			Object object = map.getIfPresent(key);
			if (null == object) {
				map.invalidate(key);
				logger.info("从缓存获取数据------>" + cacheType.name() + "(" + key + ")，缓存数据已被清除");
			}else{
				logger.info("从缓存获取数据------>" + cacheType.name() + "(" + key + ")，已从缓存取到数据");
			}
			map.cleanUp();
			return (T) object;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
}