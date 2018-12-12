/**create by liuhua at 2017年6月17日 上午9:59:33**/
package com.booting.court.dto;

import java.io.Serializable;
import java.util.Date;

import com.booting.competition.dto.CompetitionDTO;
import com.star.framework.aop.annotation.Description;

@Description(name = "区块使用明细")
public class ZoneUseDetailDTO implements Serializable {
	private static final long serialVersionUID = 8503189377503900482L;
	
	private Long id;
	private Long userId;
	private Long teamId;
	private Long courtId;
	private Long siteId;
	private Long zoneId;
	private Date beginTime;       //开始时间(日期)
	private Integer beginMinute;  //开始时间(分)
	private Date endTime;         //结束时间(日期)
	private Integer endMinute;    //结束时间(分)
	private Integer status;       //状态 1被预定 2禁用 3历史记录(已经使用过)
	private Date createTime;
	private Date modifyTime;
	private Long competitionId;             //赛事ID
	private Integer competitionFormat;  	//比赛赛制 CompetitionFormat
	
	private CompetitionDTO competition;
	private Date beginDate, endDate;
	public Long getId() {
		return id;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Long getTeamId() {
		return teamId;
	}
	
	public Long getZoneId() {
		return zoneId;
	}
	
	public Date getBeginTime() {
		return beginTime;
	}
	
	public Integer getBeginMinute() {
		return beginMinute;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public Integer getEndMinute() {
		return endMinute;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Date getModifyTime() {
		return modifyTime;
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

	public Long getCompetitionId() {
		return competitionId;
	}

	public Integer getCompetitionFormat() {
		return competitionFormat;
	}

	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}

	public void setCompetitionFormat(Integer competitionFormat) {
		this.competitionFormat = competitionFormat;
	}

	public Long getCourtId() {
		return courtId;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CompetitionDTO getCompetition() {
		return competition;
	}

	public void setCompetition(CompetitionDTO competition) {
		this.competition = competition;
	}
}
