/**create by liuhua at 2018年6月11日 下午4:53:40**/
package com.booting.business.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "会员信息")
@Entity(name = "mgr_member")
public class MemberEntity implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long memberId;
  private String mobile;
  private String name;
  private Integer identity; //MemberIdentity
  private Integer enabled;  //1可用 0不可用
  private Integer deleted;  //1已删 0未删
  private String beforeDeletedMobile; //删除/禁用后mobile置空，原号码存这里
  private Date createTime;
  private Date updateTime;
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name = "memberId")
  public Long getMemberId() {
    return memberId;
  }
  @Column(name = "mobile")
  public String getMobile() {
    return mobile;
  }
  @Column(name = "name")
  public String getName() {
    return name;
  }
  @Column(name = "identity")
  public Integer getIdentity() {
    return identity;
  }
  @Column(name = "enabled")
  public Integer getEnabled() {
    return enabled;
  }
  @Column(name = "deleted")
  public Integer getDeleted() {
    return deleted;
  }
  @Column(name = "beforeDeletedMobile")
  public String getBeforeDeletedMobile() {
    return beforeDeletedMobile;
  }
  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }
  @Column(name = "updateTime")
  public Date getUpdateTime() {
    return updateTime;
  }
  
  public void setMemberId(Long memberId) {
    this.memberId = memberId;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setIdentity(Integer identity) {
    this.identity = identity;
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
  public void setBeforeDeletedMobile(String beforeDeletedMobile) {
    this.beforeDeletedMobile = beforeDeletedMobile;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}
