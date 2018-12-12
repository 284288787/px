/**create by liuhua at 2017年6月13日 上午10:28:15**/
package com.booting.pub.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "文档")
@Entity(name = "pub_document")
public class DocumentEntity implements Serializable {
	private static final long serialVersionUID = 3233545362775907613L;
	
	private Long docId;
	private Integer type;
	private String logo;
	private String title;
	private String intro;
	private String content;
	private Date createTime;
	private Date modifyTime;
	private Integer position; //位置 1正常 2置顶
	private Integer enabled;  //1启用 0禁用
	
	@Id
	@Column(name = "docId")
	public Long getDocId() {
		return docId;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	@Column(name = "logo")
	public String getLogo() {
		return logo;
	}
	@Column(name = "title")
	public String getTitle() {
		return title;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	@Column(name = "position")
	public Integer getPosition() {
		return position;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
}
