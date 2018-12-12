/**create by liuhua at 2017年10月17日 上午10:43:43**/
package com.booting.management.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.star.framework.security.core.StarUserInfo;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

public class BaseController {
	
	protected String getLoginUserName() throws ArgsException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null == auth) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		Object object = auth.getPrincipal();
		if (null == object) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		StarUserInfo userInfo = (StarUserInfo) object;
		return userInfo.getName();
	}
	
	protected Long getLoginUserId() throws ArgsException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null == auth) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		Object object = auth.getPrincipal();
		if (null == object) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		StarUserInfo userInfo = (StarUserInfo) object;
		return userInfo.getUserId();
	}

	protected StarUserInfo getLoginUserInfo() throws ArgsException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null == auth) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		Object object = auth.getPrincipal();
		if (null == object) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		StarUserInfo userInfo = (StarUserInfo) object;
		return userInfo;
	}
}
