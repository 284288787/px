/**create by liuhua at 2017年12月18日 上午11:28:23**/
package com.booting.training.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "培训项目 海报")
@Entity(name = "px_training_item_picture")
public class TrainingItemPictureEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long itemId;
	private String picturePath;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "itemId")
	public Long getItemId() {
		return itemId;
	}
	@Column(name = "picturePath")
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
