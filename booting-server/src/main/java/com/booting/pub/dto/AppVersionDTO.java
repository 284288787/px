/**create by liuhua at 2017年6月12日 下午7:13:07**/
package com.booting.pub.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "APP版本")
public class AppVersionDTO implements Serializable {
	private static final long serialVersionUID = 1343237793739259334L;
	
	private Long id;
	private String version;          //版本号
	private String description;
	private String url;
	private String md5;
	private Integer status;          //状态 1有效 0无效
	private Integer type;            //类型 1安卓 2iOS
	private Integer upgrade;         //升级类别 1非强制升级 2强制升级
	private Date createTime;
	
	private String ids;
	public Long getId() {
		return id;
	}
	
	public String getVersion() {
		return version;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getMd5() {
		return md5;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public Integer getType() {
		return type;
	}
	
	public Integer getUpgrade() {
		return upgrade;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setUpgrade(Integer upgrade) {
		this.upgrade = upgrade;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
