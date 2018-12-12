/**create by liuhua at 2017年7月6日 下午4:45:23**/
package com.booting.mobile.controller;

import java.io.InputStream;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.booting.order.dto.AlipayPayDetailDTO;
import com.booting.order.dto.WeixinPayDetailDTO;
import com.booting.service.OrderWebService;
import com.star.framework.specification.utils.ParamHandler;
import com.star.framework.version.InterfaceVersion;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

@RestController
@RequestMapping("/api")
public class PayCallbackController {

	@Autowired
	private OrderWebService orderWebService;

	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/wxpay", method = { RequestMethod.POST, RequestMethod.GET })
	public String wxpayCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(".................微信支付返回");
		InputStream is = request.getInputStream();
		StringBuffer sBuffer = new StringBuffer();
		byte[] b = new byte[1024];
		int len = 0;
		while ((len = is.read(b)) != -1) {
			sBuffer.append(new String(b, 0, len));
		}
		String resXml = sBuffer.toString();
		resXml = resXml.replace("<xml>", "<com.booting.order.dto.WeixinPayDetailDTO>").replace("</xml>", "</com.booting.order.dto.WeixinPayDetailDTO>");
		System.out.println(resXml);
		XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		WeixinPayDetailDTO payOrder = (WeixinPayDetailDTO) xStreamForRequestPostData.fromXML(resXml);
		boolean bool = orderWebService.wxpayCallback(payOrder);
		if (bool) {
			return "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
		} else {
			return "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[FAIL]]></return_msg></xml>";
		}
	}

	@InterfaceVersion("1.0")
	@RequestMapping(value = "/{version}/alipay", method = { RequestMethod.POST, RequestMethod.GET })
	public String alipayCallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(".................支付宝支付返回");
		Enumeration<?> parameterNames = request.getParameterNames();
		String params = "";
		while (parameterNames.hasMoreElements()) {
			String name = (String) parameterNames.nextElement();
			String value = request.getParameter(name);
			String key = ParamHandler.changeName(name);
			params += "," + key + ":'" + value + "'";
			System.err.println(name + " " + value);
		}
		if (params.length() > 0) {
			params = "{" + params.substring(1) + "}";
		}
		AlipayPayDetailDTO alipayOrder = ParamHandler.strToObj(params, AlipayPayDetailDTO.class);
		boolean bool = orderWebService.alipayCallback(alipayOrder);
		if (bool) {
			return "success";
		} else {
			return "fail";
		}
	}
}
