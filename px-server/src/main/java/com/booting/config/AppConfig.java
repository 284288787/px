/**create by liuhua at 2017年5月18日 下午3:37:00**/
package com.booting.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = {"com.booting", "com.star"}, excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class}) })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({JDBCConfig.class})
public class AppConfig {

}
