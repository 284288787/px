/**create by liuhua at 2018年1月2日 上午10:31:03**/
package com.booting.kindergarten.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "班级和教练关系")
public class ClassCoachRelationDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long classId;
	private Long coachId;
	
	private Long schoolId;
	private String coachIds;
	private String classIds;
	
	private ClassDTO classDTO;
	
	public Long getId() {
		return id;
	}
	
	public Long getClassId() {
		return classId;
	}
	
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

	public Long getSchoolId() {
		return schoolId;
	}

	public String getCoachIds() {
		return coachIds;
	}

	public String getClassIds() {
		return classIds;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setCoachIds(String coachIds) {
		this.coachIds = coachIds;
	}

	public void setClassIds(String classIds) {
		this.classIds = classIds;
	}

	public ClassDTO getClassDTO() {
		return classDTO;
	}

	public void setClassDTO(ClassDTO classDTO) {
		this.classDTO = classDTO;
	}
}
