/**create by liuhua at 2017年6月10日 下午6:10:05**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "用户套餐")
public class UserPackageDTO implements Serializable {
	private static final long serialVersionUID = -5220528249928445008L;
	
	private Long upId;
	private Long userId;
	private Long packageId;
	private String packageName;      //购买时，套餐的名称
	private Integer packageType;     //购买时，套餐的类型 1基础套餐 2增值套餐
	private Integer price;           //价格 * 100
	private Integer discount;        //折扣 * 10
	private Integer count;           //购买的个数：基础套餐购买的年限（1年） 增值套餐购买的是个数
	private Date createTime;          
	private Date beginTime;          //套餐生效时间  基础套餐才有值
	private Date endTime;            //套餐失效时间  基础套餐才有值
	private Integer sourceFrom;      //来源 1订单 2后台
	private Long orderId;            //来源为1时，为订单id
	private Long teamId;
	
	private Integer teamIdEmpty;     //1空 2非空
	
	public Long getUpId() {
		return upId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getPackageId() {
		return packageId;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public Integer getPackageType() {
		return packageType;
	}
	
	public Integer getPrice() {
		return price;
	}
	
	public Integer getDiscount() {
		return discount;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}
	
	public Integer getSourceFrom() {
		return sourceFrom;
	}
	
	public Long getOrderId() {
		return orderId;
	}
	public void setUpId(Long upId) {
		this.upId = upId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public void setPackageType(Integer packageType) {
		this.packageType = packageType;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getCount() {
		return count;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Integer getTeamIdEmpty() {
		return teamIdEmpty;
	}

	public void setTeamIdEmpty(Integer teamIdEmpty) {
		this.teamIdEmpty = teamIdEmpty;
	}
}
