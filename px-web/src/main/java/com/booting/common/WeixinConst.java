package com.booting.common;

import com.star.framework.WeixinConfig;

public class WeixinConst extends WeixinConfig {
	public final String appId = "wx18e37c25035345e3";
	public final String key = "98iujhrw3f6b87hhko09876421qaxdse";
	public final String appSecret = "fc37985af81059f461ccb4eb791993c1";
	public final String mchId = "1492885122";
	public final String tradeType = "JSAPI";
	public final String notifyUrl = "http://47.104.157.80/api/1.0/wxpay";
	public final String spBillCreateIP = "47.104.157.80";

	@Override
	public String getAppId() {
		return appId;
	}

	@Override
	public String getAppSecret() {
		return appSecret;
	}

	@Override
	public String getMchId() {
		return mchId;
	}

	@Override
	public String getNotifyUrl() {
		return notifyUrl;
	}

	@Override
	public String getServerIp() {
		return spBillCreateIP;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getTradeType() {
		return tradeType;
	}
}
