/** create by auto at 2017-05-24 09:29:23**/
package com.booting.system.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.pub.entity.MessageEntity;
import com.booting.pub.service.MessageService;
import com.booting.system.dto.ResourceDTO;
import com.booting.system.dto.RoleDTO;
import com.booting.system.dto.RoleSourceRelationDTO;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.dto.UserRoleRelationDTO;
import com.booting.system.entity.ResourceEntity;
import com.booting.system.entity.RoleEntity;
import com.booting.system.entity.RoleSourceRelationEntity;
import com.booting.system.entity.UserAccountEntity;
import com.booting.system.entity.UserInfoEntity;
import com.booting.system.entity.UserRoleRelationEntity;
import com.booting.system.facade.SystemFacade;
import com.booting.system.service.ResourceService;
import com.booting.system.service.RoleService;
import com.booting.system.service.RoleSourceRelationService;
import com.booting.system.service.UserAccountService;
import com.booting.system.service.UserInfoService;
import com.booting.system.service.UserRoleRelationService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.utils.CglibBeanUtils;

@Service("systemFacade")
public class SystemFacadeImpl implements SystemFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserAccountService userAccountService;

	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private ResourceService resourceService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleSourceRelationService roleSourceRelationService;
	
	@Autowired
	private UserRoleRelationService userRoleRelationService;
	
	@Autowired
	private MessageService messageService;
	
	@Override
	public Long saveUserAccount(UserAccountDTO userAccountDTO){
		if (null == userAccountDTO) {
			return null;
		}
		UserAccountDTO dto = new UserAccountDTO();
		dto.setAccount(userAccountDTO.getAccount());
		UserAccountDTO temp = userAccountService.get(dto);
		if (null != temp && null != temp.getUserId()) {
          throw new ArgsException(FailureCode.ERR_002, "用户账号已经存在");
        }
		UserAccountEntity entity = toUserAccountEntity(userAccountDTO);
		entity.setNonExpired(1);
		entity.setNonLocked(1);
		entity.setEnabled(1);
		entity.setDeleted(0);
		entity.setCreateTime(new Date());
		entity.setLastModifyTime(new Date());
		userAccountService.save(entity);
		Long userId = entity.getUserId();
		UserInfoEntity userInfoEntity = new UserInfoEntity();
		CglibBeanUtils.copy(userAccountDTO, userInfoEntity);
		userInfoEntity.setLastModifyTime(new Date());
		userInfoEntity.setUserId(entity.getUserId());
		if (StringUtils.isNotBlank(userAccountDTO.getMobile())) {
			userInfoEntity.setMobile(userAccountDTO.getMobile());
		}else{
			userInfoEntity.setMobile(userAccountDTO.getAccount());
		}
		userInfoEntity.setIdentity(userAccountDTO.getIdentity());
		userInfoEntity.setName(userAccountDTO.getName());
		userInfoService.save(userInfoEntity);
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setUserId(userId);
		messageEntity.setCreateTime(new Date());
		messageEntity.setTitle("欢迎来到精茵体育~");
		messageEntity.setMessageType(1);
		messageEntity.setStatus(1);
		messageEntity.setContent("足球运动的企业级社交，让足球不只运动这么简单！");
		this.messageService.save(messageEntity);
		return userId;
	}

	@Override
	public void batchSaveUserAccount(List<UserAccountDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<UserAccountEntity> entities = toUserAccountEntities(dtos);
		userAccountService.batchSave(entities);
	}
	
	@Override
	public int updateUserAccount(UserAccountDTO userAccountDTO){
		userAccountService.updateBySql(userAccountDTO);
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		CglibBeanUtils.copy(userAccountDTO, userInfoDTO);
		userInfoService.updateBySql(userInfoDTO);
		return 1;
	}

	@Override
	public void updateUserAccountOnly(UserAccountDTO userAccountDTO){
		userAccountService.updateBySql(userAccountDTO);
	}

	@Override
	public void batchUpdateUserAccount(List<UserAccountDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		userAccountService.batchUpdate(dtos);
	}

	@Override
	public void deleteUserAccount(String userIds) throws ArgsException{
		if (StringUtils.isBlank(userIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserAccountDTO dto = new UserAccountDTO();
		dto.setDeleted(1);
		dto.setDeletedTime(new Date());
		dto.setUserIds(userIds);
		userAccountService.updateBySql(dto);
	}

	@Override
	public UserAccountDTO getUserAccount(long userId){
		UserAccountDTO userAccount = userAccountService.get(userId);
		UserInfoDTO userInfo = userInfoService.get(userId);
		Map<String, Object> target = new HashMap<>();
		CglibBeanUtils.addToMap(userAccount, target);
		CglibBeanUtils.addToMap(userInfo, target);
		CglibBeanUtils.copy(target, userAccount);
//		userAccount.setName(userInfo.getName());
//		userAccount.setAddress(userInfo.getAddress());
//		userAccount.setMobile(userInfo.getMobile());
//		userAccount.setInfoModifyTime(userInfo.getLastModifyTime());
//		userAccount.setIdentity(userInfo.getIdentity());
		return userAccount;
	}

	@Override
	public UserAccountDTO getUserAccount(UserAccountDTO userAccountDTO){
		return userAccountService.get(userAccountDTO);
	}

	@Override
	public List<UserAccountDTO> getUserAccountList(UserAccountDTO userAccountDTO){
		return userAccountService.getSimpleList(userAccountDTO);
	}

	@Override
	public PageList<UserAccountDTO> getUserAccountListForPage(UserAccountDTO userAccountDTO, int pageNumber, int pageSize){
		return userAccountService.getSimpleListForPage(userAccountDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UserAccountDTO> getUserAccountListForPage(QueryParam queryParam){
		return userAccountService.getSimpleListForPage(queryParam);
	}

	@Override
	public UserAccountEntity toUserAccountEntity(UserAccountDTO dto){
		UserAccountEntity entity = new UserAccountEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UserAccountEntity> toUserAccountEntities(List<UserAccountDTO> dtos){
		List<UserAccountEntity> entities = new ArrayList<>();
		for(UserAccountDTO dto : dtos){
			entities.add(toUserAccountEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveUserInfo(UserInfoDTO userInfoDTO){
		if (null == userInfoDTO) {
			return null;
		}
		UserInfoEntity entity = toUserInfoEntity(userInfoDTO);
		userInfoDTO = userInfoService.save(entity);
		return userInfoDTO.getUserId();
	}

	@Override
	public void batchSaveUserInfo(List<UserInfoDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<UserInfoEntity> entities = toUserInfoEntities(dtos);
		userInfoService.batchSave(entities);
	}

	@Override
	public int updateUserInfo(UserInfoDTO userInfoDTO){
		userInfoDTO.setLastModifyTime(new Date());
		userInfoDTO = userInfoService.updateBySql(userInfoDTO);
		return 1;
	}

	@Override
	public void batchUpdateUserInfo(List<UserInfoDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		userInfoService.batchUpdate(dtos);
	}

	@Override
	public UserInfoDTO getUserInfo(long userId){
		return userInfoService.get(userId);
	}

	@Override
	public UserInfoDTO getUserInfo(UserInfoDTO userInfoDTO){
		return userInfoService.get(userInfoDTO);
	}

	@Override
	public List<UserInfoDTO> getUserInfoList(UserInfoDTO userInfoDTO){
		return userInfoService.getSimpleList(userInfoDTO);
	}

	@Override
	public PageList<UserInfoDTO> getUserInfoListForPage(UserInfoDTO userInfoDTO, int pageNumber, int pageSize){
		return userInfoService.getSimpleListForPage(userInfoDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<UserInfoDTO> getUserInfoListForPage(QueryParam queryParam){
		return userInfoService.getSimpleListForPage(queryParam);
	}

	@Override
	public UserInfoEntity toUserInfoEntity(UserInfoDTO dto){
		UserInfoEntity entity = new UserInfoEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<UserInfoEntity> toUserInfoEntities(List<UserInfoDTO> dtos){
		List<UserInfoEntity> entities = new ArrayList<>();
		for(UserInfoDTO dto : dtos){
			entities.add(toUserInfoEntity(dto));
		}
		return entities;
	}

	@Override
	public <T> List<T> getList(T dto){
		return this.userAccountService.getList(dto);
	}

	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize){
		return this.userAccountService.getListForPage(dto, pageNumber, pageSize);
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz){
		return this.userAccountService.getListForPage(queryParam, clazz);
	}

	@Override
	public void disabledUserAccount(String userIds) throws ArgsException {
		if (StringUtils.isBlank(userIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserAccountDTO dto = new UserAccountDTO();
		dto.setEnabled(0);
		dto.setEnabledTime(new Date());
		dto.setUserIds(userIds);
		userAccountService.updateBySql(dto);
	}

	@Override
	public void enabledUserAccount(String userIds) throws ArgsException {
		if (StringUtils.isBlank(userIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserAccountDTO dto = new UserAccountDTO();
		dto.setEnabled(1);
		dto.setEnabledTime(new Date());
		dto.setUserIds(userIds);
		userAccountService.updateBySql(dto);
	}

	@Override
	public Long saveResource(ResourceDTO resourceDTO) throws ArgsException{
		if (null == resourceDTO) {
			return null;
		}
		ResourceEntity entity = toResourceEntity(resourceDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
		resourceDTO = resourceService.save(entity);
		return resourceDTO.getSourceId();
	}

	@Override
	public void batchSaveResource(List<ResourceDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ResourceEntity> entities = toResourceEntities(dtos);
		resourceService.batchSave(entities);
	}

	@Override
	public int updateResource(ResourceDTO resourceDTO) throws ArgsException {
		resourceDTO = resourceService.updateBySql(resourceDTO);
		return 1;
	}

	@Override
	public void batchUpdateResource(List<ResourceDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		resourceService.batchUpdate(dtos);
	}

	@Override
	public int deleteResource(long sourceId){
		return resourceService.delete(sourceId);
	}

	@Override
	public ResourceDTO getResource(long sourceId){
		return resourceService.get(sourceId);
	}

	@Override
	public ResourceDTO getResource(ResourceDTO resourceDTO){
		return resourceService.get(resourceDTO);
	}

	@Override
	public List<ResourceDTO> getResourceList(ResourceDTO resourceDTO){
		return resourceService.getSimpleList(resourceDTO);
	}

	@Override
	public PageList<ResourceDTO> getResourceListForPage(ResourceDTO resourceDTO, int pageNumber, int pageSize){
		return resourceService.getSimpleListForPage(resourceDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ResourceDTO> getResourceListForPage(QueryParam queryParam){
		return resourceService.getSimpleListForPage(queryParam);
	}

	@Override
	public ResourceEntity toResourceEntity(ResourceDTO dto){
		ResourceEntity entity = new ResourceEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ResourceEntity> toResourceEntities(List<ResourceDTO> dtos){
		List<ResourceEntity> entities = new ArrayList<>();
		for(ResourceDTO dto : dtos){
			entities.add(toResourceEntity(dto));
		}
		return entities;
	}
	
	@Override
	public Long saveRole(RoleDTO roleDTO) throws ArgsException{
		if (null == roleDTO) {
			return null;
		}
		RoleEntity entity = toRoleEntity(roleDTO);
		entity.setEnabled(1);
		entity.setCreateTime(new Date());
		roleDTO = roleService.save(entity);
		return roleDTO.getRoleId();
	}

	@Override
	public void batchSaveRole(List<RoleDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<RoleEntity> entities = toRoleEntities(dtos);
		roleService.batchSave(entities);
	}

	@Override
	public int updateRole(RoleDTO roleDTO) throws ArgsException{
		roleDTO = roleService.updateBySql(roleDTO);
		return 1;
	}

	@Override
	public void batchUpdateRole(List<RoleDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		roleService.batchUpdate(dtos);
	}

	@Override
	public int deleteRole(long roleId){
		return roleService.delete(roleId);
	}

	@Override
	public RoleDTO getRole(long roleId){
		return roleService.get(roleId);
	}

	@Override
	public RoleDTO getRole(RoleDTO roleDTO){
		return roleService.get(roleDTO);
	}

	@Override
	public List<RoleDTO> getRoleList(RoleDTO roleDTO){
		return roleService.getSimpleList(roleDTO);
	}

	@Override
	public PageList<RoleDTO> getRoleListForPage(RoleDTO roleDTO, int pageNumber, int pageSize){
		return roleService.getSimpleListForPage(roleDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<RoleDTO> getRoleListForPage(QueryParam queryParam){
		return roleService.getSimpleListForPage(queryParam);
	}

	@Override
	public RoleEntity toRoleEntity(RoleDTO dto){
		RoleEntity entity = new RoleEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<RoleEntity> toRoleEntities(List<RoleDTO> dtos){
		List<RoleEntity> entities = new ArrayList<>();
		for(RoleDTO dto : dtos){
			entities.add(toRoleEntity(dto));
		}
		return entities;
	}

	@Override
	public Map<String, List<ResourceDTO>> getParentTypeBySourceType(Integer sourceType) throws ArgsException {
		Map<String, List<ResourceDTO>> map = new HashMap<>();
		if (sourceType == 1 || sourceType == 6) {
			ResourceDTO dto = new ResourceDTO();
			dto.setSourceId(0l);
			dto.setSourceName("根资源");
			List<ResourceDTO> list = new ArrayList<>();
			list.add(dto);
			map.put("all", list);
		}else {
			ResourceDTO dto = new ResourceDTO();
			dto.setEnabled(1);
			dto.setType(sourceType - 1);
			List<ResourceDTO> list = this.resourceService.getSimpleList(dto);
			map.put("all", list);
//		}else if (sourceType == 4) {
//			ResourceDTO dto = new ResourceDTO();
//			dto.setEnabled(1);
//			dto.setType(2);
//			List<ResourceDTO> list = this.resourceService.getSimpleList(dto);
//			map.put("一级菜单", list);
//			dto.setType(3);
//			List<ResourceDTO> list2 = this.resourceService.getSimpleList(dto);
//			map.put("子菜单", list2);
		}
		return map;
	}

	@Override
	public void disabledResource(String resourceIds) throws ArgsException {
		if (StringUtils.isBlank(resourceIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		ResourceDTO dto = new ResourceDTO();
		dto.setEnabled(0);
		dto.setSourceIds(resourceIds);
		resourceService.updateBySql(dto);
	}

	@Override
	public void enabledResource(String resourceIds) throws ArgsException {
		if (StringUtils.isBlank(resourceIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		ResourceDTO dto = new ResourceDTO();
		dto.setEnabled(1);
		dto.setSourceIds(resourceIds);
		resourceService.updateBySql(dto);
	}

	@Override
	public void enabledRole(String roleIds) throws ArgsException {
		if (StringUtils.isBlank(roleIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		RoleDTO dto = new RoleDTO();
		dto.setEnabled(1);
		dto.setRoleIds(roleIds);
		roleService.updateBySql(dto);
	}

	@Override
	public void disabledRole(String roleIds) throws ArgsException {
		if (StringUtils.isBlank(roleIds)) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		RoleDTO dto = new RoleDTO();
		dto.setEnabled(0);
		dto.setRoleIds(roleIds);
		roleService.updateBySql(dto);
	}

	@Override
	public PageList<UserRoleRelationDTO> getUserRolesForPage(QueryParam queryParam) {
		PageList<UserRoleRelationDTO> list = this.userRoleRelationService.getListForPage(queryParam, UserRoleRelationDTO.class);
		return list;
	}

	@Override
	public PageList<UserRoleRelationDTO> getUserRoleForPage(QueryParam queryParam) {
		PageList<UserRoleRelationDTO> list = this.userRoleRelationService.getUserRole(queryParam);
		return list;
	}

	@Override
	public void setUserRoles(Long userId, String roleIds) throws ArgsException {
		if (null == userId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		UserRoleRelationDTO dto = new UserRoleRelationDTO();
		dto.setUserId(userId);
		this.userRoleRelationService.deleteBySql(dto);
		if (StringUtils.isNotBlank(roleIds)) {
			String roleId[] = roleIds.split(",");
			List<UserRoleRelationEntity> entities = new ArrayList<>();
			for (String rId : roleId) {
				UserRoleRelationEntity entity = new UserRoleRelationEntity();
				entity.setCreateTime(new Date());
				entity.setRoleId(Long.parseLong(rId));
				entity.setUserId(userId);
				entities.add(entity);
			}
			this.userRoleRelationService.batchSave(entities);
		}
	}

	@Override
	public PageList<RoleSourceRelationDTO> getRoleSourcesForPage(QueryParam queryParam) {
		PageList<RoleSourceRelationDTO> list = this.roleSourceRelationService.getRoleSourcesForPage(queryParam);
		return list;
	}

	@Override
	public PageList<RoleSourceRelationDTO> getRoleSourceForPage(QueryParam queryParam) {
		PageList<RoleSourceRelationDTO> list = this.roleSourceRelationService.getRoleSourceForPage(queryParam);
		return list;
	}
	
	@Override
	public void setRoleResources(Long roleId, String sourceIds) throws ArgsException {
		if (null == roleId) {
			throw new ArgsException(FailureCode.ERR_002);
		}
		RoleSourceRelationDTO dto = new RoleSourceRelationDTO();
		dto.setRoleId(roleId);
		this.roleSourceRelationService.deleteBySql(dto);
		if (StringUtils.isNotBlank(sourceIds)) {
			String sourceId[] = sourceIds.split(",");
			List<RoleSourceRelationEntity> entities = new ArrayList<>();
			for (String sId : sourceId) {
				RoleSourceRelationEntity entity = new RoleSourceRelationEntity();
				entity.setCreateTime(new Date());
				entity.setRoleId(roleId);
				entity.setSourceId(Long.parseLong(sId));
				entities.add(entity);
			}
			this.roleSourceRelationService.batchSave(entities);
		}
	}

	@Override
	public List<RoleSourceRelationDTO> getAllRoleSourceRelation() {
		return this.roleSourceRelationService.getAllRoleSourceRelation();
	}

	@Override
	public List<RoleSourceRelationDTO> getUserResources(String roleIds) {
		return this.roleSourceRelationService.getUserResources(roleIds);
	}

	@Override
	public int getUserInfoCount(UserInfoDTO userInfoDTO) {
		return this.userInfoService.getCount(userInfoDTO);
	}
}