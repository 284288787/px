/**create by liuhua at 2017年5月18日 下午4:29:08**/
package com.booting.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.framework.version.CustomRequestMappingHandlerMapping;

@Configuration
// @EnableWebMvc
@ComponentScan(basePackages = { "com.booting.**.controller" }, 
               useDefaultFilters = false, 
               includeFilters = { 
                   @ComponentScan.Filter(type = FilterType.ANNOTATION, 
                   value = { Controller.class, ControllerAdvice.class }) 
               }
)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MvcConfig extends WebMvcConfigurationSupport {

  @Bean("viewResolver")
  public ViewResolver viewResolver() throws IOException {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setOrder(1);
    viewResolver.setViewClass(JstlView.class);
    viewResolver.setPrefix("/WEB-INF/jsp/");
    viewResolver.setSuffix(".jsp");
    Properties props = new Properties();
    InputStream inputStream = getClass().getResourceAsStream("/star-setting.properties");
    props.load(inputStream);
    viewResolver.setAttributes(props);
    return viewResolver;
  }

  @Bean
  public RequestMappingHandlerAdapter handlerAdapter() {
    RequestMappingHandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();

    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
    stringHttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
    mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
    List<MediaType> supportedMediaTypes = Arrays.asList(MediaType.TEXT_PLAIN, MediaType.TEXT_HTML, MediaType.APPLICATION_JSON_UTF8);
    mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
    List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
    messageConverters.add(stringHttpMessageConverter);
    messageConverters.add(mappingJackson2HttpMessageConverter);
    handlerAdapter.setMessageConverters(messageConverters);
    return handlerAdapter;
  }

  @Override
  protected RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
    return new CustomRequestMappingHandlerMapping();
  }

  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(localeChangeInterceptor());
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("MP_verify_CwvsHjk3WzPkyA9Q.txt").addResourceLocations("classpath:/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    registry.addResourceHandler("/px/**", "/bui/**", "/static/**", "/swagger/**").addResourceLocations("/px/", "/bui/", "/static/", "/swagger/");
    ;
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/index.html");
    super.addViewControllers(registry);
  }

  /**
   * 文件上传处理器
   */
  @Bean(name = "multipartResolver")
  public CommonsMultipartResolver commonsMultipartResolver() {
    CommonsMultipartResolver cmr = new CommonsMultipartResolver();
    return cmr;
  }

  @Bean
  public LocaleResolver localeResolver() {
    SessionLocaleResolver slr = new SessionLocaleResolver();
    slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
    return slr;
  }

  @Bean
  public LocaleChangeInterceptor localeChangeInterceptor() {
    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
    lci.setParamName("lang");
    return lci;
  }

  @Bean("messageSource")
  public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource() {
    ReloadableResourceBundleMessageSource r = new ReloadableResourceBundleMessageSource();
    r.addBasenames("classpath:i18n/messages");
    return r;
  }
}
