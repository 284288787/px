/**create by liuhua at 2017年6月17日 上午9:22:03**/
package com.booting.court.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "球场")
@Entity(name = "court_info")
public class CourtEntity implements Serializable {
	private static final long serialVersionUID = -9189593414022981386L;
	
	private Long courtId;
	private String courtName;
	private Long userId;       //创建者
	private String linkman;
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
	
	@Id
	@Column(name = "courtId")
	public Long getCourtId() {
		return courtId;
	}
	@Column(name = "courtName")
	public String getCourtName() {
		return courtName;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "linkman")
	public String getLinkman() {
		return linkman;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	@Column(name = "areaId")
	public Long getAreaId() {
		return areaId;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "longitude")
	public String getLongitude() {
		return longitude;
	}
	@Column(name = "latitude")
	public String getLatitude() {
		return latitude;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "startMinute")
	public Integer getStartMinute() {
		return startMinute;
	}
	@Column(name = "endMinute")
	public Integer getEndMinute() {
		return endMinute;
	}
	@Column(name = "oneMinute")
	public Integer getOneMinute() {
		return oneMinute;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	@Column(name = "logo")
	public String getLogo() {
		return logo;
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
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public void setLogo(String logo) {
		this.logo = logo;
	}
}
