/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.service;

import com.star.framework.jdbc.service.BaseService;

import java.util.Map;

import com.booting.kindergarten.dto.ClassTeacherRelationDTO;
import com.booting.kindergarten.entity.ClassTeacherRelationEntity;

/**
 * 班级和老师关系服务
 *
 * @author auto
 *
 */
public interface ClassTeacherRelationService extends BaseService<ClassTeacherRelationEntity, ClassTeacherRelationDTO> {

	public void deleteClassTeacherRelationByParams(Map<String, Object> params);

}