/** create by auto at 2017-12-19 15:16:14**/
package com.booting.training.facade.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.training.facade.TrainingFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.training.dto.ApplyDetailDTO;
import com.booting.training.entity.ApplyDetailEntity;
import com.booting.training.service.ApplyDetailService;
import com.booting.training.dto.ApplyInfoDTO;
import com.booting.training.entity.ApplyInfoEntity;
import com.booting.training.service.ApplyInfoService;
import com.booting.training.dto.TrainingItemDTO;
import com.booting.training.entity.TrainingItemEntity;
import com.booting.training.service.TrainingItemService;
import com.booting.training.dto.TrainingItemPictureDTO;
import com.booting.training.entity.TrainingItemPictureEntity;
import com.booting.training.service.TrainingItemPictureService;

@Service("trainingFacade")
public class TrainingFacadeImpl implements TrainingFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ApplyDetailService applyDetailService;

	@Autowired
	private ApplyInfoService applyInfoService;

	@Autowired
	private TrainingItemService trainingItemService;

	@Autowired
	private TrainingItemPictureService trainingItemPictureService;


	@Override
	public Long saveApplyDetail(ApplyDetailDTO applyDetailDTO){
		if (null == applyDetailDTO) {
			return null;
		}
		ApplyDetailEntity entity = toApplyDetailEntity(applyDetailDTO);
		applyDetailDTO = applyDetailService.save(entity);
		return applyDetailDTO.getId();
	}

	@Override
	public void batchSaveApplyDetail(List<ApplyDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ApplyDetailEntity> entities = toApplyDetailEntities(dtos);
		applyDetailService.batchSave(entities);
	}

	@Override
	public int updateApplyDetail(ApplyDetailDTO applyDetailDTO){
		applyDetailDTO = applyDetailService.updateBySql(applyDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateApplyDetail(List<ApplyDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		applyDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteApplyDetail(long id){
		return applyDetailService.delete(id);
	}

	@Override
	public ApplyDetailDTO getApplyDetail(long id){
		return applyDetailService.get(id);
	}

	@Override
	public ApplyDetailDTO getApplyDetail(ApplyDetailDTO applyDetailDTO){
		return applyDetailService.get(applyDetailDTO);
	}

	@Override
	public List<ApplyDetailDTO> getApplyDetailList(ApplyDetailDTO applyDetailDTO){
		return applyDetailService.getSimpleList(applyDetailDTO);
	}

	@Override
	public PageList<ApplyDetailDTO> getApplyDetailListForPage(ApplyDetailDTO applyDetailDTO, int pageNumber, int pageSize){
		return applyDetailService.getSimpleListForPage(applyDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ApplyDetailDTO> getApplyDetailListForPage(QueryParam queryParam){
		return applyDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public ApplyDetailEntity toApplyDetailEntity(ApplyDetailDTO dto){
		ApplyDetailEntity entity = new ApplyDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ApplyDetailEntity> toApplyDetailEntities(List<ApplyDetailDTO> dtos){
		List<ApplyDetailEntity> entities = new ArrayList<>();
		for(ApplyDetailDTO dto : dtos){
			entities.add(toApplyDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveApplyInfo(ApplyInfoDTO applyInfoDTO){
		if (null == applyInfoDTO) {
			return null;
		}
		ApplyInfoEntity entity = toApplyInfoEntity(applyInfoDTO);
		applyInfoDTO = applyInfoService.save(entity);
		return applyInfoDTO.getApplyId();
	}

	@Override
	public void batchSaveApplyInfo(List<ApplyInfoDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ApplyInfoEntity> entities = toApplyInfoEntities(dtos);
		applyInfoService.batchSave(entities);
	}

	@Override
	public int updateApplyInfo(ApplyInfoDTO applyInfoDTO){
		applyInfoDTO = applyInfoService.updateBySql(applyInfoDTO);
		return 1;
	}

	@Override
	public void batchUpdateApplyInfo(List<ApplyInfoDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		applyInfoService.batchUpdate(dtos);
	}

	@Override
	public int deleteApplyInfo(long applyId){
		return applyInfoService.delete(applyId);
	}

	@Override
	public ApplyInfoDTO getApplyInfo(long applyId){
		return applyInfoService.get(applyId);
	}

	@Override
	public ApplyInfoDTO getApplyInfo(ApplyInfoDTO applyInfoDTO){
		return applyInfoService.get(applyInfoDTO);
	}

	@Override
	public List<ApplyInfoDTO> getApplyInfoList(ApplyInfoDTO applyInfoDTO){
		return applyInfoService.getSimpleList(applyInfoDTO);
	}

	@Override
	public PageList<ApplyInfoDTO> getApplyInfoListForPage(ApplyInfoDTO applyInfoDTO, int pageNumber, int pageSize){
		return applyInfoService.getSimpleListForPage(applyInfoDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ApplyInfoDTO> getApplyInfoListForPage(QueryParam queryParam){
		return applyInfoService.getSimpleListForPage(queryParam);
	}

	@Override
	public ApplyInfoEntity toApplyInfoEntity(ApplyInfoDTO dto){
		ApplyInfoEntity entity = new ApplyInfoEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ApplyInfoEntity> toApplyInfoEntities(List<ApplyInfoDTO> dtos){
		List<ApplyInfoEntity> entities = new ArrayList<>();
		for(ApplyInfoDTO dto : dtos){
			entities.add(toApplyInfoEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveTrainingItem(TrainingItemDTO trainingItemDTO){
		if (null == trainingItemDTO) {
			return null;
		}
		TrainingItemEntity entity = toTrainingItemEntity(trainingItemDTO);
		trainingItemDTO = trainingItemService.save(entity);
		return trainingItemDTO.getItemId();
	}

	@Override
	public void batchSaveTrainingItem(List<TrainingItemDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TrainingItemEntity> entities = toTrainingItemEntities(dtos);
		trainingItemService.batchSave(entities);
	}

	@Override
	public int updateTrainingItem(TrainingItemDTO trainingItemDTO){
		trainingItemDTO = trainingItemService.updateBySql(trainingItemDTO);
		return 1;
	}

	@Override
	public void batchUpdateTrainingItem(List<TrainingItemDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		trainingItemService.batchUpdate(dtos);
	}

	@Override
	public int deleteTrainingItem(long itemId){
		return trainingItemService.delete(itemId);
	}

	@Override
	public TrainingItemDTO getTrainingItem(long itemId){
		return trainingItemService.get(itemId);
	}

	@Override
	public TrainingItemDTO getTrainingItem(TrainingItemDTO trainingItemDTO){
		return trainingItemService.get(trainingItemDTO);
	}

	@Override
	public List<TrainingItemDTO> getTrainingItemList(TrainingItemDTO trainingItemDTO){
		return trainingItemService.getSimpleList(trainingItemDTO);
	}

	@Override
	public PageList<TrainingItemDTO> getTrainingItemListForPage(TrainingItemDTO trainingItemDTO, int pageNumber, int pageSize){
		return trainingItemService.getSimpleListForPage(trainingItemDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TrainingItemDTO> getTrainingItemListForPage(QueryParam queryParam){
		return trainingItemService.getSimpleListForPage(queryParam);
	}

	@Override
	public TrainingItemEntity toTrainingItemEntity(TrainingItemDTO dto){
		TrainingItemEntity entity = new TrainingItemEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TrainingItemEntity> toTrainingItemEntities(List<TrainingItemDTO> dtos){
		List<TrainingItemEntity> entities = new ArrayList<>();
		for(TrainingItemDTO dto : dtos){
			entities.add(toTrainingItemEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO){
		if (null == trainingItemPictureDTO) {
			return null;
		}
		TrainingItemPictureEntity entity = toTrainingItemPictureEntity(trainingItemPictureDTO);
		trainingItemPictureDTO = trainingItemPictureService.save(entity);
		return trainingItemPictureDTO.getId();
	}

	@Override
	public void batchSaveTrainingItemPicture(List<TrainingItemPictureDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TrainingItemPictureEntity> entities = toTrainingItemPictureEntities(dtos);
		trainingItemPictureService.batchSave(entities);
	}

	@Override
	public int updateTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO){
		trainingItemPictureDTO = trainingItemPictureService.updateBySql(trainingItemPictureDTO);
		return 1;
	}

	@Override
	public void batchUpdateTrainingItemPicture(List<TrainingItemPictureDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		trainingItemPictureService.batchUpdate(dtos);
	}

	@Override
	public int deleteTrainingItemPicture(long id){
		return trainingItemPictureService.delete(id);
	}

	@Override
	public TrainingItemPictureDTO getTrainingItemPicture(long id){
		return trainingItemPictureService.get(id);
	}

	@Override
	public TrainingItemPictureDTO getTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO){
		return trainingItemPictureService.get(trainingItemPictureDTO);
	}

	@Override
	public List<TrainingItemPictureDTO> getTrainingItemPictureList(TrainingItemPictureDTO trainingItemPictureDTO){
		return trainingItemPictureService.getSimpleList(trainingItemPictureDTO);
	}

	@Override
	public PageList<TrainingItemPictureDTO> getTrainingItemPictureListForPage(TrainingItemPictureDTO trainingItemPictureDTO, int pageNumber, int pageSize){
		return trainingItemPictureService.getSimpleListForPage(trainingItemPictureDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TrainingItemPictureDTO> getTrainingItemPictureListForPage(QueryParam queryParam){
		return trainingItemPictureService.getSimpleListForPage(queryParam);
	}

	@Override
	public TrainingItemPictureEntity toTrainingItemPictureEntity(TrainingItemPictureDTO dto){
		TrainingItemPictureEntity entity = new TrainingItemPictureEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TrainingItemPictureEntity> toTrainingItemPictureEntities(List<TrainingItemPictureDTO> dtos){
		List<TrainingItemPictureEntity> entities = new ArrayList<>();
		for(TrainingItemPictureDTO dto : dtos){
			entities.add(toTrainingItemPictureEntity(dto));
		}
		return entities;
	}

	@Override
	public <T> List<T> getList(T dto){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize){
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz){
		return null;
	}

	@Override
	public void deleteTrainingItemPictureByItemId(Long itemId) {
		this.trainingItemPictureService.deleteTrainingItemPictureByItemId(itemId);
	}

	@Override
	public TrainingItemDTO updateBySql(TrainingItemDTO dto) {
		return this.trainingItemService.updateBySql(dto);
	}

}