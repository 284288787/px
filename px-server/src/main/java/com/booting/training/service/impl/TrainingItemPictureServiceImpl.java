/** create by auto at 2017-12-19 15:16:14**/
package com.booting.training.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.booting.training.dto.TrainingItemPictureDTO;
import com.booting.training.entity.TrainingItemPictureEntity;
import com.booting.training.service.TrainingItemPictureService;

import com.star.framework.jdbc.dao.JDBCSupport;

import com.star.framework.jdbc.dao.result.PageList;

import com.star.framework.jdbc.dao.result.QueryParam;

@Service("trainingItemPictureService")
public class TrainingItemPictureServiceImpl extends JDBCSupport<TrainingItemPictureEntity, TrainingItemPictureDTO> implements TrainingItemPictureService{

	private static final long serialVersionUID = 1L;

	@Override
	public TrainingItemPictureDTO save(TrainingItemPictureEntity trainingItemPictureEntity) {
		long id = this.persist(trainingItemPictureEntity);
		return get(id);
	}

	@Override
	public TrainingItemPictureDTO update(TrainingItemPictureEntity trainingItemPictureEntity) {
		this.dynamicMerge(trainingItemPictureEntity);
		return get(trainingItemPictureEntity.getId());
	}

	@Override
	public TrainingItemPictureDTO updateAll(TrainingItemPictureEntity trainingItemPictureEntity) {
		this.merge(trainingItemPictureEntity);
		return get(trainingItemPictureEntity.getId());
	}

	@Override
	public TrainingItemPictureDTO updateBySql(TrainingItemPictureDTO trainingItemPictureDTO) {
		if(null == trainingItemPictureDTO) return null;
		this.execute("trainingItemPicture.updateTrainingItemPicture", toMap(trainingItemPictureDTO, "yyyy-MM-dd HH:mm:ss"));
		if(null == trainingItemPictureDTO.getId()) return null;
		return get(trainingItemPictureDTO.getId());
	}

	@Override
	public int delete(long id) {
		return this.del(id);
	}

	@Override
	public void batchSave(List<TrainingItemPictureEntity> entities) {
		if(null == entities || entities.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(entities);
		this.batch("trainingItemPicture.insertTrainingItemPicture", params);
	}

	@Override
	public void batchUpdate(List<TrainingItemPictureDTO> dtos) {
		if(null == dtos || dtos.isEmpty()){
			return;
		}
		Map<String, Object>[] params = toMap(dtos);
		this.batch("trainingItemPicture.updateTrainingItemPicture", params);
	}

	@Override
	public TrainingItemPictureDTO get(long id) {
		return getById(id);
	}

	@Override
	public TrainingItemPictureDTO get(TrainingItemPictureDTO trainingItemPictureDTO) {
		if(null == trainingItemPictureDTO) {
			return null;
		}
		Map<String, Object> param = toMap(trainingItemPictureDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForObject("trainingItemPicture.getSimpleTrainingItemPictureList", param);
	}

	@Override
	public List<TrainingItemPictureDTO> getSimpleList(TrainingItemPictureDTO trainingItemPictureDTO) {
		Map<String, Object> param = toMap(trainingItemPictureDTO, "yyyy-MM-dd HH:mm:ss");
		return this.queryForList("trainingItemPicture.getSimpleTrainingItemPictureList", param);
	}

	@Override
	public PageList<TrainingItemPictureDTO> getSimpleListForPage(TrainingItemPictureDTO trainingItemPictureDTO, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(trainingItemPictureDTO);
		return this.queryForPage("trainingItemPicture.getSimpleTrainingItemPictureListCount", "trainingItemPicture.getSimpleTrainingItemPictureList", queryParam);
	}

	@Override
	public PageList<TrainingItemPictureDTO> getSimpleListForPage(QueryParam queryParam) {
		return this.queryForPage("trainingItemPicture.getSimpleTrainingItemPictureListCount", "trainingItemPicture.getSimpleTrainingItemPictureList", queryParam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList(T dto) {
		Map<String, Object> param = toMap(dto, "yyyy-MM-dd HH:mm:ss");
		return (List<T>)this.queryForList("trainingItemPicture.getTrainingItemPictureList", param);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNo, int pageSize) {
		QueryParam queryParam = new QueryParam();
		queryParam.setPageNo(pageNo);
		queryParam.setPageSize(pageSize);
		queryParam.setParam(dto);
		return (PageList<T>) this.queryForPage("trainingItemPicture.getTrainingItemPictureListCount", "trainingItemPicture.getTrainingItemPictureList", queryParam, dto.getClass());
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return (PageList<T>) this.queryForPage("trainingItemPicture.getTrainingItemPictureListCount", "trainingItemPicture.getTrainingItemPictureList", queryParam, clazz);
	}

	@Override
	public void deleteTrainingItemPictureByItemId(Long itemId) {
		Map<String, Object> params = new HashMap<>();
		params.put("itemId", itemId);
		this.execute("trainingItemPicture.deleteTrainingItemPicture", params);
	}

}