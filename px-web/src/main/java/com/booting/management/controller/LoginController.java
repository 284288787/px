/**create by liuhua at 2017年5月26日 上午10:45:04**/
package com.booting.management.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.star.framework.specification.FailureCode;
import com.star.framework.specification.result.ResultMessage;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(){
		
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value = "/getLoginInfo", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ResultMessage getLoginInfo(){
		ResultMessage resultMessage = null;
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (null != auth) {
				resultMessage = new ResultMessage(auth.getPrincipal(), "获取登录信息");
			}else{
				resultMessage = new ResultMessage("获取登录信息", FailureCode.ERR_003);
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultMessage = new ResultMessage("获取登录信息", FailureCode.ERR_001);
		}
		return resultMessage;
	}
}
