/**create by liuhua at 2017年7月12日 上午9:24:06**/
package com.booting.competition.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.booting.team.dto.TeamDTO;
import com.star.framework.aop.annotation.Description;

@Description(name = "赛事信息")
public class CompetitionDTO implements Serializable {
	private static final long serialVersionUID = -7134034664077807938L;
	
	private Long competitionId;
	private Long initiateUserId;        	//发起用户ID
	private Long initiatorTeamId;
	private Integer initiatorColor;         //发起发队伍颜色
	private String initiatorTeamName;
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
	private Integer type;                   //类型 1锁定场 2预定场 锁定场不用先付款但会自动取消；预定场需要先付全款，不会自动取消直到时间到  已经取消该功能
	private Integer open;               	//是否公开 只有挑战赛才有此项 1是 0否  公开则任何球队都可以挑战
	private Integer noOpenType;          	//不公开时候的类型 1球队 2行业
	private String typeValue;           	//根据比赛类型和是否公开决定的值，球队IDs 或 行业IDs
	private Integer initiatorPayType;   	//发起人支付方式 1约球券 2金额
	private Integer initiatorPayMoney;  	//发起人支付金额/券数量
	private Integer initiatorBuyInsurance;  //是否买保险 1是 0否
	private Integer status;             	//状态 1发起赛事 2建立赛事 3发起方取消赛事 4挑战方取消赛事 5系统取消 6赛事结束 7删除赛事
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
	
	private List<CompetitionInsuranceDTO> initiatorInsuranceList;
	private List<CompetitionInsuranceDTO> challengerInsuranceList;
	private List<TeamDTO> typeValueTeams;
	private String initiatorTeamLogo;
	private String challengerTeamLogo;  	//对手球队名称
	private String initiatorMobile;
	private String challengerMobile;  	//对手球队名称
	
	private Integer scoreStatus;            //成绩状态，必须是status=2的情况下才有值，1可传成绩 2不可传成绩
	private Integer winner;                 //胜利者 1发起方 2应战方 3平局
	private Integer initiatorScore;         //发起方进球数
	private Integer challengerScore;        //应战方进球数
	private String initiatorRemark;         //发起方备注
	private String challengerRemark;        //应战方备注
	
	private Date beginTime, endTime;
	private Long teamId;
	private Integer qj;                     //是否球局 0否 1是 2全
	private String states;
	
	public Long getCompetitionId() {
		return competitionId;
	}
	
	public Long getInitiateUserId() {
		return initiateUserId;
	}
	
	public Long getInitiatorTeamId() {
		return initiatorTeamId;
	}
	
	public String getInitiatorTeamName() {
		return initiatorTeamName;
	}
	
	public Integer getCompetitionType() {
		return competitionType;
	}
	
	public Date getCompetitionTime() {
		return competitionTime;
	}
	
	public Long getCourtId() {
		return courtId;
	}
	
	public String getCourtName() {
		return courtName;
	}
	
	public String getCourtAddr() {
		return courtAddr;
	}
	
	public Integer getCompetitionFormat() {
		return competitionFormat;
	}
	
	public Integer getOpen() {
		return open;
	}
	
	public Integer getNoOpenType() {
		return noOpenType;
	}
	
	public String getTypeValue() {
		return typeValue;
	}
	
	public Integer getInitiatorPayType() {
		return initiatorPayType;
	}
	
	public Integer getInitiatorPayMoney() {
		return initiatorPayMoney;
	}
	
	public Integer getInitiatorBuyInsurance() {
		return initiatorBuyInsurance;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public Long getChallengerTeamId() {
		return challengerTeamId;
	}
	
	public String getChallengerTeamName() {
		return challengerTeamName;
	}
	
	public Integer getChallengerPayType() {
		return challengerPayType;
	}
	
	public Integer getChallengerPayMoney() {
		return challengerPayMoney;
	}
	
	public Integer getChallengerBuyInsurance() {
		return challengerBuyInsurance;
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

	public List<CompetitionInsuranceDTO> getInitiatorInsuranceList() {
		return initiatorInsuranceList;
	}

	public void setInitiatorInsuranceList(List<CompetitionInsuranceDTO> initiatorInsuranceList) {
		this.initiatorInsuranceList = initiatorInsuranceList;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getChallengerUserId() {
		return challengerUserId;
	}

	public void setChallengerUserId(Long challengerUserId) {
		this.challengerUserId = challengerUserId;
	}

	public List<CompetitionInsuranceDTO> getChallengerInsuranceList() {
		return challengerInsuranceList;
	}

	public void setChallengerInsuranceList(List<CompetitionInsuranceDTO> challengerInsuranceList) {
		this.challengerInsuranceList = challengerInsuranceList;
	}

	public List<TeamDTO> getTypeValueTeams() {
		return typeValueTeams;
	}

	public void setTypeValueTeams(List<TeamDTO> typeValueTeams) {
		this.typeValueTeams = typeValueTeams;
	}

	public String getInitiatorTeamLogo() {
		return initiatorTeamLogo;
	}

	public String getChallengerTeamLogo() {
		return challengerTeamLogo;
	}

	public void setInitiatorTeamLogo(String initiatorTeamLogo) {
		this.initiatorTeamLogo = initiatorTeamLogo;
	}

	public void setChallengerTeamLogo(String challengerTeamLogo) {
		this.challengerTeamLogo = challengerTeamLogo;
	}

	public Date getAcceptTime() {
		return acceptTime;
	}

	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	public Integer getInitiatorColor() {
		return initiatorColor;
	}

	public Integer getChallengerColor() {
		return challengerColor;
	}

	public void setInitiatorColor(Integer initiatorColor) {
		this.initiatorColor = initiatorColor;
	}

	public void setChallengerColor(Integer challengerColor) {
		this.challengerColor = challengerColor;
	}

	public Integer getInitiatorScore() {
		return initiatorScore;
	}

	public Integer getChallengerScore() {
		return challengerScore;
	}

	public void setInitiatorScore(Integer initiatorScore) {
		this.initiatorScore = initiatorScore;
	}

	public void setChallengerScore(Integer challengerScore) {
		this.challengerScore = challengerScore;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getInitiatorTeach() {
		return initiatorTeach;
	}

	public Integer getChallengerTeach() {
		return challengerTeach;
	}

	public void setInitiatorTeach(Integer initiatorTeach) {
		this.initiatorTeach = initiatorTeach;
	}

	public void setChallengerTeach(Integer challengerTeach) {
		this.challengerTeach = challengerTeach;
	}

	public Integer getRemindTimes() {
		return remindTimes;
	}

	public void setRemindTimes(Integer remindTimes) {
		this.remindTimes = remindTimes;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getInitiatorMobile() {
		return initiatorMobile;
	}

	public String getChallengerMobile() {
		return challengerMobile;
	}

	public void setInitiatorMobile(String initiatorMobile) {
		this.initiatorMobile = initiatorMobile;
	}

	public void setChallengerMobile(String challengerMobile) {
		this.challengerMobile = challengerMobile;
	}

	public Integer getScoreStatus() {
		return scoreStatus;
	}

	public Integer getWinner() {
		return winner;
	}

	public void setScoreStatus(Integer scoreStatus) {
		this.scoreStatus = scoreStatus;
	}

	public void setWinner(Integer winner) {
		this.winner = winner;
	}

	public Long getZoneId() {
		return zoneId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getInitiatorRemark() {
		return initiatorRemark;
	}

	public String getChallengerRemark() {
		return challengerRemark;
	}

	public void setInitiatorRemark(String initiatorRemark) {
		this.initiatorRemark = initiatorRemark;
	}

	public void setChallengerRemark(String challengerRemark) {
		this.challengerRemark = challengerRemark;
	}

	public Integer getQj() {
		return qj;
	}

	public void setQj(Integer qj) {
		this.qj = qj;
	}

	public String getStates() {
		return states;
	}

	public void setStates(String states) {
		this.states = states;
	}
}
