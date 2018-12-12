/**create by liuhua at 2018年1月23日 下午3:18:31**/
package com.booting.kindergarten.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "学生和长辈的关系")
@Entity(name = "kindergarten_student_parent")
public class StudentParentRelationEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long studentId;
	private Long parentId;
	private Integer type;       //关系 ParentType
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "studentId")
	public Long getStudentId() {
		return studentId;
	}
	@Column(name = "parentId")
	public Long getParentId() {
		return parentId;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
