/**create by liuhua at 2017年6月1日 下午4:36:23**/
package com.booting.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import com.booting.common.MemoryLoginCache;
import com.booting.member.dto.MemberDTO;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.utils.ParamHandler;

public abstract class ApiBaseController {
	
	protected String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
//			System.out.println(ip + "-----" + "X-Real-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
//			System.out.println(ip + "-----" + "Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
//			System.out.println(ip + "-----" + "WL-Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
//			System.out.println(ip + "-----" + "HTTP_CLIENT_IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//			System.out.println(ip + "-----" + "HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
//			System.out.println(ip + "-----" + "getRemoteAddr");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteHost();
//			System.out.println(ip + "-----" + "getRemoteHost");
		}

//		System.out.println("ip==>" + ip);
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.contains(",")) {
			String str[] = ip.split(",");
			ip = str[0];
			/*
			 * if(ip.indexOf(",")>0){ ip = ip.substring(0,ip.indexOf(",")); }
			 */
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	protected MemberDTO getLoginUserInfo(ParamHandler paramHandler) throws ArgsException{
		String token = paramHandler.getString("token");
		if (null == token) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "token必填");
		}
		MemberDTO userInfo = null;
		if (null != token) {
			userInfo = MemoryLoginCache.getLoginInfo(token);
		}
		if (null == userInfo || null == userInfo.getMemberId()) {
			throw new ArgsException(FailureCode.ERR_003);
		}
		return userInfo;
	}
	
	protected Long getLoginUserId(ParamHandler paramHandler) throws ArgsException{
		MemberDTO userInfo = getLoginUserInfo(paramHandler);
		if (null != userInfo) {
			return userInfo.getMemberId();
		}
		return null;
	}

	protected String getLoginUsername(ParamHandler paramHandler) throws ArgsException{
		MemberDTO userInfo = getLoginUserInfo(paramHandler);
		if (null != userInfo) {
			return userInfo.getName();
		}
		return null;
	}
}
