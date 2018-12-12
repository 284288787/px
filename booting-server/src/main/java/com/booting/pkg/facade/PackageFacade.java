/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.facade;

import java.io.Serializable;
import java.util.List;

import com.booting.pkg.dto.AttributeDTO;
import com.booting.pkg.dto.PackageAttributeValueDTO;
import com.booting.pkg.dto.PackageDTO;
import com.booting.pkg.dto.PackageServiceRelationDTO;
import com.booting.pkg.dto.ServiceAttributeRelationDTO;
import com.booting.pkg.dto.ServiceDTO;
import com.booting.pkg.dto.UseServiceDetailDTO;
import com.booting.pkg.dto.UserAttributeDTO;
import com.booting.pkg.dto.UserPackageDTO;
import com.booting.pkg.dto.UserServiceDTO;
import com.booting.pkg.entity.AttributeEntity;
import com.booting.pkg.entity.PackageAttributeValueEntity;
import com.booting.pkg.entity.PackageEntity;
import com.booting.pkg.entity.PackageServiceRelationEntity;
import com.booting.pkg.entity.ServiceAttributeRelationEntity;
import com.booting.pkg.entity.ServiceEntity;
import com.booting.pkg.entity.UseServiceDetailEntity;
import com.booting.pkg.entity.UserAttributeEntity;
import com.booting.pkg.entity.UserPackageEntity;
import com.booting.pkg.entity.UserServiceEntity;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;

public interface PackageFacade extends Serializable {

	/**
	 * 新增 用户套餐
	 */
	public Long saveUserPackage(UserPackageDTO userPackageDTO);

	/**
	 * 批量新增 用户套餐
	 */
	public void batchSaveUserPackage(List<UserPackageDTO> dtos);

	/**
	 * 更新 用户套餐
	 */
	public int updateUserPackage(UserPackageDTO userPackageDTO);

	/**
	 * 批量 用户套餐
	 */
	public void batchUpdateUserPackage(List<UserPackageDTO> dtos);

	/**
	 * 删除 用户套餐
	 */
	public int deleteUserPackage(long upId);

	/**
	 * 根据主键获取 用户套餐
	 */
	public UserPackageDTO getUserPackage(long upId);

	/**
	 * 根据条件获取一条 用户套餐
	 */
	public UserPackageDTO getUserPackage(UserPackageDTO userPackageDTO);

	/**
	 * 查询满足条件的 用户套餐 列表(单表)
	 */
	public List<UserPackageDTO> getUserPackageList(UserPackageDTO userPackageDTO);

	/**
	 * 查询满足条件的 用户套餐 列表(分页)(单表)
	 */
	public PageList<UserPackageDTO> getUserPackageListForPage(UserPackageDTO userPackageDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户套餐 列表(分页)(单表)
	 */
	public PageList<UserPackageDTO> getUserPackageListForPage(QueryParam queryParam);

	/**
	 * 用户套餐DTO 转换成 Entity
	 */
	public UserPackageEntity toUserPackageEntity(UserPackageDTO userPackageDTO);

	/**
	 * 用户套餐DTOs 转换成 Entities
	 */
	public List<UserPackageEntity> toUserPackageEntities(List<UserPackageDTO> dtoes);

	/**
	 * 新增 属性
	 */
	public Long saveAttribute(AttributeDTO attributeDTO) throws ArgsException;

	/**
	 * 批量新增 属性
	 */
	public void batchSaveAttribute(List<AttributeDTO> dtos);

	/**
	 * 更新 属性
	 */
	public int updateAttribute(AttributeDTO attributeDTO) throws ArgsException;

	/**
	 * 批量 属性
	 */
	public void batchUpdateAttribute(List<AttributeDTO> dtos);

	/**
	 * 删除 属性
	 */
	public int deleteAttribute(long attributeId);

	/**
	 * 根据主键获取 属性
	 */
	public AttributeDTO getAttribute(long attributeId);

	/**
	 * 根据条件获取一条 属性
	 */
	public AttributeDTO getAttribute(AttributeDTO attributeDTO);

	/**
	 * 查询满足条件的 属性 列表(单表)
	 */
	public List<AttributeDTO> getAttributeList(AttributeDTO attributeDTO);

	/**
	 * 查询满足条件的 属性 列表(分页)(单表)
	 */
	public PageList<AttributeDTO> getAttributeListForPage(AttributeDTO attributeDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 属性 列表(分页)(单表)
	 */
	public PageList<AttributeDTO> getAttributeListForPage(QueryParam queryParam);

	/**
	 * 属性DTO 转换成 Entity
	 */
	public AttributeEntity toAttributeEntity(AttributeDTO attributeDTO);

	/**
	 * 属性DTOs 转换成 Entities
	 */
	public List<AttributeEntity> toAttributeEntities(List<AttributeDTO> dtoes);

	/**
	 * 新增 套餐属性值关系
	 */
	public Long savePackageAttributeValue(PackageAttributeValueDTO packageAttributeValueDTO);

	/**
	 * 批量新增 套餐属性值关系
	 */
	public void batchSavePackageAttributeValue(List<PackageAttributeValueEntity> dtos);

	/**
	 * 更新 套餐属性值关系
	 */
	public int updatePackageAttributeValue(PackageAttributeValueDTO packageAttributeValueDTO);

	/**
	 * 批量 套餐属性值关系
	 */
	public void batchUpdatePackageAttributeValue(List<PackageAttributeValueDTO> dtos);

	/**
	 * 删除 套餐属性值关系
	 */
	public int deletePackageAttributeValue(long id);

	/**
	 * 根据主键获取 套餐属性值关系
	 */
	public PackageAttributeValueDTO getPackageAttributeValue(long id);

	/**
	 * 根据条件获取一条 套餐属性值关系
	 */
	public PackageAttributeValueDTO getPackageAttributeValue(PackageAttributeValueDTO packageAttributeValueDTO);

	/**
	 * 查询满足条件的 套餐属性值关系 列表(单表)
	 */
	public List<PackageAttributeValueDTO> getPackageAttributeValueList(PackageAttributeValueDTO packageAttributeValueDTO);

	/**
	 * 查询满足条件的 套餐属性值关系 列表(分页)(单表)
	 */
	public PageList<PackageAttributeValueDTO> getPackageAttributeValueListForPage(PackageAttributeValueDTO packageAttributeValueDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 套餐属性值关系 列表(分页)(单表)
	 */
	public PageList<PackageAttributeValueDTO> getPackageAttributeValueListForPage(QueryParam queryParam);

	/**
	 * 套餐属性值关系DTO 转换成 Entity
	 */
	public PackageAttributeValueEntity toPackageAttributeValueEntity(PackageAttributeValueDTO packageAttributeValueDTO);

	/**
	 * 套餐属性值关系DTOs 转换成 Entities
	 */
	public List<PackageAttributeValueEntity> toPackageAttributeValueEntities(List<PackageAttributeValueDTO> dtoes);

	/**
	 * 新增 套餐
	 */
	public Long savePackage(PackageDTO packageDTO) throws ArgsException;

	/**
	 * 批量新增 套餐
	 */
	public void batchSavePackage(List<PackageDTO> dtos);

	/**
	 * 更新 套餐
	 */
	public int updatePackage(PackageDTO packageDTO) throws ArgsException;

	/**
	 * 批量 套餐
	 */
	public void batchUpdatePackage(List<PackageDTO> dtos);

	/**
	 * 删除 套餐
	 */
	public int deletePackage(long packageId);

	/**
	 * 根据主键获取 套餐
	 */
	public PackageDTO getPackage(long packageId);

	/**
	 * 根据条件获取一条 套餐
	 */
	public PackageDTO getPackage(PackageDTO packageDTO);

	/**
	 * 查询满足条件的 套餐 列表(单表)
	 */
	public List<PackageDTO> getPackageList(PackageDTO packageDTO);

	/**
	 * 查询满足条件的 套餐 列表(分页)(单表)
	 */
	public PageList<PackageDTO> getPackageListForPage(PackageDTO packageDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 套餐 列表(分页)(单表)
	 */
	public PageList<PackageDTO> getPackageListForPage(QueryParam queryParam);

	/**
	 * 套餐DTO 转换成 Entity
	 */
	public PackageEntity toPackageEntity(PackageDTO packageDTO);

	/**
	 * 套餐DTOs 转换成 Entities
	 */
	public List<PackageEntity> toPackageEntities(List<PackageDTO> dtoes);

	/**
	 * 新增 套餐服务关系
	 */
	public Long savePackageServiceRelation(PackageServiceRelationDTO packageServiceRelationDTO);

	/**
	 * 批量新增 套餐服务关系
	 */
	public void batchSavePackageServiceRelation(List<PackageServiceRelationEntity> dtos);

	/**
	 * 更新 套餐服务关系
	 */
	public int updatePackageServiceRelation(PackageServiceRelationDTO packageServiceRelationDTO);

	/**
	 * 批量 套餐服务关系
	 */
	public void batchUpdatePackageServiceRelation(List<PackageServiceRelationDTO> dtos);

	/**
	 * 删除 套餐服务关系
	 */
	public int deletePackageServiceRelation(long id);

	/**
	 * 根据主键获取 套餐服务关系
	 */
	public PackageServiceRelationDTO getPackageServiceRelation(long id);

	/**
	 * 根据条件获取一条 套餐服务关系
	 */
	public PackageServiceRelationDTO getPackageServiceRelation(PackageServiceRelationDTO packageServiceRelationDTO);

	/**
	 * 查询满足条件的 套餐服务关系 列表(单表)
	 */
	public List<PackageServiceRelationDTO> getPackageServiceRelationList(PackageServiceRelationDTO packageServiceRelationDTO);

	/**
	 * 查询满足条件的 套餐服务关系 列表(分页)(单表)
	 */
	public PageList<PackageServiceRelationDTO> getPackageServiceRelationListForPage(PackageServiceRelationDTO packageServiceRelationDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 套餐服务关系 列表(分页)(单表)
	 */
	public PageList<PackageServiceRelationDTO> getPackageServiceRelationListForPage(QueryParam queryParam);

	/**
	 * 套餐服务关系DTO 转换成 Entity
	 */
	public PackageServiceRelationEntity toPackageServiceRelationEntity(PackageServiceRelationDTO packageServiceRelationDTO);

	/**
	 * 套餐服务关系DTOs 转换成 Entities
	 */
	public List<PackageServiceRelationEntity> toPackageServiceRelationEntities(List<PackageServiceRelationDTO> dtoes);

	/**
	 * 新增 服务属性关系
	 */
	public Long saveServiceAttributeRelation(ServiceAttributeRelationDTO serviceAttributeRelationDTO);

	/**
	 * 批量新增 服务属性关系
	 */
	public void batchSaveServiceAttributeRelation(List<ServiceAttributeRelationEntity> dtos);

	/**
	 * 更新 服务属性关系
	 */
	public int updateServiceAttributeRelation(ServiceAttributeRelationDTO serviceAttributeRelationDTO);

	/**
	 * 批量 服务属性关系
	 */
	public void batchUpdateServiceAttributeRelation(List<ServiceAttributeRelationDTO> dtos);

	/**
	 * 删除 服务属性关系
	 */
	public int deleteServiceAttributeRelation(long id);

	/**
	 * 根据主键获取 服务属性关系
	 */
	public ServiceAttributeRelationDTO getServiceAttributeRelation(long id);

	/**
	 * 根据条件获取一条 服务属性关系
	 */
	public ServiceAttributeRelationDTO getServiceAttributeRelation(ServiceAttributeRelationDTO serviceAttributeRelationDTO);

	/**
	 * 查询满足条件的 服务属性关系 列表(单表)
	 */
	public List<ServiceAttributeRelationDTO> getServiceAttributeRelationList(ServiceAttributeRelationDTO serviceAttributeRelationDTO);

	/**
	 * 查询满足条件的 服务属性关系 列表(分页)(单表)
	 */
	public PageList<ServiceAttributeRelationDTO> getServiceAttributeRelationListForPage(ServiceAttributeRelationDTO serviceAttributeRelationDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 服务属性关系 列表(分页)(单表)
	 */
	public PageList<ServiceAttributeRelationDTO> getServiceAttributeRelationListForPage(QueryParam queryParam);

	/**
	 * 服务属性关系DTO 转换成 Entity
	 */
	public ServiceAttributeRelationEntity toServiceAttributeRelationEntity(ServiceAttributeRelationDTO serviceAttributeRelationDTO);

	/**
	 * 服务属性关系DTOs 转换成 Entities
	 */
	public List<ServiceAttributeRelationEntity> toServiceAttributeRelationEntities(List<ServiceAttributeRelationDTO> dtoes);

	/**
	 * 新增 服务
	 */
	public Long saveService(ServiceDTO serviceDTO) throws ArgsException;

	/**
	 * 批量新增 服务
	 */
	public void batchSaveService(List<ServiceDTO> dtos);

	/**
	 * 更新 服务
	 */
	public int updateService(ServiceDTO serviceDTO) throws ArgsException;

	/**
	 * 批量 服务
	 */
	public void batchUpdateService(List<ServiceDTO> dtos);

	/**
	 * 删除 服务
	 */
	public int deleteService(long serivceId);

	/**
	 * 根据主键获取 服务
	 */
	public ServiceDTO getService(long serivceId);

	/**
	 * 根据条件获取一条 服务
	 */
	public ServiceDTO getService(ServiceDTO serviceDTO);

	/**
	 * 查询满足条件的 服务 列表(单表)
	 */
	public List<ServiceDTO> getServiceList(ServiceDTO serviceDTO);

	/**
	 * 查询满足条件的 服务 列表(分页)(单表)
	 */
	public PageList<ServiceDTO> getServiceListForPage(ServiceDTO serviceDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 服务 列表(分页)(单表)
	 */
	public PageList<ServiceDTO> getServiceListForPage(QueryParam queryParam);

	/**
	 * 服务DTO 转换成 Entity
	 */
	public ServiceEntity toServiceEntity(ServiceDTO serviceDTO);

	/**
	 * 服务DTOs 转换成 Entities
	 */
	public List<ServiceEntity> toServiceEntities(List<ServiceDTO> dtoes);

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

	public void disabledAttribute(String attributeIds) throws ArgsException;

	public void enabledAttribute(String attributeIds) throws ArgsException;

	public void enabledService(String serviceIds) throws ArgsException;

	public void disabledService(String serviceIds) throws ArgsException;

	public void enabledPackage(String packageIds) throws ArgsException;

	public void disabledPackage(String packageIds) throws ArgsException;

	public void deletePackageServiceRelationBySql(PackageServiceRelationDTO dto);

	public void deleteServiceAttributeRelationBySql(ServiceAttributeRelationDTO dto);

	public void deletePackageAttributeValueBySql(PackageAttributeValueDTO valueDTO);

	/**
	 * 新增 用户服务
	 */
	public Long saveUserService(UserServiceDTO userServiceDTO);

	/**
	 * 批量新增 用户服务
	 */
	public void batchSaveUserService(List<UserServiceEntity> dtos);

	/**
	 * 更新 用户服务
	 */
	public int updateUserService(UserServiceDTO userServiceDTO);

	/**
	 * 批量 用户服务
	 */
	public void batchUpdateUserService(List<UserServiceDTO> dtos);

	/**
	 * 删除 用户服务
	 */
	public int deleteUserService(long id);

	/**
	 * 根据主键获取 用户服务
	 */
	public UserServiceDTO getUserService(long id);

	/**
	 * 根据条件获取一条 用户服务
	 */
	public UserServiceDTO getUserService(UserServiceDTO userServiceDTO);

	/**
	 * 查询满足条件的 用户服务 列表(单表)
	 */
	public List<UserServiceDTO> getUserServiceList(UserServiceDTO userServiceDTO);

	/**
	 * 查询满足条件的 用户服务 列表(分页)(单表)
	 */
	public PageList<UserServiceDTO> getUserServiceListForPage(UserServiceDTO userServiceDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户服务 列表(分页)(单表)
	 */
	public PageList<UserServiceDTO> getUserServiceListForPage(QueryParam queryParam);

	/**
	 * 用户服务DTO 转换成 Entity
	 */
	public UserServiceEntity toUserServiceEntity(UserServiceDTO userServiceDTO);

	/**
	 * 用户服务DTOs 转换成 Entities
	 */
	public List<UserServiceEntity> toUserServiceEntities(List<UserServiceDTO> dtoes);

	/**
	 * 新增 用户服务属性
	 */
	public Long saveUserAttribute(UserAttributeDTO userAttributeDTO);

	/**
	 * 批量新增 用户服务属性
	 */
	public void batchSaveUserAttribute(List<UserAttributeEntity> entities);

	/**
	 * 更新 用户服务属性
	 */
	public int updateUserAttribute(UserAttributeDTO userAttributeDTO);

	/**
	 * 批量 用户服务属性
	 */
	public void batchUpdateUserAttribute(List<UserAttributeDTO> dtos);

	/**
	 * 删除 用户服务属性
	 */
	public int deleteUserAttribute(long id);

	/**
	 * 根据主键获取 用户服务属性
	 */
	public UserAttributeDTO getUserAttribute(long id);

	/**
	 * 根据条件获取一条 用户服务属性
	 */
	public UserAttributeDTO getUserAttribute(UserAttributeDTO userAttributeDTO);

	/**
	 * 查询满足条件的 用户服务属性 列表(单表)
	 */
	public List<UserAttributeDTO> getUserAttributeList(UserAttributeDTO userAttributeDTO);

	/**
	 * 查询满足条件的 用户服务属性 列表(分页)(单表)
	 */
	public PageList<UserAttributeDTO> getUserAttributeListForPage(UserAttributeDTO userAttributeDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户服务属性 列表(分页)(单表)
	 */
	public PageList<UserAttributeDTO> getUserAttributeListForPage(QueryParam queryParam);

	/**
	 * 用户服务属性DTO 转换成 Entity
	 */
	public UserAttributeEntity toUserAttributeEntity(UserAttributeDTO userAttributeDTO);

	/**
	 * 用户服务属性DTOs 转换成 Entities
	 */
	public List<UserAttributeEntity> toUserAttributeEntities(List<UserAttributeDTO> dtoes);

	/**
	 * 新增 使用服务明细
	 */
	public Long saveUseServiceDetail(UseServiceDetailDTO useServiceDetailDTO);

	/**
	 * 批量新增 使用服务明细
	 */
	public void batchSaveUseServiceDetail(List<UseServiceDetailDTO> dtos);

	/**
	 * 更新 使用服务明细
	 */
	public int updateUseServiceDetail(UseServiceDetailDTO useServiceDetailDTO);

	/**
	 * 批量 使用服务明细
	 */
	public void batchUpdateUseServiceDetail(List<UseServiceDetailDTO> dtos);

	/**
	 * 删除 使用服务明细
	 */
	public int deleteUseServiceDetail(long id);

	/**
	 * 根据主键获取 使用服务明细
	 */
	public UseServiceDetailDTO getUseServiceDetail(long id);

	/**
	 * 根据条件获取一条 使用服务明细
	 */
	public UseServiceDetailDTO getUseServiceDetail(UseServiceDetailDTO useServiceDetailDTO);

	/**
	 * 查询满足条件的 使用服务明细 列表(单表)
	 */
	public List<UseServiceDetailDTO> getUseServiceDetailList(UseServiceDetailDTO useServiceDetailDTO);

	/**
	 * 查询满足条件的 使用服务明细 列表(分页)(单表)
	 */
	public PageList<UseServiceDetailDTO> getUseServiceDetailListForPage(UseServiceDetailDTO useServiceDetailDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 使用服务明细 列表(分页)(单表)
	 */
	public PageList<UseServiceDetailDTO> getUseServiceDetailListForPage(QueryParam queryParam);

	/**
	 * 使用服务明细DTO 转换成 Entity
	 */
	public UseServiceDetailEntity toUseServiceDetailEntity(UseServiceDetailDTO useServiceDetailDTO);

	/**
	 * 使用服务明细DTOs 转换成 Entities
	 */
	public List<UseServiceDetailEntity> toUseServiceDetailEntities(List<UseServiceDetailDTO> dtoes);
	
	/**
	 * 服务已经使用数量
	 *
	 * @param userId
	 * @param serviceId
	 * @return
	 */
	public Integer getUsedServiceNum(Long userId, Long serviceId);
	
	/**
	 * 服务的总数量
	 *
	 * @param userId
	 * @param serviceId
	 * @return
	 */
	public Integer getUserServiceNum(Long userId, Long serviceId);

	/**
	 * 服务已经使用数量
	 *
	 * @param teamId
	 * @param serviceId
	 * @return
	 */
	public Integer getUsedServiceNumOfTeam(Long teamId, Long serviceId);

	/**
	 * 服务的总数量
	 *
	 * @param teamId
	 * @param serviceId
	 * @return
	 */
	public Integer getServiceNumOfTeam(Long teamId, Long serviceId);
}