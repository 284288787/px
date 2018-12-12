/** create by auto at 2017-12-19 15:16:14**/
package com.booting.training.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.TrainingItemDTO;
import com.booting.training.entity.TrainingItemEntity;
import com.booting.training.service.TrainingItemService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("trainingItemService")
public class TrainingItemServiceImpl extends JDBCSupport<TrainingItemEntity, TrainingItemDTO> implements TrainingItemService{

	private static final long serialVersionUID = 1L;

	@Override
	public TrainingItemDTO save(TrainingItemEntity trainingItemEntity) {
		long id = this.persist(trainingItemEntity);
		return get(id);
	}

	@Override
	public TrainingItemDTO update(TrainingItemEntity trainingItemEntity) {
		this.dynamicMerge(trainingItemEntity);
		return get(trainingItemEntity.getItemId());
	}

	@Override
	public TrainingItemDTO updateAll(TrainingItemEntity trainingItemEntity) {
		this.merge(trainingItemEntity);
		return get(trainingItemEntity.getItemId());
	}

	@Override
	public TrainingItemDTO updateBySql(TrainingItemDTO trainingItemDTO) {
		if(null == trainingItemDTO) return null;
		this.execute("trainingItem.updateTrainingItem", toMap(trainingItemDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == trainingItemDTO.getItemId()) return null;
		return get(trainingItemDTO.getItemId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TrainingItemEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("trainingItem.insertTrainingItem", params);
	}

	@Override
	public void batchUpdate(List<TrainingItemDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("trainingItem.updateTrainingItem", params);
	}

	@Override
	public TrainingItemDTO get(long itemId) {
		return getById(itemId);
	}

	@Override
	public TrainingItemDTO get(TrainingItemDTO trainingItemDTO) {
		if(null == trainingItemDTO) {
			return null;
		}
		Map<String, Object> param = toMap(trainingItemDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("trainingItem.getSimpleTrainingItemList", param);
	}

	@Override
	public List<TrainingItemDTO> getSimpleList(TrainingItemDTO trainingItemDTO) {
		Map<String, Object> param = toMap(trainingItemDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("trainingItem.getSimpleTrainingItemList", param);
	}

	@Override
	public PageList<TrainingItemDTO> getSimpleListForPage(TrainingItemDTO trainingItemDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(trainingItemDTO);
		return this.queryForPage("trainingItem.getSimpleTrainingItemListCount", "trainingItem.getSimpleTrainingItemList", queryParam);
	}

	@Override
	public PageList<TrainingItemDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("trainingItem.getSimpleTrainingItemListCount", "trainingItem.getSimpleTrainingItemList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("trainingItem.getTrainingItemList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("trainingItem.getTrainingItemListCount", "trainingItem.getTrainingItemList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("trainingItem.getTrainingItemListCount", "trainingItem.getTrainingItemList", queryParam, clazz);
	}

}