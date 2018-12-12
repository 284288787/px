/**create by liuhua at 2017年5月26日 上午11:09:40**/
package com.star.framework.security.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class StarUserInfo extends User{
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String name;
	
	private Integer identity;
	
	private List<? extends Serializable> resources;
	
	private List<Map<String, Object>> navMenus;
	
	private List<Map<String, Object>> menu;
	
	private List<Map<String, Object>> userPackages;
	private List<Map<String, Object>> userServices;
	
	private String token;
	
	private long loginTime;
	
	private String headPic;
	private String openId;
	
	public StarUserInfo(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.loginTime = System.currentTimeMillis();
	}
	
	public StarUserInfo(Integer identity, Long userId, String name, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, List<? extends Serializable> resources, List<Map<String, Object>> menu, List<Map<String, Object>> navMenus, List<Map<String, Object>> packages, List<Map<String, Object>> services) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.identity = identity;
		this.userId = userId;
		this.name = name;
		this.resources = resources;
		this.menu = menu;
		this.navMenus = navMenus;
		this.userPackages = packages;
		this.userServices = services;
		this.loginTime = System.currentTimeMillis();
	}

	public StarUserInfo(String mobile, String name, String openId, String headPic){
		super(mobile, "", true, true, true, true, null);
		this.name = name;
		this.headPic = headPic;
		this.openId = openId;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<? extends Serializable> getResources() {
		return resources;
	}

	public void setResources(List<? extends Serializable> resources) {
		this.resources = resources;
	}

	public List<Map<String, Object>> getMenu() {
		return menu;
	}

	public void setMenu(List<Map<String, Object>> menu) {
		this.menu = menu;
	}

	public List<Map<String, Object>> getNavMenus() {
		return navMenus;
	}

	public void setNavMenus(List<Map<String, Object>> navMenus) {
		this.navMenus = navMenus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	public List<Map<String, Object>> getUserServices() {
		return userServices;
	}

	public void setUserServices(List<Map<String, Object>> userServices) {
		this.userServices = userServices;
	}

	public List<Map<String, Object>> getUserPackages() {
		return userPackages;
	}

	public void setUserPackages(List<Map<String, Object>> userPackages) {
		this.userPackages = userPackages;
	}

	public String getHeadPic() {
		return headPic;
	}

	public String getOpenId() {
		return openId;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
