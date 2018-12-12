/**create by liuhua at 2017年6月17日 上午9:22:03**/
package com.booting.court.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "球场")
public class CourtDTO implements Serializable {
	private static final long serialVersionUID = -9189593414022981386L;
	
	private Long courtId;
	private Long userId;       //创建者
	private String courtName;
	private String linkman;
	private String account;
	private String mobile;
	private String address;
	private Long areaId;
	private String intro;      //描述
	private String remark;     //备注
	private String longitude;  //经度
	private String latitude;   //纬度
	private Integer enabled;   //1可用 0禁用
	private Integer startMinute;     //开始营业时间(分)
	private Integer endMinute;       //结束营业时间(分)
	private Integer oneMinute;       //一场球的时间(分)
	private Date createTime;
	private Date modifyTime;
	private String logo;
	
	private Integer siteNum;   //场地个数
	private String areaName;
	private String userName;
	
	private String courtIds;
	
	public Long getCourtId() {
		return courtId;
	}
	
	public String getCourtName() {
		return courtName;
	}
	
	public String getLinkman() {
		return linkman;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public String getAddress() {
		return address;
	}
	
	public Long getAreaId() {
		return areaId;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public Integer getEnabled() {
		return enabled;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	
	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Integer getSiteNum() {
		return siteNum;
	}

	public void setSiteNum(Integer siteNum) {
		this.siteNum = siteNum;
	}

	public Long getUserId() {
		return userId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCourtIds() {
		return courtIds;
	}

	public void setCourtIds(String courtIds) {
		this.courtIds = courtIds;
	}

	public Integer getStartMinute() {
		return startMinute;
	}

	public Integer getEndMinute() {
		return endMinute;
	}

	public Integer getOneMinute() {
		return oneMinute;
	}

	public void setStartMinute(Integer startMinute) {
		this.startMinute = startMinute;
	}

	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}

	public void setOneMinute(Integer oneMinute) {
		this.oneMinute = oneMinute;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
}
