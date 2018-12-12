/** create by auto at 2018-04-19 11:44:40**/
package com.booting.kindergarten.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.Map;

import com.booting.kindergarten.dto.ClassCoachRelationDTO;
import com.booting.kindergarten.entity.ClassCoachRelationEntity;

/**
 * 班级和教练关系服务
 *
 * @author auto
 *
 */
public interface ClassCoachRelationService extends BaseService<ClassCoachRelationEntity, ClassCoachRelationDTO> {

	public void deleteClassCoachRelationByParams(Map<String, Object> params);

}