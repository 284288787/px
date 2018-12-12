/**create by liuhua at 2018年1月2日 上午10:23:41**/
package com.booting.kindergarten.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "老师信息")
public class TeacherDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long teacherId;
	private Long schoolId;
	private String teacherName;
	private String teacherMobile;
	private String pic;
	private String intro;
	private Date createTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	private String schoolName;
	private String teacherIds;
	
	public Long getTeacherId() {
		return teacherId;
	}
	
	public Long getSchoolId() {
		return schoolId;
	}
	
	public String getTeacherName() {
		return teacherName;
	}
	
	public String getTeacherMobile() {
		return teacherMobile;
	}
	
	public String getPic() {
		return pic;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}
	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public void setTeacherMobile(String teacherMobile) {
		this.teacherMobile = teacherMobile;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setPic(String pic) {
		this.pic = pic;
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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getTeacherIds() {
		return teacherIds;
	}

	public void setTeacherIds(String teacherIds) {
		this.teacherIds = teacherIds;
	}
}
