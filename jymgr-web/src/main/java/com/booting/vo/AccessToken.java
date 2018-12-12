/**create by liuhua at 2017年12月10日 下午5:33:29**/
package com.booting.vo;

public class AccessToken {
	private String access_token;
	private Integer expires_in;
	private String refresh_token;
	private String openid;
	private String scope;
	private String unionid;
	private boolean subscribe;
	private String nickname;
	private String headimgurl;
	private Long createTime = System.currentTimeMillis();

	public String toString(){
		return "\topenId: " + openid + "\n\taccessToken: " + access_token + "\n\tsubscribe: " + subscribe;
	}
	/**
	 * 是否有效 ture 有效 false 无效
	 * 
	 * @return
	 */
	public boolean isExpires() {
		if (createTime + expires_in > System.currentTimeMillis()) {
			return false;
		}
		return true;
	}

	public String getAccess_token() {
		return access_token;
	}

	public Integer getExpires_in() {
		return expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public String getScope() {
		return scope;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
}
