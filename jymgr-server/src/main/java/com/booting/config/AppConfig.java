/**create by liuhua at 2017年5月18日 下午3:37:00**/
package com.booting.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.star.framework.redis.EncacheCacheConfig;

@Configuration
@ComponentScan(basePackages = {"com.booting", "com.star"}, 
               excludeFilters = { 
                   @ComponentScan.Filter(type = FilterType.ANNOTATION, 
                   value = { Controller.class, ControllerAdvice.class}) 
               }
)
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({EncacheCacheConfig.class, JDBCConfig.class})
public class AppConfig {
  
}
