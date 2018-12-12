/**create by liuhua at 2018年1月2日 上午10:32:54**/
package com.booting.kindergarten.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "体测信息")
@Entity(name = "kindergarten_physical_data")
public class PhysicalDataEntity implements Serializable {
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
	private Date createTime;       //创建时间
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "studentId")
	public Long getStudentId() {
		return studentId;
	}
	@Column(name = "stature")
	public Integer getStature() {
		return stature;
	}
	@Column(name = "weight")
	public Integer getWeight() {
		return weight;
	}
	@Column(name = "sitReach")
	public Integer getSitReach() {
		return sitReach;
	}
	@Column(name = "jump")
	public Integer getJump() {
		return jump;
	}
	@Column(name = "throwTennis")
	public Integer getThrowTennis() {
		return throwTennis;
	}
	@Column(name = "doubleJump")
	public Integer getDoubleJump() {
		return doubleJump;
	}
	@Column(name = "run10")
	public Integer getRun10() {
		return run10;
	}
	@Column(name = "balance")
	public Integer getBalance() {
		return balance;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "intro")
	public String getIntro() {
		return intro;
	}
	@Column(name = "enabled")
	public Integer getEnabled() {
		return enabled;
	}
	@Column(name = "deleted")
	public Integer getDeleted() {
		return deleted;
	}
	@Column(name = "testTime")
	public Date getTestTime() {
		return testTime;
	}
	@Column(name = "sitReachScore")
	public Integer getSitReachScore() {
		return sitReachScore;
	}
	@Column(name = "jumpScore")
	public Integer getJumpScore() {
		return jumpScore;
	}
	@Column(name = "throwTennisScore")
	public Integer getThrowTennisScore() {
		return throwTennisScore;
	}
	@Column(name = "doubleJumpScore")
	public Integer getDoubleJumpScore() {
		return doubleJumpScore;
	}
	@Column(name = "run10Score")
	public Integer getRun10Score() {
		return run10Score;
	}
	@Column(name = "balanceScore")
	public Integer getBalanceScore() {
		return balanceScore;
	}
	@Column(name = "statureScore")
	public Integer getStatureScore() {
		return statureScore;
	}
	@Column(name = "weightScore")
	public Integer getWeightScore() {
		return weightScore;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
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
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public void setTestTime(Date testTime) {
		this.testTime = testTime;
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
	public void setStatureScore(Integer statureScore) {
		this.statureScore = statureScore;
	}
	public void setWeightScore(Integer weightScore) {
		this.weightScore = weightScore;
	}
}
