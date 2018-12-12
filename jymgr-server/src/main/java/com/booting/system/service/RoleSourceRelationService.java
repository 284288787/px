/** create by auto at 2017-05-24 16:40:27**/
package com.booting.system.service;

import java.util.List;

import com.booting.system.dto.RoleSourceRelationDTO;
import com.booting.system.entity.RoleSourceRelationEntity;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.jdbc.service.BaseService;

/**
 * 角色资源关系服务
 *
 * @author auto
 *
 */
public interface RoleSourceRelationService extends BaseService<RoleSourceRelationEntity, RoleSourceRelationDTO> {

	PageList<RoleSourceRelationDTO> getRoleSourcesForPage(QueryParam queryParam);

	int deleteBySql(RoleSourceRelationDTO dto);

	PageList<RoleSourceRelationDTO> getRoleSourceForPage(QueryParam queryParam);

	List<RoleSourceRelationDTO> getAllRoleSourceRelation();

	List<RoleSourceRelationDTO> getUserResources(String roleIds);

}