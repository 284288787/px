/** create by auto at 2018-03-02 10:12:43**/
package com.booting.lottery.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.booting.lottery.dto.LotteryDTO;
import com.booting.lottery.dto.LotteryDetailDTO;
import com.booting.lottery.entity.LotteryDetailEntity;
import com.booting.lottery.service.LotteryDetailService;
import com.star.framework.jdbc.dao.JDBCSupport;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

@Service("lotteryDetailService")
public class LotteryDetailServiceImpl extends JDBCSupport<LotteryDetailEntity, LotteryDetailDTO> implements LotteryDetailService{

	private static final long serialVersionUID = 1L;

	@Override
	public LotteryDetailDTO save(LotteryDetailEntity lotteryDetailEntity) {
		long id = this.persist(lotteryDetailEntity);
		return get(id);
	}

	@Override
	public LotteryDetailDTO update(LotteryDetailEntity lotteryDetailEntity) {
		this.dynamicMerge(lotteryDetailEntity);
		return get(lotteryDetailEntity.getId());
	}

	@Override
	public LotteryDetailDTO updateAll(LotteryDetailEntity lotteryDetailEntity) {
		this.merge(lotteryDetailEntity);
		return get(lotteryDetailEntity.getId());
	}

	@Override
	public LotteryDetailDTO updateBySql(LotteryDetailDTO lotteryDetailDTO) {
		if(null == lotteryDetailDTO) return null;
		this.execute("lotteryDetail.updateLotteryDetail", toMap(lotteryDetailDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == lotteryDetailDTO.getId()) return null;
		return get(lotteryDetailDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<LotteryDetailEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("lotteryDetail.insertLotteryDetail", params);
	}

	@Override
	public void batchUpdate(List<LotteryDetailDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("lotteryDetail.updateLotteryDetail", params);
	}

	@Override
	public LotteryDetailDTO get(long id) {
		return getById(id);
	}

	@Override
	public LotteryDetailDTO get(LotteryDetailDTO lotteryDetailDTO) {
		if(null == lotteryDetailDTO) {
			return null;
		}
		Map<String, Object> param = toMap(lotteryDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("lotteryDetail.getSimpleLotteryDetailList", param);
	}

	@Override
	public List<LotteryDetailDTO> getSimpleList(LotteryDetailDTO lotteryDetailDTO) {
		Map<String, Object> param = toMap(lotteryDetailDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("lotteryDetail.getSimpleLotteryDetailList", param);
	}

	@Override
	public PageList<LotteryDetailDTO> getSimpleListForPage(LotteryDetailDTO lotteryDetailDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(lotteryDetailDTO);
		return this.queryForPage("lotteryDetail.getSimpleLotteryDetailListCount", "lotteryDetail.getSimpleLotteryDetailList", queryParam);
	}

	@Override
	public PageList<LotteryDetailDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("lotteryDetail.getSimpleLotteryDetailListCount", "lotteryDetail.getSimpleLotteryDetailList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("lotteryDetail.getLotteryDetailList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("lotteryDetail.getLotteryDetailListCount", "lotteryDetail.getLotteryDetailList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("lotteryDetail.getLotteryDetailListCount", "lotteryDetail.getLotteryDetailList", queryParam, clazz);
	}

	@Override
	public Map<String, Integer> getDetail(LotteryDTO lottery, int lun, String awardUserIds) {
		Map<String, Integer> map = new LinkedHashMap<>();
		Map<String, Object> params = toMap(lottery, "yyyy-MM-dd HH:mm:ss");
		params.put("lun", lun);
		if (lun > 0) {
			params.put("awardUserIds", awardUserIds.substring(1));
		}
		List<Map<String, Object>> list = this.queryForList("lotteryDetail.getDetail", params, (rs, rowNum) ->{
			Map<String, Object> row = new HashMap<>();
			String key = rs.getString("key");
			int value = rs.getInt("value");
			row.put("key", key);
			row.put("value", value);
			return row;
		});
		for (Map<String, Object> map2 : list) {
			map.put((String) map2.get("key"), (Integer) map2.get("value"));
		}
		return map;
	}

	@Override
	public List<Map<String, Object>> getHistory(int lun, int size) {
		Map<String, Object> params = new HashMap<>();
		params.put("lun", lun);
		params.put("size", size);
		List<Map<String, Object>> list = this.queryForList("lotteryDetail.getHistory", params, new RowMapper<Map<String, Object>>() {

			public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, Object> map = new HashMap<>();
				map.put("lotteryId", rs.getLong("lotteryId"));
				map.put("openTime", rs.getString("openTime"));
				map.put("lun", rs.getInt("lun"));
				map.put("min", rs.getInt("min"));
				return map;
			}
		});
		return list;
	}

}