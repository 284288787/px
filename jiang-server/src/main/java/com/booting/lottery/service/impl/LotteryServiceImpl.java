/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.lottery.dto.LotteryDTO;
import com.booting.lottery.entity.LotteryEntity;
import com.booting.lottery.service.LotteryService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("lotteryService")
public class LotteryServiceImpl extends JDBCSupport<LotteryEntity, LotteryDTO> implements LotteryService{

	private static final long serialVersionUID = 1L;

	@Override
	public LotteryDTO save(LotteryEntity lotteryEntity) {
		long id = this.persist(lotteryEntity);
		return get(id);
	}

	@Override
	public LotteryDTO update(LotteryEntity lotteryEntity) {
		this.dynamicMerge(lotteryEntity);
		return get(lotteryEntity.getLotteryId());
	}

	@Override
	public LotteryDTO updateAll(LotteryEntity lotteryEntity) {
		this.merge(lotteryEntity);
		return get(lotteryEntity.getLotteryId());
	}

	@Override
	public LotteryDTO updateBySql(LotteryDTO lotteryDTO) {
		if(null == lotteryDTO) return null;
		this.execute("lottery.updateLottery", toMap(lotteryDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == lotteryDTO.getLotteryId()) return null;
		return get(lotteryDTO.getLotteryId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<LotteryEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("lottery.insertLottery", params);
	}

	@Override
	public void batchUpdate(List<LotteryDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("lottery.updateLottery", params);
	}

	@Override
	public LotteryDTO get(long lotteryId) {
		return getById(lotteryId);
	}

	@Override
	public LotteryDTO get(LotteryDTO lotteryDTO) {
		if(null == lotteryDTO) {
			return null;
		}
		Map<String, Object> param = toMap(lotteryDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("lottery.getSimpleLotteryList", param);
	}

	@Override
	public List<LotteryDTO> getSimpleList(LotteryDTO lotteryDTO) {
		Map<String, Object> param = toMap(lotteryDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("lottery.getSimpleLotteryList", param);
	}

	@Override
	public PageList<LotteryDTO> getSimpleListForPage(LotteryDTO lotteryDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(lotteryDTO);
		return this.queryForPage("lottery.getSimpleLotteryListCount", "lottery.getSimpleLotteryList", queryParam);
	}

	@Override
	public PageList<LotteryDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("lottery.getSimpleLotteryListCount", "lottery.getSimpleLotteryList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("lottery.getLotteryList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("lottery.getLotteryListCount", "lottery.getLotteryList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("lottery.getLotteryListCount", "lottery.getLotteryList", queryParam, clazz);
	}

	@Override
	public LotteryDTO getNewestLottery(int end) {
		Map<String, Object> param = new HashMap<>();
		param.put("end", end);
		return this.queryForObject("lottery.getNewestLottery", param);
	}

}