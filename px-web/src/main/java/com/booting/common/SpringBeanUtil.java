/**create by liuhua at 2018年1月20日 下午2:15:11**/
package com.booting.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Service
public class SpringBeanUtil implements ApplicationContextAware {

	private static ApplicationContext context;  
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public static <T> T getBean(String name, Class<T> clazz){
		if (StringUtils.isNotBlank(name)) {
			return context.getBean(name, clazz);
		}
		return null;
	}
}
