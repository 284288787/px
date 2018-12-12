/** create by auto at 2017-05-24 16:40:27**/
package com.booting.system.service;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.jdbc.service.BaseService;
import com.booting.system.dto.UserRoleRelationDTO;
import com.booting.system.entity.UserRoleRelationEntity;

/**
 * 用户角色关系服务
 *
 * @author auto
 *
 */
public interface UserRoleRelationService extends BaseService<UserRoleRelationEntity, UserRoleRelationDTO> {

	/**
	 * 删除满足条件的记录 (物理删除)
	 * @author liuhua
	 *
	 * @param dto
	 * @return
	 */
	int deleteBySql(UserRoleRelationDTO dto);

	/**
	 * 获取用户所拥有的角色
	 * @author liuhua
	 *
	 * @param queryParam
	 * @return
	 */
	PageList<UserRoleRelationDTO> getUserRole(QueryParam queryParam);

}