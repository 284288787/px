/**create by liuhua at 2018年1月2日 上午10:31:03**/
package com.booting.kindergarten.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "班级和老师关系")
@Entity(name = "kindergarten_class_teacher")
public class ClassTeacherRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long classId;
	private Long teacherId;
	private String subject;   //教授科目
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "classId")
	public Long getClassId() {
		return classId;
	}
	@Column(name = "teacherId")
	public Long getTeacherId() {
		return teacherId;
	}
	@Column(name = "subject")
	public String getSubject() {
		return subject;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
}
