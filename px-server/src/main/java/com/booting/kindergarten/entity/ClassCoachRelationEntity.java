/**create by liuhua at 2018年1月2日 上午10:31:03**/
package com.booting.kindergarten.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "班级和教练关系")
@Entity(name = "kindergarten_class_coach")
public class ClassCoachRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long classId;
	private Long coachId;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "classId")
	public Long getClassId() {
		return classId;
	}
	@Column(name = "coachId")
	public Long getCoachId() {
		return coachId;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public void setCoachId(Long coachId) {
		this.coachId = coachId;
	}
}
