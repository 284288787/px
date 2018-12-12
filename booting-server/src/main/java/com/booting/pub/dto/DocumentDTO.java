/**create by liuhua at 2017年6月13日 上午10:28:15**/
package com.booting.pub.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "文档")
public class DocumentDTO implements Serializable {
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
	
	private String docIds;
	
	public Long getDocId() {
		return docId;
	}
	
	public Integer getType() {
		return type;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
	}
	
	public Integer getPosition() {
		return position;
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

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getDocIds() {
		return docIds;
	}

	public void setDocIds(String docIds) {
		this.docIds = docIds;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}
}
