/**create by liuhua at 2018年1月2日 上午10:18:59**/
package com.booting.kindergarten.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "班级信息")
public class ClassDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long classId;
	private Long schoolId;
	private String name;
	private Long teacherId;         //班主任
	private String teacherName;
	private String teacherMobile;
	private String intro;
	private Date createTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	private String schoolName;
	
	private String classIds;
	private String otherTeacherNames;
	
	public String getOtherTeacherNames() {
		return otherTeacherNames;
	}

	public void setOtherTeacherNames(String otherTeacherNames) {
		this.otherTeacherNames = otherTeacherNames;
	}

	public Long getClassId() {
		return classId;
	}
	
	public Long getSchoolId() {
		return schoolId;
	}
	
	public String getName() {
		return name;
	}
	
	public Long getTeacherId() {
		return teacherId;
	}
	
	public String getTeacherName() {
		return teacherName;
	}
	
	public String getTeacherMobile() {
		return teacherMobile;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setClassId(Long classId) {
		this.classId = classId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setTeacherMobile(String teacherMobile) {
		this.teacherMobile = teacherMobile;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getClassIds() {
		return classIds;
	}

	public void setClassIds(String classIds) {
		this.classIds = classIds;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
}
