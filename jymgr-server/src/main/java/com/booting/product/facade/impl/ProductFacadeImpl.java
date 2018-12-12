/** create by auto at 2018-06-21 14:14:57**/
package com.booting.product.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.product.facade.ProductFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

import com.booting.product.dto.ActivityDTO;
import com.booting.product.entity.ActivityEntity;
import com.booting.product.service.ActivityService;
import com.booting.product.dto.CurriculumDTO;
import com.booting.product.entity.CurriculumEntity;
import com.booting.product.service.CurriculumService;
import com.booting.product.dto.EquipmentDTO;
import com.booting.product.dto.ProductPictureDTO;
import com.booting.product.entity.EquipmentEntity;
import com.booting.product.entity.ProductPictureEntity;
import com.booting.product.service.EquipmentService;
import com.booting.product.service.ProductPictureService;
import com.booting.product.dto.ProductTypeDTO;
import com.booting.product.entity.ProductTypeEntity;
import com.booting.product.service.ProductTypeService;

@Service("productFacade")
public class ProductFacadeImpl implements ProductFacade {
  private static final long serialVersionUID = 1L;

  @Autowired
  private ActivityService activityService;

  @Autowired
  private CurriculumService curriculumService;

  @Autowired
  private EquipmentService equipmentService;
  
  @Autowired
  private ProductTypeService productTypeService;

  @Autowired
  private ProductPictureService productPictureService;

  @Override
  public Long saveActivity(ActivityDTO activityDTO) {
    if (null == activityDTO) {
      return null;
    }
    ActivityEntity entity = toActivityEntity(activityDTO);
    activityDTO = activityService.save(entity);
    return activityDTO.getActivityId();
  }

  @Override
  public void batchSaveActivity(List<ActivityDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<ActivityEntity> entities = toActivityEntities(dtos);
    activityService.batchSave(entities);
  }

  @Override
  public int updateActivity(ActivityDTO activityDTO) {
    activityDTO = activityService.updateBySql(activityDTO);
    return 1;
  }

  @Override
  public void batchUpdateActivity(List<ActivityDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    activityService.batchUpdate(dtos);
  }

  @Override
  public int deleteActivity(long activityId) {
    return activityService.delete(activityId);
  }

  @Override
  public ActivityDTO getActivity(long activityId) {
    return activityService.get(activityId);
  }

  @Override
  public ActivityDTO getActivity(ActivityDTO activityDTO) {
    return activityService.get(activityDTO);
  }

  @Override
  public List<ActivityDTO> getActivityList(ActivityDTO activityDTO) {
    return activityService.getSimpleList(activityDTO);
  }

  @Override
  public PageList<ActivityDTO> getActivityListForPage(ActivityDTO activityDTO, int pageNumber, int pageSize) {
    return activityService.getSimpleListForPage(activityDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<ActivityDTO> getActivityListForPage(QueryParam queryParam) {
    return activityService.getSimpleListForPage(queryParam);
  }

  @Override
  public ActivityEntity toActivityEntity(ActivityDTO dto) {
    ActivityEntity entity = new ActivityEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<ActivityEntity> toActivityEntities(List<ActivityDTO> dtos) {
    List<ActivityEntity> entities = new ArrayList<>();
    for (ActivityDTO dto : dtos) {
      entities.add(toActivityEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveCurriculum(CurriculumDTO curriculumDTO) {
    if (null == curriculumDTO) {
      return null;
    }
    CurriculumEntity entity = toCurriculumEntity(curriculumDTO);
    curriculumDTO = curriculumService.save(entity);
    return curriculumDTO.getCurriculumId();
  }

  @Override
  public void batchSaveCurriculum(List<CurriculumDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<CurriculumEntity> entities = toCurriculumEntities(dtos);
    curriculumService.batchSave(entities);
  }

  @Override
  public int updateCurriculum(CurriculumDTO curriculumDTO) {
    curriculumDTO = curriculumService.updateBySql(curriculumDTO);
    return 1;
  }

  @Override
  public void batchUpdateCurriculum(List<CurriculumDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    curriculumService.batchUpdate(dtos);
  }

  @Override
  public int deleteCurriculum(long curriculumId) {
    return curriculumService.delete(curriculumId);
  }

  @Override
  public CurriculumDTO getCurriculum(long curriculumId) {
    return curriculumService.get(curriculumId);
  }

  @Override
  public CurriculumDTO getCurriculum(CurriculumDTO curriculumDTO) {
    return curriculumService.get(curriculumDTO);
  }

  @Override
  public List<CurriculumDTO> getCurriculumList(CurriculumDTO curriculumDTO) {
    return curriculumService.getSimpleList(curriculumDTO);
  }

  @Override
  public PageList<CurriculumDTO> getCurriculumListForPage(CurriculumDTO curriculumDTO, int pageNumber, int pageSize) {
    return curriculumService.getSimpleListForPage(curriculumDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<CurriculumDTO> getCurriculumListForPage(QueryParam queryParam) {
    return curriculumService.getSimpleListForPage(queryParam);
  }

  @Override
  public CurriculumEntity toCurriculumEntity(CurriculumDTO dto) {
    CurriculumEntity entity = new CurriculumEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<CurriculumEntity> toCurriculumEntities(List<CurriculumDTO> dtos) {
    List<CurriculumEntity> entities = new ArrayList<>();
    for (CurriculumDTO dto : dtos) {
      entities.add(toCurriculumEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveEquipment(EquipmentDTO equipmentDTO) {
    if (null == equipmentDTO) {
      return null;
    }
    EquipmentEntity entity = toEquipmentEntity(equipmentDTO);
    equipmentDTO = equipmentService.save(entity);
    return equipmentDTO.getEquipmentId();
  }

  @Override
  public void batchSaveEquipment(List<EquipmentDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<EquipmentEntity> entities = toEquipmentEntities(dtos);
    equipmentService.batchSave(entities);
  }

  @Override
  public int updateEquipment(EquipmentDTO equipmentDTO) {
    equipmentDTO = equipmentService.updateBySql(equipmentDTO);
    return 1;
  }

  @Override
  public void batchUpdateEquipment(List<EquipmentDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    equipmentService.batchUpdate(dtos);
  }

  @Override
  public int deleteEquipment(long equipmentId) {
    return equipmentService.delete(equipmentId);
  }

  @Override
  public EquipmentDTO getEquipment(long equipmentId) {
    return equipmentService.get(equipmentId);
  }

  @Override
  public EquipmentDTO getEquipment(EquipmentDTO equipmentDTO) {
    return equipmentService.get(equipmentDTO);
  }

  @Override
  public List<EquipmentDTO> getEquipmentList(EquipmentDTO equipmentDTO) {
    return equipmentService.getSimpleList(equipmentDTO);
  }

  @Override
  public PageList<EquipmentDTO> getEquipmentListForPage(EquipmentDTO equipmentDTO, int pageNumber, int pageSize) {
    return equipmentService.getSimpleListForPage(equipmentDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<EquipmentDTO> getEquipmentListForPage(QueryParam queryParam) {
    return equipmentService.getSimpleListForPage(queryParam);
  }

  @Override
  public EquipmentEntity toEquipmentEntity(EquipmentDTO dto) {
    EquipmentEntity entity = new EquipmentEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<EquipmentEntity> toEquipmentEntities(List<EquipmentDTO> dtos) {
    List<EquipmentEntity> entities = new ArrayList<>();
    for (EquipmentDTO dto : dtos) {
      entities.add(toEquipmentEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveProductType(ProductTypeDTO productTypeDTO) {
    if (null == productTypeDTO) {
      return null;
    }
    ProductTypeEntity entity = toProductTypeEntity(productTypeDTO);
    productTypeDTO = productTypeService.save(entity);
    return productTypeDTO.getTypeId();
  }

  @Override
  public void batchSaveProductType(List<ProductTypeDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<ProductTypeEntity> entities = toProductTypeEntities(dtos);
    productTypeService.batchSave(entities);
  }

  @Override
  public int updateProductType(ProductTypeDTO productTypeDTO) {
    productTypeDTO = productTypeService.updateBySql(productTypeDTO);
    return 1;
  }

  @Override
  public void batchUpdateProductType(List<ProductTypeDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    productTypeService.batchUpdate(dtos);
  }

  @Override
  public int deleteProductType(long typeId) {
    return productTypeService.delete(typeId);
  }

  @Override
  public ProductTypeDTO getProductType(long typeId) {
    return productTypeService.get(typeId);
  }

  @Override
  public ProductTypeDTO getProductType(ProductTypeDTO productTypeDTO) {
    return productTypeService.get(productTypeDTO);
  }

  @Override
  public List<ProductTypeDTO> getProductTypeList(ProductTypeDTO productTypeDTO) {
    return productTypeService.getSimpleList(productTypeDTO);
  }

  @Override
  public PageList<ProductTypeDTO> getProductTypeListForPage(ProductTypeDTO productTypeDTO, int pageNumber, int pageSize) {
    return productTypeService.getSimpleListForPage(productTypeDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<ProductTypeDTO> getProductTypeListForPage(QueryParam queryParam) {
    return productTypeService.getSimpleListForPage(queryParam);
  }

  @Override
  public ProductTypeEntity toProductTypeEntity(ProductTypeDTO dto) {
    ProductTypeEntity entity = new ProductTypeEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<ProductTypeEntity> toProductTypeEntities(List<ProductTypeDTO> dtos) {
    List<ProductTypeEntity> entities = new ArrayList<>();
    for (ProductTypeDTO dto : dtos) {
      entities.add(toProductTypeEntity(dto));
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
  public void updateBySql(ProductTypeDTO dto) {
    this.productTypeService.updateBySql(dto);
  }

  @Override
  public void updateBySql(ActivityDTO dto) {
    this.activityService.updateBySql(dto);
  }

  @Override
  public Long saveProductPicture(ProductPictureDTO productPictureDTO) {
    if (null == productPictureDTO) {
      return null;
    }
    ProductPictureEntity entity = toProductPictureEntity(productPictureDTO);
    productPictureDTO = productPictureService.save(entity);
    return productPictureDTO.getId();
  }

  @Override
  public void batchSaveProductPicture(List<ProductPictureDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<ProductPictureEntity> entities = toProductPictureEntities(dtos);
    productPictureService.batchSave(entities);
  }

  @Override
  public int updateProductPicture(ProductPictureDTO productPictureDTO) {
    productPictureDTO = productPictureService.updateBySql(productPictureDTO);
    return 1;
  }

  @Override
  public void batchUpdateProductPicture(List<ProductPictureDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    productPictureService.batchUpdate(dtos);
  }

  @Override
  public int deleteProductPicture(long id) {
    return productPictureService.delete(id);
  }

  @Override
  public ProductPictureDTO getProductPicture(long id) {
    return productPictureService.get(id);
  }

  @Override
  public ProductPictureDTO getProductPicture(ProductPictureDTO productPictureDTO) {
    return productPictureService.get(productPictureDTO);
  }

  @Override
  public List<ProductPictureDTO> getProductPictureList(ProductPictureDTO productPictureDTO) {
    return productPictureService.getSimpleList(productPictureDTO);
  }

  @Override
  public PageList<ProductPictureDTO> getProductPictureListForPage(ProductPictureDTO productPictureDTO, int pageNumber, int pageSize) {
    return productPictureService.getSimpleListForPage(productPictureDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<ProductPictureDTO> getProductPictureListForPage(QueryParam queryParam) {
    return productPictureService.getSimpleListForPage(queryParam);
  }

  @Override
  public ProductPictureEntity toProductPictureEntity(ProductPictureDTO dto) {
    ProductPictureEntity entity = new ProductPictureEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<ProductPictureEntity> toProductPictureEntities(List<ProductPictureDTO> dtos) {
    List<ProductPictureEntity> entities = new ArrayList<>();
    for (ProductPictureDTO dto : dtos) {
      entities.add(toProductPictureEntity(dto));
    }
    return entities;
  }

  @Override
  public void deleteProductPictureBy(int business, Long activityId) {
    Map<String, Object> param = new HashMap<>();
    param.put("business", business);
    param.put("activityId", activityId);
    this.productPictureService.deleteBySql(param);
  }

  @Override
  public void updateBySql(EquipmentDTO dto) {
    this.equipmentService.updateBySql(dto);
  }

  @Override
  public void updateBySql(CurriculumDTO dto) {
    this.curriculumService.updateBySql(dto);
  }
}