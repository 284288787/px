/** create by auto at 2017-12-19 15:16:14**/
package com.booting.training.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.training.dto.ApplyDetailDTO;
import com.booting.training.entity.ApplyDetailEntity;
import com.booting.training.dto.ApplyInfoDTO;
import com.booting.training.dto.ApplyItemDTO;
import com.booting.training.dto.AttendanceDTO;
import com.booting.training.dto.KickbackDetailDTO;
import com.booting.training.dto.PhysicalClassCoachDTO;
import com.booting.training.dto.PhysicalClassDTO;
import com.booting.training.dto.PromoterDTO;
import com.booting.training.dto.StudyAddressDTO;
import com.booting.training.entity.ApplyInfoEntity;
import com.booting.training.entity.ApplyItemEntity;
import com.booting.training.entity.AttendanceEntity;
import com.booting.training.entity.KickbackDetailEntity;
import com.booting.training.entity.PhysicalClassCoachEntity;
import com.booting.training.entity.PhysicalClassEntity;
import com.booting.training.entity.PromoterEntity;
import com.booting.training.entity.StudyAddressEntity;
import com.booting.training.dto.TrainingItemDTO;
import com.booting.training.entity.TrainingItemEntity;
import com.booting.training.dto.TrainingItemPictureDTO;
import com.booting.training.dto.TrainingItemPriceDTO;
import com.booting.training.entity.TrainingItemPictureEntity;
import com.booting.training.entity.TrainingItemPriceEntity;

public interface TrainingFacade extends Serializable {

  /**
   * 新增 体测课教练
   */
  public Long savePhysicalClassCoach(PhysicalClassCoachDTO physicalClassCoachDTO);

  /**
   * 批量新增 体测课教练
   */
  public void batchSavePhysicalClassCoach(List<PhysicalClassCoachDTO> dtos);

  /**
   * 更新 体测课教练
   */
  public int updatePhysicalClassCoach(PhysicalClassCoachDTO physicalClassCoachDTO);

  /**
   * 批量 体测课教练
   */
  public void batchUpdatePhysicalClassCoach(List<PhysicalClassCoachDTO> dtos);

  /**
   * 删除 体测课教练
   */
  public int deletePhysicalClassCoach(long id);

  /**
   * 根据主键获取 体测课教练
   */
  public PhysicalClassCoachDTO getPhysicalClassCoach(long id);

  /**
   * 根据条件获取一条 体测课教练
   */
  public PhysicalClassCoachDTO getPhysicalClassCoach(PhysicalClassCoachDTO physicalClassCoachDTO);

  /**
   * 查询满足条件的 体测课教练 列表(单表)
   */
  public List<PhysicalClassCoachDTO> getPhysicalClassCoachList(PhysicalClassCoachDTO physicalClassCoachDTO);

  /**
   * 查询满足条件的 体测课教练 列表(分页)(单表)
   */
  public PageList<PhysicalClassCoachDTO> getPhysicalClassCoachListForPage(PhysicalClassCoachDTO physicalClassCoachDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 体测课教练 列表(分页)(单表)
   */
  public PageList<PhysicalClassCoachDTO> getPhysicalClassCoachListForPage(QueryParam queryParam);

  /**
   * 体测课教练DTO 转换成 Entity
   */
  public PhysicalClassCoachEntity toPhysicalClassCoachEntity(PhysicalClassCoachDTO physicalClassCoachDTO);

  /**
   * 体测课教练DTOs 转换成 Entities
   */
  public List<PhysicalClassCoachEntity> toPhysicalClassCoachEntities(List<PhysicalClassCoachDTO> dtoes);

  /**
   * 新增 签到
   */
  public Long saveAttendance(AttendanceDTO attendanceDTO);

  /**
   * 批量新增 签到
   */
  public void batchSaveAttendance(List<AttendanceDTO> dtos);

  /**
   * 更新 签到
   */
  public int updateAttendance(AttendanceDTO attendanceDTO);

  /**
   * 批量 签到
   */
  public void batchUpdateAttendance(List<AttendanceDTO> dtos);

  /**
   * 删除 签到
   */
  public int deleteAttendance(long id);

  /**
   * 根据主键获取 签到
   */
  public AttendanceDTO getAttendance(long id);

  /**
   * 根据条件获取一条 签到
   */
  public AttendanceDTO getAttendance(AttendanceDTO attendanceDTO);

  /**
   * 查询满足条件的 签到 列表(单表)
   */
  public List<AttendanceDTO> getAttendanceList(AttendanceDTO attendanceDTO);

  /**
   * 查询满足条件的 签到 列表(分页)(单表)
   */
  public PageList<AttendanceDTO> getAttendanceListForPage(AttendanceDTO attendanceDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 签到 列表(分页)(单表)
   */
  public PageList<AttendanceDTO> getAttendanceListForPage(QueryParam queryParam);

  /**
   * 签到DTO 转换成 Entity
   */
  public AttendanceEntity toAttendanceEntity(AttendanceDTO attendanceDTO);

  /**
   * 签到DTOs 转换成 Entities
   */
  public List<AttendanceEntity> toAttendanceEntities(List<AttendanceDTO> dtoes);

  /**
   * 新增 体测课
   */
  public Long savePhysicalClass(PhysicalClassDTO physicalClassDTO);

  /**
   * 批量新增 体测课
   */
  public void batchSavePhysicalClass(List<PhysicalClassDTO> dtos);

  /**
   * 更新 体测课
   */
  public int updatePhysicalClass(PhysicalClassDTO physicalClassDTO);

  /**
   * 批量 体测课
   */
  public void batchUpdatePhysicalClass(List<PhysicalClassDTO> dtos);

  /**
   * 删除 体测课
   */
  public int deletePhysicalClass(long physicalClassId);

  /**
   * 根据主键获取 体测课
   */
  public PhysicalClassDTO getPhysicalClass(long physicalClassId);

  /**
   * 根据条件获取一条 体测课
   */
  public PhysicalClassDTO getPhysicalClass(PhysicalClassDTO physicalClassDTO);

  /**
   * 查询满足条件的 体测课 列表(单表)
   */
  public List<PhysicalClassDTO> getPhysicalClassList(PhysicalClassDTO physicalClassDTO);

  /**
   * 查询满足条件的 体测课 列表(分页)(单表)
   */
  public PageList<PhysicalClassDTO> getPhysicalClassListForPage(PhysicalClassDTO physicalClassDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 体测课 列表(分页)(单表)
   */
  public PageList<PhysicalClassDTO> getPhysicalClassListForPage(QueryParam queryParam);

  /**
   * 体测课DTO 转换成 Entity
   */
  public PhysicalClassEntity toPhysicalClassEntity(PhysicalClassDTO physicalClassDTO);

  /**
   * 体测课DTOs 转换成 Entities
   */
  public List<PhysicalClassEntity> toPhysicalClassEntities(List<PhysicalClassDTO> dtoes);

  /**
   * 新增 回扣发放明细
   */
  public Long saveKickbackDetail(KickbackDetailDTO kickbackDetailDTO);

  /**
   * 批量新增 回扣发放明细
   */
  public void batchSaveKickbackDetail(List<KickbackDetailDTO> dtos);

  /**
   * 更新 回扣发放明细
   */
  public int updateKickbackDetail(KickbackDetailDTO kickbackDetailDTO);

  /**
   * 批量 回扣发放明细
   */
  public void batchUpdateKickbackDetail(List<KickbackDetailDTO> dtos);

  /**
   * 删除 回扣发放明细
   */
  public int deleteKickbackDetail(long id);

  /**
   * 根据主键获取 回扣发放明细
   */
  public KickbackDetailDTO getKickbackDetail(long id);

  /**
   * 根据条件获取一条 回扣发放明细
   */
  public KickbackDetailDTO getKickbackDetail(KickbackDetailDTO kickbackDetailDTO);

  /**
   * 查询满足条件的 回扣发放明细 列表(单表)
   */
  public List<KickbackDetailDTO> getKickbackDetailList(KickbackDetailDTO kickbackDetailDTO);

  /**
   * 查询满足条件的 回扣发放明细 列表(分页)(单表)
   */
  public PageList<KickbackDetailDTO> getKickbackDetailListForPage(KickbackDetailDTO kickbackDetailDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 回扣发放明细 列表(分页)(单表)
   */
  public PageList<KickbackDetailDTO> getKickbackDetailListForPage(QueryParam queryParam);

  /**
   * 回扣发放明细DTO 转换成 Entity
   */
  public KickbackDetailEntity toKickbackDetailEntity(KickbackDetailDTO kickbackDetailDTO);

  /**
   * 回扣发放明细DTOs 转换成 Entities
   */
  public List<KickbackDetailEntity> toKickbackDetailEntities(List<KickbackDetailDTO> dtoes);

  /**
   * 新增 报名项目
   */
  public Long saveApplyItem(ApplyItemDTO applyItemDTO);

  /**
   * 批量新增 报名项目
   */
  public void batchSaveApplyItem(List<ApplyItemDTO> dtos);

  /**
   * 更新 报名项目
   */
  public int updateApplyItem(ApplyItemDTO applyItemDTO);

  /**
   * 批量 报名项目
   */
  public void batchUpdateApplyItem(List<ApplyItemDTO> dtos);

  /**
   * 删除 报名项目
   */
  public int deleteApplyItem(long applyItemId);

  /**
   * 根据主键获取 报名项目
   */
  public ApplyItemDTO getApplyItem(long applyItemId);

  /**
   * 根据条件获取一条 报名项目
   */
  public ApplyItemDTO getApplyItem(ApplyItemDTO applyItemDTO);

  /**
   * 查询满足条件的 报名项目 列表(单表)
   */
  public List<ApplyItemDTO> getApplyItemList(ApplyItemDTO applyItemDTO);

  /**
   * 查询满足条件的 报名项目 列表(分页)(单表)
   */
  public PageList<ApplyItemDTO> getApplyItemListForPage(ApplyItemDTO applyItemDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 报名项目 列表(分页)(单表)
   */
  public PageList<ApplyItemDTO> getApplyItemListForPage(QueryParam queryParam);

  /**
   * 报名项目DTO 转换成 Entity
   */
  public ApplyItemEntity toApplyItemEntity(ApplyItemDTO applyItemDTO);

  /**
   * 报名项目DTOs 转换成 Entities
   */
  public List<ApplyItemEntity> toApplyItemEntities(List<ApplyItemDTO> dtoes);

  /**
   * 新增 培训项目
   */
  public Long saveTrainingItemPrice(TrainingItemPriceDTO trainingItemPriceDTO);

  /**
   * 批量新增 培训项目
   */
  public void batchSaveTrainingItemPrice(List<TrainingItemPriceDTO> dtos);

  /**
   * 更新 培训项目
   */
  public int updateTrainingItemPrice(TrainingItemPriceDTO trainingItemPriceDTO);

  /**
   * 批量 培训项目
   */
  public void batchUpdateTrainingItemPrice(List<TrainingItemPriceDTO> dtos);

  /**
   * 删除 培训项目
   */
  public int deleteTrainingItemPrice(long id);

  /**
   * 根据主键获取 培训项目
   */
  public TrainingItemPriceDTO getTrainingItemPrice(long id);

  /**
   * 根据条件获取一条 培训项目
   */
  public TrainingItemPriceDTO getTrainingItemPrice(TrainingItemPriceDTO trainingItemPriceDTO);

  /**
   * 查询满足条件的 培训项目 列表(单表)
   */
  public List<TrainingItemPriceDTO> getTrainingItemPriceList(TrainingItemPriceDTO trainingItemPriceDTO);

  /**
   * 查询满足条件的 培训项目 列表(分页)(单表)
   */
  public PageList<TrainingItemPriceDTO> getTrainingItemPriceListForPage(TrainingItemPriceDTO trainingItemPriceDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 培训项目 列表(分页)(单表)
   */
  public PageList<TrainingItemPriceDTO> getTrainingItemPriceListForPage(QueryParam queryParam);

  /**
   * 培训项目DTO 转换成 Entity
   */
  public TrainingItemPriceEntity toTrainingItemPriceEntity(TrainingItemPriceDTO trainingItemPriceDTO);

  /**
   * 培训项目DTOs 转换成 Entities
   */
  public List<TrainingItemPriceEntity> toTrainingItemPriceEntities(List<TrainingItemPriceDTO> dtoes);

  /**
   * 新增 培训团体报名，成员信息
   */
  public Long saveApplyDetail(ApplyDetailDTO applyDetailDTO);

  /**
   * 批量新增 培训团体报名，成员信息
   */
  public void batchSaveApplyDetail(List<ApplyDetailDTO> dtos);

  /**
   * 更新 培训团体报名，成员信息
   */
  public int updateApplyDetail(ApplyDetailDTO applyDetailDTO);

  /**
   * 批量 培训团体报名，成员信息
   */
  public void batchUpdateApplyDetail(List<ApplyDetailDTO> dtos);

  /**
   * 删除 培训团体报名，成员信息
   */
  public int deleteApplyDetail(long id);

  /**
   * 根据主键获取 培训团体报名，成员信息
   */
  public ApplyDetailDTO getApplyDetail(long id);

  /**
   * 根据条件获取一条 培训团体报名，成员信息
   */
  public ApplyDetailDTO getApplyDetail(ApplyDetailDTO applyDetailDTO);

  /**
   * 查询满足条件的 培训团体报名，成员信息 列表(单表)
   */
  public List<ApplyDetailDTO> getApplyDetailList(ApplyDetailDTO applyDetailDTO);

  /**
   * 查询满足条件的 培训团体报名，成员信息 列表(分页)(单表)
   */
  public PageList<ApplyDetailDTO> getApplyDetailListForPage(ApplyDetailDTO applyDetailDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 培训团体报名，成员信息 列表(分页)(单表)
   */
  public PageList<ApplyDetailDTO> getApplyDetailListForPage(QueryParam queryParam);

  /**
   * 培训团体报名，成员信息DTO 转换成 Entity
   */
  public ApplyDetailEntity toApplyDetailEntity(ApplyDetailDTO applyDetailDTO);

  /**
   * 培训团体报名，成员信息DTOs 转换成 Entities
   */
  public List<ApplyDetailEntity> toApplyDetailEntities(List<ApplyDetailDTO> dtoes);

  /**
   * 新增 培训报名信息
   */
  public Long saveApplyInfo(ApplyInfoDTO applyInfoDTO);

  /**
   * 批量新增 培训报名信息
   */
  public void batchSaveApplyInfo(List<ApplyInfoDTO> dtos);

  /**
   * 更新 培训报名信息
   */
  public int updateApplyInfo(ApplyInfoDTO applyInfoDTO);

  /**
   * 批量 培训报名信息
   */
  public void batchUpdateApplyInfo(List<ApplyInfoDTO> dtos);

  /**
   * 删除 培训报名信息
   */
  public int deleteApplyInfo(long applyId);

  /**
   * 根据主键获取 培训报名信息
   */
  public ApplyInfoDTO getApplyInfo(long applyId);

  /**
   * 根据条件获取一条 培训报名信息
   */
  public ApplyInfoDTO getApplyInfo(ApplyInfoDTO applyInfoDTO);

  /**
   * 查询满足条件的 培训报名信息 列表(单表)
   */
  public List<ApplyInfoDTO> getApplyInfoList(ApplyInfoDTO applyInfoDTO);

  /**
   * 查询满足条件的 培训报名信息 列表(分页)(单表)
   */
  public PageList<ApplyInfoDTO> getApplyInfoListForPage(ApplyInfoDTO applyInfoDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 培训报名信息 列表(分页)(单表)
   */
  public PageList<ApplyInfoDTO> getApplyInfoListForPage(QueryParam queryParam);

  /**
   * 培训报名信息DTO 转换成 Entity
   */
  public ApplyInfoEntity toApplyInfoEntity(ApplyInfoDTO applyInfoDTO);

  /**
   * 培训报名信息DTOs 转换成 Entities
   */
  public List<ApplyInfoEntity> toApplyInfoEntities(List<ApplyInfoDTO> dtoes);

  /**
   * 新增 培训项目
   */
  public Long saveTrainingItem(TrainingItemDTO trainingItemDTO);

  /**
   * 批量新增 培训项目
   */
  public void batchSaveTrainingItem(List<TrainingItemDTO> dtos);

  /**
   * 更新 培训项目
   */
  public int updateTrainingItem(TrainingItemDTO trainingItemDTO);

  /**
   * 批量 培训项目
   */
  public void batchUpdateTrainingItem(List<TrainingItemDTO> dtos);

  /**
   * 删除 培训项目
   */
  public int deleteTrainingItem(long itemId);

  /**
   * 根据主键获取 培训项目
   */
  public TrainingItemDTO getTrainingItem(long itemId);

  /**
   * 根据条件获取一条 培训项目
   */
  public TrainingItemDTO getTrainingItem(TrainingItemDTO trainingItemDTO);

  /**
   * 查询满足条件的 培训项目 列表(单表)
   */
  public List<TrainingItemDTO> getTrainingItemList(TrainingItemDTO trainingItemDTO);

  /**
   * 查询满足条件的 培训项目 列表(分页)(单表)
   */
  public PageList<TrainingItemDTO> getTrainingItemListForPage(TrainingItemDTO trainingItemDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 培训项目 列表(分页)(单表)
   */
  public PageList<TrainingItemDTO> getTrainingItemListForPage(QueryParam queryParam);

  /**
   * 培训项目DTO 转换成 Entity
   */
  public TrainingItemEntity toTrainingItemEntity(TrainingItemDTO trainingItemDTO);

  /**
   * 培训项目DTOs 转换成 Entities
   */
  public List<TrainingItemEntity> toTrainingItemEntities(List<TrainingItemDTO> dtoes);

  /**
   * 新增 培训项目 海报
   */
  public Long saveTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO);

  /**
   * 批量新增 培训项目 海报
   */
  public void batchSaveTrainingItemPicture(List<TrainingItemPictureDTO> dtos);

  /**
   * 更新 培训项目 海报
   */
  public int updateTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO);

  /**
   * 批量 培训项目 海报
   */
  public void batchUpdateTrainingItemPicture(List<TrainingItemPictureDTO> dtos);

  /**
   * 删除 培训项目 海报
   */
  public int deleteTrainingItemPicture(long id);

  /**
   * 根据主键获取 培训项目 海报
   */
  public TrainingItemPictureDTO getTrainingItemPicture(long id);

  /**
   * 根据条件获取一条 培训项目 海报
   */
  public TrainingItemPictureDTO getTrainingItemPicture(TrainingItemPictureDTO trainingItemPictureDTO);

  /**
   * 查询满足条件的 培训项目 海报 列表(单表)
   */
  public List<TrainingItemPictureDTO> getTrainingItemPictureList(TrainingItemPictureDTO trainingItemPictureDTO);

  /**
   * 查询满足条件的 培训项目 海报 列表(分页)(单表)
   */
  public PageList<TrainingItemPictureDTO> getTrainingItemPictureListForPage(TrainingItemPictureDTO trainingItemPictureDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 培训项目 海报 列表(分页)(单表)
   */
  public PageList<TrainingItemPictureDTO> getTrainingItemPictureListForPage(QueryParam queryParam);

  /**
   * 培训项目 海报DTO 转换成 Entity
   */
  public TrainingItemPictureEntity toTrainingItemPictureEntity(TrainingItemPictureDTO trainingItemPictureDTO);

  /**
   * 培训项目 海报DTOs 转换成 Entities
   */
  public List<TrainingItemPictureEntity> toTrainingItemPictureEntities(List<TrainingItemPictureDTO> dtoes);

  /**
   * 查询满足条件的 列表(多表)
   */
  public <T> List<T> getList(T dto);

  /**
   * 查询满足条件的列表(分页)(多表)
   */
  public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize);

  /**
   * 查询满足条件的列表(分页)(多表)
   */
  public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz);

  public void deleteTrainingItemPictureByItemId(Long itemId);

  public TrainingItemDTO updateBySql(TrainingItemDTO dto);

  /**
   * 新增 培训上课校区
   */
  public Long saveStudyAddress(StudyAddressDTO studyAddressDTO);

  /**
   * 批量新增 培训上课校区
   */
  public void batchSaveStudyAddress(List<StudyAddressDTO> dtos);

  /**
   * 更新 培训上课校区
   */
  public int updateStudyAddress(StudyAddressDTO studyAddressDTO);

  /**
   * 批量 培训上课校区
   */
  public void batchUpdateStudyAddress(List<StudyAddressDTO> dtos);

  /**
   * 删除 培训上课校区
   */
  public int deleteStudyAddress(long addrId);

  /**
   * 根据主键获取 培训上课校区
   */
  public StudyAddressDTO getStudyAddress(long addrId);

  /**
   * 根据条件获取一条 培训上课校区
   */
  public StudyAddressDTO getStudyAddress(StudyAddressDTO studyAddressDTO);

  /**
   * 查询满足条件的 培训上课校区 列表(单表)
   */
  public List<StudyAddressDTO> getStudyAddressList(StudyAddressDTO studyAddressDTO);

  /**
   * 查询满足条件的 培训上课校区 列表(分页)(单表)
   */
  public PageList<StudyAddressDTO> getStudyAddressListForPage(StudyAddressDTO studyAddressDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 培训上课校区 列表(分页)(单表)
   */
  public PageList<StudyAddressDTO> getStudyAddressListForPage(QueryParam queryParam);

  /**
   * 培训上课校区DTO 转换成 Entity
   */
  public StudyAddressEntity toStudyAddressEntity(StudyAddressDTO studyAddressDTO);

  /**
   * 培训上课校区DTOs 转换成 Entities
   */
  public List<StudyAddressEntity> toStudyAddressEntities(List<StudyAddressDTO> dtoes);

  /**
   * 新增 推广员
   */
  public Long savePromoter(PromoterDTO promoterDTO);

  /**
   * 批量新增 推广员
   */
  public void batchSavePromoter(List<PromoterDTO> dtos);

  /**
   * 更新 推广员
   */
  public int updatePromoter(PromoterDTO promoterDTO);

  /**
   * 批量 推广员
   */
  public void batchUpdatePromoter(List<PromoterDTO> dtos);

  /**
   * 删除 推广员
   */
  public int deletePromoter(long promoterId);

  /**
   * 根据主键获取 推广员
   */
  public PromoterDTO getPromoter(long promoterId);

  /**
   * 根据条件获取一条 推广员
   */
  public PromoterDTO getPromoter(PromoterDTO promoterDTO);

  /**
   * 查询满足条件的 推广员 列表(单表)
   */
  public List<PromoterDTO> getPromoterList(PromoterDTO promoterDTO);

  /**
   * 查询满足条件的 推广员 列表(分页)(单表)
   */
  public PageList<PromoterDTO> getPromoterListForPage(PromoterDTO promoterDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 推广员 列表(分页)(单表)
   */
  public PageList<PromoterDTO> getPromoterListForPage(QueryParam queryParam);

  /**
   * 推广员DTO 转换成 Entity
   */
  public PromoterEntity toPromoterEntity(PromoterDTO promoterDTO);

  /**
   * 推广员DTOs 转换成 Entities
   */
  public List<PromoterEntity> toPromoterEntities(List<PromoterDTO> dtoes);

  public void updateBySql(PromoterDTO dto);

  public void updateBySql(StudyAddressDTO dto);

  public void deleteTrainingItemPriceByItemId(Long itemId);

  public void updateBySql(ApplyItemDTO dto);

  public KickbackDetailDTO getLatestKickbackDetail(Long promoterId);

  public Map<String, Object> totalMoneyByPromoter(Long promoterId, Date beginDatePoint);

  public void updateBySql(PhysicalClassDTO dto);

  public void deletePhysicalClassCoachByClassId(Long physicalClassId);

}