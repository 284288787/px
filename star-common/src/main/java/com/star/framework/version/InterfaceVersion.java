/**create by liuhua at 2016年7月14日 上午9:17:22**/
package com.star.framework.version;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.web.bind.annotation.Mapping;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface InterfaceVersion {
	
	/**
	 * 版本号 数组 默认1.0
	 * @author liuhua
	 *
	 * @return
	 */
	String[] value() default "1.0";
}
