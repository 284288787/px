/**create by liuhua at 2019年3月12日 下午8:47:17**/
package com.booting.training.dto;

import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "报名项目")
public class ApplyItemDTO {

  private Long applyItemId;
  private String itemName;
  private Integer deleted;  //删除 1是 0否
  private Integer enabled;  //禁用 1是 0否
  private Date createTime;
  private String applyItemIds;
    
  public Long getApplyItemId() {
    return applyItemId;
  }
  
  public String getItemName() {
    return itemName;
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

  public String getApplyItemIds() {
    return applyItemIds;
  }

  public void setApplyItemIds(String applyItemIds) {
    this.applyItemIds = applyItemIds;
  }
}
