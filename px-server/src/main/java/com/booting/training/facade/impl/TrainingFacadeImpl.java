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
import com.booting.training.dto.PromoterDTO;
import com.booting.training.dto.StudyAddressDTO;
import com.booting.training.entity.ApplyInfoEntity;
import com.booting.training.entity.PromoterEntity;
import com.booting.training.entity.StudyAddressEntity;
import com.booting.training.service.ApplyInfoService;
import com.booting.training.service.PromoterService;
import com.booting.training.service.StudyAddressService;
import com.booting.training.dto.TrainingItemDTO;
import com.booting.training.entity.TrainingItemEntity;
import com.booting.training.service.TrainingItemService;
import com.booting.training.dto.TrainingItemPictureDTO;
import com.booting.training.dto.TrainingItemPriceDTO;
import com.booting.training.entity.TrainingItemPictureEntity;
import com.booting.training.entity.TrainingItemPriceEntity;
import com.booting.training.service.TrainingItemPictureService;
import com.booting.training.service.TrainingItemPriceService;

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

  @Autowired
  private StudyAddressService studyAddressService;

  @Autowired
  private PromoterService promoterService;

  @Autowired
  private TrainingItemPriceService trainingItemPriceService;
  
  @Override
  public Long saveTrainingItemPrice(TrainingItemPriceDTO trainingItemPriceDTO){
      if (null == trainingItemPriceDTO) {
          return null;
      }
      TrainingItemPriceEntity entity = toTrainingItemPriceEntity(trainingItemPriceDTO);
      trainingItemPriceDTO = trainingItemPriceService.save(entity);
      return trainingItemPriceDTO.getId();
  }

  @Override
  public void batchSaveTrainingItemPrice(List<TrainingItemPriceDTO> dtos){
      if (null == dtos || dtos.isEmpty()) {
          return;
      }
      List<TrainingItemPriceEntity> entities = toTrainingItemPriceEntities(dtos);
      trainingItemPriceService.batchSave(entities);
  }

  @Override
  public int updateTrainingItemPrice(TrainingItemPriceDTO trainingItemPriceDTO){
      trainingItemPriceDTO = trainingItemPriceService.updateBySql(trainingItemPriceDTO);
      return 1;
  }

  @Override
  public void batchUpdateTrainingItemPrice(List<TrainingItemPriceDTO> dtos){
      if (null == dtos || dtos.isEmpty()) {
          return;
      }
      trainingItemPriceService.batchUpdate(dtos);
  }

  @Override
  public int deleteTrainingItemPrice(long id){
      return trainingItemPriceService.delete(id);
  }

  @Override
  public TrainingItemPriceDTO getTrainingItemPrice(long id){
      return trainingItemPriceService.get(id);
  }

  @Override
  public TrainingItemPriceDTO getTrainingItemPrice(TrainingItemPriceDTO trainingItemPriceDTO){
      return trainingItemPriceService.get(trainingItemPriceDTO);
  }

  @Override
  public List<TrainingItemPriceDTO> getTrainingItemPriceList(TrainingItemPriceDTO trainingItemPriceDTO){
      return trainingItemPriceService.getSimpleList(trainingItemPriceDTO);
  }

  @Override
  public PageList<TrainingItemPriceDTO> getTrainingItemPriceListForPage(TrainingItemPriceDTO trainingItemPriceDTO, int pageNumber, int pageSize){
      return trainingItemPriceService.getSimpleListForPage(trainingItemPriceDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<TrainingItemPriceDTO> getTrainingItemPriceListForPage(QueryParam queryParam){
      return trainingItemPriceService.getSimpleListForPage(queryParam);
  }

  @Override
  public TrainingItemPriceEntity toTrainingItemPriceEntity(TrainingItemPriceDTO dto){
      TrainingItemPriceEntity entity = new TrainingItemPriceEntity();
      CglibBeanUtils.copy(dto, entity);
      return entity;
  }

  @Override
  public List<TrainingItemPriceEntity> toTrainingItemPriceEntities(List<TrainingItemPriceDTO> dtos){
      List<TrainingItemPriceEntity> entities = new ArrayList<>();
      for(TrainingItemPriceDTO dto : dtos){
          entities.add(toTrainingItemPriceEntity(dto));
      }
      return entities;
  }

  @Override
  public Long savePromoter(PromoterDTO promoterDTO) {
    if (null == promoterDTO) {
      return null;
    }
    PromoterEntity entity = toPromoterEntity(promoterDTO);
    promoterDTO = promoterService.save(entity);
    return promoterDTO.getPromoterId();
  }

  @Override
  public void batchSavePromoter(List<PromoterDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<PromoterEntity> entities = toPromoterEntities(dtos);
    promoterService.batchSave(entities);
  }

  @Override
  public int updatePromoter(PromoterDTO promoterDTO) {
    promoterDTO = promoterService.updateBySql(promoterDTO);
    return 1;
  }

  @Override
  public void batchUpdatePromoter(List<PromoterDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    promoterService.batchUpdate(dtos);
  }

  @Override
  public int deletePromoter(long promoterId) {
    return promoterService.delete(promoterId);
  }

  @Override
  public PromoterDTO getPromoter(long promoterId) {
    return promoterService.get(promoterId);
  }

  @Override
  public PromoterDTO getPromoter(PromoterDTO promoterDTO) {
    return promoterService.get(promoterDTO);
  }

  @Override
  public List<PromoterDTO> getPromoterList(PromoterDTO promoterDTO) {
    return promoterService.getSimpleList(promoterDTO);
  }

  @Override
  public PageList<PromoterDTO> getPromoterListForPage(PromoterDTO promoterDTO, int pageNumber, int pageSize) {
    return promoterService.getSimpleListForPage(promoterDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<PromoterDTO> getPromoterListForPage(QueryParam queryParam) {
    return promoterService.getSimpleListForPage(queryParam);
  }

  @Override
  public PromoterEntity toPromoterEntity(PromoterDTO dto) {
    PromoterEntity entity = new PromoterEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<PromoterEntity> toPromoterEntities(List<PromoterDTO> dtos) {
    List<PromoterEntity> entities = new ArrayList<>();
    for (PromoterDTO dto : dtos) {
      entities.add(toPromoterEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveStudyAddress(StudyAddressDTO studyAddressDTO) {
    if (null == studyAddressDTO) {
      return null;
    }
    StudyAddressEntity entity = toStudyAddressEntity(studyAddressDTO);
    studyAddressDTO = studyAddressService.save(entity);
    return studyAddressDTO.getAddrId();
  }

  @Override
  public void batchSaveStudyAddress(List<StudyAddressDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<StudyAddressEntity> entities = toStudyAddressEntities(dtos);
    studyAddressService.batchSave(entities);
  }

  @Override
  public int updateStudyAddress(StudyAddressDTO studyAddressDTO) {
    studyAddressDTO = studyAddressService.updateBySql(studyAddressDTO);
    return 1;
  }

  @Override
  public void batchUpdateStudyAddress(List<StudyAddressDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    studyAddressService.batchUpdate(dtos);
  }

  @Override
  public int deleteStudyAddress(long addrId) {
    return studyAddressService.delete(addrId);
  }

  @Override
  public StudyAddressDTO getStudyAddress(long addrId) {
    return studyAddressService.get(addrId);
  }

  @Override
  public StudyAddressDTO getStudyAddress(StudyAddressDTO studyAddressDTO) {
    return studyAddressService.get(studyAddressDTO);
  }

  @Override
  public List<StudyAddressDTO> getStudyAddressList(StudyAddressDTO studyAddressDTO) {
    return studyAddressService.getSimpleList(studyAddressDTO);
  }

  @Override
  public PageList<StudyAddressDTO> getStudyAddressListForPage(StudyAddressDTO studyAddressDTO, int pageNumber, int pageSize) {
    return studyAddressService.getSimpleListForPage(studyAddressDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<StudyAddressDTO> getStudyAddressListForPage(QueryParam queryParam) {
    return studyAddressService.getSimpleListForPage(queryParam);
  }

  @Override
  public Long saveApplyDetail(ApplyDetailDTO applyDetailDTO) {
    if (null == applyDetailDTO) {
      return null;
    }
    ApplyDetailEntity entity = toApplyDetailEntity(applyDetailDTO);
    applyDetailDTO = applyDetailService.save(entity);
    return applyDetailDTO.getId();
  }

  @Override
  public void batchSaveApplyDetail(List<ApplyDetailDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<ApplyDetailEntity> entities = toApplyDetailEntities(dtos);
    applyDetailService.batchSave(entities);
  }

  @Override
  public int updateApplyDetail(ApplyDetailDTO applyDetailDTO) {
    applyDetailDTO = applyDetailService.updateBySql(applyDetailDTO);
    return 1;
  }

  @Override
  public void batchUpdateApplyDetail(List<ApplyDetailDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    applyDetailService.batchUpdate(dtos);
  }

  @Override
  public int deleteApplyDetail(long id) {
    return applyDetailService.delete(id);
  }

  @Override
  public ApplyDetailDTO getApplyDetail(long id) {
    return applyDetailService.get(id);
  }

  @Override
  public ApplyDetailDTO getApplyDetail(ApplyDetailDTO applyDetailDTO) {
    return applyDetailService.get(applyDetailDTO);
  }

  @Override
  public List<ApplyDetailDTO> getApplyDetailList(ApplyDetailDTO applyDetailDTO) {
    return applyDetailService.getSimpleList(applyDetailDTO);
  }

  @Override
  public PageList<ApplyDetailDTO> getApplyDetailListForPage(ApplyDetailDTO applyDetailDTO, int pageNumber, int pageSize) {
    return applyDetailService.getSimpleListForPage(applyDetailDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<ApplyDetailDTO> getApplyDetailListForPage(QueryParam queryParam) {
    return applyDetailService.getSimpleListForPage(queryParam);
  }

  @Override
  public ApplyDetailEntity toApplyDetailEntity(ApplyDetailDTO dto) {
    ApplyDetailEntity entity = new ApplyDetailEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<ApplyDetailEntity> toApplyDetailEntities(List<ApplyDetailDTO> dtos) {
    List<ApplyDetailEntity> entities = new ArrayList<>();
    for (ApplyDetailDTO dto : dtos) {
      entities.add(toApplyDetailEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveApplyInfo(ApplyInfoDTO applyInfoDTO) {
    if (null == applyInfoDTO) {
      return null;
    }
    ApplyInfoEntity entity = toApplyInfoEntity(applyInfoDTO);
    applyInfoDTO = applyInfoService.save(entity);
    return applyInfoDTO.getApplyId();
  }

  @Override
  public void batchSaveApplyInfo(List<ApplyInfoDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<ApplyInfoEntity> entities = toApplyInfoEntities(dtos);
    applyInfoService.batchSave(entities);
  }

  @Override
  public int updateApplyInfo(ApplyInfoDTO applyInfoDTO) {
    applyInfoDTO = applyInfoService.updateBySql(applyInfoDTO);
    return 1;
  }

  @Override
  public void batchUpdateApplyInfo(List<ApplyInfoDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    applyInfoService.batchUpdate(dtos);
  }

  @Override
  public int deleteApplyInfo(long applyId) {
    return applyInfoService.delete(applyId);
  }

  @Override
  public ApplyInfoDTO getApplyInfo(long applyId) {
    return applyInfoService.get(applyId);
  }

  @Override
  public ApplyInfoDTO getApplyInfo(ApplyInfoDTO applyInfoDTO) {
    return applyInfoService.get(applyInfoDTO);
  }

  @Override
  public List<ApplyInfoDTO> getApplyInfoList(ApplyInfoDTO applyInfoDTO) {
    return applyInfoService.getSimpleList(applyInfoDTO);
  }

  @Override
  public PageList<ApplyInfoDTO> getApplyInfoListForPage(ApplyInfoDTO applyInfoDTO, int pageNumber, int pageSize) {
    return applyInfoService.getSimpleListForPage(applyInfoDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<ApplyInfoDTO> getApplyInfoListForPage(QueryParam queryParam) {
    return applyInfoService.getSimpleListForPage(queryParam);
  }

  @Override
  public ApplyInfoEntity toApplyInfoEntity(ApplyInfoDTO dto) {
    ApplyInfoEntity entity = new ApplyInfoEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<ApplyInfoEntity> toApplyInfoEntities(List<ApplyInfoDTO> dtos) {
    List<ApplyInfoEntity> entities = new ArrayList<>();
    for (ApplyInfoDTO dto : dtos) {
      entities.add(toApplyInfoEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveTrainingItem(TrainingItemDTO trainingItemDTO) {
    if (null == trainingItemDTO) {
      return null;
    }
    TrainingItemEntity entity = toTrainingItemEntity(trainingItemDTO);
    trainingItemDTO = trainingItemService.save(entity);
    return trainingItemDTO.getItemId();
  }

  @Override
  public void batchSaveTrainingItem(List<TrainingItemDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<TrainingItemEntity> entities = toTrainingItemEntities(dtos);
    trainingItemService.batchSave(entities);
  }

  @Override
  public int updateTrainingItem(TrainingItemDTO trainingItemDTO) {
    trainingItemDTO = trainingItemService.updateBySql(trainingItemDTO);
    return 1;
  }

  @Override
  public void batchUpdateTrainingItem(List<TrainingItemDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    trainingItemService.batchUpdate(dtos);
  }

  @Override
  public int deleteTrainingItem(long itemId) {
    return trainingItemService.delete(itemId);
  }

  @Override
  public TrainingItemDTO getTrainingItem(long itemId) {
    return trainingItemService.get(itemId);
  }

  @Override
  public TrainingItemDTO getTrainingItem(TrainingItemDTO trainingItemDTO) {
    return trainingItemService.get(trainingItemDTO);
  }

  @Override
  public List<TrainingItemDTO> getTrainingItemList(TrainingItemDTO trainingItemDTO) {
    return trainingItemService.getSimpleList(trainingItemDTO);
  }

  @Override
  public PageList<TrainingItemDTO> getTrainingItemListForPage(TrainingItemDTO trainingItemDTO, int pageNumber, int pageSize) {
    return trainingItemService.getSimpleListForPage(trainingItemDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<TrainingItemDTO> getTrainingItemListForPage(QueryParam queryParam) {
    return trainingItemService.getSimpleListForPage(queryParam);
  }

  @Override
  public TrainingItemEntity toTrainingItemEntity(TrainingItemDTO dto) {
    TrainingItemEntity entity = new TrainingItemEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<TrainingItemEntity> toTrainingItemEntities(List<TrainingItemDTO> dtos) {
    List<TrainingItemEntity> entities = new ArrayList<>();
    for (TrainingItemDTO dto : dtos) {
      entities.add(toTrainingItemEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO) {
    if (null == trainingItemPictureDTO) {
      return null;
    }
    TrainingItemPictureEntity entity = toTrainingItemPictureEntity(trainingItemPictureDTO);
    trainingItemPictureDTO = trainingItemPictureService.save(entity);
    return trainingItemPictureDTO.getId();
  }

  @Override
  public void batchSaveTrainingItemPicture(List<TrainingItemPictureDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<TrainingItemPictureEntity> entities = toTrainingItemPictureEntities(dtos);
    trainingItemPictureService.batchSave(entities);
  }

  @Override
  public int updateTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO) {
    trainingItemPictureDTO = trainingItemPictureService.updateBySql(trainingItemPictureDTO);
    return 1;
  }

  @Override
  public void batchUpdateTrainingItemPicture(List<TrainingItemPictureDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    trainingItemPictureService.batchUpdate(dtos);
  }

  @Override
  public int deleteTrainingItemPicture(long id) {
    return trainingItemPictureService.delete(id);
  }

  @Override
  public TrainingItemPictureDTO getTrainingItemPicture(long id) {
    return trainingItemPictureService.get(id);
  }

  @Override
  public TrainingItemPictureDTO getTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO) {
    return trainingItemPictureService.get(trainingItemPictureDTO);
  }

  @Override
  public List<TrainingItemPictureDTO> getTrainingItemPictureList(TrainingItemPictureDTO trainingItemPictureDTO) {
    return trainingItemPictureService.getSimpleList(trainingItemPictureDTO);
  }

  @Override
  public PageList<TrainingItemPictureDTO> getTrainingItemPictureListForPage(TrainingItemPictureDTO trainingItemPictureDTO, int pageNumber, int pageSize) {
    return trainingItemPictureService.getSimpleListForPage(trainingItemPictureDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<TrainingItemPictureDTO> getTrainingItemPictureListForPage(QueryParam queryParam) {
    return trainingItemPictureService.getSimpleListForPage(queryParam);
  }

  @Override
  public TrainingItemPictureEntity toTrainingItemPictureEntity(TrainingItemPictureDTO dto) {
    TrainingItemPictureEntity entity = new TrainingItemPictureEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<TrainingItemPictureEntity> toTrainingItemPictureEntities(List<TrainingItemPictureDTO> dtos) {
    List<TrainingItemPictureEntity> entities = new ArrayList<>();
    for (TrainingItemPictureDTO dto : dtos) {
      entities.add(toTrainingItemPictureEntity(dto));
    }
    return entities;
  }

  @Override
  public <T> List<T> getList(T dto) {
    return null;
  }

  @Override
  public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize) {
    return null;
  }

  @Override
  public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
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

  @Override
  public StudyAddressEntity toStudyAddressEntity(StudyAddressDTO dto) {
    StudyAddressEntity entity = new StudyAddressEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<StudyAddressEntity> toStudyAddressEntities(List<StudyAddressDTO> dtos) {
    List<StudyAddressEntity> entities = new ArrayList<>();
    for (StudyAddressDTO dto : dtos) {
      entities.add(toStudyAddressEntity(dto));
    }
    return entities;
  }

  @Override
  public void updateBySql(PromoterDTO dto) {
    this.promoterService.updateBySql(dto);
  }

  @Override
  public void updateBySql(StudyAddressDTO dto) {
    this.studyAddressService.updateBySql(dto);
  }

  @Override
  public void deleteTrainingItemPriceByItemId(Long itemId) {
    this.trainingItemPriceService.deleteTrainingItemPriceByItemId(itemId);
  }
}