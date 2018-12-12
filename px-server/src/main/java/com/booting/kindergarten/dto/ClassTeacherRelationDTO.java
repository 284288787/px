/**create by liuhua at 2018年1月2日 上午10:31:03**/
package com.booting.kindergarten.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "班级和老师关系")
public class ClassTeacherRelationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long classId;
	private Long teacherId;
	private String subject;   //教授科目
	
	private Long schoolId;
	private String teacherIds;
	private String classIds;
		
	public Long getId() {
		return id;
	}
	
	public Long getClassId() {
		return classId;
	}
	
	public Long getTeacherId() {
		return teacherId;
	}
	
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

	public Long getSchoolId() {
		return schoolId;
	}

	public String getTeacherIds() {
		return teacherIds;
	}

	public String getClassIds() {
		return classIds;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setTeacherIds(String teacherIds) {
		this.teacherIds = teacherIds;
	}

	public void setClassIds(String classIds) {
		this.classIds = classIds;
	}
}
