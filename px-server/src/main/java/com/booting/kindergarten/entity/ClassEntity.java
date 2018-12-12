/**create by liuhua at 2018年1月2日 上午10:18:59**/
package com.booting.kindergarten.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "班级信息")
@Entity(name = "kindergarten_class")
public class ClassEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long classId;
	private Long schoolId;
	private String name;
	private Long teacherId;         //班主任
	private String intro;
	private Date createTime;
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	
	@Id
	@Column(name = "classId")
	public Long getClassId() {
		return classId;
	}
	@Column(name = "schoolId")
	public Long getSchoolId() {
		return schoolId;
	}
	@Column(name = "name")
	public String getName() {
		return name;
	}
	@Column(name = "teacherId")
	public Long getTeacherId() {
		return teacherId;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "deleted")
	public Integer getDeleted() {
		return deleted;
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
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
