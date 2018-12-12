/** create by auto at 2017-06-02 22:16:20**/
package com.booting.pkg.service;

import com.star.framework.jdbc.service.BaseService;
import com.booting.pkg.dto.PackageAttributeValueDTO;
import com.booting.pkg.entity.PackageAttributeValueEntity;

/**
 * 套餐属性值关系服务
 *
 * @author auto
 *
 */
public interface PackageAttributeValueService extends BaseService<PackageAttributeValueEntity, PackageAttributeValueDTO> {

	void deletePackageAttributeValueBySql(PackageAttributeValueDTO valueDTO);

}