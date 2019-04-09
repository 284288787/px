/**create by liuhua at 2019年4月9日 上午10:28:43**/
package com.booting.training.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "体测课")
public class PhysicalClassDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long physicalClassId;  //体测课id
  private String title;          //课程标题
  private Date schoolTime;       //上课时间
  private Date deadlineTime;     //报名截止时间
  private String address;
  private Integer price;         //* 100
  private Integer enabled;
  private Integer deleted;
  private Date createTime;
  private String physicalClassIds;
  
  private String beginSchoolTime;
  
  public Long getPhysicalClassId() {
    return physicalClassId;
  }
  
  public String getTitle() {
    return title;
  }
  
  public Date getSchoolTime() {
    return schoolTime;
  }
  
  public Date getDeadlineTime() {
    return deadlineTime;
  }
  
  public String getAddress() {
    return address;
  }
  
  public Integer getPrice() {
    return price;
  }
  
  public Integer getEnabled() {
    return enabled;
  }
  
  public Integer getDeleted() {
    return deleted;
  }
  
  public Date getCreateTime() {
    return createTime;
  }
  
  public void setPhysicalClassId(Long physicalClassId) {
    this.physicalClassId = physicalClassId;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public void setSchoolTime(Date schoolTime) {
    this.schoolTime = schoolTime;
  }
  public void setDeadlineTime(Date deadlineTime) {
    this.deadlineTime = deadlineTime;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public void setPrice(Integer price) {
    this.price = price;
  }
  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }
  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getPhysicalClassIds() {
    return physicalClassIds;
  }

  public void setPhysicalClassIds(String physicalClassIds) {
    this.physicalClassIds = physicalClassIds;
  }

  public String getBeginSchoolTime() {
    return beginSchoolTime;
  }

  public void setBeginSchoolTime(String beginSchoolTime) {
    this.beginSchoolTime = beginSchoolTime;
  }
}
