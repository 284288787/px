/**create by liuhua at 2018年6月21日 上午11:06:40**/
package com.booting.product.dto;

import java.io.Serializable;
import java.util.Date;
import com.star.framework.aop.annotation.Description;

@Description(name = "产品类型")
public class ProductTypeDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long typeId;
  private String typeName;
  private Integer business;   //1活动 2设备 3课程
  private Integer enabled;    //1可用 0不可用
  private Integer deleted;    //1已删 0未删
  private Date createTime;
  private Date updateTime;
  
  private String typeIds;
  
  public Long getTypeId() {
    return typeId;
  }
  
  public String getTypeName() {
    return typeName;
  }
  
  public Integer getBusiness() {
    return business;
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
  
  public Date getUpdateTime() {
    return updateTime;
  }
  
  public void setTypeId(Long typeId) {
    this.typeId = typeId;
  }
  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }
  public void setBusiness(Integer business) {
    this.business = business;
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
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  public void setTypeIds(String typeIds) {
    this.typeIds = typeIds;
  }

  public String getTypeIds() {
    return typeIds;
  }
}
