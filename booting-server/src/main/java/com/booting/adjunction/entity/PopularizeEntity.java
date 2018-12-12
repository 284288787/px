/**create by liuhua at 2017年10月17日 上午10:05:12**/
package com.booting.adjunction.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "推广信息")
@Entity(name = "popularize_info")
public class PopularizeEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;   
	private Long userId;
	private Long teamId;
	private Integer type;       //类型 1图文 2视频
	private String title;       
	private String content;
	private String videoUrl;
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	@Column(name = "videoUrl")
	public String getVideoUrl() {
		return videoUrl;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "createUser")
	public String getCreateUser() {
		return createUser;
	}
	@Column(name = "updateTime")
	public Date getUpdateTime() {
		return updateTime;
	}
	@Column(name = "updateUser")
	public String getUpdateUser() {
		return updateUser;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
}
