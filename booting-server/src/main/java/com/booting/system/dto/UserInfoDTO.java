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
	private Integer stature;        //身高 cm
	private Integer weight;         //体重 * 10 例如655 表示65.5kg
	private Integer poloShirtNo;    //球衣编号 最大99 不重复
	private Integer teamLocation;   //踢球的位置
	private String clientid;        //app唯一标识
	private String remark;          //签名备注
	private String identityNo;      //身份证号
	private Integer sourceFrom;     //来源 1安卓 2ios
	
	private Integer attentionNum;   //关注数量
	private Integer teamFansNum;    //球队粉丝数量
	private String userIdentityStr;
	private String teamLocationStr;
	private String teamIdentityStr;
	private String teamName;
	private Long teamId;
	
	private String userIds;
	
	private String teamIds;
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

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
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

	public Integer getStature() {
		return stature;
	}

	public Integer getWeight() {
		return weight;
	}

	public Integer getPoloShirtNo() {
		return poloShirtNo;
	}

	public Integer getTeamLocation() {
		return teamLocation;
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

	public void setStature(Integer stature) {
		this.stature = stature;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setPoloShirtNo(Integer poloShirtNo) {
		this.poloShirtNo = poloShirtNo;
	}

	public void setTeamLocation(Integer teamLocation) {
		this.teamLocation = teamLocation;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Integer getAttentionNum() {
		return attentionNum;
	}

	public Integer getTeamFansNum() {
		return teamFansNum;
	}

	public String getTeamLocationStr() {
		return teamLocationStr;
	}

	public String getTeamIdentityStr() {
		return teamIdentityStr;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setAttentionNum(Integer attentionNum) {
		this.attentionNum = attentionNum;
	}

	public void setTeamFansNum(Integer teamFansNum) {
		this.teamFansNum = teamFansNum;
	}

	public void setTeamLocationStr(String teamLocationStr) {
		this.teamLocationStr = teamLocationStr;
	}

	public void setTeamIdentityStr(String teamIdentityStr) {
		this.teamIdentityStr = teamIdentityStr;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getUserIdentityStr() {
		return userIdentityStr;
	}

	public void setUserIdentityStr(String userIdentityStr) {
		this.userIdentityStr = userIdentityStr;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public String getTeamIds() {
		return teamIds;
	}

	public String getBusinessIds() {
		return businessIds;
	}

	public void setTeamIds(String teamIds) {
		this.teamIds = teamIds;
	}

	public void setBusinessIds(String businessIds) {
		this.businessIds = businessIds;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
}
