/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.PackageServiceRelationDTO;
import com.booting.pkg.entity.PackageServiceRelationEntity;
import com.booting.pkg.service.PackageServiceRelationService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("packageServiceRelationService")
public class PackageServiceRelationServiceImpl extends JDBCSupport<PackageServiceRelationEntity, PackageServiceRelationDTO> implements PackageServiceRelationService{

	private static final long serialVersionUID = 1L;

	@Override
	public PackageServiceRelationDTO save(PackageServiceRelationEntity packageServiceRelationEntity) {
		long id = this.persist(packageServiceRelationEntity);
		return get(id);
	}

	@Override
	public PackageServiceRelationDTO update(PackageServiceRelationEntity packageServiceRelationEntity) {
		this.dynamicMerge(packageServiceRelationEntity);
		return get(packageServiceRelationEntity.getId());
	}

	@Override
	public PackageServiceRelationDTO updateAll(PackageServiceRelationEntity packageServiceRelationEntity) {
		this.merge(packageServiceRelationEntity);
		return get(packageServiceRelationEntity.getId());
	}

	@Override
	public PackageServiceRelationDTO updateBySql(PackageServiceRelationDTO packageServiceRelationDTO) {
		if(null == packageServiceRelationDTO) return null;
		this.execute("packageServiceRelation.updatePackageServiceRelation", toMap(packageServiceRelationDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == packageServiceRelationDTO.getId()) return null;
		return get(packageServiceRelationDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PackageServiceRelationEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("packageServiceRelation.insertPackageServiceRelation", params);
	}

	@Override
	public void batchUpdate(List<PackageServiceRelationDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("packageServiceRelation.updatePackageServiceRelation", params);
	}

	@Override
	public PackageServiceRelationDTO get(long id) {
		return getById(id);
	}

	@Override
	public PackageServiceRelationDTO get(PackageServiceRelationDTO packageServiceRelationDTO) {
		if(null == packageServiceRelationDTO) {
			return null;
		}
		Map<String, Object> param = toMap(packageServiceRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("packageServiceRelation.getSimplePackageServiceRelationList", param);
	}

	@Override
	public List<PackageServiceRelationDTO> getSimpleList(PackageServiceRelationDTO packageServiceRelationDTO) {
		Map<String, Object> param = toMap(packageServiceRelationDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("packageServiceRelation.getSimplePackageServiceRelationList", param);
	}

	@Override
	public PageList<PackageServiceRelationDTO> getSimpleListForPage(PackageServiceRelationDTO packageServiceRelationDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(packageServiceRelationDTO);
		return this.queryForPage("packageServiceRelation.getSimplePackageServiceRelationListCount", "packageServiceRelation.getSimplePackageServiceRelationList", queryParam);
	}

	@Override
	public PageList<PackageServiceRelationDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("packageServiceRelation.getSimplePackageServiceRelationListCount", "packageServiceRelation.getSimplePackageServiceRelationList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("packageServiceRelation.getPackageServiceRelationList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("packageServiceRelation.getPackageServiceRelationListCount", "packageServiceRelation.getPackageServiceRelationList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("packageServiceRelation.getPackageServiceRelationListCount", "packageServiceRelation.getPackageServiceRelationList", queryParam, clazz);
	}

	@Override
	public void deletePackageServiceRelationBySql(PackageServiceRelationDTO dto) {
		this.execute("packageServiceRelation.deletePackageServiceRelationBySql", toMap(dto, "yyyy-MM-dd HH:mm:ss"));
	}

}