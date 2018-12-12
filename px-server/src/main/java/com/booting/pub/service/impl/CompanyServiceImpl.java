/** create by auto at 2017-06-22 15:36:53**/
package com.booting.pub.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pub.dto.CompanyDTO;
import com.booting.pub.entity.CompanyEntity;
import com.booting.pub.service.CompanyService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("companyService")
public class CompanyServiceImpl extends JDBCSupport<CompanyEntity, CompanyDTO> implements CompanyService{

	private static final long serialVersionUID = 1L;

	@Override
	public CompanyDTO save(CompanyEntity companyEntity) {
		long id = this.persist(companyEntity);
		return get(id);
	}

	@Override
	public CompanyDTO update(CompanyEntity companyEntity) {
		this.dynamicMerge(companyEntity);
		return get(companyEntity.getCompanyId());
	}

	@Override
	public CompanyDTO updateAll(CompanyEntity companyEntity) {
		this.merge(companyEntity);
		return get(companyEntity.getCompanyId());
	}

	@Override
	public CompanyDTO updateBySql(CompanyDTO companyDTO) {
		if(null == companyDTO) return null;
		this.execute("company.updateCompany", toMap(companyDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == companyDTO.getCompanyId()) return null;
		return get(companyDTO.getCompanyId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<CompanyEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("company.insertCompany", params);
	}

	@Override
	public void batchUpdate(List<CompanyDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("company.updateCompany", params);
	}

	@Override
	public CompanyDTO get(long companyId) {
		return getById(companyId);
	}

	@Override
	public CompanyDTO get(CompanyDTO companyDTO) {
		if(null == companyDTO) {
			return null;
		}
		Map<String, Object> param = toMap(companyDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("company.getSimpleCompanyList", param);
	}

	@Override
	public List<CompanyDTO> getSimpleList(CompanyDTO companyDTO) {
		Map<String, Object> param = toMap(companyDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("company.getSimpleCompanyList", param);
	}

	@Override
	public PageList<CompanyDTO> getSimpleListForPage(CompanyDTO companyDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(companyDTO);
		return this.queryForPage("company.getSimpleCompanyListCount", "company.getSimpleCompanyList", queryParam);
	}

	@Override
	public PageList<CompanyDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("company.getSimpleCompanyListCount", "company.getSimpleCompanyList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("company.getCompanyList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("company.getCompanyListCount", "company.getCompanyList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("company.getCompanyListCount", "company.getCompanyList", queryParam, clazz);
	}

}