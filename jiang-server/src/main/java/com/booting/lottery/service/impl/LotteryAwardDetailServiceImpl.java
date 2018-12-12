/** create by auto at 2018-03-07 09:29:01**/
package com.booting.lottery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.lottery.dto.LotteryAwardDetailDTO;
import com.booting.lottery.entity.LotteryAwardDetailEntity;
import com.booting.lottery.service.LotteryAwardDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("lotteryAwardDetailService")
public class LotteryAwardDetailServiceImpl extends JDBCSupport<LotteryAwardDetailEntity, LotteryAwardDetailDTO> implements LotteryAwardDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public LotteryAwardDetailDTO save(LotteryAwardDetailEntity lotteryAwardDetailEntity) {
		long id = this.persist(lotteryAwardDetailEntity);
		return get(id);
	}

	@Override
	public LotteryAwardDetailDTO update(LotteryAwardDetailEntity lotteryAwardDetailEntity) {
		this.dynamicMerge(lotteryAwardDetailEntity);
		return get(lotteryAwardDetailEntity.getAwardId());
	}

	@Override
	public LotteryAwardDetailDTO updateAll(LotteryAwardDetailEntity lotteryAwardDetailEntity) {
		this.merge(lotteryAwardDetailEntity);
		return get(lotteryAwardDetailEntity.getAwardId());
	}

	@Override
	public LotteryAwardDetailDTO updateBySql(LotteryAwardDetailDTO lotteryAwardDetailDTO) {
		if(null == lotteryAwardDetailDTO) return null;
		this.execute("lotteryAwardDetail.updateLotteryAwardDetail", toMap(lotteryAwardDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == lotteryAwardDetailDTO.getAwardId()) return null;
		return get(lotteryAwardDetailDTO.getAwardId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<LotteryAwardDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("lotteryAwardDetail.insertLotteryAwardDetail", params);
	}

	@Override
	public void batchUpdate(List<LotteryAwardDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("lotteryAwardDetail.updateLotteryAwardDetail", params);
	}

	@Override
	public LotteryAwardDetailDTO get(long awardId) {
		return getById(awardId);
	}

	@Override
	public LotteryAwardDetailDTO get(LotteryAwardDetailDTO lotteryAwardDetailDTO) {
		if(null == lotteryAwardDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(lotteryAwardDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("lotteryAwardDetail.getSimpleLotteryAwardDetailList", param);
	}

	@Override
	public List<LotteryAwardDetailDTO> getSimpleList(LotteryAwardDetailDTO lotteryAwardDetailDTO) {
		Map<String, Object> param = toMap(lotteryAwardDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("lotteryAwardDetail.getSimpleLotteryAwardDetailList", param);
	}

	@Override
	public PageList<LotteryAwardDetailDTO> getSimpleListForPage(LotteryAwardDetailDTO lotteryAwardDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(lotteryAwardDetailDTO);
		return this.queryForPage("lotteryAwardDetail.getSimpleLotteryAwardDetailListCount", "lotteryAwardDetail.getSimpleLotteryAwardDetailList", queryParam);
	}

	@Override
	public PageList<LotteryAwardDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("lotteryAwardDetail.getSimpleLotteryAwardDetailListCount", "lotteryAwardDetail.getSimpleLotteryAwardDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("lotteryAwardDetail.getLotteryAwardDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("lotteryAwardDetail.getLotteryAwardDetailListCount", "lotteryAwardDetail.getLotteryAwardDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("lotteryAwardDetail.getLotteryAwardDetailListCount", "lotteryAwardDetail.getLotteryAwardDetailList", queryParam, clazz);
	}

}