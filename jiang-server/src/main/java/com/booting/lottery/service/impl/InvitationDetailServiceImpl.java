/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.lottery.dto.InvitationDetailDTO;
import com.booting.lottery.entity.InvitationDetailEntity;
import com.booting.lottery.service.InvitationDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("invitationDetailService")
public class InvitationDetailServiceImpl extends JDBCSupport<InvitationDetailEntity, InvitationDetailDTO> implements InvitationDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public InvitationDetailDTO save(InvitationDetailEntity invitationDetailEntity) {
		long id = this.persist(invitationDetailEntity);
		return get(id);
	}

	@Override
	public InvitationDetailDTO update(InvitationDetailEntity invitationDetailEntity) {
		this.dynamicMerge(invitationDetailEntity);
		return get(invitationDetailEntity.getId());
	}

	@Override
	public InvitationDetailDTO updateAll(InvitationDetailEntity invitationDetailEntity) {
		this.merge(invitationDetailEntity);
		return get(invitationDetailEntity.getId());
	}

	@Override
	public InvitationDetailDTO updateBySql(InvitationDetailDTO invitationDetailDTO) {
		if(null == invitationDetailDTO) return null;
		this.execute("invitationDetail.updateInvitationDetail", toMap(invitationDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == invitationDetailDTO.getId()) return null;
		return get(invitationDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<InvitationDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("invitationDetail.insertInvitationDetail", params);
	}

	@Override
	public void batchUpdate(List<InvitationDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("invitationDetail.updateInvitationDetail", params);
	}

	@Override
	public InvitationDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public InvitationDetailDTO get(InvitationDetailDTO invitationDetailDTO) {
		if(null == invitationDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(invitationDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("invitationDetail.getSimpleInvitationDetailList", param);
	}

	@Override
	public List<InvitationDetailDTO> getSimpleList(InvitationDetailDTO invitationDetailDTO) {
		Map<String, Object> param = toMap(invitationDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("invitationDetail.getSimpleInvitationDetailList", param);
	}

	@Override
	public PageList<InvitationDetailDTO> getSimpleListForPage(InvitationDetailDTO invitationDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(invitationDetailDTO);
		return this.queryForPage("invitationDetail.getSimpleInvitationDetailListCount", "invitationDetail.getSimpleInvitationDetailList", queryParam);
	}

	@Override
	public PageList<InvitationDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("invitationDetail.getSimpleInvitationDetailListCount", "invitationDetail.getSimpleInvitationDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("invitationDetail.getInvitationDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("invitationDetail.getInvitationDetailListCount", "invitationDetail.getInvitationDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("invitationDetail.getInvitationDetailListCount", "invitationDetail.getInvitationDetailList", queryParam, clazz);
	}

	@Override
	public List<InvitationDetailDTO> getInvitationDetailList(Map<String, Object> params) {
		return this.queryForList("invitationDetail.getSimpleInvitationDetailList", params);
	}

}