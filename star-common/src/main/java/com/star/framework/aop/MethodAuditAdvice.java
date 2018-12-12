/**create by liuhua at 2016年11月8日 下午3:15:36**/
package com.star.framework.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Service;

import com.star.framework.aop.annotation.Audit;

@Service("methodAuditAdvice")
public class MethodAuditAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object result = invocation.proceed();
		Audit audit = invocation.getMethod().getAnnotation(Audit.class);
		if (null != audit) {
			String d = audit.data();
			String b = audit.businessType();
			System.out.println(d + " " + b);
			Object object = AuditData.datas.get(d);
			System.out.println(object);
			System.out.println("......111111111111111");
		}
		return result;
	}

}
