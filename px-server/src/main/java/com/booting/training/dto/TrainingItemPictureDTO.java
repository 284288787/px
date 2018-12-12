/**create by liuhua at 2017年12月18日 上午11:28:23**/
package com.booting.training.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训项目 海报")
public class TrainingItemPictureDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long itemId;
	private String picturePath;
	
	public Long getId() {
		return id;
	}
	
	public Long getItemId() {
		return itemId;
	}
	
	public String getPicturePath() {
		return picturePath;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
}
