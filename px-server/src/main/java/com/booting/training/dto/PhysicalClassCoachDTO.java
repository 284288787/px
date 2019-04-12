/**create by liuhua at 2019年4月9日 上午10:28:43**/
package com.booting.training.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "体测课教练")
public class PhysicalClassCoachDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long id;
  private Long physicalClassId;  //体测课id
  private Long coachId;
  private Date createTime;
  
    
  public Long getId() {
    return id;
  }
  
  public Long getPhysicalClassId() {
    return physicalClassId;
  }
  
  public Long getCoachId() {
    return coachId;
  }
  
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
