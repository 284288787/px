/**create by liuhua at 2017年6月28日 上午9:17:28**/
package com.booting.team.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "球队成员关系")
public class TeamMemberDTO implements Serializable {
	private static final long serialVersionUID = -308466815070060876L;
	
	private Long id;
	private Long teamId;
	private Long userId;
	private Integer teamIdentity; //球队身份 1教练 2队医 3球员 4啦啦队  TeamIdentity
	private Date createTime;
	private Date modifyTime;
	
	private String teamName;
	private String userName;
	private String mobile;          //电话
	private String address;         //地址
	private Integer sex;            //性别 1男 0女
	private String photo;           //头像路径
	private Integer age;            //年龄
	private Integer stature;        //身高 cm
	private Integer weight;         //体重 * 10 例如655 表示65.5kg
	private Integer poloShirtNo;    //球衣编号 最大99 不重复
	private Integer teamLocation;   //踢球的位置
	private Integer userIdentity;   //身份 1会员 2球队管理员 3球队副管理员
	private String identityNo;      //身份证号
	private Integer status;       //状态 1待审核 2审核通过 3审核不通过
	private String noPassReason;  //不通过原因
	
	
	private String states;
	
	public Long getId() {
		return id;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getUserName() {
		return userName;
	}

	public String getMobile() {
		return mobile;
	}

	public String getAddress() {
		return address;
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

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getTeamIdentity() {
		return teamIdentity;
	}

	public void setTeamIdentity(Integer teamIdentity) {
		this.teamIdentity = teamIdentity;
	}

	public Integer getUserIdentity() {
		return userIdentity;
	}

	public void setUserIdentity(Integer userIdentity) {
		this.userIdentity = userIdentity;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public Integer getStatus() {
		return status;
	}

	public String getNoPassReason() {
		return noPassReason;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}
}
