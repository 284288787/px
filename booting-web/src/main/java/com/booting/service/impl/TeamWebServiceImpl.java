/**create by liuhua at 2017年6月26日 上午10:18:40**/
package com.booting.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.booting.common.CommonConstants;
import com.booting.common.CommonConstants.MemberLog;
import com.booting.common.CommonConstants.TeamIdentity;
import com.booting.common.CommonConstants.UserIdentity;
import com.booting.common.PushInfo;
import com.booting.pub.dto.CompanyDTO;
import com.booting.pub.facade.CommonFacade;
import com.booting.service.TeamWebService;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.facade.SystemFacade;
import com.booting.team.dto.TeamAttentionDTO;
import com.booting.team.dto.TeamDTO;
import com.booting.team.dto.TeamHabitCourtDTO;
import com.booting.team.dto.TeamHabitNumberDTO;
import com.booting.team.dto.TeamHabitTimeDTO;
import com.booting.team.dto.TeamMemberDTO;
import com.booting.team.dto.TeamMemberLogDTO;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

@Service("teamWebService")
public class TeamWebServiceImpl extends BaseWebService implements TeamWebService{

	@Autowired
	private SystemFacade systemFacade;
	@Autowired
	private CommonFacade commonFacade;
	
	private TeamDTO getTeamByUserId(Long userId){
		TeamDTO td = new TeamDTO();
		td.setUserId(userId);
		TeamDTO temp = this.teamFacade.getTeam(td);
		return temp;
	}
	
	private TeamDTO getMineTeam(Long userId){
		TeamDTO team = getTeamByUserId(userId);
		Long teamId = null;
		if (null == team) {
			teamId = getTeamIdByUserId(userId);
		}else{
			teamId = team.getTeamId();
		}
		team = getTeam(teamId);
		return team;
	}
	
	private List<TeamMemberDTO> getTeamMemberList(Long teamId, String states){
		TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
		teamMemberDTO.setTeamId(teamId);
		if (StringUtils.isBlank(states)) {
			teamMemberDTO.setStatus(2);
		}else{
			teamMemberDTO.setStates(states);
		}
		List<TeamMemberDTO> members = this.teamFacade.getTeamMemberList(teamMemberDTO);
		return members;
	}
	
	@Override
	public void saveTeam(TeamDTO teamDTO) throws ArgsException {
		UserInfoDTO userInfoDTO = this.systemFacade.getUserInfo(teamDTO.getUserId());
		if (null == userInfoDTO) {
			throw new ArgsException(FailureCode.ERR_002, "会员信息未找到，请联系客服");
		}
		Long teamId = getTeamIdByUserId(teamDTO.getUserId());
		if (userInfoDTO.getIdentity().intValue() != UserIdentity.normal.getIdentity() || null != teamId) {
			throw new ArgsException(FailureCode.ERR_002, "不能创建球队，或你已经属于球队");
		}
		TeamDTO temp = getTeamByUserId(teamDTO.getUserId());
		if (null != temp) {
			throw new ArgsException(FailureCode.ERR_000, "只允许添加一支球队");
		}
		Long teamId2 = this.teamFacade.saveTeam(teamDTO);
		
		userInfoDTO.setIdentity(UserIdentity.teamManager.getIdentity());
		this.systemFacade.updateUserInfo(userInfoDTO);
		
		UserAccountDTO userAccountDTO = new UserAccountDTO();
		userAccountDTO.setMobile(userInfoDTO.getMobile());
		addMember(teamDTO.getUserId(), teamId2, TeamIdentity.footballer.getIdentity(), userAccountDTO, false);
	}
	

	@Override
	public TeamDTO getTeamByLoginUserId(Long loginUserId) throws ArgsException {
		TeamDTO teamDTO = getMineTeam(loginUserId);
		if (null == teamDTO) {
			throw new ArgsException(FailureCode.ERR_101);
		}
		return teamDTO;
	}
	
	@Override
	public TeamDTO getTeamByTeamId(Long teamId) {
		TeamDTO team = getTeam(teamId);
		return team;
	}
	
	@Override
	public TeamDTO getTeam(Long teamId) {
		if (null == teamId) {
			return null;
		}
		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setTeamId(teamId);
		teamDTO = teamFacade.getTeam(teamDTO);
		
		teamDTO.setTeamHabitCourts(getTeamHabitCourtsByTeamId(teamId));
		
		teamDTO.setTeamHabitTimes(getTeamHabitTimeByTeamId(teamId));
		
		teamDTO.setTeamHabitNumbers(getTeamHabitNumberByTeamId(teamId));
		
		List<TeamMemberDTO> members = getTeamMemberList(teamId, "2");
		teamDTO.setMembers(members);
		return teamDTO;
	}
	
	private List<TeamHabitCourtDTO> getTeamHabitCourtsByTeamId(Long teamId){
		TeamHabitCourtDTO teamHabitCourtDTO = new TeamHabitCourtDTO();
		teamHabitCourtDTO.setTeamId(teamId);
		List<TeamHabitCourtDTO> teamHabitCourts = teamFacade.getTeamHabitCourtList(teamHabitCourtDTO);
		return teamHabitCourts;
	}
	
	private List<TeamHabitTimeDTO> getTeamHabitTimeByTeamId(Long teamId){
		TeamHabitTimeDTO teamHabitTimeDTO = new TeamHabitTimeDTO();
		teamHabitTimeDTO.setTeamId(teamId);
		List<TeamHabitTimeDTO> teamHabitTimes = teamFacade.getTeamHabitTimeList(teamHabitTimeDTO);
		return teamHabitTimes;
	}

	private List<TeamHabitNumberDTO> getTeamHabitNumberByTeamId(Long teamId){
		TeamHabitNumberDTO teamHabitNumberDTO = new TeamHabitNumberDTO();
		teamHabitNumberDTO.setTeamId(teamId);
		List<TeamHabitNumberDTO> teamHabitNumbers = teamFacade.getTeamHabitNumberList(teamHabitNumberDTO);
		if (null != teamHabitNumbers && teamHabitNumbers.size() > 0) {
			for (TeamHabitNumberDTO dto : teamHabitNumbers) {
				dto.setNumberName(CommonConstants.getCompetitionFormat(dto.getNumber()));
			}
		}
		return teamHabitNumbers;
	}
	
	public List<TeamHabitCourtDTO> getTeamHabitCourts(Long loginUserId) throws ArgsException{
		Long teamId = getTeamIdByUserId(loginUserId);
		if (null == teamId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		return getTeamHabitCourtsByTeamId(teamId);
	}
	
	public List<TeamHabitTimeDTO> getTeamHabitTime(Long loginUserId) throws ArgsException{
		Long teamId = getTeamIdByUserId(loginUserId);
		if (null == teamId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		return getTeamHabitTimeByTeamId(teamId);
	}
	
	public List<TeamHabitNumberDTO> getTeamHabitNumber(Long loginUserId) throws ArgsException{
		Long teamId = getTeamIdByUserId(loginUserId);
		if (null == teamId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		return getTeamHabitNumberByTeamId(teamId);
	}
	
	@Override
	public void updateTeam(Long loginUserId, TeamDTO teamDTO) throws ArgsException {
		TeamDTO oldTeam = this.teamFacade.getTeam(teamDTO.getTeamId());
		UserInfoDTO userInfoDTO = this.systemFacade.getUserInfo(loginUserId);
		Long teamId = getTeamIdByUserId(loginUserId);
		if (null == oldTeam || null == userInfoDTO || null == teamId || teamId != teamDTO.getTeamId() || 
			(userInfoDTO.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && 
			userInfoDTO.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "管理员或副管理员才可以编辑球队信息");
		}
		teamFacade.updateTeam(teamDTO);
	}

	@Override
	public void updateMember(Long userId, Integer teamIdentity, UserAccountDTO userInfoDTO) throws ArgsException {
		UserInfoDTO mgrUser = systemFacade.getUserInfo(userId);
		if (null == mgrUser || (mgrUser.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && mgrUser.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "只有管理员或副管理员才可以添加成员");
		}
		UserInfoDTO member = systemFacade.getUserInfo(userInfoDTO.getUserId());
		if (null == member) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "球员不存在");
		}
		TeamDTO team = getTeamByLoginUserId(userId);
		TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
		teamMemberDTO.setUserId(userInfoDTO.getUserId());
		teamMemberDTO = teamFacade.getTeamMember(teamMemberDTO);
		if (null == teamMemberDTO || teamMemberDTO.getTeamId().longValue() != team.getTeamId()) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "球员不属于该球队");
		}
		teamMemberDTO.setTeamIdentity(teamIdentity);
		this.teamFacade.updateTeamMember(teamMemberDTO);
		member.setName(userInfoDTO.getName());
		member.setPoloShirtNo(userInfoDTO.getPoloShirtNo());
		this.systemFacade.updateUserInfo(member);
	}
	
	@Override
	public void addMember(Long userId, Long teamId, Integer teamIdentity, UserAccountDTO userAccountDTO, boolean push) throws ArgsException {
		TeamDTO team = this.teamFacade.getTeam(teamId);
		if (null == team || team.getUserId().longValue() != userId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserInfoDTO userInfo2 = systemFacade.getUserInfo(userId);
		if (null == userInfo2 || (userInfo2.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo2.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "只有管理员或副管理员才可以添加成员");
		}
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setMobile(userAccountDTO.getMobile());
		userInfoDTO = systemFacade.getUserInfo(userInfoDTO);
		Long ballerUserId = null;
		boolean saveRelation = true;
		int smsFlag = 1; //1加入球队成功短信 2注册并加入球队成功短信
		if (null == userInfoDTO || null == userInfoDTO.getUserId()) { //不存在则去创建帐号和用户信息
			userAccountDTO.setAccount(userAccountDTO.getMobile());
			Md5PasswordEncoder encode = new Md5PasswordEncoder();
			userAccountDTO.setPassword(encode.encodePassword("123456", null));
			userAccountDTO.setIdentity(UserIdentity.normal.getIdentity());
			userAccountDTO.setSourceFrom(userInfo2.getSourceFrom());
			ballerUserId = systemFacade.saveUserAccount(userAccountDTO);
			userInfoDTO = systemFacade.getUserInfo(ballerUserId);
			smsFlag = 2;
		}else{
			ballerUserId = userInfoDTO.getUserId();
			TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
			teamMemberDTO.setUserId(ballerUserId);
			teamMemberDTO = teamFacade.getTeamMember(teamMemberDTO);
			if (null != teamMemberDTO) {
				if (teamMemberDTO.getStatus() == 2) {
					if (teamMemberDTO.getTeamId().longValue() == teamId) {
						throw new ArgsException(FailureCode.ERR_000.getCode(), "该成员已存在本球队中");
					}else{
						throw new ArgsException(FailureCode.ERR_000.getCode(), "该成员已存在其他球队中");
					}
				}else{
					if (teamMemberDTO.getTeamId().longValue() == teamId) {
						teamMemberDTO.setStatus(2);
					}else{
						teamMemberDTO.setStatus(2);
						teamMemberDTO.setTeamId(teamId);
					}
					saveRelation = false;
					this.teamFacade.updateTeamMember(teamMemberDTO);
				}
			}
			//没有的信息 补全
			if (StringUtils.isBlank(userInfoDTO.getName()) || null == userInfoDTO.getPoloShirtNo() || null == userInfoDTO.getTeamLocation()) {
				userInfoDTO.setName(userAccountDTO.getName());
				userInfoDTO.setPoloShirtNo(userAccountDTO.getPoloShirtNo());
				userInfoDTO.setTeamLocation(userAccountDTO.getTeamLocation());
				this.systemFacade.updateUserInfo(userInfoDTO);
			}
		}
		if (null != ballerUserId) {
			if (saveRelation) {
				TeamMemberDTO tmd = new TeamMemberDTO();
				tmd.setTeamId(teamId);
				tmd.setUserId(ballerUserId);
				tmd.setCreateTime(new Date());
				tmd.setModifyTime(tmd.getCreateTime());
				tmd.setTeamIdentity(teamIdentity);
				tmd.setStatus(2);
				this.teamFacade.saveTeamMember(tmd);
			}
			
			if (push) {
				PushInfo pushInfo = CommonConstants.smsNotes.get("team_addMember_" + smsFlag);
				pushInfo.fillContent(team.getTeamName());
				pushInfo.setUserId(ballerUserId);
				pushInfo.setMobiles(userAccountDTO.getMobile());
				pushInfo.fillTemplateParam(team.getTeamName());
				pushInfo.setSendNote(true);
				pushInfo.setSendMsg(false);
				if (null != userInfoDTO.getSourceFrom() && StringUtils.isNotBlank(userInfoDTO.getClientid())) {
					pushInfo.setSourceFrom(userInfoDTO.getSourceFrom());
					pushInfo.setClientIds(userInfoDTO.getClientid());
					pushInfo.setSendMsg(true);
				}
				this.writeMessage(pushInfo);
			}
			
			TeamMemberLogDTO tmld = new TeamMemberLogDTO();
			tmld.setUserId(ballerUserId);
			tmld.setUserName(userAccountDTO.getName());
			tmld.setTeamName(team.getTeamName());
			tmld.setOperation(MemberLog.add.getOperation());
			tmld.setContent(MemberLog.add.getCaption());
			tmld.setCreateTime(new Date());
			this.teamFacade.saveTeamMemberLog(tmld);
		}else{
			throw new ArgsException(FailureCode.ERR_001);
		}
	}
	
	@Override
	public void auditMember(Long loginUserId, TeamMemberDTO teamMemberDTO) throws ArgsException{
		if (null == teamMemberDTO || null == teamMemberDTO.getUserId() || null == teamMemberDTO.getStatus()) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		TeamDTO team = getTeamByLoginUserId(loginUserId);
		if (null == team) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "没有球队，或不是该球队的管理员");
		}
		UserInfoDTO userInfoDTO = systemFacade.getUserInfo(teamMemberDTO.getUserId());
		if (null == userInfoDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "用户查询失败");
		}
		TeamMemberDTO teamMember = new TeamMemberDTO();
		teamMember.setTeamId(team.getTeamId());
		teamMember.setUserId(teamMemberDTO.getUserId());
		teamMember = teamFacade.getTeamMember(teamMember);
		if (null == teamMember || null == teamMember.getId()) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "没有找到申请信息");
		}
		teamMember.setStatus(teamMemberDTO.getStatus());
		teamMember.setNoPassReason(teamMemberDTO.getNoPassReason());
		this.teamFacade.updateTeamMember(teamMember);
		
		TeamMemberLogDTO tmld = new TeamMemberLogDTO();
		tmld.setUserId(userInfoDTO.getUserId());
		tmld.setUserName(userInfoDTO.getName());
		tmld.setTeamName(team.getTeamName());
		tmld.setOperation(teamMember.getStatus() == 2 ? MemberLog.add.getOperation() : MemberLog.nopass.getOperation());
		tmld.setContent(teamMember.getStatus() == 2 ? MemberLog.add.getCaption() : teamMember.getNoPassReason());
		tmld.setCreateTime(new Date());
		this.teamFacade.saveTeamMemberLog(tmld);
		
		String key = "team_auditMember_3";
		if (teamMember.getStatus() == 2) {
			key = "team_addMember_1";
		}
		PushInfo pushInfo = CommonConstants.smsNotes.get(key);
		pushInfo.fillContent(team.getTeamName(), teamMember.getNoPassReason());
		pushInfo.setUserId(userInfoDTO.getUserId());
		pushInfo.setMobiles(userInfoDTO.getMobile());
		pushInfo.fillTemplateParam(userInfoDTO.getName());
		pushInfo.setSendNote(true);
		pushInfo.setSendMsg(false);
		if (null != userInfoDTO.getSourceFrom() && StringUtils.isNotBlank(userInfoDTO.getClientid())) {
			pushInfo.setSourceFrom(userInfoDTO.getSourceFrom());
			pushInfo.setClientIds(userInfoDTO.getClientid());
			pushInfo.setSendMsg(true);
		}
		this.writeMessage(pushInfo);
	}
	
	@Override
	public void applyMember(Long userId, Long teamId, Integer teamIdentity, UserAccountDTO userAccountDTO) throws ArgsException {
		TeamDTO team = this.teamFacade.getTeam(teamId);
		if (null == team) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserInfoDTO userInfoDTO = systemFacade.getUserInfo(userId);
		if (null == userInfoDTO || userInfoDTO.getIdentity().intValue() != UserIdentity.normal.getIdentity()) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "只有普通会员才可以申请成为球队成员");
		}
		boolean saveRelation = true;
		TeamMemberDTO teamMemberDTO = new TeamMemberDTO();
		teamMemberDTO.setUserId(userInfoDTO.getUserId());
		teamMemberDTO = teamFacade.getTeamMember(teamMemberDTO);
		if (null != teamMemberDTO) {
			if (teamMemberDTO.getStatus() == 2) {
				if (teamMemberDTO.getTeamId().longValue() == teamId) {
					throw new ArgsException(FailureCode.ERR_000.getCode(), "你已经是该球队成员");
				}else{
					throw new ArgsException(FailureCode.ERR_000.getCode(), "你已经是其他球队成员，不 能申请");
				}
			}else if(teamMemberDTO.getTeamId().longValue() == teamId){
				if (teamMemberDTO.getStatus() == 1) {
					throw new ArgsException(FailureCode.ERR_000.getCode(), "你已经申请过该球队，清等待审核");
				}else if(teamMemberDTO.getStatus() == 3) {
					throw new ArgsException(FailureCode.ERR_000.getCode(), teamMemberDTO.getNoPassReason());
				}
			}else{
				if (teamMemberDTO.getStatus() == 1) {
					throw new ArgsException(FailureCode.ERR_000.getCode(), "你已经申请过其他球队，清等待审核");
				}else if(teamMemberDTO.getStatus() == 3) {
					//其他球队被拒，可以申请
					teamMemberDTO.setStatus(1);
					teamMemberDTO.setTeamId(teamId);
					teamMemberDTO.setNoPassReason(null);
					this.teamFacade.updateTeamMember(teamMemberDTO);
					TeamMemberLogDTO tmld = new TeamMemberLogDTO();
					tmld.setUserId(userInfoDTO.getUserId());
					tmld.setUserName(userAccountDTO.getName());
					tmld.setTeamName(team.getTeamName());
					tmld.setOperation(MemberLog.apply.getOperation());
					tmld.setContent(MemberLog.apply.getCaption());
					tmld.setCreateTime(new Date());
					this.teamFacade.saveTeamMemberLog(tmld);
					saveRelation = false;
				}
			}
		}
		//没有的信息 补全
		boolean updateUser = false;
		if (StringUtils.isBlank(userInfoDTO.getName()) ) {
			userInfoDTO.setName(userAccountDTO.getName());
			updateUser = true;
		}
		if (null == userInfoDTO.getPoloShirtNo()) {
			userInfoDTO.setPoloShirtNo(userAccountDTO.getPoloShirtNo());
			updateUser = true;
		}
		if (null == userInfoDTO.getTeamLocation()) {
			userInfoDTO.setTeamLocation(userAccountDTO.getTeamLocation());
			updateUser = true;
		}
		if (updateUser) {
			this.systemFacade.updateUserInfo(userInfoDTO);
		}
		if (saveRelation) {
			TeamMemberDTO tmd = new TeamMemberDTO();
			tmd.setTeamId(teamId);
			tmd.setUserId(userInfoDTO.getUserId());
			tmd.setCreateTime(new Date());
			tmd.setModifyTime(tmd.getCreateTime());
			tmd.setTeamIdentity(teamIdentity);
			tmd.setStatus(1);
			this.teamFacade.saveTeamMember(tmd);
			TeamMemberLogDTO tmld = new TeamMemberLogDTO();
			tmld.setUserId(userInfoDTO.getUserId());
			tmld.setUserName(userAccountDTO.getName());
			tmld.setTeamName(team.getTeamName());
			tmld.setOperation(MemberLog.apply.getOperation());
			tmld.setContent(MemberLog.apply.getCaption());
			tmld.setCreateTime(new Date());
			this.teamFacade.saveTeamMemberLog(tmld);
		}
		
		UserInfoDTO teamManager = this.systemFacade.getUserInfo(team.getUserId());
		PushInfo pushInfo = CommonConstants.smsNotes.get("team_applyMember");
		pushInfo.fillContent(userInfoDTO.getName());
		pushInfo.setUserId(teamManager.getUserId());
		pushInfo.setMobiles(teamManager.getMobile());
		pushInfo.fillTemplateParam(userInfoDTO.getName());
		pushInfo.setSendNote(false);
		pushInfo.setSendMsg(false);
		if (null != teamManager.getSourceFrom() && StringUtils.isNotBlank(teamManager.getClientid())) {
			pushInfo.setSourceFrom(teamManager.getSourceFrom());
			pushInfo.setClientIds(teamManager.getClientid());
			pushInfo.setSendMsg(true);
		}
		this.writeMessage(pushInfo);
	}
	
	@Override
	public void delMember(Long loginUserId, TeamMemberDTO tmd) throws ArgsException {
		List<TeamMemberDTO> tmds = this.teamFacade.getTeamMemberList(tmd);
		if (null == tmds || tmds.isEmpty()) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserInfoDTO userInfo2 = systemFacade.getUserInfo(loginUserId);
		if (null == userInfo2 || (userInfo2.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo2.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "只有管理员或副管理员才可以添加成员");
		}
		tmd = tmds.get(0);
		TeamDTO teamDTO = this.getTeamByUserId(loginUserId);
		if (null == teamDTO || teamDTO.getTeamId().longValue() != tmd.getTeamId()) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserInfoDTO userInfoDTO = this.systemFacade.getUserInfo(tmd.getUserId());
		if (null != userInfoDTO && userInfoDTO.getIdentity().intValue() != UserIdentity.normal.getIdentity()) {
			userInfoDTO.setIdentity(UserIdentity.normal.getIdentity());
			this.systemFacade.updateUserInfo(userInfoDTO);
		}
		this.teamFacade.deleteTeamMember(tmd.getId());
		
		TeamMemberLogDTO tmld = new TeamMemberLogDTO();
		tmld.setUserId(tmd.getUserId());
		tmld.setUserName(tmd.getUserName());
		tmld.setTeamName(teamDTO.getTeamName());
		tmld.setOperation(MemberLog.del.getOperation());
		tmld.setContent(MemberLog.del.getCaption());
		tmld.setCreateTime(new Date());
		this.teamFacade.saveTeamMemberLog(tmld);
		
		PushInfo pushInfo = CommonConstants.smsNotes.get("team_delMember");
		pushInfo.fillContent(teamDTO.getTeamName());
		pushInfo.setUserId(tmd.getUserId());
		pushInfo.setMobiles(userInfoDTO.getMobile());
		pushInfo.fillTemplateParam(teamDTO.getTeamName());
		pushInfo.setSendNote(true);
		pushInfo.setSendMsg(false);
		if (null != userInfoDTO.getSourceFrom() && StringUtils.isNotBlank(userInfoDTO.getClientid())) {
			pushInfo.setSendMsg(true);
			pushInfo.setSourceFrom(userInfoDTO.getSourceFrom());
			pushInfo.setClientIds(userInfoDTO.getClientid());
		}
		this.writeMessage(pushInfo);
	}

	@Override
	public void setFreeTime(Long loginUserId, Long teamId, List<TeamHabitTimeDTO> thtds) throws ArgsException {
		UserInfoDTO userInfo2 = systemFacade.getUserInfo(loginUserId);
		if (null == userInfo2 || (userInfo2.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo2.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "只有管理员或副管理员才可以添加成员");
		}
		TeamDTO teamDTO = this.getTeamByLoginUserId(loginUserId); // this.teamFacade.getTeam(loginUserId);
		if (null == teamDTO || teamDTO.getTeamId().longValue() != teamId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		this.teamFacade.deleteTeamHabitTimeByTeamId(teamId);
		if (null != thtds && !thtds.isEmpty()) {
			for (TeamHabitTimeDTO teamHabitTimeDTO : thtds) {
				teamHabitTimeDTO.setTeamId(teamId);
				teamHabitTimeDTO.setUserId(loginUserId);
			}
			this.teamFacade.batchSaveTeamHabitTime(thtds);
		}
	}

	@Override
	public void setCourt(Long loginUserId, Long teamId, String courtIds) throws ArgsException {
		UserInfoDTO userInfo2 = systemFacade.getUserInfo(loginUserId);
		if (null == userInfo2 || (userInfo2.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo2.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "只有管理员或副管理员才可以添加成员");
		}
		TeamDTO teamDTO = this.getTeamByLoginUserId(loginUserId);
		if (null == teamDTO || teamDTO.getTeamId().longValue() != teamId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		this.teamFacade.deleteTeamHabitCourtByTeamId(teamId);
		if (StringUtils.isNotBlank(courtIds)) {
			String[] ids = courtIds.split(",");
			List<TeamHabitCourtDTO> courts = new ArrayList<>();
			for (String courtId : ids) {
				TeamHabitCourtDTO dto = new TeamHabitCourtDTO();
				dto.setCourtId(Long.valueOf(courtId));
				dto.setTeamId(teamId);
				dto.setUserId(loginUserId);
				courts.add(dto);
			}
			this.teamFacade.batchSaveTeamHabitCourt(courts);
		}
	}

	@Override
	public void setNumber(Long loginUserId, Long teamId, String numbers) throws ArgsException {
		UserInfoDTO userInfo2 = systemFacade.getUserInfo(loginUserId);
		if (null == userInfo2 || (userInfo2.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfo2.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity())) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "只有管理员或副管理员才可以添加成员");
		}
		TeamDTO teamDTO = this.getTeamByLoginUserId(loginUserId);
		if (null == teamDTO || teamDTO.getTeamId().longValue() != teamId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		this.teamFacade.deleteTeamHabitNumberByTeamId(teamId);
		if (StringUtils.isNotBlank(numbers)) {
			String[] num = numbers.split(",");
			List<TeamHabitNumberDTO> nums = new ArrayList<>();
			for (String n : num) {
				TeamHabitNumberDTO dto = new TeamHabitNumberDTO();
				dto.setNumber(Integer.valueOf(n));
				dto.setTeamId(teamId);
				dto.setUserId(loginUserId);
				nums.add(dto);
			}
			this.teamFacade.batchSaveTeamHabitNumber(nums);
		}
	}

	@Override
	public List<Map<String, Object>> getMembersByLoginUserId(Long loginUserId, String states) {
		Long teamId = getTeamIdByUserId(loginUserId);
		List<Map<String, Object>> res = new ArrayList<>();
		if (null != teamId) {
			List<TeamMemberDTO> members = getTeamMemberList(teamId, states);
			for (TeamMemberDTO teamMemberDTO : members) {
				Map<String, Object> temp = new HashMap<>();
				CglibBeanUtils.addToMap(teamMemberDTO, temp);
				temp.remove("id");
				temp.put("teamIdentityStr", CommonConstants.getTeanIdentity(teamMemberDTO.getTeamIdentity()));
				temp.put("userIdentityStr", CommonConstants.getUserIdentity(teamMemberDTO.getUserIdentity()));
				res.add(temp);
			}
		}
		return res;
	}
	
	@Override
	public void transferTeam(Long loginUserId, Long userId) throws ArgsException {
		UserInfoDTO manager = this.systemFacade.getUserInfo(loginUserId);
		UserInfoDTO member = this.systemFacade.getUserInfo(userId);
		if (null == manager || null == member || manager.getIdentity().intValue() != UserIdentity.teamManager.getIdentity()) {
			throw new ArgsException(FailureCode.ERR_002, "参数错误，球队管理员才能做此操作");
		}
		if (member.getIdentity().intValue() == UserIdentity.teamManager.getIdentity()) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "被转让的用户已经是球队管理员");
		}
		TeamDTO team = getTeamByUserId(loginUserId);
		Long teamId = getTeamIdByUserId(userId);
		if (team.getTeamId().longValue() != teamId) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "管理员和成员必须在同一支球队");
		}
		team.setUserId(userId);
		int res = this.teamFacade.updateTeam(team);
		if (res > 0) {
			manager.setIdentity(UserIdentity.normal.getIdentity());
			member.setIdentity(UserIdentity.teamManager.getIdentity());
			this.systemFacade.updateUserInfo(manager);
			this.systemFacade.updateUserInfo(member);
			
			//发push和短信  给被转让人
			PushInfo pushInfo = CommonConstants.smsNotes.get("team_transfer_1");
			pushInfo.fillContent(team.getTeamName());
			pushInfo.fillTemplateParam(team.getTeamName());
			pushInfo.setUserId(member.getUserId());
			pushInfo.setMobiles(member.getMobile());
			pushInfo.setSendNote(true);
			pushInfo.setSendMsg(false);
			if (null != member.getSourceFrom() && StringUtils.isNotBlank(member.getClientid())) {
				pushInfo.setSendMsg(true);
				pushInfo.setClientIds(member.getClientid());
				pushInfo.setSourceFrom(member.getSourceFrom());
			}
			this.writeMessage(pushInfo);
			
			//发push和短信  给被转让人和转让人之外的其他成员
			List<Long> exceptUserIds = new ArrayList<>();
			exceptUserIds.add(loginUserId);
			exceptUserIds.add(userId);
			PushInfo[] pushInfo3 = getTeamMemberUserIdsAndMobiles(teamId, exceptUserIds);
			PushInfo pushInfo2 = CommonConstants.smsNotes.get("team_transfer_2");
			pushInfo2.fillContent(team.getTeamName(), member.getName());
			pushInfo2.fillTemplateParam(team.getTeamName(), member.getName());
			pushInfo2.setMobiles(pushInfo3[0].getMobiles());
			pushInfo2.setSendNote(true);
			pushInfo2.setSendMsg(false);
			if (null != pushInfo3[0].getUserId() && pushInfo3[0].getUserId().length > 0 && null != pushInfo3[0].getClientIds() && pushInfo3[0].getClientIds().length > 0) {
				pushInfo2.setSendMsg(true);
				pushInfo2.setUserId(pushInfo3[0].getUserId());
				pushInfo2.setClientIds(pushInfo3[0].getClientIds());
			}
			this.writeMessage(pushInfo2);
			pushInfo2.setSendNote(false);
			pushInfo2.setSendMsg(false);
			if (null != pushInfo3[1].getUserId() && pushInfo3[1].getUserId().length > 0 && null != pushInfo3[1].getClientIds() && pushInfo3[1].getClientIds().length > 0) {
				pushInfo2.setSendMsg(true);
				pushInfo2.setUserId(pushInfo3[1].getUserId());
				pushInfo2.setClientIds(pushInfo3[1].getClientIds());
			}
			this.writeMessage(pushInfo2);
		}else{
			throw new ArgsException(FailureCode.ERR_000.getCode(), "更新球队信息失败");
		}
	}
	
	/**
	 * 得到球队的成员用户Id和mobile
	 * @author liuhua
	 *
	 * @param teamId
	 * @param exceptUserIds
	 * @return
	 */
	private PushInfo[] getTeamMemberUserIdsAndMobiles(Long teamId, List<Long> exceptUserIds){
		List<TeamMemberDTO> memebers = getTeamMemberList(teamId, "2");
		String mobiles = "";
		List<Long> userIds1 = new ArrayList<>();
		List<String> clientIds1 = new ArrayList<>();
		List<Long> userIds2 = new ArrayList<>();
		List<String> clientIds2 = new ArrayList<>();
		for (TeamMemberDTO teamMemberDTO : memebers) {
			Long uid = teamMemberDTO.getUserId().longValue();
			if (null == exceptUserIds || ! exceptUserIds.contains(uid)) {
				UserInfoDTO userInfoDTO = this.systemFacade.getUserInfo(uid);
				if (null != userInfoDTO.getSourceFrom()) {
					if (userInfoDTO.getSourceFrom() == 1) {
						userIds1.add(uid);
						clientIds1.add(userInfoDTO.getClientid());
					}else if (userInfoDTO.getSourceFrom() == 2) {
						userIds2.add(uid);
						clientIds2.add(userInfoDTO.getClientid());
					}
				}
				mobiles += "," + userInfoDTO.getMobile();
			}
		}
		if (mobiles.length() > 0) {
			mobiles = mobiles.substring(1);
		}
		PushInfo pushInfo1 = new PushInfo(userIds1, mobiles, clientIds1);
		PushInfo pushInfo2 = new PushInfo(userIds2, mobiles, clientIds2);
		return new PushInfo[]{pushInfo1, pushInfo2};
	}

	@Override
	public void setManager(Long loginUserId, Long userId) throws ArgsException {
		UserInfoDTO manager = this.systemFacade.getUserInfo(loginUserId);
		UserInfoDTO member = this.systemFacade.getUserInfo(userId);
		if (null == manager || null == member || manager.getIdentity().intValue() != UserIdentity.teamManager.getIdentity()) {
			throw new ArgsException(FailureCode.ERR_002, "参数错误，球队管理员才能做此操作");
		}
		TeamDTO team = getTeamByUserId(loginUserId);
		if (member.getIdentity().intValue() == UserIdentity.teamManager.getIdentity() || member.getIdentity().intValue() == UserIdentity.teamManager2.getIdentity()) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "被指定的用户已经是球队管理员或副管理员");
		}
		Long teamId = getTeamIdByUserId(userId);
		if (team.getTeamId().longValue() != teamId) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "管理员和成员必须在同一支球队");
		}
		UserInfoDTO userInfoDTO = getTeamManager(teamId, UserIdentity.teamManager2);
		if (null != userInfoDTO) {
			//如果之前有副管理员，则置为普通用户
			userInfoDTO.setIdentity(UserIdentity.normal.getIdentity());
			this.systemFacade.updateUserInfo(member);
		}
		//将用户指定为副管理员
		member.setIdentity(UserIdentity.teamManager2.getIdentity());
		int res = this.systemFacade.updateUserInfo(member);
		if (res > 0) {
			//发push和短信  给被转让人
			PushInfo pushInfo = CommonConstants.smsNotes.get("team_setManager_1");
			pushInfo.fillContent(team.getTeamName());
			pushInfo.fillTemplateParam(team.getTeamName());
			pushInfo.setUserId(member.getUserId());
			pushInfo.setMobiles(member.getMobile());
			pushInfo.setSendNote(true);
			pushInfo.setSendMsg(false);
			if (null != member.getSourceFrom() && StringUtils.isNotBlank(member.getClientid())) {
				pushInfo.setSendMsg(true);
				pushInfo.setClientIds(member.getClientid());
				pushInfo.setSourceFrom(member.getSourceFrom());
			}
			this.writeMessage(pushInfo);
			
			//发push和短信  给被转让人和转让人之外的其他成员
			List<Long> exceptUserIds = new ArrayList<>();
			exceptUserIds.add(loginUserId);
			exceptUserIds.add(userId);
			PushInfo[] pushInfo3 = getTeamMemberUserIdsAndMobiles(teamId, exceptUserIds);
			PushInfo pushInfo2 = CommonConstants.smsNotes.get("team_setManager_2");
			pushInfo2.fillContent(member.getName(), team.getTeamName());
			pushInfo2.fillTemplateParam(team.getTeamName(), member.getName());
			pushInfo2.setMobiles(pushInfo3[0].getMobiles());
			pushInfo2.setSendNote(true);
			pushInfo2.setSendMsg(false);
			if (null != pushInfo3[0].getUserId() && pushInfo3[0].getUserId().length > 0 && null != pushInfo3[0].getClientIds() && pushInfo3[0].getClientIds().length > 0) {
				pushInfo2.setSendMsg(true);
				pushInfo2.setUserId(pushInfo3[0].getUserId());
				pushInfo2.setClientIds(pushInfo3[0].getClientIds());
			}
			this.writeMessage(pushInfo2);
			pushInfo2.setSendNote(false);
			pushInfo2.setSendMsg(false);
			if (null != pushInfo3[1].getUserId() && pushInfo3[1].getUserId().length > 0 && null != pushInfo3[1].getClientIds() && pushInfo3[1].getClientIds().length > 0) {
				pushInfo2.setSendMsg(true);
				pushInfo2.setUserId(pushInfo3[1].getUserId());
				pushInfo2.setClientIds(pushInfo3[1].getClientIds());
			}
			this.writeMessage(pushInfo2);
		}else{
			throw new ArgsException(FailureCode.ERR_000.getCode(), "更新副管理员信息失败");
		}
	}

	/**
	 * 获取球队的管理员
	 * @author liuhua
	 *
	 * @param teamId
	 * @param userIdentity
	 * @return
	 */
	private UserInfoDTO getTeamManager(Long teamId, UserIdentity userIdentity) {
		PushInfo[] pi = getTeamMemberUserIdsAndMobiles(teamId, null);
		Long[] userIds = pi[0].getUserId();
		Long[] userIds1 = pi[1].getUserId();
		String uids = "";
		for (Long userId : userIds) {
			uids += "," + userId;
		}
		for (Long userId : userIds1) {
			uids += "," + userId;
		}
		if (uids.length() > 0) {
			uids = uids.substring(1);
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			userInfoDTO.setUserIds(uids);
			userInfoDTO.setIdentity(userIdentity.getIdentity());
			userInfoDTO = this.systemFacade.getUserInfo(userInfoDTO);
			return userInfoDTO;
		}
		return null;
	}

	@Override
	public void attentionTeam(Long userId, Long teamId) {
		TeamAttentionDTO teamAttentionDTO = new TeamAttentionDTO();
		teamAttentionDTO.setTeamId(teamId);
		teamAttentionDTO.setUserId(userId);
		teamAttentionDTO = this.teamFacade.getTeamAttention(teamAttentionDTO);
		if (null == teamAttentionDTO || null == teamAttentionDTO.getId()) {
			teamAttentionDTO = new TeamAttentionDTO();
			teamAttentionDTO.setCreateTime(new Date());
			teamAttentionDTO.setTeamId(teamId);
			teamAttentionDTO.setUserId(userId);
			this.teamFacade.saveTeamAttention(teamAttentionDTO);
		}else{
			this.teamFacade.deleteTeamAttention(teamAttentionDTO.getId());
		}
	}

	@Override
	public boolean isAttentionTeam(Long userId, Long teamId) {
		TeamAttentionDTO teamAttentionDTO = new TeamAttentionDTO();
		teamAttentionDTO.setTeamId(teamId);
		teamAttentionDTO.setUserId(userId);
		teamAttentionDTO = this.teamFacade.getTeamAttention(teamAttentionDTO);
		if (null == teamAttentionDTO || null == teamAttentionDTO.getId()) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Object> getCompanyByLoginUserId(Long loginUserId) throws ArgsException {
		Long teamId = getTeamIdByUserId(loginUserId);
		if (null == teamId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		TeamDTO teamDTO = teamFacade.getTeam(teamId);
		if (null == teamDTO) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		Long companyId = teamDTO.getCompanyId();
		if (null == companyId) {
			throw new ArgsException(FailureCode.ERR_000.getCode(), "没有企业信息");
		}
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setCompanyId(companyId);
		companyDTO.setEnabled(1);
		companyDTO = this.commonFacade.getCompany(companyDTO);
		if (null == companyDTO) {
			throw new ArgsException(FailureCode.ERR_000.getCode(), "没有企业信息或企业信息已禁用");
		}
		Map<String, Object> map = new HashMap<>();
		CglibBeanUtils.addToMap(companyDTO, map, "yyyy-MM-dd HH:mm:ss");
		return map;
	}

	@Override
	public void updateCompany(Long loginUserId, CompanyDTO companyDTO) throws ArgsException {
		if (null == companyDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "参数没有接收到");
		}
		UserInfoDTO userInfoDTO = this.systemFacade.getUserInfo(loginUserId);
		if (null == userInfoDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "登录用户信息没有获取到");
		}
		if (userInfoDTO.getIdentity().intValue() != UserIdentity.teamManager.getIdentity() && userInfoDTO.getIdentity().intValue() != UserIdentity.teamManager2.getIdentity()) {
			throw new ArgsException(FailureCode.ERR_000.getCode(), "球队管理员或副管理员才可以修改企业信息");
		}
		Long teamId = getTeamIdByUserId(loginUserId);
		if (null == teamId) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "登录用户没有球队");
		}
		TeamDTO teamDTO = teamFacade.getTeam(teamId);
		if (null == teamDTO) {
			throw new ArgsException(FailureCode.ERR_002.getCode(), "登录用户的球队信息不存在");
		}
		if (null != companyDTO.getFrom() && companyDTO.getFrom() == 1) {
			companyDTO.setCompanyName(companyDTO.getInvoiceTitle());
			Long companyId = this.commonFacade.saveCompany(companyDTO);
			teamDTO.setCompanyId(companyId);
			this.teamFacade.updateTeam(teamDTO);
		}else{
			Long companyId = teamDTO.getCompanyId();
			if (null == companyId) {
				throw new ArgsException(FailureCode.ERR_002.getCode(), "登录用户的球队没有绑定企业");
			}
			companyDTO.setCompanyId(companyId);
			companyDTO.setCompanyName(companyDTO.getInvoiceTitle());
			int res = this.commonFacade.updateCompany(companyDTO);
			if (res == 0) {
				throw new ArgsException(FailureCode.ERR_001.getCode(), "执行更新操作失败！bug");
			}
		}
	}

	/*private void checkTeamManagerNumber(TeamDTO team) throws ArgsException {
		PushInfo pi = getTeamMemberUserIdsAndMobiles(team.getTeamId(), null);
		Long[] userIds = pi.getUserId();
		String uids = "";
		for (Long userId : userIds) {
			uids += "," + userId;
		}
		if (uids.length() > 0) {
			uids = uids.substring(1);
			UserInfoDTO userInfoDTO = new UserInfoDTO();
			userInfoDTO.setUserIds(uids);
			userInfoDTO.setIdentity(UserIdentity.teamManager2.getIdentity());
			int num = this.systemFacade.getUserInfoCount(userInfoDTO);
			if (num > 1) {
				throw new ArgsException(FailureCode.ERR_002.getCode(), "球队最多允许一名副管理员");
			}
		}
		throw new ArgsException(FailureCode.ERR_002.getCode(), "球队成员数量不够");
	}*/
}
