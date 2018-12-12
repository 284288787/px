/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.booting.pkg.facade.PackageFacade;
import com.booting.pkg.service.AttributeService;
import com.booting.pkg.service.PackageAttributeValueService;
import com.booting.pkg.service.PackageService;
import com.booting.pkg.service.PackageServiceRelationService;
import com.booting.pkg.service.ServiceAttributeRelationService;
import com.booting.pkg.service.ServiceService;
import com.booting.pkg.service.UseServiceDetailService;
import com.booting.pkg.service.UserAttributeService;
import com.booting.pkg.service.UserPackageService;
import com.booting.pkg.service.UserServiceService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

@Service("packageFacade")
public class PackageFacadeImpl implements PackageFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private PackageAttributeValueService packageAttributeValueService;

	@Autowired
	private PackageService packageService;

	@Autowired
	private PackageServiceRelationService packageServiceRelationService;

	@Autowired
	private ServiceAttributeRelationService serviceAttributeRelationService;

	@Autowired
	private ServiceService serviceService;

	@Autowired
	private UserServiceService userServiceService;
	
	@Autowired
	private UserAttributeService userAttributeService;
	
	@Autowired
	private UserPackageService userPackageService;
	
	@Autowired
	private UseServiceDetailService useServiceDetailService;
	
	@Override
	public Long saveUserPackage(UserPackageDTO userPackageDTO){
		if (null == userPackageDTO) {
			return null;
		}
		UserPackageEntity entity = toUserPackageEntity(userPackageDTO);
		userPackageDTO = userPackageService.save(entity);
		return userPackageDTO.getUpId();
	}

	@Override
	public void batchSaveUserPackage(List<UserPackageDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<UserPackageEntity> entities = toUserPackageEntities(dtos);
		userPackageService.batchSave(entities);
	}

	@Override
	public int updateUserPackage(UserPackageDTO userPackageDTO){
		userPackageDTO = userPackageService.updateBySql(userPackageDTO);
		return 1;
	}

	@Override
	public void batchUpdateUserPackage(List<UserPackageDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		userPackageService.batchUpdate(dtos);
	}

	@Override
	public int deleteUserPackage(long upId){
		return userPackageService.delete(upId);
	}

	@Override
	public UserPackageDTO getUserPackage(long upId){
		return userPackageService.get(upId);
	}

	@Override
	public UserPackageDTO getUserPackage(UserPackageDTO userPackageDTO){
		return userPackageService.get(userPackageDTO);
	}

	@Override
	public List<UserPackageDTO> getUserPackageList(UserPackageDTO userPackageDTO){
		return userPackageService.getSimpleList(userPackageDTO);
	}

	@Override
	public PageList<UserPackageDTO> getUserPackageListForPage(UserPackageDTO userPackageDTO, int pageNumber, int pageSize){
		return userPackageService.getSimpleListForPage(userPackageDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UserPackageDTO> getUserPackageListForPage(QueryParam queryParam){
		return userPackageService.getSimpleListForPage(queryParam);
	}

	@Override
	public UserPackageEntity toUserPackageEntity(UserPackageDTO dto){
		UserPackageEntity entity = new UserPackageEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UserPackageEntity> toUserPackageEntities(List<UserPackageDTO> dtos){
		List<UserPackageEntity> entities = new ArrayList<>();
		for(UserPackageDTO dto : dtos){
			entities.add(toUserPackageEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveAttribute(AttributeDTO attributeDTO){
		if (null == attributeDTO) {
			return null;
		}
		AttributeEntity entity = toAttributeEntity(attributeDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
		attributeDTO = attributeService.save(entity);
		return attributeDTO.getAttributeId();
	}

	@Override
	public void batchSaveAttribute(List<AttributeDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<AttributeEntity> entities = toAttributeEntities(dtos);
		attributeService.batchSave(entities);
	}

	@Override
	public int updateAttribute(AttributeDTO attributeDTO){
		attributeDTO = attributeService.updateBySql(attributeDTO);
		return 1;
	}

	@Override
	public void batchUpdateAttribute(List<AttributeDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		attributeService.batchUpdate(dtos);
	}

	@Override
	public int deleteAttribute(long attributeId){
		return attributeService.delete(attributeId);
	}

	@Override
	public AttributeDTO getAttribute(long attributeId){
		return attributeService.get(attributeId);
	}

	@Override
	public AttributeDTO getAttribute(AttributeDTO attributeDTO){
		return attributeService.get(attributeDTO);
	}

	@Override
	public List<AttributeDTO> getAttributeList(AttributeDTO attributeDTO){
		return attributeService.getSimpleList(attributeDTO);
	}

	@Override
	public PageList<AttributeDTO> getAttributeListForPage(AttributeDTO attributeDTO, int pageNumber, int pageSize){
		return attributeService.getSimpleListForPage(attributeDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<AttributeDTO> getAttributeListForPage(QueryParam queryParam){
		return attributeService.getSimpleListForPage(queryParam);
	}

	@Override
	public AttributeEntity toAttributeEntity(AttributeDTO dto){
		AttributeEntity entity = new AttributeEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<AttributeEntity> toAttributeEntities(List<AttributeDTO> dtos){
		List<AttributeEntity> entities = new ArrayList<>();
		for(AttributeDTO dto : dtos){
			entities.add(toAttributeEntity(dto));
		}
		return entities;
	}

	@Override
	public Long savePackageAttributeValue(PackageAttributeValueDTO packageAttributeValueDTO){
		if (null == packageAttributeValueDTO) {
			return null;
		}
		PackageAttributeValueEntity entity = toPackageAttributeValueEntity(packageAttributeValueDTO);
		packageAttributeValueDTO = packageAttributeValueService.save(entity);
		return packageAttributeValueDTO.getId();
	}

	@Override
	public void batchSavePackageAttributeValue(List<PackageAttributeValueEntity> entities){
		if (null == entities || entities.isEmpty()) {
			return;
		}
		packageAttributeValueService.batchSave(entities);
	}

	@Override
	public int updatePackageAttributeValue(PackageAttributeValueDTO packageAttributeValueDTO){
		packageAttributeValueDTO = packageAttributeValueService.updateBySql(packageAttributeValueDTO);
		return 1;
	}

	@Override
	public void batchUpdatePackageAttributeValue(List<PackageAttributeValueDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		packageAttributeValueService.batchUpdate(dtos);
	}

	@Override
	public int deletePackageAttributeValue(long id){
		return packageAttributeValueService.delete(id);
	}

	@Override
	public PackageAttributeValueDTO getPackageAttributeValue(long id){
		return packageAttributeValueService.get(id);
	}

	@Override
	public PackageAttributeValueDTO getPackageAttributeValue(PackageAttributeValueDTO packageAttributeValueDTO){
		return packageAttributeValueService.get(packageAttributeValueDTO);
	}

	@Override
	public List<PackageAttributeValueDTO> getPackageAttributeValueList(PackageAttributeValueDTO packageAttributeValueDTO){
		return packageAttributeValueService.getSimpleList(packageAttributeValueDTO);
	}

	@Override
	public PageList<PackageAttributeValueDTO> getPackageAttributeValueListForPage(PackageAttributeValueDTO packageAttributeValueDTO, int pageNumber, int pageSize){
		return packageAttributeValueService.getSimpleListForPage(packageAttributeValueDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<PackageAttributeValueDTO> getPackageAttributeValueListForPage(QueryParam queryParam){
		return packageAttributeValueService.getSimpleListForPage(queryParam);
	}

	@Override
	public PackageAttributeValueEntity toPackageAttributeValueEntity(PackageAttributeValueDTO dto){
		PackageAttributeValueEntity entity = new PackageAttributeValueEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<PackageAttributeValueEntity> toPackageAttributeValueEntities(List<PackageAttributeValueDTO> dtos){
		List<PackageAttributeValueEntity> entities = new ArrayList<>();
		for(PackageAttributeValueDTO dto : dtos){
			entities.add(toPackageAttributeValueEntity(dto));
		}
		return entities;
	}

	@Override
	public Long savePackage(PackageDTO packageDTO){
		if (null == packageDTO) {
			return null;
		}
		PackageEntity entity = toPackageEntity(packageDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
//		entity.setPrice(entity.getPrice() * 100);
//		if (null != entity.getDiscount()) {
//			entity.setDiscount(entity.getDiscount() * 10);
//		}
		packageDTO = packageService.save(entity);
		return packageDTO.getPackageId();
	}

	@Override
	public void batchSavePackage(List<PackageDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<PackageEntity> entities = toPackageEntities(dtos);
		packageService.batchSave(entities);
	}

	@Override
	public int updatePackage(PackageDTO packageDTO){
//		packageDTO.setPrice(packageDTO.getPrice() * 100);
//		if (null != packageDTO.getDiscount()) {
//			packageDTO.setDiscount(packageDTO.getDiscount() * 10);
//		}
		packageDTO = packageService.updateBySql(packageDTO);
		return 1;
	}

	@Override
	public void batchUpdatePackage(List<PackageDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		packageService.batchUpdate(dtos);
	}

	@Override
	public int deletePackage(long packageId){
		return packageService.delete(packageId);
	}

	@Override
	public PackageDTO getPackage(long packageId){
		return packageService.get(packageId);
	}

	@Override
	public PackageDTO getPackage(PackageDTO packageDTO){
		return packageService.get(packageDTO);
	}

	@Override
	public List<PackageDTO> getPackageList(PackageDTO packageDTO){
		return packageService.getSimpleList(packageDTO);
	}

	@Override
	public PageList<PackageDTO> getPackageListForPage(PackageDTO packageDTO, int pageNumber, int pageSize){
		return packageService.getSimpleListForPage(packageDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<PackageDTO> getPackageListForPage(QueryParam queryParam){
		return packageService.getSimpleListForPage(queryParam);
	}

	@Override
	public PackageEntity toPackageEntity(PackageDTO dto){
		PackageEntity entity = new PackageEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<PackageEntity> toPackageEntities(List<PackageDTO> dtos){
		List<PackageEntity> entities = new ArrayList<>();
		for(PackageDTO dto : dtos){
			entities.add(toPackageEntity(dto));
		}
		return entities;
	}

	@Override
	public Long savePackageServiceRelation(PackageServiceRelationDTO packageServiceRelationDTO){
		if (null == packageServiceRelationDTO) {
			return null;
		}
		PackageServiceRelationEntity entity = toPackageServiceRelationEntity(packageServiceRelationDTO);
		packageServiceRelationDTO = packageServiceRelationService.save(entity);
		return packageServiceRelationDTO.getId();
	}

	@Override
	public void batchSavePackageServiceRelation(List<PackageServiceRelationEntity> entities){
		if (null == entities || entities.isEmpty()) {
			return;
		}
		packageServiceRelationService.batchSave(entities);
	}

	@Override
	public int updatePackageServiceRelation(PackageServiceRelationDTO packageServiceRelationDTO){
		packageServiceRelationDTO = packageServiceRelationService.updateBySql(packageServiceRelationDTO);
		return 1;
	}

	@Override
	public void batchUpdatePackageServiceRelation(List<PackageServiceRelationDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		packageServiceRelationService.batchUpdate(dtos);
	}

	@Override
	public int deletePackageServiceRelation(long id){
		return packageServiceRelationService.delete(id);
	}

	@Override
	public PackageServiceRelationDTO getPackageServiceRelation(long id){
		return packageServiceRelationService.get(id);
	}

	@Override
	public PackageServiceRelationDTO getPackageServiceRelation(PackageServiceRelationDTO packageServiceRelationDTO){
		return packageServiceRelationService.get(packageServiceRelationDTO);
	}

	@Override
	public List<PackageServiceRelationDTO> getPackageServiceRelationList(PackageServiceRelationDTO packageServiceRelationDTO){
		return packageServiceRelationService.getSimpleList(packageServiceRelationDTO);
	}

	@Override
	public PageList<PackageServiceRelationDTO> getPackageServiceRelationListForPage(PackageServiceRelationDTO packageServiceRelationDTO, int pageNumber, int pageSize){
		return packageServiceRelationService.getSimpleListForPage(packageServiceRelationDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<PackageServiceRelationDTO> getPackageServiceRelationListForPage(QueryParam queryParam){
		return packageServiceRelationService.getSimpleListForPage(queryParam);
	}

	@Override
	public PackageServiceRelationEntity toPackageServiceRelationEntity(PackageServiceRelationDTO dto){
		PackageServiceRelationEntity entity = new PackageServiceRelationEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<PackageServiceRelationEntity> toPackageServiceRelationEntities(List<PackageServiceRelationDTO> dtos){
		List<PackageServiceRelationEntity> entities = new ArrayList<>();
		for(PackageServiceRelationDTO dto : dtos){
			entities.add(toPackageServiceRelationEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveServiceAttributeRelation(ServiceAttributeRelationDTO serviceAttributeRelationDTO){
		if (null == serviceAttributeRelationDTO) {
			return null;
		}
		ServiceAttributeRelationEntity entity = toServiceAttributeRelationEntity(serviceAttributeRelationDTO);
		serviceAttributeRelationDTO = serviceAttributeRelationService.save(entity);
		return serviceAttributeRelationDTO.getId();
	}

	@Override
	public void batchSaveServiceAttributeRelation(List<ServiceAttributeRelationEntity> entities){
		if (null == entities || entities.isEmpty()) {
			return;
		}
		serviceAttributeRelationService.batchSave(entities);
	}

	@Override
	public int updateServiceAttributeRelation(ServiceAttributeRelationDTO serviceAttributeRelationDTO){
		serviceAttributeRelationDTO = serviceAttributeRelationService.updateBySql(serviceAttributeRelationDTO);
		return 1;
	}

	@Override
	public void batchUpdateServiceAttributeRelation(List<ServiceAttributeRelationDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		serviceAttributeRelationService.batchUpdate(dtos);
	}

	@Override
	public int deleteServiceAttributeRelation(long id){
		return serviceAttributeRelationService.delete(id);
	}

	@Override
	public ServiceAttributeRelationDTO getServiceAttributeRelation(long id){
		return serviceAttributeRelationService.get(id);
	}

	@Override
	public ServiceAttributeRelationDTO getServiceAttributeRelation(ServiceAttributeRelationDTO serviceAttributeRelationDTO){
		return serviceAttributeRelationService.get(serviceAttributeRelationDTO);
	}

	@Override
	public List<ServiceAttributeRelationDTO> getServiceAttributeRelationList(ServiceAttributeRelationDTO serviceAttributeRelationDTO){
		return serviceAttributeRelationService.getSimpleList(serviceAttributeRelationDTO);
	}

	@Override
	public PageList<ServiceAttributeRelationDTO> getServiceAttributeRelationListForPage(ServiceAttributeRelationDTO serviceAttributeRelationDTO, int pageNumber, int pageSize){
		return serviceAttributeRelationService.getSimpleListForPage(serviceAttributeRelationDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ServiceAttributeRelationDTO> getServiceAttributeRelationListForPage(QueryParam queryParam){
		return serviceAttributeRelationService.getSimpleListForPage(queryParam);
	}

	@Override
	public ServiceAttributeRelationEntity toServiceAttributeRelationEntity(ServiceAttributeRelationDTO dto){
		ServiceAttributeRelationEntity entity = new ServiceAttributeRelationEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ServiceAttributeRelationEntity> toServiceAttributeRelationEntities(List<ServiceAttributeRelationDTO> dtos){
		List<ServiceAttributeRelationEntity> entities = new ArrayList<>();
		for(ServiceAttributeRelationDTO dto : dtos){
			entities.add(toServiceAttributeRelationEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveService(ServiceDTO serviceDTO){
		if (null == serviceDTO) {
			return null;
		}
		ServiceEntity entity = toServiceEntity(serviceDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
		serviceDTO = serviceService.save(entity);
		return serviceDTO.getServiceId();
	}

	@Override
	public void batchSaveService(List<ServiceDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ServiceEntity> entities = toServiceEntities(dtos);
		serviceService.batchSave(entities);
	}

	@Override
	public int updateService(ServiceDTO serviceDTO){
		serviceDTO = serviceService.updateBySql(serviceDTO);
		return 1;
	}

	@Override
	public void batchUpdateService(List<ServiceDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		serviceService.batchUpdate(dtos);
	}

	@Override
	public int deleteService(long serivceId){
		return serviceService.delete(serivceId);
	}

	@Override
	public ServiceDTO getService(long serivceId){
		return serviceService.get(serivceId);
	}

	@Override
	public ServiceDTO getService(ServiceDTO serviceDTO){
		return serviceService.get(serviceDTO);
	}

	@Override
	public List<ServiceDTO> getServiceList(ServiceDTO serviceDTO){
		return serviceService.getSimpleList(serviceDTO);
	}

	@Override
	public PageList<ServiceDTO> getServiceListForPage(ServiceDTO serviceDTO, int pageNumber, int pageSize){
		return serviceService.getSimpleListForPage(serviceDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ServiceDTO> getServiceListForPage(QueryParam queryParam){
		return serviceService.getSimpleListForPage(queryParam);
	}

	@Override
	public ServiceEntity toServiceEntity(ServiceDTO dto){
		ServiceEntity entity = new ServiceEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ServiceEntity> toServiceEntities(List<ServiceDTO> dtos){
		List<ServiceEntity> entities = new ArrayList<>();
		for(ServiceDTO dto : dtos){
			entities.add(toServiceEntity(dto));
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
	public void enabledAttribute(String attributeIds) throws ArgsException {
		if (StringUtils.isBlank(attributeIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		AttributeDTO dto = new AttributeDTO();
		dto.setEnabled(1);
		dto.setAttributeIds(attributeIds);
		attributeService.updateBySql(dto);
	}
	
	@Override
	public void disabledAttribute(String attributeIds) throws ArgsException {
		if (StringUtils.isBlank(attributeIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		AttributeDTO dto = new AttributeDTO();
		dto.setEnabled(0);
		dto.setAttributeIds(attributeIds);
		attributeService.updateBySql(dto);
	}

	@Override
	public void enabledService(String serviceIds) throws ArgsException {
		if (StringUtils.isBlank(serviceIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		ServiceDTO dto = new ServiceDTO();
		dto.setEnabled(1);
		dto.setServiceIds(serviceIds);
		serviceService.updateBySql(dto);
	}

	@Override
	public void disabledService(String serviceIds) throws ArgsException {
		if (StringUtils.isBlank(serviceIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		ServiceDTO dto = new ServiceDTO();
		dto.setEnabled(0);
		dto.setServiceIds(serviceIds);
		serviceService.updateBySql(dto);
	}

	@Override
	public void enabledPackage(String packageIds) throws ArgsException {
		if (StringUtils.isBlank(packageIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		PackageDTO dto = new PackageDTO();
		dto.setEnabled(1);
		dto.setPackageIds(packageIds);
		packageService.updateBySql(dto);
	}

	@Override
	public void disabledPackage(String packageIds) throws ArgsException {
		if (StringUtils.isBlank(packageIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		PackageDTO dto = new PackageDTO();
		dto.setEnabled(0);
		dto.setPackageIds(packageIds);
		packageService.updateBySql(dto);
	}

	@Override
	public void deletePackageServiceRelationBySql(PackageServiceRelationDTO dto) {
		this.packageServiceRelationService.deletePackageServiceRelationBySql(dto);
	}

	@Override
	public void deleteServiceAttributeRelationBySql(ServiceAttributeRelationDTO dto) {
		this.serviceAttributeRelationService.deleteServiceAttributeRelationBySql(dto);
	}

	@Override
	public void deletePackageAttributeValueBySql(PackageAttributeValueDTO valueDTO) {
		this.packageAttributeValueService.deletePackageAttributeValueBySql(valueDTO);
	}
	
	@Override
	public Long saveUserService(UserServiceDTO userServiceDTO){
		if (null == userServiceDTO) {
			return null;
		}
		UserServiceEntity entity = toUserServiceEntity(userServiceDTO);
		userServiceDTO = userServiceService.save(entity);
		return userServiceDTO.getUsId();
	}

	@Override
	public void batchSaveUserService(List<UserServiceEntity> entities){
		if (null == entities || entities.isEmpty()) {
			return;
		}
		userServiceService.batchSave(entities);
	}

	@Override
	public int updateUserService(UserServiceDTO userServiceDTO){
		userServiceDTO = userServiceService.updateBySql(userServiceDTO);
		return 1;
	}

	@Override
	public void batchUpdateUserService(List<UserServiceDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		userServiceService.batchUpdate(dtos);
	}

	@Override
	public int deleteUserService(long id){
		return userServiceService.delete(id);
	}

	@Override
	public UserServiceDTO getUserService(long id){
		return userServiceService.get(id);
	}

	@Override
	public UserServiceDTO getUserService(UserServiceDTO userServiceDTO){
		return userServiceService.get(userServiceDTO);
	}

	@Override
	public List<UserServiceDTO> getUserServiceList(UserServiceDTO userServiceDTO){
		return userServiceService.getSimpleList(userServiceDTO);
	}

	@Override
	public PageList<UserServiceDTO> getUserServiceListForPage(UserServiceDTO userServiceDTO, int pageNumber, int pageSize){
		return userServiceService.getSimpleListForPage(userServiceDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UserServiceDTO> getUserServiceListForPage(QueryParam queryParam){
		return userServiceService.getSimpleListForPage(queryParam);
	}

	@Override
	public UserServiceEntity toUserServiceEntity(UserServiceDTO dto){
		UserServiceEntity entity = new UserServiceEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UserServiceEntity> toUserServiceEntities(List<UserServiceDTO> dtos){
		List<UserServiceEntity> entities = new ArrayList<>();
		for(UserServiceDTO dto : dtos){
			entities.add(toUserServiceEntity(dto));
		}
		return entities;
	}
	
	@Override
	public Long saveUserAttribute(UserAttributeDTO userAttributeDTO){
		if (null == userAttributeDTO) {
			return null;
		}
		UserAttributeEntity entity = toUserAttributeEntity(userAttributeDTO);
		userAttributeDTO = userAttributeService.save(entity);
		return userAttributeDTO.getId();
	}

	@Override
	public void batchSaveUserAttribute(List<UserAttributeEntity> entities){
		if (null == entities || entities.isEmpty()) {
			return;
		}
		userAttributeService.batchSave(entities);
	}

	@Override
	public int updateUserAttribute(UserAttributeDTO userAttributeDTO){
		userAttributeDTO = userAttributeService.updateBySql(userAttributeDTO);
		return 1;
	}

	@Override
	public void batchUpdateUserAttribute(List<UserAttributeDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		userAttributeService.batchUpdate(dtos);
	}

	@Override
	public int deleteUserAttribute(long id){
		return userAttributeService.delete(id);
	}

	@Override
	public UserAttributeDTO getUserAttribute(long id){
		return userAttributeService.get(id);
	}

	@Override
	public UserAttributeDTO getUserAttribute(UserAttributeDTO userAttributeDTO){
		return userAttributeService.get(userAttributeDTO);
	}

	@Override
	public List<UserAttributeDTO> getUserAttributeList(UserAttributeDTO userAttributeDTO){
		return userAttributeService.getSimpleList(userAttributeDTO);
	}

	@Override
	public PageList<UserAttributeDTO> getUserAttributeListForPage(UserAttributeDTO userAttributeDTO, int pageNumber, int pageSize){
		return userAttributeService.getSimpleListForPage(userAttributeDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UserAttributeDTO> getUserAttributeListForPage(QueryParam queryParam){
		return userAttributeService.getSimpleListForPage(queryParam);
	}

	@Override
	public UserAttributeEntity toUserAttributeEntity(UserAttributeDTO dto){
		UserAttributeEntity entity = new UserAttributeEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UserAttributeEntity> toUserAttributeEntities(List<UserAttributeDTO> dtos){
		List<UserAttributeEntity> entities = new ArrayList<>();
		for(UserAttributeDTO dto : dtos){
			entities.add(toUserAttributeEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveUseServiceDetail(UseServiceDetailDTO useServiceDetailDTO){
		if (null == useServiceDetailDTO) {
			return null;
		}
		UseServiceDetailEntity entity = toUseServiceDetailEntity(useServiceDetailDTO);
		useServiceDetailDTO = useServiceDetailService.save(entity);
		return useServiceDetailDTO.getId();
	}

	@Override
	public void batchSaveUseServiceDetail(List<UseServiceDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<UseServiceDetailEntity> entities = toUseServiceDetailEntities(dtos);
		useServiceDetailService.batchSave(entities);
	}

	@Override
	public int updateUseServiceDetail(UseServiceDetailDTO useServiceDetailDTO){
		useServiceDetailDTO = useServiceDetailService.updateBySql(useServiceDetailDTO);
		return 1;
	}

	@Override
	public void batchUpdateUseServiceDetail(List<UseServiceDetailDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		useServiceDetailService.batchUpdate(dtos);
	}

	@Override
	public int deleteUseServiceDetail(long id){
		return useServiceDetailService.delete(id);
	}

	@Override
	public UseServiceDetailDTO getUseServiceDetail(long id){
		return useServiceDetailService.get(id);
	}

	@Override
	public UseServiceDetailDTO getUseServiceDetail(UseServiceDetailDTO useServiceDetailDTO){
		return useServiceDetailService.get(useServiceDetailDTO);
	}

	@Override
	public List<UseServiceDetailDTO> getUseServiceDetailList(UseServiceDetailDTO useServiceDetailDTO){
		return useServiceDetailService.getSimpleList(useServiceDetailDTO);
	}

	@Override
	public PageList<UseServiceDetailDTO> getUseServiceDetailListForPage(UseServiceDetailDTO useServiceDetailDTO, int pageNumber, int pageSize){
		return useServiceDetailService.getSimpleListForPage(useServiceDetailDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UseServiceDetailDTO> getUseServiceDetailListForPage(QueryParam queryParam){
		return useServiceDetailService.getSimpleListForPage(queryParam);
	}

	@Override
	public UseServiceDetailEntity toUseServiceDetailEntity(UseServiceDetailDTO dto){
		UseServiceDetailEntity entity = new UseServiceDetailEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UseServiceDetailEntity> toUseServiceDetailEntities(List<UseServiceDetailDTO> dtos){
		List<UseServiceDetailEntity> entities = new ArrayList<>();
		for(UseServiceDetailDTO dto : dtos){
			entities.add(toUseServiceDetailEntity(dto));
		}
		return entities;
	}

	@Override
	public Integer getUsedServiceNum(Long userId, Long serviceId) {
		return this.useServiceDetailService.getUseServiceNum(userId, serviceId);
	}

	@Override
	public Integer getUserServiceNum(Long userId, Long serviceId) {
		return this.userServiceService.getUserServiceNum(userId, serviceId);
	}

	@Override
	public Integer getUsedServiceNumOfTeam(Long teamId, Long serviceId) {
		return this.useServiceDetailService.getUsedServiceNumOfTeam(teamId, serviceId);
	}

	@Override
	public Integer getServiceNumOfTeam(Long teamId, Long serviceId) {
		return this.userServiceService.getServiceNumOfTeam(teamId, serviceId);
	}
}