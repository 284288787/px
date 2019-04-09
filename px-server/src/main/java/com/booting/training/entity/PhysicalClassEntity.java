/**create by liuhua at 2019年4月9日 上午10:28:43**/
package com.booting.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "体测课")
@Entity(name = "px_physical_class")
public class PhysicalClassEntity implements Serializable {
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
  
  @Id
  @Column(name = "physicalClassId")
  public Long getPhysicalClassId() {
    return physicalClassId;
  }
  @Column(name = "title")
  public String getTitle() {
    return title;
  }
  @Column(name = "schoolTime")
  public Date getSchoolTime() {
    return schoolTime;
  }
  @Column(name = "deadlineTime")
  public Date getDeadlineTime() {
    return deadlineTime;
  }
  @Column(name = "address")
  public String getAddress() {
    return address;
  }
  @Column(name = "price")
  public Integer getPrice() {
    return price;
  }
  @Column(name = "enabled")
  public Integer getEnabled() {
    return enabled;
  }
  @Column(name = "deleted")
  public Integer getDeleted() {
    return deleted;
  }
  @Column(name = "createTime")
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
}
