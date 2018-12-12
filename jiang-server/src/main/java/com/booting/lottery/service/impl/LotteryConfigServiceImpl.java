/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.lottery.dto.LotteryConfigDTO;
import com.booting.lottery.entity.LotteryConfigEntity;
import com.booting.lottery.service.LotteryConfigService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("lotteryConfigService")
public class LotteryConfigServiceImpl extends JDBCSupport<LotteryConfigEntity, LotteryConfigDTO> implements LotteryConfigService{

	private static final long serialVersionUID = 1L;

	@Override
	public LotteryConfigDTO save(LotteryConfigEntity lotteryConfigEntity) {
		long id = this.persist(lotteryConfigEntity);
		return get(id);
	}

	@Override
	public LotteryConfigDTO update(LotteryConfigEntity lotteryConfigEntity) {
		this.dynamicMerge(lotteryConfigEntity);
		return get(lotteryConfigEntity.getConfigId());
	}

	@Override
	public LotteryConfigDTO updateAll(LotteryConfigEntity lotteryConfigEntity) {
		this.merge(lotteryConfigEntity);
		return get(lotteryConfigEntity.getConfigId());
	}

	@Override
	public LotteryConfigDTO updateBySql(LotteryConfigDTO lotteryConfigDTO) {
		if(null == lotteryConfigDTO) return null;
		this.execute("lotteryConfig.updateLotteryConfig", toMap(lotteryConfigDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == lotteryConfigDTO.getConfigId()) return null;
		return get(lotteryConfigDTO.getConfigId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<LotteryConfigEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("lotteryConfig.insertLotteryConfig", params);
	}

	@Override
	public void batchUpdate(List<LotteryConfigDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("lotteryConfig.updateLotteryConfig", params);
	}

	@Override
	public LotteryConfigDTO get(long configId) {
		return getById(configId);
	}

	@Override
	public LotteryConfigDTO get(LotteryConfigDTO lotteryConfigDTO) {
		if(null == lotteryConfigDTO) {
			return null;
		}
		Map<String, Object> param = toMap(lotteryConfigDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("lotteryConfig.getSimpleLotteryConfigList", param);
	}

	@Override
	public List<LotteryConfigDTO> getSimpleList(LotteryConfigDTO lotteryConfigDTO) {
		Map<String, Object> param = toMap(lotteryConfigDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("lotteryConfig.getSimpleLotteryConfigList", param);
	}

	@Override
	public PageList<LotteryConfigDTO> getSimpleListForPage(LotteryConfigDTO lotteryConfigDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(lotteryConfigDTO);
		return this.queryForPage("lotteryConfig.getSimpleLotteryConfigListCount", "lotteryConfig.getSimpleLotteryConfigList", queryParam);
	}

	@Override
	public PageList<LotteryConfigDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("lotteryConfig.getSimpleLotteryConfigListCount", "lotteryConfig.getSimpleLotteryConfigList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("lotteryConfig.getLotteryConfigList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("lotteryConfig.getLotteryConfigListCount", "lotteryConfig.getLotteryConfigList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("lotteryConfig.getLotteryConfigListCount", "lotteryConfig.getLotteryConfigList", queryParam, clazz);
	}

}