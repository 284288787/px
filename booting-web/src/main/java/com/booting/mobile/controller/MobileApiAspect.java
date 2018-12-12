/**create by liuhua at 2017年6月2日 上午10:06:00**/
package com.booting.mobile.controller;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.result.v2.ErrorResultMessage;
import com.star.framework.specification.result.v2.ResultMessage;
import com.star.framework.specification.result.v2.SuccessForPageInfoResultMessage;
import com.star.framework.specification.result.v2.SuccessResultMessage;
import com.star.framework.utils.DesUtil;

@Service
@Aspect
public class MobileApiAspect {
	public Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	@Around("execution(* com.booting.mobile.controller.*ApiController.*(..))")
	public Object around(ProceedingJoinPoint joinPoint) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("\n[" + simpleDateFormat.format(new Date()) + "] method: " + joinPoint.getSignature().getName());
		Object[] args = joinPoint.getArgs();
		if (null != args && args.length > 0 && ! DesUtil.encrypt_args) {
			System.out.println("args: " + args[0]);
		}
		if (null != args && args.length > 0 && DesUtil.encrypt_args) {
			String params = args[0].toString();
			String temp = DesUtil.decrypt(params);
			System.out.println("args_after_decrypt: " + temp);
			Type type = new TypeToken<Map<String, Object>>(){}.getType();
			gson.fromJson(temp, type);
			args[0] = temp;
		}
		ResultMessage resultMessage = null;
		try {
			Object object = joinPoint.proceed(args);
			resultMessage = new SuccessResultMessage("ok");
			if (null != object && StringUtils.isNotBlank(object.toString())) {
				ApiResult apiResult = gson.fromJson(object.toString(), ApiResult.class);
				if (null != apiResult) {
					if (null != apiResult.getData() && null != apiResult.getPageInfo()) {
						resultMessage = new SuccessForPageInfoResultMessage(apiResult.getData(), apiResult.getPageInfo());
					}else if(null != apiResult.getData()){
						resultMessage = new SuccessResultMessage(apiResult.getData());
					}
				}
			}
		} catch (ArgsException ex) {
//			ex.printStackTrace();
			resultMessage = new ErrorResultMessage(ex.getCode(), ex.getMessage());
		} catch (AuthenticationException ex) {
//			ex.printStackTrace();
			resultMessage = new ErrorResultMessage(FailureCode.ERR_000.getCode(), ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = new ErrorResultMessage(FailureCode.ERR_001);
		} catch (Throwable e) {
			e.printStackTrace();
			resultMessage = new ErrorResultMessage(FailureCode.ERR_001);
		}
		String result = gson.toJson(resultMessage);
		System.out.println("result: " + result);
		if (DesUtil.encrypt_result) {
			result = DesUtil.encrypt(result);
		}
		return result;
	}
	
	
}
