/**create by liuhua at 2017年7月15日 上午9:12:36**/
package com.booting.pkg.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "服务使用明细")
@Entity(name = "pkg_serviceusedetail")
public class ServiceUseDetailEntity implements Serializable {
	private static final long serialVersionUID = -6053655693563958832L;
	
	private Long id;
	private Long userId;        //使用人
	private Long serviceId;     //使用服务
	private Integer totalNum;   //可使用的总数
	private Integer useNum;     //使用个数
	private Date createTime;    //使用时间
	private String intro;       //用途描述
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "serviceId")
	public Long getServiceId() {
		return serviceId;
	}
	@Column(name = "totalNum")
	public Integer getTotalNum() {
		return totalNum;
	}
	@Column(name = "useNum")
	public Integer getUseNum() {
		return useNum;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "intro")
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
