/**create by liuhua at 2016年11月8日 上午9:27:27**/
package com.star.framework.aop;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.star.framework.aop.annotation.Aop;
import com.star.framework.aop.annotation.Audit;

public class AopListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(event.getServletContext());
		Map<String, Object> aopObject = context.getBeansWithAnnotation(Aop.class);
		for (Iterator<String> iterator = aopObject.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			Object ai = aopObject.get(key);
			Method[] methods = ai.getClass().getDeclaredMethods();
			for (Method method : methods) {
				Audit aop = AnnotationUtils.findAnnotation(method, Audit.class);
				if (null != aop) {
					System.out.println(aop);
				}
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
