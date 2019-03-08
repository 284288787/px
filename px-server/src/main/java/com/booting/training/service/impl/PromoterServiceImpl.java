/** create by auto at 2019-03-08 17:12:08**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.PromoterDTO;
import com.booting.training.entity.PromoterEntity;
import com.booting.training.service.PromoterService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("promoterService")
public class PromoterServiceImpl extends JDBCSupport<PromoterEntity, PromoterDTO> implements PromoterService{

	private static final long serialVersionUID = 1L;

	@Override
	public PromoterDTO save(PromoterEntity promoterEntity) {
		long id = this.persist(promoterEntity);
		return get(id);
	}

	@Override
	public PromoterDTO update(PromoterEntity promoterEntity) {
		this.dynamicMerge(promoterEntity);
		return get(promoterEntity.getPromoterId());
	}

	@Override
	public PromoterDTO updateAll(PromoterEntity promoterEntity) {
		this.merge(promoterEntity);
		return get(promoterEntity.getPromoterId());
	}

	@Override
	public PromoterDTO updateBySql(PromoterDTO promoterDTO) {
		if(null == promoterDTO) return null;
		this.execute("promoter.updatePromoter", toMap(promoterDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == promoterDTO.getPromoterId()) return null;
		return get(promoterDTO.getPromoterId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PromoterEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("promoter.insertPromoter", params);
	}

	@Override
	public void batchUpdate(List<PromoterDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("promoter.updatePromoter", params);
	}

	@Override
	public PromoterDTO get(long promoterId) {
		return getById(promoterId);
	}

	@Override
	public PromoterDTO get(PromoterDTO promoterDTO) {
		if(null == promoterDTO) {
			return null;
		}
		Map<String, Object> param = toMap(promoterDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("promoter.getSimplePromoterList", param);
	}

	@Override
	public List<PromoterDTO> getSimpleList(PromoterDTO promoterDTO) {
		Map<String, Object> param = toMap(promoterDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("promoter.getSimplePromoterList", param);
	}

	@Override
	public PageList<PromoterDTO> getSimpleListForPage(PromoterDTO promoterDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(promoterDTO);
		return this.queryForPage("promoter.getSimplePromoterListCount", "promoter.getSimplePromoterList", queryParam);
	}

	@Override
	public PageList<PromoterDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("promoter.getSimplePromoterListCount", "promoter.getSimplePromoterList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("promoter.getPromoterList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("promoter.getPromoterListCount", "promoter.getPromoterList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("promoter.getPromoterListCount", "promoter.getPromoterList", queryParam, clazz);
	}

}