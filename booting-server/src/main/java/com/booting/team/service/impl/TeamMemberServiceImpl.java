/** create by auto at 2017-06-28 09:27:44**/
package com.booting.team.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.team.dto.TeamMemberDTO;
import com.booting.team.entity.TeamMemberEntity;
import com.booting.team.service.TeamMemberService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("teamMemberService")
public class TeamMemberServiceImpl extends JDBCSupport<TeamMemberEntity, TeamMemberDTO> implements TeamMemberService{

	private static final long serialVersionUID = 1L;

	@Override
	public TeamMemberDTO save(TeamMemberEntity teamMemberEntity) {
		long id = this.persist(teamMemberEntity);
		return get(id);
	}

	@Override
	public TeamMemberDTO update(TeamMemberEntity teamMemberEntity) {
		this.dynamicMerge(teamMemberEntity);
		return get(teamMemberEntity.getId());
	}

	@Override
	public TeamMemberDTO updateAll(TeamMemberEntity teamMemberEntity) {
		this.merge(teamMemberEntity);
		return get(teamMemberEntity.getId());
	}

	@Override
	public TeamMemberDTO updateBySql(TeamMemberDTO teamMemberDTO) {
		if(null == teamMemberDTO) return null;
		this.execute("teamMember.updateTeamMember", toMap(teamMemberDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == teamMemberDTO.getId()) return null;
		return get(teamMemberDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TeamMemberEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("teamMember.insertTeamMember", params);
	}

	@Override
	public void batchUpdate(List<TeamMemberDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("teamMember.updateTeamMember", params);
	}

	@Override
	public TeamMemberDTO get(long id) {
		return getById(id);
	}

	@Override
	public TeamMemberDTO get(TeamMemberDTO teamMemberDTO) {
		if(null == teamMemberDTO) {
			return null;
		}
		Map<String, Object> param = toMap(teamMemberDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("teamMember.getSimpleTeamMemberList", param);
	}

	@Override
	public List<TeamMemberDTO> getSimpleList(TeamMemberDTO teamMemberDTO) {
		Map<String, Object> param = toMap(teamMemberDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("teamMember.getSimpleTeamMemberList", param);
	}

	@Override
	public PageList<TeamMemberDTO> getSimpleListForPage(TeamMemberDTO teamMemberDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(teamMemberDTO);
		return this.queryForPage("teamMember.getSimpleTeamMemberListCount", "teamMember.getSimpleTeamMemberList", queryParam);
	}

	@Override
	public PageList<TeamMemberDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("teamMember.getSimpleTeamMemberListCount", "teamMember.getSimpleTeamMemberList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("teamMember.getTeamMemberList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("teamMember.getTeamMemberListCount", "teamMember.getTeamMemberList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("teamMember.getTeamMemberListCount", "teamMember.getTeamMemberList", queryParam, clazz);
	}

}