/** create by auto at 2017-06-23 16:08:02**/
package com.booting.team.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;
import com.booting.team.dto.TeamAttentionDTO;
import com.booting.team.dto.TeamDTO;
import com.booting.team.entity.TeamAttentionEntity;
import com.booting.team.entity.TeamEntity;
import com.booting.team.dto.TeamHabitCourtDTO;
import com.booting.team.entity.TeamHabitCourtEntity;
import com.booting.team.dto.TeamHabitNumberDTO;
import com.booting.team.entity.TeamHabitNumberEntity;
import com.booting.team.dto.TeamHabitTimeDTO;
import com.booting.team.dto.TeamMemberDTO;
import com.booting.team.dto.TeamMemberLogDTO;
import com.booting.team.entity.TeamHabitTimeEntity;
import com.booting.team.entity.TeamMemberEntity;
import com.booting.team.entity.TeamMemberLogEntity;

public interface TeamFacade extends Serializable {

	/**
	 * 新增 球队
	 */
	public Long saveTeam(TeamDTO teamDTO);

	/**
	 * 批量新增 球队
	 */
	public void batchSaveTeam(List<TeamDTO> dtos);

	/**
	 * 更新 球队
	 */
	public int updateTeam(TeamDTO teamDTO);

	/**
	 * 批量 球队
	 */
	public void batchUpdateTeam(List<TeamDTO> dtos);

	/**
	 * 删除 球队
	 */
	public int deleteTeam(long teamId);

	/**
	 * 根据主键获取 球队
	 */
	public TeamDTO getTeam(long teamId);

	/**
	 * 根据条件获取一条 球队
	 */
	public TeamDTO getTeam(TeamDTO teamDTO);

	/**
	 * 查询满足条件的 球队 列表(单表)
	 */
	public List<TeamDTO> getTeamList(TeamDTO teamDTO);

	/**
	 * 查询满足条件的 球队 列表(分页)(单表)
	 */
	public PageList<TeamDTO> getTeamListForPage(TeamDTO teamDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球队 列表(分页)(单表)
	 */
	public PageList<TeamDTO> getTeamListForPage(QueryParam queryParam);

	/**
	 * 球队DTO 转换成 Entity
	 */
	public TeamEntity toTeamEntity(TeamDTO teamDTO);

	/**
	 * 球队DTOs 转换成 Entities
	 */
	public List<TeamEntity> toTeamEntities(List<TeamDTO> dtoes);

	/**
	 * 新增 球队习惯_球场
	 */
	public Long saveTeamHabitCourt(TeamHabitCourtDTO teamHabitCourtDTO);

	/**
	 * 批量新增 球队习惯_球场
	 */
	public void batchSaveTeamHabitCourt(List<TeamHabitCourtDTO> dtos);

	/**
	 * 更新 球队习惯_球场
	 */
	public int updateTeamHabitCourt(TeamHabitCourtDTO teamHabitCourtDTO);

	/**
	 * 批量 球队习惯_球场
	 */
	public void batchUpdateTeamHabitCourt(List<TeamHabitCourtDTO> dtos);

	/**
	 * 删除 球队习惯_球场
	 */
	public int deleteTeamHabitCourt(long id);

	/**
	 * 根据主键获取 球队习惯_球场
	 */
	public TeamHabitCourtDTO getTeamHabitCourt(long id);

	/**
	 * 根据条件获取一条 球队习惯_球场
	 */
	public TeamHabitCourtDTO getTeamHabitCourt(TeamHabitCourtDTO teamHabitCourtDTO);

	/**
	 * 查询满足条件的 球队习惯_球场 列表(单表)
	 */
	public List<TeamHabitCourtDTO> getTeamHabitCourtList(TeamHabitCourtDTO teamHabitCourtDTO);

	/**
	 * 查询满足条件的 球队习惯_球场 列表(分页)(单表)
	 */
	public PageList<TeamHabitCourtDTO> getTeamHabitCourtListForPage(TeamHabitCourtDTO teamHabitCourtDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球队习惯_球场 列表(分页)(单表)
	 */
	public PageList<TeamHabitCourtDTO> getTeamHabitCourtListForPage(QueryParam queryParam);

	/**
	 * 球队习惯_球场DTO 转换成 Entity
	 */
	public TeamHabitCourtEntity toTeamHabitCourtEntity(TeamHabitCourtDTO teamHabitCourtDTO);

	/**
	 * 球队习惯_球场DTOs 转换成 Entities
	 */
	public List<TeamHabitCourtEntity> toTeamHabitCourtEntities(List<TeamHabitCourtDTO> dtoes);

	/**
	 * 新增 球队习惯_人数
	 */
	public Long saveTeamHabitNumber(TeamHabitNumberDTO teamHabitNumberDTO);

	/**
	 * 批量新增 球队习惯_人数
	 */
	public void batchSaveTeamHabitNumber(List<TeamHabitNumberDTO> dtos);

	/**
	 * 更新 球队习惯_人数
	 */
	public int updateTeamHabitNumber(TeamHabitNumberDTO teamHabitNumberDTO);

	/**
	 * 批量 球队习惯_人数
	 */
	public void batchUpdateTeamHabitNumber(List<TeamHabitNumberDTO> dtos);

	/**
	 * 删除 球队习惯_人数
	 */
	public int deleteTeamHabitNumber(long id);

	/**
	 * 根据主键获取 球队习惯_人数
	 */
	public TeamHabitNumberDTO getTeamHabitNumber(long id);

	/**
	 * 根据条件获取一条 球队习惯_人数
	 */
	public TeamHabitNumberDTO getTeamHabitNumber(TeamHabitNumberDTO teamHabitNumberDTO);

	/**
	 * 查询满足条件的 球队习惯_人数 列表(单表)
	 */
	public List<TeamHabitNumberDTO> getTeamHabitNumberList(TeamHabitNumberDTO teamHabitNumberDTO);

	/**
	 * 查询满足条件的 球队习惯_人数 列表(分页)(单表)
	 */
	public PageList<TeamHabitNumberDTO> getTeamHabitNumberListForPage(TeamHabitNumberDTO teamHabitNumberDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球队习惯_人数 列表(分页)(单表)
	 */
	public PageList<TeamHabitNumberDTO> getTeamHabitNumberListForPage(QueryParam queryParam);

	/**
	 * 球队习惯_人数DTO 转换成 Entity
	 */
	public TeamHabitNumberEntity toTeamHabitNumberEntity(TeamHabitNumberDTO teamHabitNumberDTO);

	/**
	 * 球队习惯_人数DTOs 转换成 Entities
	 */
	public List<TeamHabitNumberEntity> toTeamHabitNumberEntities(List<TeamHabitNumberDTO> dtoes);

	/**
	 * 新增 球队习惯_时间
	 */
	public Long saveTeamHabitTime(TeamHabitTimeDTO teamHabitTimeDTO);

	/**
	 * 批量新增 球队习惯_时间
	 */
	public void batchSaveTeamHabitTime(List<TeamHabitTimeDTO> dtos);

	/**
	 * 更新 球队习惯_时间
	 */
	public int updateTeamHabitTime(TeamHabitTimeDTO teamHabitTimeDTO);

	/**
	 * 批量 球队习惯_时间
	 */
	public void batchUpdateTeamHabitTime(List<TeamHabitTimeDTO> dtos);

	/**
	 * 删除 球队习惯_时间
	 */
	public int deleteTeamHabitTime(long id);

	/**
	 * 根据主键获取 球队习惯_时间
	 */
	public TeamHabitTimeDTO getTeamHabitTime(long id);

	/**
	 * 根据条件获取一条 球队习惯_时间
	 */
	public TeamHabitTimeDTO getTeamHabitTime(TeamHabitTimeDTO teamHabitTimeDTO);

	/**
	 * 查询满足条件的 球队习惯_时间 列表(单表)
	 */
	public List<TeamHabitTimeDTO> getTeamHabitTimeList(TeamHabitTimeDTO teamHabitTimeDTO);

	/**
	 * 查询满足条件的 球队习惯_时间 列表(分页)(单表)
	 */
	public PageList<TeamHabitTimeDTO> getTeamHabitTimeListForPage(TeamHabitTimeDTO teamHabitTimeDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球队习惯_时间 列表(分页)(单表)
	 */
	public PageList<TeamHabitTimeDTO> getTeamHabitTimeListForPage(QueryParam queryParam);

	/**
	 * 球队习惯_时间DTO 转换成 Entity
	 */
	public TeamHabitTimeEntity toTeamHabitTimeEntity(TeamHabitTimeDTO teamHabitTimeDTO);

	/**
	 * 球队习惯_时间DTOs 转换成 Entities
	 */
	public List<TeamHabitTimeEntity> toTeamHabitTimeEntities(List<TeamHabitTimeDTO> dtoes);

	/**
	 * 查询满足条件的 列表(多表)
	 */
	public <T> List<T> getList(T dto);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的列表(分页)(多表)
	 */
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz);

	public void enabledTeam(String teamIds) throws ArgsException;

	public void disabledTeam(String teamIds) throws ArgsException;

	/**
	 * 新增 球队成员关系
	 */
	public Long saveTeamMember(TeamMemberDTO teamMemberDTO);

	/**
	 * 批量新增 球队成员关系
	 */
	public void batchSaveTeamMember(List<TeamMemberDTO> dtos);

	/**
	 * 更新 球队成员关系
	 */
	public int updateTeamMember(TeamMemberDTO teamMemberDTO);

	/**
	 * 批量 球队成员关系
	 */
	public void batchUpdateTeamMember(List<TeamMemberDTO> dtos);

	/**
	 * 删除 球队成员关系
	 */
	public int deleteTeamMember(long id);

	/**
	 * 根据主键获取 球队成员关系
	 */
	public TeamMemberDTO getTeamMember(long id);

	/**
	 * 根据条件获取一条 球队成员关系
	 */
	public TeamMemberDTO getTeamMember(TeamMemberDTO teamMemberDTO);

	/**
	 * 查询满足条件的 球队成员关系 列表(单表)
	 */
	public List<TeamMemberDTO> getTeamMemberList(TeamMemberDTO teamMemberDTO);

	/**
	 * 查询满足条件的 球队成员关系 列表(分页)(单表)
	 */
	public PageList<TeamMemberDTO> getTeamMemberListForPage(TeamMemberDTO teamMemberDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球队成员关系 列表(分页)(单表)
	 */
	public PageList<TeamMemberDTO> getTeamMemberListForPage(QueryParam queryParam);

	/**
	 * 球队成员关系DTO 转换成 Entity
	 */
	public TeamMemberEntity toTeamMemberEntity(TeamMemberDTO teamMemberDTO);

	/**
	 * 球队成员关系DTOs 转换成 Entities
	 */
	public List<TeamMemberEntity> toTeamMemberEntities(List<TeamMemberDTO> dtoes);

	/**
	 * 新增 球队成员日志
	 */
	public Long saveTeamMemberLog(TeamMemberLogDTO teamMemberLogDTO);

	/**
	 * 批量新增 球队成员日志
	 */
	public void batchSaveTeamMemberLog(List<TeamMemberLogDTO> dtos);

	/**
	 * 更新 球队成员日志
	 */
	public int updateTeamMemberLog(TeamMemberLogDTO teamMemberLogDTO);

	/**
	 * 批量 球队成员日志
	 */
	public void batchUpdateTeamMemberLog(List<TeamMemberLogDTO> dtos);

	/**
	 * 删除 球队成员日志
	 */
	public int deleteTeamMemberLog(long id);

	/**
	 * 根据主键获取 球队成员日志
	 */
	public TeamMemberLogDTO getTeamMemberLog(long id);

	/**
	 * 根据条件获取一条 球队成员日志
	 */
	public TeamMemberLogDTO getTeamMemberLog(TeamMemberLogDTO teamMemberLogDTO);

	/**
	 * 查询满足条件的 球队成员日志 列表(单表)
	 */
	public List<TeamMemberLogDTO> getTeamMemberLogList(TeamMemberLogDTO teamMemberLogDTO);

	/**
	 * 查询满足条件的 球队成员日志 列表(分页)(单表)
	 */
	public PageList<TeamMemberLogDTO> getTeamMemberLogListForPage(TeamMemberLogDTO teamMemberLogDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球队成员日志 列表(分页)(单表)
	 */
	public PageList<TeamMemberLogDTO> getTeamMemberLogListForPage(QueryParam queryParam);

	/**
	 * 球队成员日志DTO 转换成 Entity
	 */
	public TeamMemberLogEntity toTeamMemberLogEntity(TeamMemberLogDTO teamMemberLogDTO);

	/**
	 * 球队成员日志DTOs 转换成 Entities
	 */
	public List<TeamMemberLogEntity> toTeamMemberLogEntities(List<TeamMemberLogDTO> dtoes);

	public void deleteTeamHabitTimeByTeamId(Long teamId);

	public void deleteTeamHabitCourtByTeamId(Long teamId);

	public void deleteTeamHabitNumberByTeamId(Long teamId);

	/**
	 * 新增 球队关注
	 */
	public Long saveTeamAttention(TeamAttentionDTO teamAttentionDTO);

	/**
	 * 批量新增 球队关注
	 */
	public void batchSaveTeamAttention(List<TeamAttentionDTO> dtos);

	/**
	 * 更新 球队关注
	 */
	public int updateTeamAttention(TeamAttentionDTO teamAttentionDTO);

	/**
	 * 批量 球队关注
	 */
	public void batchUpdateTeamAttention(List<TeamAttentionDTO> dtos);

	/**
	 * 删除 球队关注
	 */
	public int deleteTeamAttention(long id);

	/**
	 * 根据主键获取 球队关注
	 */
	public TeamAttentionDTO getTeamAttention(long id);

	/**
	 * 根据条件获取一条 球队关注
	 */
	public TeamAttentionDTO getTeamAttention(TeamAttentionDTO teamAttentionDTO);

	/**
	 * 查询满足条件的 球队关注 列表(单表)
	 */
	public List<TeamAttentionDTO> getTeamAttentionList(TeamAttentionDTO teamAttentionDTO);

	/**
	 * 查询满足条件的 球队关注 列表(分页)(单表)
	 */
	public PageList<TeamAttentionDTO> getTeamAttentionListForPage(TeamAttentionDTO teamAttentionDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 球队关注 列表(分页)(单表)
	 */
	public PageList<TeamAttentionDTO> getTeamAttentionListForPage(QueryParam queryParam);

	/**
	 * 球队关注DTO 转换成 Entity
	 */
	public TeamAttentionEntity toTeamAttentionEntity(TeamAttentionDTO teamAttentionDTO);

	/**
	 * 球队关注DTOs 转换成 Entities
	 */
	public List<TeamAttentionEntity> toTeamAttentionEntities(List<TeamAttentionDTO> dtoes);

	public Integer getTeamAttentionCount(TeamAttentionDTO teamAttentionDTO);

	public PageList<TeamDTO> recommendTeams(QueryParam queryParam);

	public TeamDTO getNewTeam();

	public List<TeamDTO> getActiveTeams(int num);

}