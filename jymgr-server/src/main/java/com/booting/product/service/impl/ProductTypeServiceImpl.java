/** create by auto at 2018-06-21 14:14:57**/
package com.booting.product.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.product.dto.ProductTypeDTO;
import com.booting.product.entity.ProductTypeEntity;
import com.booting.product.service.ProductTypeService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("productTypeService")
public class ProductTypeServiceImpl extends JDBCSupport<ProductTypeEntity, ProductTypeDTO> implements ProductTypeService{

	private static final long serialVersionUID = 1L;

	@Override
	public ProductTypeDTO save(ProductTypeEntity productTypeEntity) {
		long id = this.persist(productTypeEntity);
		return get(id);
	}

	@Override
	public ProductTypeDTO update(ProductTypeEntity productTypeEntity) {
		this.dynamicMerge(productTypeEntity);
		return get(productTypeEntity.getTypeId());
	}

	@Override
	public ProductTypeDTO updateAll(ProductTypeEntity productTypeEntity) {
		this.merge(productTypeEntity);
		return get(productTypeEntity.getTypeId());
	}

	@Override
	public ProductTypeDTO updateBySql(ProductTypeDTO productTypeDTO) {
		if(null == productTypeDTO) return null;
		this.execute("productType.updateProductType", toMap(productTypeDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == productTypeDTO.getTypeId()) return null;
		return get(productTypeDTO.getTypeId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<ProductTypeEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("productType.insertProductType", params);
	}

	@Override
	public void batchUpdate(List<ProductTypeDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("productType.updateProductType", params);
	}

	@Override
	public ProductTypeDTO get(long typeId) {
		return getById(typeId);
	}

	@Override
	public ProductTypeDTO get(ProductTypeDTO productTypeDTO) {
		if(null == productTypeDTO) {
			return null;
		}
		Map<String, Object> param = toMap(productTypeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("productType.getSimpleProductTypeList", param);
	}

	@Override
	public List<ProductTypeDTO> getSimpleList(ProductTypeDTO productTypeDTO) {
		Map<String, Object> param = toMap(productTypeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("productType.getSimpleProductTypeList", param);
	}

	@Override
	public PageList<ProductTypeDTO> getSimpleListForPage(ProductTypeDTO productTypeDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(productTypeDTO);
		return this.queryForPage("productType.getSimpleProductTypeListCount", "productType.getSimpleProductTypeList", queryParam);
	}

	@Override
	public PageList<ProductTypeDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("productType.getSimpleProductTypeListCount", "productType.getSimpleProductTypeList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("productType.getProductTypeList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("productType.getProductTypeListCount", "productType.getProductTypeList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("productType.getProductTypeListCount", "productType.getProductTypeList", queryParam, clazz);
	}

}