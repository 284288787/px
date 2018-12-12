///**create by liuhua at 2017年6月15日 下午3:23:48**/
//package com.booting.aop;
//
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Parameter;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiImplicitParams;
//
///**
// * 根据swagger的标签校验必填参数
// * @author liuhua
// *
// */
//@Aspect
//@Component
//public class ControllerAspect {
//	//javac -parameters 通过jdk获取参数名称，需要打开开关
//	
//	@Around("execution(* com.huinong.truffle.supply.basic.mono.web.controller.*Controller.*(..))")
//	public Object around(ProceedingJoinPoint joinPoint) {
//		try {
//			checkParams(joinPoint);
//			Object object = joinPoint.proceed();
//			return object;
//		} catch (ResultException e) {
//			return e.getBaseResult();
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	private void checkParams(ProceedingJoinPoint joinPoint) throws ResultException, Throwable {
//		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//		List<String> argNames = new ArrayList<>();
//		ApiImplicitParams apiImplicitParams = methodSignature.getMethod().getAnnotation(ApiImplicitParams.class);
//		if (null != apiImplicitParams) {
//			ApiImplicitParam[] params = apiImplicitParams.value();
//			if (null != params && params.length > 0) {
//				for (ApiImplicitParam apiImplicitParam : params) {
//					if (apiImplicitParam.required()) {
//						argNames.add(apiImplicitParam.name());
//					}
//				}
//			}
//		}
//		Map<String, Object> baseArgMap = new HashMap<>();
//		Map<String, Object> beanArgMap = new HashMap<>();
//		Object[] args = joinPoint.getArgs();
//		Parameter[] parameters = methodSignature.getMethod().getParameters();
//		if (null != parameters && parameters.length > 0) {
//			for (int i = 0; i < parameters.length; i++) {
//				Parameter parameter = parameters[i];
//				Type clazz = parameter.getParameterizedType();
//				if (clazz.equals(Character.class) || clazz.equals(Short.class) || clazz.equals(Integer.class) || 
//					clazz.equals(Integer.class) || clazz.equals(Float.class) || clazz.equals(Double.class) || 
//					clazz.equals(Long.class) || clazz.equals(Boolean.class) || clazz.equals(String.class)) {
//					baseArgMap.put(parameter.getName(), args[i]);
//				}else{
//					beanArgMap.put(parameter.getName(), args[i]);
//				}
//				
//			}
//		}
//		boolean bool = true;
//		if (! argNames.isEmpty() && ! baseArgMap.isEmpty()) {
//			for (String argName : argNames) {
//				if (baseArgMap.containsKey(argName)) {
//					Object arg = baseArgMap.get(argName);
//					if (null == arg) {
//						bool = false;
//						break;
//					}
//				}
//			}
//		}
//		if (bool) {
//			if (! argNames.isEmpty() && ! beanArgMap.isEmpty()) {
//				for (String argName : argNames) {
//					if(! notNullValueInBean(beanArgMap, argName)){
//						bool = false;
//						break;
//					}
//				}
//			}
//		}
//		if (! bool) {
//			Object object = methodSignature.getReturnType().newInstance();
//			setValue(object);
//			throw new ResultException((BaseResult<?>)object);
//		}
//	}
//
//	private void setValue(Object object) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		Method methodCode = object.getClass().getMethod("setCode", int.class);
//		Method methodMsg = object.getClass().getMethod("setMsg", String.class);
//		methodCode.invoke(object, ErrorCode.Err_16501.getCode());
//		methodMsg.invoke(object, "参数错误，必填参数未填");
//	}
//	
//	private boolean notNullValueInBean(Map<String, Object> beanArgMap, String argName) throws SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		for (Iterator<Object> iterator = beanArgMap.values().iterator(); iterator.hasNext();) {
//			Object object = iterator.next();
//			if (! notNullValueInBean(object, argName)) {
//				return false;
//			}
//		}
//		return true;
//	}
//
//	private boolean notNullValueInBean(Object bean, String argName) throws SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		String methodName = "get" + firstCharToUpper(argName);
//		try{
//			Method method = bean.getClass().getMethod(methodName);
//			Object object = method.invoke(bean);
//			return null != object;
//		}catch(NoSuchMethodException e){
//			
//		}
//		return true;
//	}
//
//	private String firstCharToUpper(String argName) {
//		return argName.substring(0, 1).toUpperCase() + argName.substring(1);
//	}
//}
