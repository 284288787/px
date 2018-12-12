/**create by liuhua at 2018年1月2日 上午10:32:54**/
package com.booting.kindergarten.dto;

import java.io.Serializable;
import java.util.Date;

import com.star.framework.aop.annotation.Description;

@Description(name = "体测信息")
public class PhysicalDataDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long studentId;
	private Integer stature;       //身高 cm
	private Integer weight;        //体重 kg * 100;
	private Integer sitReach;      //坐位体前屈 cm * 100
	private Integer jump;          //立定跳远 cm * 100
	private Integer throwTennis;   //网球投掷 米 * 100
	private Integer doubleJump;    //双脚连续跳 秒 * 100
	private Integer run10;         //10米折返跑 秒 * 100
	private Integer balance;       //走平衡木 秒 * 100
	private Integer statureScore;      //身高 成绩
	private Integer weightScore;      //体重 成绩
	private Integer sitReachScore;      //坐位体前屈 成绩
	private Integer jumpScore;          //立定跳远 成绩
	private Integer throwTennisScore;   //网球投掷 成绩
	private Integer doubleJumpScore;    //双脚连续跳 成绩
	private Integer run10Score;         //10米折返跑 成绩
	private Integer balanceScore;       //走平衡木成绩
	private Date testTime;         //测试时间
	private String intro;          //描述
	private Integer enabled;       //是否有效 1有效     0无效
	private Integer deleted;       //是否删除 1已删除     0未删除
	private String schoolName;
	private String guardianName;
	private Integer sex;
	private String name;
	private Date birth;            //生日
	private Date createTime;       //创建时间
	private String ids;
	private Long schoolId;
	private Long classId;
	private String className;
	
	private String schoolIds;
	
	public Long getId() {
		return id;
	}
	
	public Long getStudentId() {
		return studentId;
	}
	
	public String getName() {
		return name;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public Integer getStature() {
		return stature;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
	public Integer getSitReach() {
		return sitReach;
	}
	
	public Integer getJump() {
		return jump;
	}
	
	public Integer getThrowTennis() {
		return throwTennis;
	}
	
	public Integer getDoubleJump() {
		return doubleJump;
	}
	
	public Integer getRun10() {
		return run10;
	}
	
	public Integer getBalance() {
		return balance;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public String getIntro() {
		return intro;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public void setStature(Integer stature) {
		this.stature = stature;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public void setSitReach(Integer sitReach) {
		this.sitReach = sitReach;
	}
	public void setJump(Integer jump) {
		this.jump = jump;
	}
	public void setThrowTennis(Integer throwTennis) {
		this.throwTennis = throwTennis;
	}
	public void setDoubleJump(Integer doubleJump) {
		this.doubleJump = doubleJump;
	}
	public void setRun10(Integer run10) {
		this.run10 = run10;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public String getIds() {
		return ids;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getTestTime() {
		return testTime;
	}

	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public Long getClassId() {
		return classId;
	}

	public String getClassName() {
		return className;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSchoolIds() {
		return schoolIds;
	}

	public void setSchoolIds(String schoolIds) {
		this.schoolIds = schoolIds;
	}

	public Integer getSitReachScore() {
		return sitReachScore;
	}

	public Integer getJumpScore() {
		return jumpScore;
	}

	public Integer getThrowTennisScore() {
		return throwTennisScore;
	}

	public Integer getDoubleJumpScore() {
		return doubleJumpScore;
	}

	public Integer getRun10Score() {
		return run10Score;
	}

	public Integer getBalanceScore() {
		return balanceScore;
	}

	public void setSitReachScore(Integer sitReachScore) {
		this.sitReachScore = sitReachScore;
	}

	public void setJumpScore(Integer jumpScore) {
		this.jumpScore = jumpScore;
	}

	public void setThrowTennisScore(Integer throwTennisScore) {
		this.throwTennisScore = throwTennisScore;
	}

	public void setDoubleJumpScore(Integer doubleJumpScore) {
		this.doubleJumpScore = doubleJumpScore;
	}

	public void setRun10Score(Integer run10Score) {
		this.run10Score = run10Score;
	}

	public void setBalanceScore(Integer balanceScore) {
		this.balanceScore = balanceScore;
	}

	public Integer getStatureScore() {
		return statureScore;
	}

	public Integer getWeightScore() {
		return weightScore;
	}

	public void setStatureScore(Integer statureScore) {
		this.statureScore = statureScore;
	}

	public void setWeightScore(Integer weightScore) {
		this.weightScore = weightScore;
	}
}
