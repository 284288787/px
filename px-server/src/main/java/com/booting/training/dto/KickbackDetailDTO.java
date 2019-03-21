/**create by liuhua at 2019年3月21日 上午10:35:30**/
package com.booting.training.dto;

import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "回扣发放明细")
public class KickbackDetailDTO {

  private Long id;
  private Long promoterId;
  private Integer money;          //发放金额 单位分
  private Date createTime;        //发放时间
  private String createUser;      //发放人
  
    
  public Long getId() {
    return id;
  }
  
  public Long getPromoterId() {
    return promoterId;
  }
  
  public Integer getMoney() {
    return money;
  }
  
  public Date getCreateTime() {
    return createTime;
  }
  
  public String getCreateUser() {
    return createUser;
  }
  
  public void setId(Long id) {
    this.id = id;
  }
  public void setPromoterId(Long promoterId) {
    this.promoterId = promoterId;
  }
  public void setMoney(Integer money) {
    this.money = money;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }
}
