/**create by liuhua at 2018年1月2日 上午10:12:03**/
package com.booting.kindergarten.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "幼儿园信息")
@Entity(name = "kindergarten_info")
public class KindergartenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long schoolId;
	private String account;
	private String password;
	private String name;
	private String linkman;            //联系人
	private String mobile;             //联系人电话
	private String tel;                //联系人座机
	private Long areaId;
	private String address;
	private String intro;
	private Date createTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	
	@Id
	@Column(name = "schoolId")
	public Long getSchoolId() {
		return schoolId;
	}
	@Column(name = "account")
	public String getAccount() {
		return account;
	}
	@Column(name = "password")
	public String getPassword() {
		return password;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "linkman")
	public String getLinkman() {
		return linkman;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "tel")
	public String getTel() {
		return tel;
	}
	@Column(name = "areaId")
	public Long getAreaId() {
		return areaId;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "deleted")
	public Integer getDeleted() {
		return deleted;
	}
	
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
