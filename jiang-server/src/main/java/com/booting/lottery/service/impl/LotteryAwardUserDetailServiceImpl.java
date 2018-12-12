/** create by auto at 2018-03-07 09:29:01**/
package com.booting.lottery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.lottery.dto.LotteryAwardUserDetailDTO;
import com.booting.lottery.entity.LotteryAwardUserDetailEntity;
import com.booting.lottery.service.LotteryAwardUserDetailService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("lotteryAwardUserDetailService")
public class LotteryAwardUserDetailServiceImpl extends JDBCSupport<LotteryAwardUserDetailEntity, LotteryAwardUserDetailDTO> implements LotteryAwardUserDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public LotteryAwardUserDetailDTO save(LotteryAwardUserDetailEntity lotteryAwardUserDetailEntity) {
		long id = this.persist(lotteryAwardUserDetailEntity);
		return get(id);
	}

	@Override
	public LotteryAwardUserDetailDTO update(LotteryAwardUserDetailEntity lotteryAwardUserDetailEntity) {
		this.dynamicMerge(lotteryAwardUserDetailEntity);
		return get(lotteryAwardUserDetailEntity.getId());
	}

	@Override
	public LotteryAwardUserDetailDTO updateAll(LotteryAwardUserDetailEntity lotteryAwardUserDetailEntity) {
		this.merge(lotteryAwardUserDetailEntity);
		return get(lotteryAwardUserDetailEntity.getId());
	}

	@Override
	public LotteryAwardUserDetailDTO updateBySql(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO) {
		if(null == lotteryAwardUserDetailDTO) return null;
		this.execute("lotteryAwardUserDetail.updateLotteryAwardUserDetail", toMap(lotteryAwardUserDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == lotteryAwardUserDetailDTO.getId()) return null;
		return get(lotteryAwardUserDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<LotteryAwardUserDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("lotteryAwardUserDetail.insertLotteryAwardUserDetail", params);
	}

	@Override
	public void batchUpdate(List<LotteryAwardUserDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("lotteryAwardUserDetail.updateLotteryAwardUserDetail", params);
	}

	@Override
	public LotteryAwardUserDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public LotteryAwardUserDetailDTO get(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO) {
		if(null == lotteryAwardUserDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(lotteryAwardUserDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("lotteryAwardUserDetail.getSimpleLotteryAwardUserDetailList", param);
	}

	@Override
	public List<LotteryAwardUserDetailDTO> getSimpleList(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO) {
		Map<String, Object> param = toMap(lotteryAwardUserDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("lotteryAwardUserDetail.getSimpleLotteryAwardUserDetailList", param);
	}

	@Override
	public PageList<LotteryAwardUserDetailDTO> getSimpleListForPage(LotteryAwardUserDetailDTO lotteryAwardUserDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(lotteryAwardUserDetailDTO);
		return this.queryForPage("lotteryAwardUserDetail.getSimpleLotteryAwardUserDetailListCount", "lotteryAwardUserDetail.getSimpleLotteryAwardUserDetailList", queryParam);
	}

	@Override
	public PageList<LotteryAwardUserDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("lotteryAwardUserDetail.getSimpleLotteryAwardUserDetailListCount", "lotteryAwardUserDetail.getSimpleLotteryAwardUserDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("lotteryAwardUserDetail.getLotteryAwardUserDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("lotteryAwardUserDetail.getLotteryAwardUserDetailListCount", "lotteryAwardUserDetail.getLotteryAwardUserDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("lotteryAwardUserDetail.getLotteryAwardUserDetailListCount", "lotteryAwardUserDetail.getLotteryAwardUserDetailList", queryParam, clazz);
	}

}