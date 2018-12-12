/**create by liuhua at 2018年1月2日 上午10:23:41**/
package com.booting.kindergarten.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "老师信息")
@Entity(name = "kindergarten_teacher")
public class TeacherEntity implements Serializable {
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
	
	@Id
	@Column(name = "teacherId")
	public Long getTeacherId() {
		return teacherId;
	}
	@Column(name = "schoolId")
	public Long getSchoolId() {
		return schoolId;
	}
	@Column(name = "teacherName")
	public String getTeacherName() {
		return teacherName;
	}
	@Column(name = "teacherMobile")
	public String getTeacherMobile() {
		return teacherMobile;
	}
	@Column(name = "pic")
	public String getPic() {
		return pic;
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
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
