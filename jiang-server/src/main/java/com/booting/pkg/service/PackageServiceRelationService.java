/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.pkg.dto.PackageServiceRelationDTO;
import com.booting.pkg.entity.PackageServiceRelationEntity;

/**
 * 套餐服务关系服务
 *
 * @author auto
 *
 */
public interface PackageServiceRelationService extends BaseService<PackageServiceRelationEntity, PackageServiceRelationDTO> {

	void deletePackageServiceRelationBySql(PackageServiceRelationDTO dto);

}