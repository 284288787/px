/**create by liuhua at 2019年3月21日 上午10:35:30**/
package com.booting.training.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "回扣发放明细")
@Entity(name = "px_kickback_detail")
public class KickbackDetailEntity {

  private Long id;
  private Long promoterId;
  private Integer money;          //发放金额 单位分
  private Date createTime;        //发放时间
  private String createUser;      //发放人
  
  @Id
  @Column(name = "id")
  public Long getId() {
    return id;
  }
  @Column(name = "promoterId")
  public Long getPromoterId() {
    return promoterId;
  }
  @Column(name = "money")
  public Integer getMoney() {
    return money;
  }
  @Column(name = "createTime")
  public Date getCreateTime() {
    return createTime;
  }
  @Column(name = "createUser")
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
