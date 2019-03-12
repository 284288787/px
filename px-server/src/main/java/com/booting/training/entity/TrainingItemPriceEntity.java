/**create by liuhua at 2019年3月12日 下午2:19:41**/
package com.booting.training.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训项目")
@Entity(name = "px_training_item_price")
public class TrainingItemPriceEntity implements Serializable{
  private static final long serialVersionUID = 1L;

  private Long id;
  private Long itemId;
  private Long applyItemId;
  private Integer price;         //* 100
  
  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }
  @Column(name = "itemId")
  public Long getItemId() {
    return itemId;
  }
  @Column(name = "applyItemId")
  public Long getApplyItemId() {
    return applyItemId;
  }
  @Column(name = "price")
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
}
