/**create by liuhua at 2018年4月19日 上午11:18:39**/
package com.booting.kindergarten.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.star.framework.aop.annotation.Description;

@Description(name = "教练信息")
public class CoachDTO implements Serializable {
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
	
	private String coachIds;
	
	private List<ClassCoachRelationDTO> classRelations;
	
	public Long getCoachId() {
		return coachId;
	}
	
	public String getPic() {
		return pic;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getSex() {
		return sex;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public Integer getCertificateType() {
		return certificateType;
	}
	
	public String getCertificateCode() {
		return certificateCode;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getLevel() {
		return level;
	}
	
	public String getTrainingRecord() {
		return trainingRecord;
	}
	
	public String getWorkExperience() {
		return workExperience;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
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

	public Integer getEnabled() {
		return enabled;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getCoachIds() {
		return coachIds;
	}

	public void setCoachIds(String coachIds) {
		this.coachIds = coachIds;
	}

	public List<ClassCoachRelationDTO> getClassRelations() {
		return classRelations;
	}

	public void setClassRelations(List<ClassCoachRelationDTO> classRelations) {
		this.classRelations = classRelations;
	}
}
