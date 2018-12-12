/**create by liuhua at 2018年6月11日 下午4:17:51**/
package com.booting.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "教练")
@Entity(name = "mgr_coach")
public class CoachEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long memberId;
  private Integer sex;         //性别 1男 0女
  private Date birthTime;
  private Integer certificateType; //证件类型 1身份在 2护照     
  private String certificateCode;  //证件号码
  private String address;
  private String emergencyContact;         //紧急联系人
  private String emergencyContactNumber;   //紧急联系人电话
  private String emergencyContactAddress;  //紧急联系人住址
  private String educationBackground;      //学历
  private String school;               //毕业学校
  private String specialty;            //专业
  private Integer professionalService; //是否职业服役 1是 0否
  private String workExperience;       //工作经历
  private String awardResume;          //获奖履历
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "memberId")
  public Long getMemberId() {
    return memberId;
  }
  @Column(name = "sex")
  public Integer getSex() {
    return sex;
  }
  @Column(name = "birthTime")
  public Date getBirthTime() {
    return birthTime;
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
  @Column(name = "emergencyContact")
  public String getEmergencyContact() {
    return emergencyContact;
  }
  @Column(name = "emergencyContactNumber")
  public String getEmergencyContactNumber() {
    return emergencyContactNumber;
  }
  @Column(name = "emergencyContactAddress")
  public String getEmergencyContactAddress() {
    return emergencyContactAddress;
  }
  @Column(name = "educationBackground")
  public String getEducationBackground() {
    return educationBackground;
  }
  @Column(name = "school")
  public String getSchool() {
    return school;
  }
  @Column(name = "specialty")
  public String getSpecialty() {
    return specialty;
  }
  @Column(name = "professionalService")
  public Integer getProfessionalService() {
    return professionalService;
  }
  @Column(name = "workExperience")
  public String getWorkExperience() {
    return workExperience;
  }
  @Column(name = "awardResume")
  public String getAwardResume() {
    return awardResume;
  }
  
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }
  public void setSex(Integer sex) {
    this.sex = sex;
  }
  public void setBirthTime(Date birthTime) {
    this.birthTime = birthTime;
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
  public void setEmergencyContact(String emergencyContact) {
    this.emergencyContact = emergencyContact;
  }
  public void setEmergencyContactNumber(String emergencyContactNumber) {
    this.emergencyContactNumber = emergencyContactNumber;
  }
  public void setEmergencyContactAddress(String emergencyContactAddress) {
    this.emergencyContactAddress = emergencyContactAddress;
  }
  public void setEducationBackground(String educationBackground) {
    this.educationBackground = educationBackground;
  }
  public void setSchool(String school) {
    this.school = school;
  }
  public void setSpecialty(String specialty) {
    this.specialty = specialty;
  }
  public void setProfessionalService(Integer professionalService) {
    this.professionalService = professionalService;
  }
  public void setWorkExperience(String workExperience) {
    this.workExperience = workExperience;
  }
  public void setAwardResume(String awardResume) {
    this.awardResume = awardResume;
  }
}
