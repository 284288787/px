/**create by liuhua at 2017年7月12日 上午9:24:06**/
package com.booting.competition.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "赛事信息")
@Entity(name = "competition_info")
public class CompetitionEntity implements Serializable {
	private static final long serialVersionUID = -7134034664077807938L;
	
	private Long competitionId;
	private Long initiateUserId;        	//发起用户ID
	private Long initiatorTeamId;
	private String initiatorTeamName;
	private Integer initiatorColor;         //发起发队伍颜色
	private Integer initiatorTeach;         //发起发队伍是否有教练 1是 0否
	//邀请赛 对手只能设置一个;友谊赛 对手设置多个，多个先来先得;挑战赛 如果公开不用选对手，对手去平台自己选，如果不公开，则需要选择球队或者行业，此时相当于友谊赛的匹配规则
	private Integer competitionType;    	//比赛类型 1邀请赛 2友谊赛 3挑战赛
	private Date competitionTime;       	//比赛时间
	private Long courtId;                   //比赛球场
	private String courtName;
	private String courtAddr;
	private Long zoneId;                    //区域ID
	private String zoneName;                //区域名称
	private Integer competitionFormat;  	//比赛赛制 CompetitionFormat
	private Integer type;                   //类型 1锁定场 2预定场 锁定场不用先付款但会自动取消；预定场需要先付全款，不会自动取消直到时间到
	private Integer open;               	//是否公开 只有挑战赛才有此项 1是 0否  公开则任何球队都可以挑战
	private Integer noOpenType;          	//不公开时候的类型 1球队 2行业
	private String typeValue;           	//根据比赛类型和是否公开决定的值，球队IDs 或 行业IDs
	private Integer initiatorPayType;   	//发起人支付方式 1约球券 2金额
	private Integer initiatorPayMoney;  	//发起人支付金额/券数量
	private Integer initiatorBuyInsurance;  //是否买保险 1是 0否
	private Integer status;             	//状态 1发起赛事 2建立赛事 3发起方取消赛事 4挑战方取消赛事 5系统取消
	private Date createTime;
	private Long challengerUserId;      	//对手用户ID
	private Long challengerTeamId;      	//对手球队ID
	private String challengerTeamName;  	//对手球队名称
	private Date acceptTime;      	        //接受时间
	private Integer challengerColor;        //挑战方队伍颜色
	private Integer challengerTeach;        //挑战方队伍是否有教练 1是 0否
	private Integer challengerPayType;   	//对手支付方式(邀请赛不用支付) 1约球券
	private Integer challengerPayMoney;  	//对手支付金额/券数量
	private Integer challengerBuyInsurance; //是否买保险 1是 0否
	private Integer remindTimes;            //提醒剩余次数
	private String initiatorMobile;
	private String challengerMobile;  	    //对手球队名称
	private Integer scoreStatus;            //成绩状态，必须是status=2的情况下才有值，1可传成绩 2不可传成绩
	private Integer winner;                 //胜利者 1发起方 2应战方
	private Integer initiatorScore;         //发起方进球数
	private Integer challengerScore;        //应战方进球数
	private String initiatorRemark;         //发起方备注
	private String challengerRemark;        //应战方备注
	
	@Id
	@Column(name = "competitionId")
	public Long getCompetitionId() {
		return competitionId;
	}
	@Column(name = "initiateUserId")
	public Long getInitiateUserId() {
		return initiateUserId;
	}
	@Column(name = "initiatorTeamId")
	public Long getInitiatorTeamId() {
		return initiatorTeamId;
	}
	@Column(name = "initiatorTeamName")
	public String getInitiatorTeamName() {
		return initiatorTeamName;
	}
	@Column(name = "competitionType")
	public Integer getCompetitionType() {
		return competitionType;
	}
	@Column(name = "competitionTime")
	public Date getCompetitionTime() {
		return competitionTime;
	}
	@Column(name = "courtId")
	public Long getCourtId() {
		return courtId;
	}
	@Column(name = "courtName")
	public String getCourtName() {
		return courtName;
	}
	@Column(name = "courtAddr")
	public String getCourtAddr() {
		return courtAddr;
	}
	@Column(name = "zoneId")
	public Long getZoneId() {
		return zoneId;
	}
	@Column(name = "zoneName")
	public String getZoneName() {
		return zoneName;
	}
	@Column(name = "competitionFormat")
	public Integer getCompetitionFormat() {
		return competitionFormat;
	}
	@Column(name = "type")
	public Integer getType() {
		return type;
	}
	@Column(name = "open")
	public Integer getOpen() {
		return open;
	}
	@Column(name = "noOpenType")
	public Integer getNoOpenType() {
		return noOpenType;
	}
	@Column(name = "typeValue")
	public String getTypeValue() {
		return typeValue;
	}
	@Column(name = "initiatorPayType")
	public Integer getInitiatorPayType() {
		return initiatorPayType;
	}
	@Column(name = "initiatorPayMoney")
	public Integer getInitiatorPayMoney() {
		return initiatorPayMoney;
	}
	@Column(name = "initiatorBuyInsurance")
	public Integer getInitiatorBuyInsurance() {
		return initiatorBuyInsurance;
	}
	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}
	@Column(name = "createTime")
	public Date getCreateTime() {
		return createTime;
	}
	@Column(name = "challengerUserId")
	public Long getChallengerUserId() {
		return challengerUserId;
	}
	@Column(name = "challengerTeamId")
	public Long getChallengerTeamId() {
		return challengerTeamId;
	}
	@Column(name = "challengerTeamName")
	public String getChallengerTeamName() {
		return challengerTeamName;
	}
	@Column(name = "challengerPayType")
	public Integer getChallengerPayType() {
		return challengerPayType;
	}
	@Column(name = "challengerPayMoney")
	public Integer getChallengerPayMoney() {
		return challengerPayMoney;
	}
	@Column(name = "challengerBuyInsurance")
	public Integer getChallengerBuyInsurance() {
		return challengerBuyInsurance;
	}
	@Column(name = "acceptTime")
	public Date getAcceptTime() {
		return acceptTime;
	}
	@Column(name = "initiatorColor")
	public Integer getInitiatorColor() {
		return initiatorColor;
	}
	@Column(name = "challengerColor")
	public Integer getChallengerColor() {
		return challengerColor;
	}
	@Column(name = "initiatorTeach")
	public Integer getInitiatorTeach() {
		return initiatorTeach;
	}
	@Column(name = "challengerTeach")
	public Integer getChallengerTeach() {
		return challengerTeach;
	}
	@Column(name = "remindTimes")
	public Integer getRemindTimes() {
		return remindTimes;
	}
	@Column(name = "initiatorMobile")
	public String getInitiatorMobile() {
		return initiatorMobile;
	}
	@Column(name = "challengerMobile")
	public String getChallengerMobile() {
		return challengerMobile;
	}
	@Column(name = "scoreStatus")
	public Integer getScoreStatus() {
		return scoreStatus;
	}
	@Column(name = "winner")
	public Integer getWinner() {
		return winner;
	}
	@Column(name = "initiatorScore")
	public Integer getInitiatorScore() {
		return initiatorScore;
	}
	@Column(name = "challengerScore")
	public Integer getChallengerScore() {
		return challengerScore;
	}
	@Column(name = "initiatorRemark")
	public String getInitiatorRemark() {
		return initiatorRemark;
	}
	@Column(name = "challengerRemark")
	public String getChallengerRemark() {
		return challengerRemark;
	}
	
	public void setCompetitionId(Long competitionId) {
		this.competitionId = competitionId;
	}
	public void setInitiateUserId(Long initiateUserId) {
		this.initiateUserId = initiateUserId;
	}
	public void setInitiatorTeamId(Long initiatorTeamId) {
		this.initiatorTeamId = initiatorTeamId;
	}
	public void setInitiatorTeamName(String initiatorTeamName) {
		this.initiatorTeamName = initiatorTeamName;
	}
	public void setCompetitionType(Integer competitionType) {
		this.competitionType = competitionType;
	}
	public void setCompetitionTime(Date competitionTime) {
		this.competitionTime = competitionTime;
	}
	public void setCourtId(Long courtId) {
		this.courtId = courtId;
	}
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	public void setCourtAddr(String courtAddr) {
		this.courtAddr = courtAddr;
	}
	public void setCompetitionFormat(Integer competitionFormat) {
		this.competitionFormat = competitionFormat;
	}
	public void setOpen(Integer open) {
		this.open = open;
	}
	public void setNoOpenType(Integer noOpenType) {
		this.noOpenType = noOpenType;
	}
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}
	public void setInitiatorPayType(Integer initiatorPayType) {
		this.initiatorPayType = initiatorPayType;
	}
	public void setInitiatorPayMoney(Integer initiatorPayMoney) {
		this.initiatorPayMoney = initiatorPayMoney;
	}
	public void setInitiatorBuyInsurance(Integer initiatorBuyInsurance) {
		this.initiatorBuyInsurance = initiatorBuyInsurance;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setChallengerTeamId(Long challengerTeamId) {
		this.challengerTeamId = challengerTeamId;
	}
	public void setChallengerTeamName(String challengerTeamName) {
		this.challengerTeamName = challengerTeamName;
	}
	public void setChallengerPayType(Integer challengerPayType) {
		this.challengerPayType = challengerPayType;
	}
	public void setChallengerPayMoney(Integer challengerPayMoney) {
		this.challengerPayMoney = challengerPayMoney;
	}
	public void setChallengerBuyInsurance(Integer challengerBuyInsurance) {
		this.challengerBuyInsurance = challengerBuyInsurance;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setChallengerUserId(Long challengerUserId) {
		this.challengerUserId = challengerUserId;
	}
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}
	public void setInitiatorColor(Integer initiatorColor) {
		this.initiatorColor = initiatorColor;
	}
	public void setChallengerColor(Integer challengerColor) {
		this.challengerColor = challengerColor;
	}
	public void setInitiatorTeach(Integer initiatorTeach) {
		this.initiatorTeach = initiatorTeach;
	}
	public void setChallengerTeach(Integer challengerTeach) {
		this.challengerTeach = challengerTeach;
	}
	public void setRemindTimes(Integer remindTimes) {
		this.remindTimes = remindTimes;
	}
	public void setInitiatorMobile(String initiatorMobile) {
		this.initiatorMobile = initiatorMobile;
	}
	public void setChallengerMobile(String challengerMobile) {
		this.challengerMobile = challengerMobile;
	}
	public void setScoreStatus(Integer scoreStatus) {
		this.scoreStatus = scoreStatus;
	}
	public void setWinner(Integer winner) {
		this.winner = winner;
	}
	public void setInitiatorScore(Integer initiatorScore) {
		this.initiatorScore = initiatorScore;
	}
	public void setChallengerScore(Integer challengerScore) {
		this.challengerScore = challengerScore;
	}
	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	public void setInitiatorRemark(String initiatorRemark) {
		this.initiatorRemark = initiatorRemark;
	}
	public void setChallengerRemark(String challengerRemark) {
		this.challengerRemark = challengerRemark;
	}
}