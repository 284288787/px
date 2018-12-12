/**create by liuhua at 2017年5月24日 上午9:15:40**/
package com.booting.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户信息")
@Entity(name = "sys_userinfo")
public class UserInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long userId;            //用户Id 来自账号表
	private String name;            //姓名
	private String mobile;          //电话
	private String address;         //地址
	private Date lastModifyTime;    //最后修改时间
	private Integer identity;       //身份 UserIdentity
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
	private Integer sourceFrom;     //来源 1安卓 2ios 3后台
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	@Column(name = "lastModifyTime")
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	@Column(name = "identity")
	public Integer getIdentity() {
		return identity;
	}
	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}
	@Column(name = "photo")
	public String getPhoto() {
		return photo;
	}
	@Column(name = "age")
	public Integer getAge() {
		return age;
	}
	@Column(name = "stature")
	public Integer getStature() {
		return stature;
	}
	@Column(name = "weight")
	public Integer getWeight() {
		return weight;
	}
	@Column(name = "poloShirtNo")
	public Integer getPoloShirtNo() {
		return poloShirtNo;
	}
	@Column(name = "teamLocation")
	public Integer getTeamLocation() {
		return teamLocation;
	}
	@Column(name = "clientid")
	public String getClientid() {
		return clientid;
	}
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}
	@Column(name = "identityNo")
	public String getIdentityNo() {
		return identityNo;
	}
	@Column(name = "sourceFrom")
	public Integer getSourceFrom() {
		return sourceFrom;
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
}
