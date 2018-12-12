/**create by liuhua at 2017年5月18日 下午2:08:41**/
package com.booting.config;

import javax.servlet.Filter;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import com.booting.swagger.SwaggerConfig;
import com.star.framework.security.config.SecurityConfig;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	/*
     * 创建一个可以在非spring管理bean中获得spring管理的相关bean的对象：CP_SpringContext.getBean(String beanName)
     */
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		// TODO Auto-generated method stub
		WebApplicationContext ctx = super.createRootApplicationContext();
//		CP_SpringContext.getInstance().setApplicationContext(ctx);
		
		
		return ctx;
	}
	
	@Override  
    protected Filter[] getServletFilters() {  
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();  
        characterEncodingFilter.setEncoding("UTF-8");  
        characterEncodingFilter.setForceEncoding(true);  
        return new Filter[] {characterEncodingFilter};  
    } 

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[]{AppConfig.class, SecurityConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{MvcConfig.class, SwaggerConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

}
