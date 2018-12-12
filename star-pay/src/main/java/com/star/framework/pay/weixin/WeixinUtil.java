package com.star.framework.pay.weixin;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.star.framework.WeixinConfig;
import com.star.framework.pay.weixin.domain.PayReqData;
import com.star.framework.pay.weixin.domain.PayResData;
import com.star.framework.specification.utils.ParamHandler;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

public class WeixinUtil {

	static Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
//	public static final String appId = "wx18e37c25035345e3";
//	public static final String key = "98iujhrw3f6b87hhko09876421qaxdse";
//	public static final String mchId = "1492885122";                                   //商户号
//	public static final String tradeType = "APP";
//	public static final String notifyUrl = "http://47.104.157.80/api/1.0/wxpay";
//	public static final String spBillCreateIP = "47.104.157.80";
//	public static final String notifyUrl = "http://chuxing.wicp.net/mm/finishOrderWx.do";
//	public static final String notifyUrl = "http://didisunliu1.eicp.net/mm/finishOrderWx.do";
	//统一下单 地址
	private static String unifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	
	private WeixinConfig weixinConfig;
	
	public void setWeixinConfig(WeixinConfig config) {
		this.weixinConfig = config;
	}
	
	private WeixinUtil() {}
	
	private static WeixinUtil instance = null;
	
	public static WeixinUtil getInstance(WeixinConfig config) {
		if (null == instance) {
			instance = new WeixinUtil();
			instance.setWeixinConfig(config);
		}
		return instance;
	}
	
	public PayResData unifiedOrderGZH(PayReqData data){
		try {
			String resXml = new HttpsRequest().sendPost(unifiedOrderUrl, data);
			System.out.println(resXml);
//			String resXml = "<com.star.mami.vo.PayResData><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wxfe0f67d8612b2d5f]]></appid><mch_id><![CDATA[1261895701]]></mch_id><nonce_str><![CDATA[IUYYclitLuf1JOPp]]></nonce_str><sign><![CDATA[5806341F0096E8DFC0EED05CA2646785]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx20151018174522e7ee4e96960029475260]]></prepay_id><trade_type><![CDATA[APP]]></trade_type></com.star.mami.vo.PayResData>";
			resXml = resXml.replace("<xml>", "<com.star.framework.pay.weixin.domain.PayResData>").replace("</xml>", "</com.star.framework.pay.weixin.domain.PayResData>");
			System.out.println(resXml);
			//解决XStream对出现双下划线的bug
	        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
	        //将要提交给API的数据对象转换成XML格式数据Post给API
	        PayResData obje = (PayResData)xStreamForRequestPostData.fromXML(resXml);
	        Date now = ParamHandler.toDate(data.getTime_start(), "yyyyMMddHHmmss");
	        String noncestr = UUID.randomUUID().toString().replace("-", "");
	        Map<String, Object> dd = new HashMap<>();
	        dd.put("appId", weixinConfig.getAppId());
	        dd.put("signType", "MD5");
	        dd.put("package", "prepay_id="+obje.getPrepay_id());
	        dd.put("nonceStr", noncestr);
	        dd.put("timeStamp", now.getTime()/1000);
	        data.setTime_start(now);
	        obje.setSign(Signature.getSign(dd, this.weixinConfig.getKey()));
	        obje.setNonce_str(noncestr);
	        obje.setTimeStamp(now.getTime()/1000);
        	return obje;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public PayResData unifiedOrder(PayReqData data){
		try {
			String resXml = new HttpsRequest().sendPost(unifiedOrderUrl, data);
			System.out.println(resXml);
//			String resXml = "<com.star.mami.vo.PayResData><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wxfe0f67d8612b2d5f]]></appid><mch_id><![CDATA[1261895701]]></mch_id><nonce_str><![CDATA[IUYYclitLuf1JOPp]]></nonce_str><sign><![CDATA[5806341F0096E8DFC0EED05CA2646785]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx20151018174522e7ee4e96960029475260]]></prepay_id><trade_type><![CDATA[APP]]></trade_type></com.star.mami.vo.PayResData>";
			resXml = resXml.replace("<xml>", "<com.star.framework.pay.weixin.domain.PayResData>").replace("</xml>", "</com.star.framework.pay.weixin.domain.PayResData>");
			System.out.println(resXml);
			//解决XStream对出现双下划线的bug
	        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
	        //将要提交给API的数据对象转换成XML格式数据Post给API
	        PayResData obje = (PayResData)xStreamForRequestPostData.fromXML(resXml);
	        Date now = new Date();
	        String noncestr = UUID.randomUUID().toString().replace("-", "");
	        Map<String, Object> dd = new HashMap<>();
	        dd.put("appid", weixinConfig.getAppId());
	        dd.put("partnerid", weixinConfig.getMchId());
	        dd.put("prepayid", obje.getPrepay_id());
	        dd.put("package", "Sign=WXPay");
	        dd.put("noncestr", noncestr);
	        dd.put("timestamp", now.getTime()/1000);
	        data.setTime_start(now);
	        obje.setSign(Signature.getSign(dd, this.weixinConfig.getKey()));
	        obje.setNonce_str(noncestr);
	        obje.setTimeStamp(now.getTime()/1000);
        	return obje;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
