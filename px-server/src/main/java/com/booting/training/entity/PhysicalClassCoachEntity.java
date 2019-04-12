/**create by liuhua at 2019年4月9日 上午10:28:43**/
package com.booting.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "体测课教练")
@Entity(name = "px_physical_class_coach")
public class PhysicalClassCoachEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long id;
  private Long physicalClassId;  //体测课id
  private Long coachId;
  private Date createTime;
  
  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }
  @Column(name = "physicalClassId")
  public Long getPhysicalClassId() {
    return physicalClassId;
  }
  @Column(name = "coachId")
  public Long getCoachId() {
    return coachId;
  }
  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public void setPhysicalClassId(Long physicalClassId) {
    this.physicalClassId = physicalClassId;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public void setCoachId(Long coachId) {
    this.coachId = coachId;
  }
}
