/**create by liuhua at 2019年3月8日 下午4:30:10**/
package com.booting.training.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "推广员")
public class PromoterDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Long promoterId;
  private String name;
  private String mobile;
  private Integer deleted;  //删除 1是 0否
  private Integer enabled;  //禁用 1是 0否
  private Date createTime;
  private Integer count;    //推广的人数
  private Double totalTc;   //总提成
  private String promoterIds;
  
  public Long getPromoterId() {
    return promoterId;
  }
  
  public String getName() {
    return name;
  }
  
  public String getMobile() {
    return mobile;
  }
  
  public Integer getDeleted() {
    return deleted;
  }
  
  public Integer getEnabled() {
    return enabled;
  }
  
  public Date getCreateTime() {
    return createTime;
  }
  
  public void setPromoterId(Long promoterId) {
    this.promoterId = promoterId;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getPromoterIds() {
    return promoterIds;
  }

  public void setPromoterIds(String promoterIds) {
    this.promoterIds = promoterIds;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Double getTotalTc() {
    return totalTc;
  }

  public void setTotalTc(Double totalTc) {
    this.totalTc = totalTc;
  } 
  
}
