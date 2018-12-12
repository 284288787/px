/** create by auto at 2018-01-16 16:09:43**/
package com.booting.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.member.dto.MemberDTO;
import com.booting.member.entity.MemberEntity;
import com.booting.member.service.MemberService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("memberService")
public class MemberServiceImpl extends JDBCSupport<MemberEntity, MemberDTO> implements MemberService{

	private static final long serialVersionUID = 1L;

	@Override
	public MemberDTO save(MemberEntity memberEntity) {
		long id = this.persist(memberEntity);
		return get(id);
	}

	@Override
	public MemberDTO update(MemberEntity memberEntity) {
		this.dynamicMerge(memberEntity);
		return get(memberEntity.getMemberId());
	}

	@Override
	public MemberDTO updateAll(MemberEntity memberEntity) {
		this.merge(memberEntity);
		return get(memberEntity.getMemberId());
	}

	@Override
	public MemberDTO updateBySql(MemberDTO memberDTO) {
		if(null == memberDTO) return null;
		this.execute("member.updateMember", toMap(memberDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == memberDTO.getMemberId()) return null;
		return get(memberDTO.getMemberId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<MemberEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("member.insertMember", params);
	}

	@Override
	public void batchUpdate(List<MemberDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("member.updateMember", params);
	}

	@Override
	public MemberDTO get(long memberId) {
		return getById(memberId);
	}

	@Override
	public MemberDTO get(MemberDTO memberDTO) {
		if(null == memberDTO) {
			return null;
		}
		Map<String, Object> param = toMap(memberDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("member.getSimpleMemberList", param);
	}

	@Override
	public List<MemberDTO> getSimpleList(MemberDTO memberDTO) {
		Map<String, Object> param = toMap(memberDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("member.getSimpleMemberList", param);
	}

	@Override
	public PageList<MemberDTO> getSimpleListForPage(MemberDTO memberDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(memberDTO);
		return this.queryForPage("member.getSimpleMemberListCount", "member.getSimpleMemberList", queryParam);
	}

	@Override
	public PageList<MemberDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("member.getSimpleMemberListCount", "member.getSimpleMemberList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("member.getMemberList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("member.getMemberListCount", "member.getMemberList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("member.getMemberListCount", "member.getMemberList", queryParam, clazz);
	}

}