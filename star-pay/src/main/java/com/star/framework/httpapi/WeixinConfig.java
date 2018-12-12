/**create by liuhua at 2017年12月7日 下午2:49:44**/
package com.star.framework.httpapi;

public class WeixinConfig {

	private boolean debug;
	private String appId;
	private Long timestamp;
	private String nonceStr;
	private String signature;
	private String[] jsApiList;
	
	public WeixinConfig(String noncestr, Long timestamp, String signature){
		this.debug = false;
		this.appId = WeiXinUtil.appId;
		this.nonceStr = noncestr;
		this.timestamp = timestamp;
		this.jsApiList = new String[]{"onMenuShareTimeline","onMenuShareAppMessage", "onMenuShareQQ", 
				"onMenuShareWeibo", "onMenuShareQZone"};
		this.signature = signature;
	}

	public boolean isDebug() {
		return debug;
	}

	public String getAppId() {
		return appId;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public String getSignature() {
		return signature;
	}

	public String[] getJsApiList() {
		return jsApiList;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setJsApiList(String[] jsApiList) {
		this.jsApiList = jsApiList;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
}
