/** create by auto at 2017-06-02 09:14:49**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.entity.SmsIdentityEntity;
import com.booting.pub.service.SmsIdentityService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("smsIdentityService")
public class SmsIdentityServiceImpl extends JDBCSupport<SmsIdentityEntity, SmsIdentityDTO> implements SmsIdentityService{

	private static final long serialVersionUID = 1L;

	@Override
	public SmsIdentityDTO save(SmsIdentityEntity smsIdentityEntity) {
		long id = this.persist(smsIdentityEntity);
		return get(id);
	}

	@Override
	public SmsIdentityDTO update(SmsIdentityEntity smsIdentityEntity) {
		this.dynamicMerge(smsIdentityEntity);
		return get(smsIdentityEntity.getId());
	}

	@Override
	public SmsIdentityDTO updateAll(SmsIdentityEntity smsIdentityEntity) {
		this.merge(smsIdentityEntity);
		return get(smsIdentityEntity.getId());
	}

	@Override
	public SmsIdentityDTO updateBySql(SmsIdentityDTO smsIdentityDTO) {
		if(null == smsIdentityDTO) return null;
		this.execute("smsIdentity.updateSmsIdentity", toMap(smsIdentityDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == smsIdentityDTO.getId()) return null;
		return get(smsIdentityDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<SmsIdentityEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("smsIdentity.insertSmsIdentity", params);
	}

	@Override
	public void batchUpdate(List<SmsIdentityDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("smsIdentity.updateSmsIdentity", params);
	}

	@Override
	public SmsIdentityDTO get(long id) {
		return getById(id);
	}

	@Override
	public SmsIdentityDTO get(SmsIdentityDTO smsIdentityDTO) {
		if(null == smsIdentityDTO) {
			return null;
		}
		Map<String, Object> param = toMap(smsIdentityDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("smsIdentity.getSimpleSmsIdentityList", param);
	}

	@Override
	public List<SmsIdentityDTO> getSimpleList(SmsIdentityDTO smsIdentityDTO) {
		Map<String, Object> param = toMap(smsIdentityDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("smsIdentity.getSimpleSmsIdentityList", param);
	}

	@Override
	public PageList<SmsIdentityDTO> getSimpleListForPage(SmsIdentityDTO smsIdentityDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(smsIdentityDTO);
		return this.queryForPage("smsIdentity.getSimpleSmsIdentityListCount", "smsIdentity.getSimpleSmsIdentityList", queryParam);
	}

	@Override
	public PageList<SmsIdentityDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("smsIdentity.getSimpleSmsIdentityListCount", "smsIdentity.getSimpleSmsIdentityList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("smsIdentity.getSmsIdentityList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("smsIdentity.getSmsIdentityListCount", "smsIdentity.getSmsIdentityList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("smsIdentity.getSmsIdentityListCount", "smsIdentity.getSmsIdentityList", queryParam, clazz);
	}

}