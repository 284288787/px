/**create by liuhua at 2017年5月24日 上午9:15:40**/
package com.booting.system.dto;

import java.io.Serializable;
import java.util.Date;

public class UserInfoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long userId;            //用户Id 来自账号表
	private String name;            //姓名
	private String mobile;          //电话
	private String address;         //地址
	private Date lastModifyTime;    //最后修改时间
	private Integer identity;       //身份
	private Integer sex;            //性别 1男 0女
	private String photo;           //头像路径
	private Integer age;            //年龄
	private String clientid;        //app唯一标识
	private String remark;          //签名备注
	private String identityNo;      //身份证号
	private Integer sourceFrom;     //来源 1安卓 2ios
	
	private String userIdentityStr;
	private String userIds;
	
	private String businessIds;
	private Long areaId;
	public Long getUserId() {
		return userId;
	}
	public String getName() {
		return name;
	}
	public String getMobile() {
		return mobile;
	}
	public String getAddress() {
		return address;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public Integer getIdentity() {
		return identity;
	}
	public Integer getSex() {
		return sex;
	}
	public String getPhoto() {
		return photo;
	}
	public Integer getAge() {
		return age;
	}
	public String getClientid() {
		return clientid;
	}
	public String getRemark() {
		return remark;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public Integer getSourceFrom() {
		return sourceFrom;
	}
	public String getUserIdentityStr() {
		return userIdentityStr;
	}
	public String getUserIds() {
		return userIds;
	}
	public String getBusinessIds() {
		return businessIds;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public void setUserIdentityStr(String userIdentityStr) {
		this.userIdentityStr = userIdentityStr;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public void setBusinessIds(String businessIds) {
		this.businessIds = businessIds;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
}
