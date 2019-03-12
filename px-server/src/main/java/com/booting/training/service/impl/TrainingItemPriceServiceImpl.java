/** create by auto at 2019-03-12 14:26:03**/
package com.booting.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.TrainingItemPriceDTO;
import com.booting.training.entity.TrainingItemPriceEntity;
import com.booting.training.service.TrainingItemPriceService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("trainingItemPriceService")
public class TrainingItemPriceServiceImpl extends JDBCSupport<TrainingItemPriceEntity, TrainingItemPriceDTO> implements TrainingItemPriceService{

	private static final long serialVersionUID = 1L;

	@Override
	public TrainingItemPriceDTO save(TrainingItemPriceEntity trainingItemPriceEntity) {
		long id = this.persist(trainingItemPriceEntity);
		return get(id);
	}

	@Override
	public TrainingItemPriceDTO update(TrainingItemPriceEntity trainingItemPriceEntity) {
		this.dynamicMerge(trainingItemPriceEntity);
		return get(trainingItemPriceEntity.getId());
	}

	@Override
	public TrainingItemPriceDTO updateAll(TrainingItemPriceEntity trainingItemPriceEntity) {
		this.merge(trainingItemPriceEntity);
		return get(trainingItemPriceEntity.getId());
	}

	@Override
	public TrainingItemPriceDTO updateBySql(TrainingItemPriceDTO trainingItemPriceDTO) {
		if(null == trainingItemPriceDTO) return null;
		this.execute("trainingItemPrice.updateTrainingItemPrice", toMap(trainingItemPriceDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == trainingItemPriceDTO.getId()) return null;
		return get(trainingItemPriceDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TrainingItemPriceEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("trainingItemPrice.insertTrainingItemPrice", params);
	}

	@Override
	public void batchUpdate(List<TrainingItemPriceDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("trainingItemPrice.updateTrainingItemPrice", params);
	}

	@Override
	public TrainingItemPriceDTO get(long id) {
		return getById(id);
	}

	@Override
	public TrainingItemPriceDTO get(TrainingItemPriceDTO trainingItemPriceDTO) {
		if(null == trainingItemPriceDTO) {
			return null;
		}
		Map<String, Object> param = toMap(trainingItemPriceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("trainingItemPrice.getSimpleTrainingItemPriceList", param);
	}

	@Override
	public List<TrainingItemPriceDTO> getSimpleList(TrainingItemPriceDTO trainingItemPriceDTO) {
		Map<String, Object> param = toMap(trainingItemPriceDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("trainingItemPrice.getSimpleTrainingItemPriceList", param);
	}

	@Override
	public PageList<TrainingItemPriceDTO> getSimpleListForPage(TrainingItemPriceDTO trainingItemPriceDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(trainingItemPriceDTO);
		return this.queryForPage("trainingItemPrice.getSimpleTrainingItemPriceListCount", "trainingItemPrice.getSimpleTrainingItemPriceList", queryParam);
	}

	@Override
	public PageList<TrainingItemPriceDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("trainingItemPrice.getSimpleTrainingItemPriceListCount", "trainingItemPrice.getSimpleTrainingItemPriceList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("trainingItemPrice.getTrainingItemPriceList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("trainingItemPrice.getTrainingItemPriceListCount", "trainingItemPrice.getTrainingItemPriceList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("trainingItemPrice.getTrainingItemPriceListCount", "trainingItemPrice.getTrainingItemPriceList", queryParam, clazz);
	}

  @Override
  public void deleteTrainingItemPriceByItemId(Long itemId) {
    Map<String, Object> params = new HashMap<>();
    params.put("itemId", itemId);
    this.execute("trainingItemPrice.deleteTrainingItemPrice", params);
  }

}