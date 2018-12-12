/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.pkg.dto.ServiceAttributeRelationDTO;
import com.booting.pkg.entity.ServiceAttributeRelationEntity;

/**
 * 服务属性关系服务
 *
 * @author auto
 *
 */
public interface ServiceAttributeRelationService extends BaseService<ServiceAttributeRelationEntity, ServiceAttributeRelationDTO> {

	void deleteServiceAttributeRelationBySql(ServiceAttributeRelationDTO dto);

}