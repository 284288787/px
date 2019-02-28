/**create by liuhua at 2019年2月28日 上午10:27:35**/
package com.booting.training.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训上课校区")
@Entity(name = "px_study_address")
public class StudyAddressEntity implements Serializable {
  private static final long serialVersionUID = -3199489089128012645L;
  
  private Long addrId;
  private String addrName;
  private Integer deleted;  //删除 1是 0否
  private Integer enabled;  //禁用 1是 0否
  private Integer viewFront;//显示 1在前端显示 其他不显示
  private Date createTime;
  
  @Id
  @Column(name = "addrId")
  public Long getAddrId() {
    return addrId;
  }
  @Column(name = "addrName")
  public String getAddrName() {
    return addrName;
  }
  @Column(name = "deleted")
  public Integer getDeleted() {
    return deleted;
  }
  @Column(name = "enabled")
  public Integer getEnabled() {
    return enabled;
  }
  @Column(name = "viewFront")
  public Integer getViewFront() {
    return viewFront;
  }
  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }
  
  public void setAddrId(Long addrId) {
    this.addrId = addrId;
  }
  public void setAddrName(String addrName) {
    this.addrName = addrName;
  }
  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }
  public void setViewFront(Integer viewFront) {
    this.viewFront = viewFront;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
