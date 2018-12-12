/**create by liuhua at 2017年5月19日 下午3:09:50**/
package com.booting.config;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
}
