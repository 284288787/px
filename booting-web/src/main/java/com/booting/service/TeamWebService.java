/**create by liuhua at 2017年6月26日 上午10:18:27**/
package com.booting.service;

import java.util.List;
import java.util.Map;

import com.booting.pub.dto.CompanyDTO;
import com.booting.system.dto.UserAccountDTO;
import com.booting.team.dto.TeamDTO;
import com.booting.team.dto.TeamHabitCourtDTO;
import com.booting.team.dto.TeamHabitNumberDTO;
import com.booting.team.dto.TeamHabitTimeDTO;
import com.booting.team.dto.TeamMemberDTO;
import com.star.framework.specification.exception.ArgsException;

public interface TeamWebService {

	public TeamDTO getTeam(Long teamId);

	public void saveTeam(TeamDTO teamDTO) throws ArgsException;

	public void updateTeam(Long loginUserId, TeamDTO teamDTO) throws ArgsException;

	public void addMember(Long userId, Long teamId, Integer teamIdentity, UserAccountDTO userAccountDTO, boolean push) throws ArgsException;

	public void delMember(Long loginUserId, TeamMemberDTO tmd) throws ArgsException;

	public void setFreeTime(Long loginUserId, Long teamId, List<TeamHabitTimeDTO> thtds) throws ArgsException;

	public void setCourt(Long loginUserId, Long teamId, String courtIds) throws ArgsException;

	public void setNumber(Long loginUserId, Long teamId, String numbers) throws ArgsException;

	public TeamDTO getTeamByLoginUserId(Long loginUserId) throws ArgsException;

	public List<Map<String, Object>> getMembersByLoginUserId(Long loginUserId, String statuses);

	public void transferTeam(Long loginUserId, Long userId) throws ArgsException;

	public void setManager(Long loginUserId, Long userId) throws ArgsException;

	public void attentionTeam(Long loginUserId, Long teamId);

	public boolean isAttentionTeam(Long loginUserId, Long teamId);

	public List<TeamHabitCourtDTO> getTeamHabitCourts(Long loginUserId) throws ArgsException;
	public List<TeamHabitTimeDTO> getTeamHabitTime(Long loginUserId) throws ArgsException;
	public List<TeamHabitNumberDTO> getTeamHabitNumber(Long loginUserId) throws ArgsException;

	public Map<String, Object> getCompanyByLoginUserId(Long loginUserId) throws ArgsException;

	public void updateCompany(Long loginUserId, CompanyDTO companyDTO) throws ArgsException;

	public TeamDTO getTeamByTeamId(Long teamId);

	public void applyMember(Long loginUserId, Long teamId, Integer teamIdentity, UserAccountDTO userInfoDTO) throws ArgsException;

	public void auditMember(Long loginUserId, TeamMemberDTO teamMemberDTO) throws ArgsException;

	public void updateMember(Long loginUserId, Integer teamIdentity, UserAccountDTO userInfoDTO) throws ArgsException;
	
}
