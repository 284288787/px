/** create by liuhua at 2018年1月2日 上午10:25:51 **/
package com.booting.kindergarten.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "幼儿园学生信息")
@Entity(name = "kindergarten_student")
public class StudentEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long studentId;
  private Long schoolId;
  private Long classId;
  private String name;
  private String guardianMobile; // 监护人手机
  private Integer sex; // 性别 1男 0女
  private Date birth;
  private Integer stature; // 身高 cm
  private Integer weight; // 体重 kg * 100;
  private Date createTime;
  private Integer enabled; // 是否有效 1有效 0无效
  private Integer deleted; // 是否删除 1已删除 0未删除
  private String braceletMac; // 手环mac地址
  private Integer type; // 类型 1正式 2临时
  private Long physicalClassId; //通过体测课报名的学员

  @Id
  @Column(name = "studentId")
  public Long getStudentId() {
    return studentId;
  }

  @Column(name = "schoolId")
  public Long getSchoolId() {
    return schoolId;
  }

  @Column(name = "classId")
  public Long getClassId() {
    return classId;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  @Column(name = "guardianMobile")
  public String getGuardianMobile() {
    return guardianMobile;
  }

  @Column(name = "sex")
  public Integer getSex() {
    return sex;
  }

  @Column(name = "birth")
  public Date getBirth() {
    return birth;
  }

  @Column(name = "stature")
  public Integer getStature() {
    return stature;
  }

  @Column(name = "weight")
  public Integer getWeight() {
    return weight;
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
  
  @Column(name = "braceletMac")
  public String getBraceletMac() {
    return braceletMac;
  }
  
  @Column(name = "type")
  public Integer getType() {
    return type;
  }
  
  @Column(name = "physicalClassId")
  public Long getPhysicalClassId() {
    return physicalClassId;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }

  public void setSchoolId(Long schoolId) {
    this.schoolId = schoolId;
  }

  public void setClassId(Long classId) {
    this.classId = classId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setGuardianMobile(String guardianMobile) {
    this.guardianMobile = guardianMobile;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  public void setStature(Integer stature) {
    this.stature = stature;
  }

  public void setWeight(Integer weight) {
    this.weight = weight;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  public void setBraceletMac(String braceletMac) {
    this.braceletMac = braceletMac;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public void setPhysicalClassId(Long physicalClassId) {
    this.physicalClassId = physicalClassId;
  }
}
