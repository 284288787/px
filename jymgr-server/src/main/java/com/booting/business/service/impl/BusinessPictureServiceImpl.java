/** create by auto at 2018-06-12 09:48:37**/
package com.booting.business.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.business.dto.BusinessPictureDTO;
import com.booting.business.entity.BusinessPictureEntity;
import com.booting.business.service.BusinessPictureService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("businessPictureService")
public class BusinessPictureServiceImpl extends JDBCSupport<BusinessPictureEntity, BusinessPictureDTO> implements BusinessPictureService{

	private static final long serialVersionUID = 1L;

	@Override
	public BusinessPictureDTO save(BusinessPictureEntity businessPictureEntity) {
		long id = this.persist(businessPictureEntity);
		return get(id);
	}

	@Override
	public BusinessPictureDTO update(BusinessPictureEntity businessPictureEntity) {
		this.dynamicMerge(businessPictureEntity);
		return get(businessPictureEntity.getId());
	}

	@Override
	public BusinessPictureDTO updateAll(BusinessPictureEntity businessPictureEntity) {
		this.merge(businessPictureEntity);
		return get(businessPictureEntity.getId());
	}

	@Override
	public BusinessPictureDTO updateBySql(BusinessPictureDTO businessPictureDTO) {
		if(null == businessPictureDTO) return null;
		this.execute("businessPicture.updateBusinessPicture", toMap(businessPictureDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == businessPictureDTO.getId()) return null;
		return get(businessPictureDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<BusinessPictureEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("businessPicture.insertBusinessPicture", params);
	}

	@Override
	public void batchUpdate(List<BusinessPictureDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("businessPicture.updateBusinessPicture", params);
	}

	@Override
	public BusinessPictureDTO get(long id) {
		return getById(id);
	}

	@Override
	public BusinessPictureDTO get(BusinessPictureDTO businessPictureDTO) {
		if(null == businessPictureDTO) {
			return null;
		}
		Map<String, Object> param = toMap(businessPictureDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("businessPicture.getSimpleBusinessPictureList", param);
	}

	@Override
	public List<BusinessPictureDTO> getSimpleList(BusinessPictureDTO businessPictureDTO) {
		Map<String, Object> param = toMap(businessPictureDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("businessPicture.getSimpleBusinessPictureList", param);
	}

	@Override
	public PageList<BusinessPictureDTO> getSimpleListForPage(BusinessPictureDTO businessPictureDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(businessPictureDTO);
		return this.queryForPage("businessPicture.getSimpleBusinessPictureListCount", "businessPicture.getSimpleBusinessPictureList", queryParam);
	}

	@Override
	public PageList<BusinessPictureDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("businessPicture.getSimpleBusinessPictureListCount", "businessPicture.getSimpleBusinessPictureList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("businessPicture.getBusinessPictureList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("businessPicture.getBusinessPictureListCount", "businessPicture.getBusinessPictureList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("businessPicture.getBusinessPictureListCount", "businessPicture.getBusinessPictureList", queryParam, clazz);
	}

  @Override
  public void deleteBySql(Map<String, Object> param) {
    this.execute("businessPicture.deleteBusinessPicture", param);
  }

}