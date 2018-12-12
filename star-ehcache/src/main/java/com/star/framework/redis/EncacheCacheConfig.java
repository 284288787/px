package com.star.framework.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class EncacheCacheConfig extends CachingConfigurerSupport {

  @Bean
  public EhCacheManagerFactoryBean cacheManagerFactoryBean(){
    EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
    bean.setConfigLocation(new ClassPathResource("encache.xml"));
    return bean;
  }
  
  @Override
  public CacheManager cacheManager() {
    EhCacheCacheManager cacheManager = new EhCacheCacheManager(cacheManagerFactoryBean().getObject());
    return cacheManager;
  }

}
