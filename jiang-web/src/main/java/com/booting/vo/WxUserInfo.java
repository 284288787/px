/**create by liuhua at 2018年2月28日 上午9:28:41**/
package com.booting.vo;

import java.util.List;

public class WxUserInfo {

	private String openid;
	private String nickname;
	private Integer sex;
	private String province;
	private String city;
	private String country;
	private String headimgurl;
	private List<String> privilege;
	private String unionid;
	
	public String getOpenid() {
		return openid;
	}
	public String getNickname() {
		return nickname;
	}
	public Integer getSex() {
		return sex;
	}
	public String getProvince() {
		return province;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public List<String> getPrivilege() {
		return privilege;
	}
	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}
}
