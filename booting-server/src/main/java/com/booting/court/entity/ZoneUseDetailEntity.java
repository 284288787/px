/**create by liuhua at 2017年6月17日 上午9:59:33**/
package com.booting.court.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "区块使用明细")
@Entity(name = "court_zoneuse")
public class ZoneUseDetailEntity implements Serializable {
	private static final long serialVersionUID = 8503189377503900482L;
	
	private Long id;
	private Long userId;
	private Long teamId;
	private Long competitionId;             //赛事ID
	private Long courtId;
	private Long siteId;
	private Long zoneId;
	private Integer competitionFormat;  	//比赛赛制 CompetitionFormat
	private Date beginTime;                 //开始时间(日期)
	private Integer beginMinute;            //开始时间(分)
	private Date endTime;                   //结束时间(日期)
	private Integer endMinute;              //结束时间(分)
	private Integer status;                 //状态 1锁定场 2预定场 3不可用
	private Date createTime;
	private Date modifyTime;
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "userId")
	public Long getUserId() {
		return userId;
	}
	@Column(name = "teamId")
	public Long getTeamId() {
		return teamId;
	}
	@Column(name = "courtId")
	public Long getCourtId() {
		return courtId;
	}
	@Column(name = "siteId")
	public Long getSiteId() {
		return siteId;
	}
	@Column(name = "zoneId")
	public Long getZoneId() {
		return zoneId;
	}
	@Column(name = "beginTime")
	public Date getBeginTime() {
		return beginTime;
	}
	@Column(name = "beginMinute")
	public Integer getBeginMinute() {
		return beginMinute;
	}
	@Column(name = "endTime")
	public Date getEndTime() {
		return endTime;
	}
	@Column(name = "endMinute")
	public Integer getEndMinute() {
		return endMinute;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "modifyTime")
	public Date getModifyTime() {
		return modifyTime;
	}
	@Column(name = "competitionId")
	public Long getCompetitionId() {
		return competitionId;
	}
	@Column(name = "competitionFormat")
	public Integer getCompetitionFormat() {
		return competitionFormat;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public void setBeginMinute(Integer beginMinute) {
		this.beginMinute = beginMinute;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public void setEndMinute(Integer endMinute) {
		this.endMinute = endMinute;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}
	public void setCompetitionFormat(Integer competitionFormat) {
		this.competitionFormat = competitionFormat;
	}
	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}
	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}
}
