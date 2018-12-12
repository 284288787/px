/** create by auto at 2018-06-21 14:14:57**/
package com.booting.product.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.product.dto.ActivityDTO;
import com.booting.product.entity.ActivityEntity;
import com.booting.product.dto.CurriculumDTO;
import com.booting.product.entity.CurriculumEntity;
import com.booting.product.dto.EquipmentDTO;
import com.booting.product.dto.ProductPictureDTO;
import com.booting.product.entity.EquipmentEntity;
import com.booting.product.entity.ProductPictureEntity;
import com.booting.product.dto.ProductTypeDTO;
import com.booting.product.entity.ProductTypeEntity;

public interface ProductFacade extends Serializable {

	/**
	 * 新增 活动
	 */
	public Long saveActivity(ActivityDTO activityDTO);

	/**
	 * 批量新增 活动
	 */
	public void batchSaveActivity(List<ActivityDTO> dtos);

	/**
	 * 更新 活动
	 */
	public int updateActivity(ActivityDTO activityDTO);

	/**
	 * 批量 活动
	 */
	public void batchUpdateActivity(List<ActivityDTO> dtos);

	/**
	 * 删除 活动
	 */
	public int deleteActivity(long activityId);

	/**
	 * 根据主键获取 活动
	 */
	public ActivityDTO getActivity(long activityId);

	/**
	 * 根据条件获取一条 活动
	 */
	public ActivityDTO getActivity(ActivityDTO activityDTO);

	/**
	 * 查询满足条件的 活动 列表(单表)
	 */
	public List<ActivityDTO> getActivityList(ActivityDTO activityDTO);

	/**
	 * 查询满足条件的 活动 列表(分页)(单表)
	 */
	public PageList<ActivityDTO> getActivityListForPage(ActivityDTO activityDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 活动 列表(分页)(单表)
	 */
	public PageList<ActivityDTO> getActivityListForPage(QueryParam queryParam);

	/**
	 * 活动DTO 转换成 Entity
	 */
	public ActivityEntity toActivityEntity(ActivityDTO activityDTO);

	/**
	 * 活动DTOs 转换成 Entities
	 */
	public List<ActivityEntity> toActivityEntities(List<ActivityDTO> dtoes);

	/**
	 * 新增 课程
	 */
	public Long saveCurriculum(CurriculumDTO curriculumDTO);

	/**
	 * 批量新增 课程
	 */
	public void batchSaveCurriculum(List<CurriculumDTO> dtos);

	/**
	 * 更新 课程
	 */
	public int updateCurriculum(CurriculumDTO curriculumDTO);

	/**
	 * 批量 课程
	 */
	public void batchUpdateCurriculum(List<CurriculumDTO> dtos);

	/**
	 * 删除 课程
	 */
	public int deleteCurriculum(long curriculumId);

	/**
	 * 根据主键获取 课程
	 */
	public CurriculumDTO getCurriculum(long curriculumId);

	/**
	 * 根据条件获取一条 课程
	 */
	public CurriculumDTO getCurriculum(CurriculumDTO curriculumDTO);

	/**
	 * 查询满足条件的 课程 列表(单表)
	 */
	public List<CurriculumDTO> getCurriculumList(CurriculumDTO curriculumDTO);

	/**
	 * 查询满足条件的 课程 列表(分页)(单表)
	 */
	public PageList<CurriculumDTO> getCurriculumListForPage(CurriculumDTO curriculumDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 课程 列表(分页)(单表)
	 */
	public PageList<CurriculumDTO> getCurriculumListForPage(QueryParam queryParam);

	/**
	 * 课程DTO 转换成 Entity
	 */
	public CurriculumEntity toCurriculumEntity(CurriculumDTO curriculumDTO);

	/**
	 * 课程DTOs 转换成 Entities
	 */
	public List<CurriculumEntity> toCurriculumEntities(List<CurriculumDTO> dtoes);

	/**
	 * 新增 设备
	 */
	public Long saveEquipment(EquipmentDTO equipmentDTO);

	/**
	 * 批量新增 设备
	 */
	public void batchSaveEquipment(List<EquipmentDTO> dtos);

	/**
	 * 更新 设备
	 */
	public int updateEquipment(EquipmentDTO equipmentDTO);

	/**
	 * 批量 设备
	 */
	public void batchUpdateEquipment(List<EquipmentDTO> dtos);

	/**
	 * 删除 设备
	 */
	public int deleteEquipment(long equipmentId);

	/**
	 * 根据主键获取 设备
	 */
	public EquipmentDTO getEquipment(long equipmentId);

	/**
	 * 根据条件获取一条 设备
	 */
	public EquipmentDTO getEquipment(EquipmentDTO equipmentDTO);

	/**
	 * 查询满足条件的 设备 列表(单表)
	 */
	public List<EquipmentDTO> getEquipmentList(EquipmentDTO equipmentDTO);

	/**
	 * 查询满足条件的 设备 列表(分页)(单表)
	 */
	public PageList<EquipmentDTO> getEquipmentListForPage(EquipmentDTO equipmentDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 设备 列表(分页)(单表)
	 */
	public PageList<EquipmentDTO> getEquipmentListForPage(QueryParam queryParam);

	/**
	 * 设备DTO 转换成 Entity
	 */
	public EquipmentEntity toEquipmentEntity(EquipmentDTO equipmentDTO);

	/**
	 * 设备DTOs 转换成 Entities
	 */
	public List<EquipmentEntity> toEquipmentEntities(List<EquipmentDTO> dtoes);

	/**
	 * 新增 产品类型
	 */
	public Long saveProductType(ProductTypeDTO productTypeDTO);

	/**
	 * 批量新增 产品类型
	 */
	public void batchSaveProductType(List<ProductTypeDTO> dtos);

	/**
	 * 更新 产品类型
	 */
	public int updateProductType(ProductTypeDTO productTypeDTO);

	/**
	 * 批量 产品类型
	 */
	public void batchUpdateProductType(List<ProductTypeDTO> dtos);

	/**
	 * 删除 产品类型
	 */
	public int deleteProductType(long typeId);

	/**
	 * 根据主键获取 产品类型
	 */
	public ProductTypeDTO getProductType(long typeId);

	/**
	 * 根据条件获取一条 产品类型
	 */
	public ProductTypeDTO getProductType(ProductTypeDTO productTypeDTO);

	/**
	 * 查询满足条件的 产品类型 列表(单表)
	 */
	public List<ProductTypeDTO> getProductTypeList(ProductTypeDTO productTypeDTO);

	/**
	 * 查询满足条件的 产品类型 列表(分页)(单表)
	 */
	public PageList<ProductTypeDTO> getProductTypeListForPage(ProductTypeDTO productTypeDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 产品类型 列表(分页)(单表)
	 */
	public PageList<ProductTypeDTO> getProductTypeListForPage(QueryParam queryParam);

	/**
	 * 产品类型DTO 转换成 Entity
	 */
	public ProductTypeEntity toProductTypeEntity(ProductTypeDTO productTypeDTO);

	/**
	 * 产品类型DTOs 转换成 Entities
	 */
	public List<ProductTypeEntity> toProductTypeEntities(List<ProductTypeDTO> dtoes);

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

  public void updateBySql(ProductTypeDTO dto);

  public void updateBySql(ActivityDTO dto);
  /**
   * 新增 产品图片
   */
  public Long saveProductPicture(ProductPictureDTO productPictureDTO);

  /**
   * 批量新增 产品图片
   */
  public void batchSaveProductPicture(List<ProductPictureDTO> dtos);

  /**
   * 更新 产品图片
   */
  public int updateProductPicture(ProductPictureDTO productPictureDTO);

  /**
   * 批量 产品图片
   */
  public void batchUpdateProductPicture(List<ProductPictureDTO> dtos);

  /**
   * 删除 产品图片
   */
  public int deleteProductPicture(long id);

  /**
   * 根据主键获取 产品图片
   */
  public ProductPictureDTO getProductPicture(long id);

  /**
   * 根据条件获取一条 产品图片
   */
  public ProductPictureDTO getProductPicture(ProductPictureDTO productPictureDTO);

  /**
   * 查询满足条件的 产品图片 列表(单表)
   */
  public List<ProductPictureDTO> getProductPictureList(ProductPictureDTO productPictureDTO);

  /**
   * 查询满足条件的 产品图片 列表(分页)(单表)
   */
  public PageList<ProductPictureDTO> getProductPictureListForPage(ProductPictureDTO productPictureDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 产品图片 列表(分页)(单表)
   */
  public PageList<ProductPictureDTO> getProductPictureListForPage(QueryParam queryParam);

  /**
   * 产品图片DTO 转换成 Entity
   */
  public ProductPictureEntity toProductPictureEntity(ProductPictureDTO productPictureDTO);

  /**
   * 产品图片DTOs 转换成 Entities
   */
  public List<ProductPictureEntity> toProductPictureEntities(List<ProductPictureDTO> dtoes);

  public void deleteProductPictureBy(int business, Long activityId);

  public void updateBySql(EquipmentDTO dto);

  public void updateBySql(CurriculumDTO dto);

}