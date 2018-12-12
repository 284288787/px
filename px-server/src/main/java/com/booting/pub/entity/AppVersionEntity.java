/**create by liuhua at 2017年6月12日 下午7:13:07**/
package com.booting.pub.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "APP版本")
@Entity(name = "pub_appversion")
public class AppVersionEntity implements Serializable {
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

	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "version")
	public String getVersion() {
		return version;
	}
	@Column(name = "description")
	public String getDescription() {
		return description;
	}
	@Column(name = "url")
	public String getUrl() {
		return url;
	}
	@Column(name = "md5")
	public String getMd5() {
		return md5;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	@Column(name = "upgrade")
	public Integer getUpgrade() {
		return upgrade;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
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
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
