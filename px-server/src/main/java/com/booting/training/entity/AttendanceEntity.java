/**create by liuhua at 2019年4月12日 下午2:08:17**/
package com.booting.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "签到")
@Entity(name = "px_attendance")
public class AttendanceEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private Integer type;    //1体测课签到
  private Long businessId;    //业务对象id, type=1为 体测课id
  private Long attendanceId;  //签到对象Id, type=1为 studentId
  private Date createTime;    //签到时间
  private Integer state;      //状态 1签到 2迟到 3请假 4其他
  private String remark;
  
  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }
  @Column(name = "type")
  public Integer getType() {
    return type;
  }
  @Column(name = "businessId")
  public Long getBusinessId() {
    return businessId;
  }
  @Column(name = "attendanceId")
  public Long getAttendanceId() {
    return attendanceId;
  }
  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }
  @Column(name = "state")
  public Integer getState() {
    return state;
  }
  @Column(name = "remark")
  public String getRemark() {
    return remark;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  public void setType(Integer type) {
    this.type = type;
  }
  public void setBusinessId(Long businessId) {
    this.businessId = businessId;
  }
  public void setAttendanceId(Long attendanceId) {
    this.attendanceId = attendanceId;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public void setState(Integer state) {
    this.state = state;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
}
