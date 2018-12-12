/**create by liuhua at 2017年9月3日 上午11:40:19**/
package com.booting.h5.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.booting.common.MemoryLoginCache;
import com.booting.common.PxConstants.LotteryConfig;
import com.booting.common.ValidateCode;
import com.booting.member.dto.MemberDTO;
import com.booting.service.impl.LotteryWebServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.result.v2.ErrorResultMessage;
import com.star.framework.specification.result.v2.ResultMessage;
import com.star.framework.specification.result.v2.SuccessResultMessage;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.utils.MemoryCacheUtil;
import com.star.framework.utils.MemoryCacheUtil.CacheType;

@Controller
public class LotteryController {

	@Autowired
	private LotteryWebServiceImpl lotteryWebServiceImpl;
	
	private List<String> mianLogin = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
		{
			add("login");
			add("loginp");
			add("oi");
			add("history");
			add("openAward");
			add("user");
		}
	};
	
	@RequestMapping("/lottery")
	public String index(Model model) {
		model.addAttribute("moneyView", lotteryWebServiceImpl.getConfig(LotteryConfig.LOTTERY_MONEY_VIEW));
		String[] openTimes = lotteryWebServiceImpl.getConfig(LotteryConfig.LOTTERY_TIME).split(",");
		String openTime = "";
		for (String time : openTimes) {
			openTime += "，" + time + "点";
		}
		model.addAttribute("openTime", openTime.substring(1));
		return "h5/lottery";
	}

	@RequestMapping("/o-{path}")
	public String jump(@PathVariable String path, HttpServletRequest request, Model model) {
		if (! mianLogin.contains(path)) {
			MemberDTO member = MemoryLoginCache.getLoginInfo(request);
			if (null == member) {
				StringBuffer url = request.getRequestURL();
				String qs = request.getQueryString();
				if (StringUtils.isBlank(qs)) {
					qs = "";
				}else{
					qs = "?" + qs;
				}
				return "redirect:/o-loginp?returnUrl=" + url.append(qs);
			}
		}
		String qs = request.getQueryString();
		if (StringUtils.isNotBlank(qs)) {
			qs = qs.replace("returnUrl=", "");
			model.addAttribute("returnUrl", qs);
		}
		return "h5/" + path;
	}
	
	@RequestMapping("/ocode")
	public void code(String mobile, HttpServletResponse response) throws IOException {
		// 设置响应的类型格式为图片格式
		response.setContentType("image/jpeg");
		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		ValidateCode vCode = new ValidateCode(82, 31, 4, 10);
		MemoryCacheUtil.put(CacheType.reg_pic_code, mobile, vCode.getCode());
		
		vCode.write(response.getOutputStream());
	}

	@ResponseBody
	@RequestMapping(value = "/ocodecheck", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/html;charset=UTF-8")
	public String ocodecheck(String mobile, String code) throws IOException {
		ResultMessage resultMessage = null;
		if(StringUtils.isBlank(mobile) || StringUtils.isBlank(code)){
			resultMessage = new ErrorResultMessage(FailureCode.ERR_002);
		}
		String scode = MemoryCacheUtil.get(CacheType.reg_pic_code, mobile, new TypeReference<String>() {});
		if (null != scode && scode.toLowerCase().equals(code.toLowerCase())) {
			resultMessage = new SuccessResultMessage(code);
		}else{
			resultMessage = new ErrorResultMessage(FailureCode.ERR_018);
		}
		return ParamHandler.gson.toJson(resultMessage);
	}
	
	@RequestMapping("/ologout")
	public String logout() {

		return "redirect:/lottery";
	}
}
