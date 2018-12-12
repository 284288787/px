/**create by liuhua at 2017年6月1日 下午4:36:23**/
package com.booting.mobile.controller;

import com.booting.common.MemoryLoginInfoUtil;
import com.star.framework.security.core.StarUserInfo;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.utils.ParamHandler;

public abstract class ApiBaseController {
	
	protected StarUserInfo getLoginUserInfo(ParamHandler paramHandler) throws ArgsException{
		String token = paramHandler.getString("token");
		System.out.println("token: " + token);
		if (null == token) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "token必填");
		}
		StarUserInfo userInfo = MemoryLoginInfoUtil.getLoginInfo(token);
		if (null == userInfo || null == userInfo.getUserId()) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		return userInfo;
	}
	
	protected Long getLoginUserId(ParamHandler paramHandler) throws ArgsException{
		StarUserInfo userInfo = getLoginUserInfo(paramHandler);
		if (null != userInfo) {
			return userInfo.getUserId();
		}
		return null;
	}

	protected String getLoginUsername(ParamHandler paramHandler) throws ArgsException{
		StarUserInfo userInfo = getLoginUserInfo(paramHandler);
		if (null != userInfo) {
			return userInfo.getUsername();
		}
		return null;
	}
}
