/**create by liuhua at 2018年6月21日 下午10:19:02**/
package com.booting.product.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "产品图片")
@Entity(name = "mgr_product_picture")
public class ProductPictureEntity {
  
  private Long id;
  private Integer business;   //ProductType 1活动 2设备 3课程
  private Long businessId;
  private String picPath;
  
  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }
  @Column(name = "business")
  public Integer getBusiness() {
    return business;
  }
  @Column(name = "businessId")
  public Long getBusinessId() {
    return businessId;
  }
  @Column(name = "picPath")
  public String getPicPath() {
    return picPath;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  public void setBusiness(Integer business) {
    this.business = business;
  }
  public void setBusinessId(Long businessId) {
    this.businessId = businessId;
  }
  public void setPicPath(String picPath) {
    this.picPath = picPath;
  }
}
