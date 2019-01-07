/** create by auto at 2018-06-01 11:34:19**/
package com.booting.bracelet.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.dto.TotalData;
import com.booting.bracelet.entity.BraceletEntity;
import com.booting.bracelet.service.BraceletService;
import com.star.framework.jdbc.dao.JDBCSupport;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

@Service("braceletService")
public class BraceletServiceImpl extends JDBCSupport<BraceletEntity, BraceletDTO> implements BraceletService{

	private static final long serialVersionUID = 1L;

	@Override
	public BraceletDTO save(BraceletEntity braceletEntity) {
		long id = this.persist(braceletEntity);
		return get(id);
	}

	@Override
	public BraceletDTO update(BraceletEntity braceletEntity) {
		this.dynamicMerge(braceletEntity);
		return get(braceletEntity.getBraceletId());
	}

	@Override
	public BraceletDTO updateAll(BraceletEntity braceletEntity) {
		this.merge(braceletEntity);
		return get(braceletEntity.getBraceletId());
	}

	@Override
	public BraceletDTO updateBySql(BraceletDTO braceletDTO) {
		if(null == braceletDTO) return null;
		this.execute("bracelet.updateBracelet", toMap(braceletDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == braceletDTO.getBraceletId()) return null;
		return get(braceletDTO.getBraceletId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<BraceletEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("bracelet.insertBracelet", params);
	}

	@Override
	public void batchUpdate(List<BraceletDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("bracelet.updateBracelet", params);
	}

	@Override
	public BraceletDTO get(long braceletId) {
		return getById(braceletId);
	}

	@Override
	public BraceletDTO get(BraceletDTO braceletDTO) {
		if(null == braceletDTO) {
			return null;
		}
		Map<String, Object> param = toMap(braceletDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("bracelet.getSimpleBraceletList", param);
	}

	@Override
	public List<BraceletDTO> getSimpleList(BraceletDTO braceletDTO) {
		Map<String, Object> param = toMap(braceletDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("bracelet.getSimpleBraceletList", param);
	}

	@Override
	public PageList<BraceletDTO> getSimpleListForPage(BraceletDTO braceletDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(braceletDTO);
		return this.queryForPage("bracelet.getSimpleBraceletListCount", "bracelet.getSimpleBraceletList", queryParam);
	}

	@Override
	public PageList<BraceletDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("bracelet.getSimpleBraceletListCount", "bracelet.getSimpleBraceletList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("bracelet.getBraceletList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("bracelet.getBraceletListCount", "bracelet.getBraceletList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("bracelet.getBraceletListCount", "bracelet.getBraceletList", queryParam, clazz);
	}

  @Override
  public List<BraceletDTO> getBraceletList(BraceletDTO braceletDTO, Integer num) {
    Map<String, Object> param = toMap(braceletDTO, "yyyy-MM-dd HH:mm:ss");
    if (null == param) {
      param = new HashMap<>();
    }
    param.put("_num", num);
    return this.queryForList("bracelet.getSimpleBraceletList", param);
  }

  @Override
  public PageList<TotalData> getStudentTotalData(QueryParam queryParam) {
    return this.queryForPage("bracelet.getStudentTotalDataCount", "bracelet.getStudentTotalData", queryParam, TotalData.class);
  }

  @Override
  public Map<String, Object> getExtremeValue(BraceletDTO bracelet, String field) {
    Map<String, Object> param = toMap(bracelet, "yyyy-MM-dd HH:mm:ss");
    if (null == param) {
      param = new HashMap<>();
    }
    param.put("field", field);
    return this.queryForMap("bracelet.getExtremeValue", param);
  }

  @Override
  public PageList<BraceletDTO> getBraceletListForPageGroupDate(QueryParam queryParam) {
    return this.queryForPage("bracelet.getBraceletListForPageGroupDateCount", "bracelet.getBraceletListForPageGroupDate", queryParam);
  }

  @Override
  public List<BraceletDTO> getDataByHour(BraceletDTO bracelet) {
    Map<String, Object> param = toMap(bracelet, "yyyy-MM-dd HH:mm:ss");
    if (null == param) {
      param = new HashMap<>();
    }
    return this.queryForList("bracelet.getDataByHour", param);
  }

  @Override
  public Integer getRankingOfStepNum(Long studentId) {
    Map<String, Object> param = new HashMap<>();
    param.put("studentId", studentId);
    Integer val = this.queryForObject("bracelet.getRankingOfStepNum", param, Integer.class);
    return val;
  }

}