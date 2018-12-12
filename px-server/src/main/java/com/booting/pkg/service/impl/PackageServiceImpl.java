/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.entity.PackageEntity;
import com.booting.pkg.service.PackageService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("packageService")
public class PackageServiceImpl extends JDBCSupport<PackageEntity, PackageDTO> implements PackageService{

	private static final long serialVersionUID = 1L;

	@Override
	public PackageDTO save(PackageEntity packageEntity) {
		long id = this.persist(packageEntity);
		return get(id);
	}

	@Override
	public PackageDTO update(PackageEntity packageEntity) {
		this.dynamicMerge(packageEntity);
		return get(packageEntity.getPackageId());
	}

	@Override
	public PackageDTO updateAll(PackageEntity packageEntity) {
		this.merge(packageEntity);
		return get(packageEntity.getPackageId());
	}

	@Override
	public PackageDTO updateBySql(PackageDTO packageDTO) {
		if(null == packageDTO) return null;
		this.execute("package.updatePackage", toMap(packageDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == packageDTO.getPackageId()) return null;
		return get(packageDTO.getPackageId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PackageEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("package.insertPackage", params);
	}

	@Override
	public void batchUpdate(List<PackageDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("package.updatePackage", params);
	}

	@Override
	public PackageDTO get(long packageId) {
		PackageDTO packageDTO = new PackageDTO();
		packageDTO.setPackageId(packageId);
		return get(packageDTO);
	}

	@Override
	public PackageDTO get(PackageDTO packageDTO) {
		if(null == packageDTO) {
			return null;
		}
		Map<String, Object> param = toMap(packageDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("package.getSimplePackageList", param);
	}

	@Override
	public List<PackageDTO> getSimpleList(PackageDTO packageDTO) {
		Map<String, Object> param = toMap(packageDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("package.getSimplePackageList", param);
	}

	@Override
	public PageList<PackageDTO> getSimpleListForPage(PackageDTO packageDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(packageDTO);
		return this.queryForPage("package.getSimplePackageListCount", "package.getSimplePackageList", queryParam);
	}

	@Override
	public PageList<PackageDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("package.getSimplePackageListCount", "package.getSimplePackageList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("package.getPackageList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("package.getPackageListCount", "package.getPackageList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("package.getPackageListCount", "package.getPackageList", queryParam, clazz);
	}

}