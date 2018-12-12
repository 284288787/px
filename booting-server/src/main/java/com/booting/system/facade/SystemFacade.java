/** create by auto at 2017-05-24 09:28:10**/
package com.booting.system.facade;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.booting.system.dto.ResourceDTO;
import com.booting.system.dto.RoleDTO;
import com.booting.system.dto.RoleSourceRelationDTO;
import com.booting.system.dto.UserAccountDTO;
import com.booting.system.dto.UserInfoDTO;
import com.booting.system.dto.UserRoleRelationDTO;
import com.booting.system.entity.ResourceEntity;
import com.booting.system.entity.RoleEntity;
import com.booting.system.entity.UserAccountEntity;
import com.booting.system.entity.UserInfoEntity;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.exception.ArgsException;

public interface SystemFacade extends Serializable {

	/**
	 * 新增 用户帐号
	 */
	public Long saveUserAccount(UserAccountDTO userAccountDTO) throws ArgsException;

	/**
	 * 批量新增 用户帐号
	 */
	public void batchSaveUserAccount(List<UserAccountDTO> dtos);

	/**
	 * 更新 用户帐号
	 */
	public int updateUserAccount(UserAccountDTO userAccountDTO) throws ArgsException;

	/**
	 * 批量 用户帐号
	 */
	public void batchUpdateUserAccount(List<UserAccountDTO> dtos);

	/**
	 * 根据主键获取 用户帐号
	 */
	public UserAccountDTO getUserAccount(long userId);

	/**
	 * 根据条件获取一条 用户帐号
	 */
	public UserAccountDTO getUserAccount(UserAccountDTO userAccountDTO);

	/**
	 * 查询满足条件的 用户帐号 列表(单表)
	 */
	public List<UserAccountDTO> getUserAccountList(UserAccountDTO userAccountDTO);

	/**
	 * 查询满足条件的 用户帐号 列表(分页)(单表)
	 */
	public PageList<UserAccountDTO> getUserAccountListForPage(UserAccountDTO userAccountDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户帐号 列表(分页)(单表)
	 */
	public PageList<UserAccountDTO> getUserAccountListForPage(QueryParam queryParam);

	/**
	 * 用户帐号DTO 转换成 Entity
	 */
	public UserAccountEntity toUserAccountEntity(UserAccountDTO userAccountDTO);

	/**
	 * 用户帐号DTOs 转换成 Entities
	 */
	public List<UserAccountEntity> toUserAccountEntities(List<UserAccountDTO> dtoes);

	/**
	 * 新增 用户信息
	 */
	public Long saveUserInfo(UserInfoDTO userInfoDTO);

	/**
	 * 批量新增 用户信息
	 */
	public void batchSaveUserInfo(List<UserInfoDTO> dtos);

	/**
	 * 更新 用户信息
	 */
	public int updateUserInfo(UserInfoDTO userInfoDTO);

	/**
	 * 批量 用户信息
	 */
	public void batchUpdateUserInfo(List<UserInfoDTO> dtos);

	/**
	 * 根据主键获取 用户信息
	 */
	public UserInfoDTO getUserInfo(long userId);

	/**
	 * 根据条件获取一条 用户信息
	 */
	public UserInfoDTO getUserInfo(UserInfoDTO userInfoDTO);

	/**
	 * 查询满足条件的 用户信息 列表(单表)
	 */
	public List<UserInfoDTO> getUserInfoList(UserInfoDTO userInfoDTO);

	/**
	 * 查询满足条件的 用户信息 列表(分页)(单表)
	 */
	public PageList<UserInfoDTO> getUserInfoListForPage(UserInfoDTO userInfoDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户信息 列表(分页)(单表)
	 */
	public PageList<UserInfoDTO> getUserInfoListForPage(QueryParam queryParam);

	/**
	 * 用户信息DTO 转换成 Entity
	 */
	public UserInfoEntity toUserInfoEntity(UserInfoDTO userInfoDTO);

	/**
	 * 用户信息DTOs 转换成 Entities
	 */
	public List<UserInfoEntity> toUserInfoEntities(List<UserInfoDTO> dtoes);

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

	/**
	 * 根据id 删除帐号(逻辑删除)
	 * @author liuhua
	 *
	 * @param userIds
	 * @throws ArgsException 
	 */
	public void deleteUserAccount(String userIds) throws ArgsException;

	/**
	 * 根据id 禁用帐号
	 * @author liuhua
	 *
	 * @param userIds
	 * @throws ArgsException
	 */
	public void disabledUserAccount(String userIds) throws ArgsException;

	/**
	 * 根据id 启用帐号
	 * @author liuhua
	 *
	 * @param userIds
	 * @throws ArgsException
	 */
	public void enabledUserAccount(String userIds) throws ArgsException;

	/**
	 * 只修改用户帐号信息
	 * @author liuhua
	 *
	 * @param userAccount
	 */
	public void updateUserAccountOnly(UserAccountDTO userAccount) throws ArgsException;
	
	/**
	 * 新增 系统资源
	 */
	public Long saveResource(ResourceDTO resourceDTO) throws ArgsException;

	/**
	 * 批量新增 系统资源
	 */
	public void batchSaveResource(List<ResourceDTO> dtos);

	/**
	 * 更新 系统资源
	 */
	public int updateResource(ResourceDTO resourceDTO) throws ArgsException;

	/**
	 * 批量 系统资源
	 */
	public void batchUpdateResource(List<ResourceDTO> dtos);

	/**
	 * 删除 系统资源
	 */
	public int deleteResource(long sourceId);

	/**
	 * 根据主键获取 系统资源
	 */
	public ResourceDTO getResource(long sourceId);

	/**
	 * 根据条件获取一条 系统资源
	 */
	public ResourceDTO getResource(ResourceDTO resourceDTO);

	/**
	 * 查询满足条件的 系统资源 列表(单表)
	 */
	public List<ResourceDTO> getResourceList(ResourceDTO resourceDTO);

	/**
	 * 查询满足条件的 系统资源 列表(分页)(单表)
	 */
	public PageList<ResourceDTO> getResourceListForPage(ResourceDTO resourceDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 系统资源 列表(分页)(单表)
	 */
	public PageList<ResourceDTO> getResourceListForPage(QueryParam queryParam);

	/**
	 * 系统资源DTO 转换成 Entity
	 */
	public ResourceEntity toResourceEntity(ResourceDTO resourceDTO);

	/**
	 * 系统资源DTOs 转换成 Entities
	 */
	public List<ResourceEntity> toResourceEntities(List<ResourceDTO> dtoes);
	
	/**
	 * 新增 用户角色
	 */
	public Long saveRole(RoleDTO roleDTO) throws ArgsException;

	/**
	 * 批量新增 用户角色
	 */
	public void batchSaveRole(List<RoleDTO> dtos);

	/**
	 * 更新 用户角色
	 */
	public int updateRole(RoleDTO roleDTO) throws ArgsException;

	/**
	 * 批量 用户角色
	 */
	public void batchUpdateRole(List<RoleDTO> dtos);

	/**
	 * 删除 用户角色
	 */
	public int deleteRole(long roleId);

	/**
	 * 根据主键获取 用户角色
	 */
	public RoleDTO getRole(long roleId);

	/**
	 * 根据条件获取一条 用户角色
	 */
	public RoleDTO getRole(RoleDTO roleDTO);

	/**
	 * 查询满足条件的 用户角色 列表(单表)
	 */
	public List<RoleDTO> getRoleList(RoleDTO roleDTO);

	/**
	 * 查询满足条件的 用户角色 列表(分页)(单表)
	 */
	public PageList<RoleDTO> getRoleListForPage(RoleDTO roleDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 用户角色 列表(分页)(单表)
	 */
	public PageList<RoleDTO> getRoleListForPage(QueryParam queryParam);

	/**
	 * 用户角色DTO 转换成 Entity
	 */
	public RoleEntity toRoleEntity(RoleDTO roleDTO);

	/**
	 * 用户角色DTOs 转换成 Entities
	 */
	public List<RoleEntity> toRoleEntities(List<RoleDTO> dtoes);

	/**
	 * 根据资源类型得到父级资源类型
	 * @author liuhua
	 *
	 * @param sourceType
	 * @return
	 */
	public Map<String, List<ResourceDTO>> getParentTypeBySourceType(Integer sourceType) throws ArgsException;

	public void disabledResource(String resourceIds) throws ArgsException;

	public void enabledResource(String resourceIds) throws ArgsException;

	public void enabledRole(String roleIds) throws ArgsException;

	public void disabledRole(String roleIds) throws ArgsException;

	public PageList<UserRoleRelationDTO> getUserRolesForPage(QueryParam queryParam);

	public PageList<UserRoleRelationDTO> getUserRoleForPage(QueryParam queryParam);

	public void setUserRoles(Long userId, String roleIds) throws ArgsException;

	public PageList<RoleSourceRelationDTO> getRoleSourcesForPage(QueryParam queryParam);

	public PageList<RoleSourceRelationDTO> getRoleSourceForPage(QueryParam queryParam);

	public void setRoleResources(Long roleId, String sourceIds) throws ArgsException;

	public List<RoleSourceRelationDTO> getAllRoleSourceRelation();

	public List<RoleSourceRelationDTO> getUserResources(String roleIds);

	public int getUserInfoCount(UserInfoDTO userInfoDTO);
}