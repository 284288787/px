/** create by auto at 2018-01-16 16:09:43**/
package com.booting.member.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.member.dto.MemberDTO;
import com.booting.member.entity.MemberEntity;

public interface MemberFacade extends Serializable {

	/**
	 * 新增 用户信息
	 */
	public Long saveMember(MemberDTO memberDTO);

	/**
	 * 批量新增 用户信息
	 */
	public void batchSaveMember(List<MemberDTO> dtos);

	/**
	 * 更新 用户信息
	 */
	public int updateMember(MemberDTO memberDTO);

	/**
	 * 批量 用户信息
	 */
	public void batchUpdateMember(List<MemberDTO> dtos);

	/**
	 * 删除 用户信息
	 */
	public int deleteMember(long memberId);

	/**
	 * 根据主键获取 用户信息
	 */
	public MemberDTO getMember(long memberId);

	/**
	 * 根据条件获取一条 用户信息
	 */
	public MemberDTO getMember(MemberDTO memberDTO);

	/**
	 * 查询满足条件的 用户信息 列表(单表)
	 */
	public List<MemberDTO> getMemberList(MemberDTO memberDTO);

	/**
	 * 查询满足条件的 用户信息 列表(分页)(单表)
	 */
	public PageList<MemberDTO> getMemberListForPage(MemberDTO memberDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户信息 列表(分页)(单表)
	 */
	public PageList<MemberDTO> getMemberListForPage(QueryParam queryParam);

	/**
	 * 用户信息DTO 转换成 Entity
	 */
	public MemberEntity toMemberEntity(MemberDTO memberDTO);

	/**
	 * 用户信息DTOs 转换成 Entities
	 */
	public List<MemberEntity> toMemberEntities(List<MemberDTO> dtoes);

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

}