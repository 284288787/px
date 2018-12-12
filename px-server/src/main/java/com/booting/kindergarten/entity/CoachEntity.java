/**create by liuhua at 2018年4月19日 上午11:18:39**/
package com.booting.kindergarten.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "教练信息")
@Entity(name = "kindergarten_coach")
public class CoachEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long coachId;                 //主键
	private String pic;                   //头像路径
	private String name;                  //姓名
	private Integer sex;                  //性别 1男 0女
	private Integer age;                  //年龄
	private String mobile;                //手机
	private Integer certificateType;      //证件类型 1身份证
	private String certificateCode;       //证件号码
	private String address;               //联系地址
	private String level;                 //级别 初级 中级 高级
	private String trainingRecord;        //培训记录
	private String workExperience;        //工作经验
	private Date createTime;
	private Date updateTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	
	@Id
	@Column(name = "coachId")
	public Long getCoachId() {
		return coachId;
	}
	@Column(name = "pic")
	public String getPic() {
		return pic;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "sex")
	public Integer getSex() {
		return sex;
	}
	@Column(name = "age")
	public Integer getAge() {
		return age;
	}
	@Column(name = "mobile")
	public String getMobile() {
		return mobile;
	}
	@Column(name = "certificateType")
	public Integer getCertificateType() {
		return certificateType;
	}
	@Column(name = "certificateCode")
	public String getCertificateCode() {
		return certificateCode;
	}
	@Column(name = "address")
	public String getAddress() {
		return address;
	}
	@Column(name = "level")
	public String getLevel() {
		return level;
	}
	@Column(name = "trainingRecord")
	public String getTrainingRecord() {
		return trainingRecord;
	}
	@Column(name = "workExperience")
	public String getWorkExperience() {
		return workExperience;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "deleted")
	public Integer getDeleted() {
		return deleted;
	}
	
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public void setTrainingRecord(String trainingRecord) {
		this.trainingRecord = trainingRecord;
	}
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
