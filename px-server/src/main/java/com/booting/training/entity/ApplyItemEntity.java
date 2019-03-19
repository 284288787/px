/**create by liuhua at 2019年3月12日 下午8:47:17**/
package com.booting.training.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "报名项目")
@Entity(name = "px_apply_item")
public class ApplyItemEntity {

  private Long applyItemId;
  private String itemName;
  private Double brokerageRate; //提成比率
  private Integer deleted;  //删除 1是 0否
  private Integer enabled;  //禁用 1是 0否
  private Date createTime;
  
  @Id
  @Column(name = "applyItemId")
  public Long getApplyItemId() {
    return applyItemId;
  }
  @Column(name = "itemName")
  public String getItemName() {
    return itemName;
  }
  @Column(name = "deleted")
  public Integer getDeleted() {
    return deleted;
  }
  @Column(name = "enabled")
  public Integer getEnabled() {
    return enabled;
  }
  @Column(name = "brokerageRate")
  public Double getBrokerageRate() {
    return brokerageRate;
  }
  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }
  
  public void setApplyItemId(Long applyItemId) {
    this.applyItemId = applyItemId;
  }
  public void setItemName(String itemName) {
    this.itemName = itemName;
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
  public void setBrokerageRate(Double brokerageRate) {
    this.brokerageRate = brokerageRate;
  }
}
