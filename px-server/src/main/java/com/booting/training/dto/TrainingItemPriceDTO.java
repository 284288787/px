/**create by liuhua at 2019年3月12日 下午2:19:41**/
package com.booting.training.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训项目")
public class TrainingItemPriceDTO implements Serializable{
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long itemId;
  private Long applyItemId;
  private Integer price;         //* 100
  private String itemName;
  
  public Long getId() {
    return id;
  }
  
  public Long getItemId() {
    return itemId;
  }
  
  public Long getApplyItemId() {
    return applyItemId;
  }
  
  public Integer getPrice() {
    return price;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }
  public void setApplyItemId(Long applyItemId) {
    this.applyItemId = applyItemId;
  }
  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }
}
