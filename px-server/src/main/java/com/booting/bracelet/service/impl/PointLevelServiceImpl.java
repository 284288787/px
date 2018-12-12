/** create by auto at 2018-09-12 16:14:42**/
package com.booting.bracelet.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.bracelet.dto.PointLevelDTO;
import com.booting.bracelet.entity.PointLevelEntity;
import com.booting.bracelet.service.PointLevelService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("pointLevelService")
public class PointLevelServiceImpl extends JDBCSupport<PointLevelEntity, PointLevelDTO> implements PointLevelService{

	private static final long serialVersionUID = 1L;

	@Override
	public PointLevelDTO save(PointLevelEntity pointLevelEntity) {
		long id = this.persist(pointLevelEntity);
		return get(id);
	}

	@Override
	public PointLevelDTO update(PointLevelEntity pointLevelEntity) {
		this.dynamicMerge(pointLevelEntity);
		return get(pointLevelEntity.getId());
	}

	@Override
	public PointLevelDTO updateAll(PointLevelEntity pointLevelEntity) {
		this.merge(pointLevelEntity);
		return get(pointLevelEntity.getId());
	}

	@Override
	public PointLevelDTO updateBySql(PointLevelDTO pointLevelDTO) {
		if(null == pointLevelDTO) return null;
		this.execute("pointLevel.updatePointLevel", toMap(pointLevelDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == pointLevelDTO.getId()) return null;
		return get(pointLevelDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<PointLevelEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("pointLevel.insertPointLevel", params);
	}

	@Override
	public void batchUpdate(List<PointLevelDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("pointLevel.updatePointLevel", params);
	}

	@Override
	public PointLevelDTO get(long id) {
		return getById(id);
	}

	@Override
	public PointLevelDTO get(PointLevelDTO pointLevelDTO) {
		if(null == pointLevelDTO) {
			return null;
		}
		Map<String, Object> param = toMap(pointLevelDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("pointLevel.getSimplePointLevelList", param);
	}

	@Override
	public List<PointLevelDTO> getSimpleList(PointLevelDTO pointLevelDTO) {
		Map<String, Object> param = toMap(pointLevelDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("pointLevel.getSimplePointLevelList", param);
	}

	@Override
	public PageList<PointLevelDTO> getSimpleListForPage(PointLevelDTO pointLevelDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(pointLevelDTO);
		return this.queryForPage("pointLevel.getSimplePointLevelListCount", "pointLevel.getSimplePointLevelList", queryParam);
	}

	@Override
	public PageList<PointLevelDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("pointLevel.getSimplePointLevelListCount", "pointLevel.getSimplePointLevelList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("pointLevel.getPointLevelList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("pointLevel.getPointLevelListCount", "pointLevel.getPointLevelList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("pointLevel.getPointLevelListCount", "pointLevel.getPointLevelList", queryParam, clazz);
	}

  @Override
  public PointLevelDTO getByLevel(int level) {
    Map<String, Object> param = new HashMap<>();
    param.put("level", level);
    return this.queryForObject("pointLevel.getByLevel", param);
  }

}