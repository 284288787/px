/**create by liuhua at 2017年5月27日 上午11:03:09**/
package com.booting.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.security.StarFilterInvocationSecurityMetadataSource;

@Aspect
@Service
public class BootingAspectj {
	
	@Autowired
	private StarFilterInvocationSecurityMetadataSource starFilterInvocationSecurityMetadataSource;
	
	@Around("execution(* com.booting.system.facade.impl.SystemFacadeImpl.setRoleResources(..))")
	public Object setRoleResources(ProceedingJoinPoint joinPoint) {
		Object obj = null;
		try{
			obj = joinPoint.proceed();
			starFilterInvocationSecurityMetadataSource.loadResourceDefine();
		} catch (Throwable e) {
			e.printStackTrace();
			obj = null;
		}
		return obj;
	}
}
