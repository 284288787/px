/**create by liuhua at 2017年7月15日 上午9:12:36**/
package com.booting.pkg.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "服务使用明细")
public class ServiceUseDetailDTO implements Serializable {
	private static final long serialVersionUID = -6053655693563958832L;
	
	private Long id;
	private Long userId;        //使用人
	private Long serviceId;     //使用服务
	private Integer totalNum;   //可使用的总数
	private Integer useNum;     //使用个数
	private Date createTime;    //使用时间
	private String intro;       //用途描述
	
		
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getServiceId() {
		return serviceId;
	}
	
	public Integer getTotalNum() {
		return totalNum;
	}
	
	public Integer getUseNum() {
		return useNum;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
}
