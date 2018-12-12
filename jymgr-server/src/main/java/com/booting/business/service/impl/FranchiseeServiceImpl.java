/** create by auto at 2018-06-25 10:42:18**/
package com.booting.business.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.booting.business.dto.FranchiseeDTO;
import com.booting.business.entity.FranchiseeEntity;
import com.booting.business.service.FranchiseeService;
import com.star.framework.jdbc.dao.JDBCSupport;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

@Service("franchiseeService")
public class FranchiseeServiceImpl extends JDBCSupport<FranchiseeEntity, FranchiseeDTO> implements FranchiseeService{

	private static final long serialVersionUID = 1L;

	@Override
	public FranchiseeDTO save(FranchiseeEntity franchiseeEntity) {
		long id = this.persist(franchiseeEntity);
		return get(id);
	}

	@Override
	public FranchiseeDTO update(FranchiseeEntity franchiseeEntity) {
		this.dynamicMerge(franchiseeEntity);
		return get(franchiseeEntity.getMemberId());
	}

	@Override
	public FranchiseeDTO updateAll(FranchiseeEntity franchiseeEntity) {
		this.merge(franchiseeEntity);
		return get(franchiseeEntity.getMemberId());
	}

	@Override
	public FranchiseeDTO updateBySql(FranchiseeDTO franchiseeDTO) {
		if(null == franchiseeDTO) return null;
		this.execute("franchisee.updateFranchisee", toMap(franchiseeDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == franchiseeDTO.getMemberId()) return null;
		return get(franchiseeDTO.getMemberId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<FranchiseeEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("franchisee.insertFranchisee", params);
	}

	@Override
	public void batchUpdate(List<FranchiseeDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("franchisee.updateFranchisee", params);
	}

//	@Cacheable(value = "franchisee_info", key = "'franchisee_id_'+#memberId", condition = "#memberId != null")
	public FranchiseeDTO get(long memberId) {
		return getById(memberId);
	}

	@Override
	public FranchiseeDTO get(FranchiseeDTO franchiseeDTO) {
		if(null == franchiseeDTO) {
			return null;
		}
		Map<String, Object> param = toMap(franchiseeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("franchisee.getSimpleFranchiseeList", param);
	}

	@Override
	public List<FranchiseeDTO> getSimpleList(FranchiseeDTO franchiseeDTO) {
		Map<String, Object> param = toMap(franchiseeDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("franchisee.getSimpleFranchiseeList", param);
	}

	@Override
	public PageList<FranchiseeDTO> getSimpleListForPage(FranchiseeDTO franchiseeDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(franchiseeDTO);
		return this.queryForPage("franchisee.getSimpleFranchiseeListCount", "franchisee.getSimpleFranchiseeList", queryParam);
	}

	@Override
	public PageList<FranchiseeDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("franchisee.getSimpleFranchiseeListCount", "franchisee.getSimpleFranchiseeList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("franchisee.getFranchiseeList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("franchisee.getFranchiseeListCount", "franchisee.getFranchiseeList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("franchisee.getFranchiseeListCount", "franchisee.getFranchiseeList", queryParam, clazz);
	}

}