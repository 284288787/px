/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.PackageAttributeValueDTO;
import com.booting.pkg.entity.PackageAttributeValueEntity;
import com.booting.pkg.service.PackageAttributeValueService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("packageAttributeValueService")
public class PackageAttributeValueServiceImpl extends JDBCSupport<PackageAttributeValueEntity, PackageAttributeValueDTO> implements PackageAttributeValueService{

	private static final long serialVersionUID = 1L;

	@Override
	public PackageAttributeValueDTO save(PackageAttributeValueEntity packageAttributeValueEntity) {
		long id = this.persist(packageAttributeValueEntity);
		return get(id);
	}

	@Override
	public PackageAttributeValueDTO update(PackageAttributeValueEntity packageAttributeValueEntity) {
		this.dynamicMerge(packageAttributeValueEntity);
		return get(packageAttributeValueEntity.getId());
	}

	@Override
	public PackageAttributeValueDTO updateAll(PackageAttributeValueEntity packageAttributeValueEntity) {
		this.merge(packageAttributeValueEntity);
		return get(packageAttributeValueEntity.getId());
	}

	@Override
	public PackageAttributeValueDTO updateBySql(PackageAttributeValueDTO packageAttributeValueDTO) {
		if(null == packageAttributeValueDTO) return null;
		this.execute("packageAttributeValue.updatePackageAttributeValue", toMap(packageAttributeValueDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == packageAttributeValueDTO.getId()) return null;
		return get(packageAttributeValueDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PackageAttributeValueEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("packageAttributeValue.insertPackageAttributeValue", params);
	}

	@Override
	public void batchUpdate(List<PackageAttributeValueDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("packageAttributeValue.updatePackageAttributeValue", params);
	}

	@Override
	public PackageAttributeValueDTO get(long id) {
		return getById(id);
	}

	@Override
	public PackageAttributeValueDTO get(PackageAttributeValueDTO packageAttributeValueDTO) {
		if(null == packageAttributeValueDTO) {
			return null;
		}
		Map<String, Object> param = toMap(packageAttributeValueDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("packageAttributeValue.getSimplePackageAttributeValueList", param);
	}

	@Override
	public List<PackageAttributeValueDTO> getSimpleList(PackageAttributeValueDTO packageAttributeValueDTO) {
		Map<String, Object> param = toMap(packageAttributeValueDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("packageAttributeValue.getSimplePackageAttributeValueList", param);
	}

	@Override
	public PageList<PackageAttributeValueDTO> getSimpleListForPage(PackageAttributeValueDTO packageAttributeValueDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(packageAttributeValueDTO);
		return this.queryForPage("packageAttributeValue.getSimplePackageAttributeValueListCount", "packageAttributeValue.getSimplePackageAttributeValueList", queryParam);
	}

	@Override
	public PageList<PackageAttributeValueDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("packageAttributeValue.getSimplePackageAttributeValueListCount", "packageAttributeValue.getSimplePackageAttributeValueList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("packageAttributeValue.getPackageAttributeValueList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("packageAttributeValue.getPackageAttributeValueListCount", "packageAttributeValue.getPackageAttributeValueList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("packageAttributeValue.getPackageAttributeValueListCount", "packageAttributeValue.getPackageAttributeValueList", queryParam, clazz);
	}

	@Override
	public void deletePackageAttributeValueBySql(PackageAttributeValueDTO valueDTO) {
		this.execute("packageAttributeValue.deletePackageAttributeValueBySql", toMap(valueDTO, "yyyy-MM-dd HH:mm:ss"));
	}

}