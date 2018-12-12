/**create by liuhua at 2018年6月21日 上午11:06:40**/
package com.booting.product.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.star.framework.aop.annotation.Description;

@Description(name = "产品类型")
@Entity(name = "mgr_product_type")
public class ProductTypeEntity implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long typeId;
  private String typeName;
  private Integer business;   //ProductType  1活动 2设备 3课程
  private Integer enabled;    //1可用 0不可用
  private Integer deleted;    //1已删 0未删
  private Date createTime;
  private Date updateTime;
  
  @Id
  @Column(name = "typeId")
  public Long getTypeId() {
    return typeId;
  }
  @Column(name = "typeName")
  public String getTypeName() {
    return typeName;
  }
  @Column(name = "business")
  public Integer getBusiness() {
    return business;
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
  @Column(name = "updateTime")
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
}
