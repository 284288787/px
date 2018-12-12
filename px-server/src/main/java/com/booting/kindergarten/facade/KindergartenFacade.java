/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.facade;

import java.io.Serializable;
import java.util.List;

import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.booting.kindergarten.dto.ClassCoachRelationDTO;
import com.booting.kindergarten.dto.ClassDTO;
import com.booting.kindergarten.entity.ClassCoachRelationEntity;
import com.booting.kindergarten.entity.ClassEntity;
import com.booting.kindergarten.dto.ClassTeacherRelationDTO;
import com.booting.kindergarten.dto.CoachDTO;
import com.booting.kindergarten.entity.ClassTeacherRelationEntity;
import com.booting.kindergarten.entity.CoachEntity;
import com.booting.kindergarten.dto.KindergartenDTO;
import com.booting.kindergarten.entity.KindergartenEntity;
import com.booting.kindergarten.dto.ParentAssessmentDTO;
import com.booting.kindergarten.entity.ParentAssessmentEntity;
import com.booting.kindergarten.dto.ParentDTO;
import com.booting.kindergarten.entity.ParentEntity;
import com.booting.kindergarten.dto.PhysicalDataDTO;
import com.booting.kindergarten.entity.PhysicalDataEntity;
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.kindergarten.dto.StudentParentRelationDTO;
import com.booting.kindergarten.entity.StudentEntity;
import com.booting.kindergarten.entity.StudentParentRelationEntity;
import com.booting.kindergarten.dto.TeacherDTO;
import com.booting.kindergarten.entity.TeacherEntity;

public interface KindergartenFacade extends Serializable {

	/**
	 * 新增 班级信息
	 */
	public Long saveClass(ClassDTO classDTO);

	/**
	 * 批量新增 班级信息
	 */
	public void batchSaveClass(List<ClassDTO> dtos);

	/**
	 * 更新 班级信息
	 */
	public int updateClass(ClassDTO classDTO);

	/**
	 * 批量 班级信息
	 */
	public void batchUpdateClass(List<ClassDTO> dtos);

	/**
	 * 删除 班级信息
	 */
	public int deleteClass(long classId);

	/**
	 * 根据主键获取 班级信息
	 */
	public ClassDTO getClass(long classId);

	/**
	 * 根据条件获取一条 班级信息
	 */
	public ClassDTO getClass(ClassDTO classDTO);

	/**
	 * 查询满足条件的 班级信息 列表(单表)
	 */
	public List<ClassDTO> getClassList(ClassDTO classDTO);

	/**
	 * 查询满足条件的 班级信息 列表(分页)(单表)
	 */
	public PageList<ClassDTO> getClassListForPage(ClassDTO classDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 班级信息 列表(分页)(单表)
	 */
	public PageList<ClassDTO> getClassListForPage(QueryParam queryParam);

	/**
	 * 班级信息DTO 转换成 Entity
	 */
	public ClassEntity toClassEntity(ClassDTO classDTO);

	/**
	 * 班级信息DTOs 转换成 Entities
	 */
	public List<ClassEntity> toClassEntities(List<ClassDTO> dtoes);

	/**
	 * 新增 班级和老师关系
	 */
	public Long saveClassTeacherRelation(ClassTeacherRelationDTO classTeacherRelationDTO);

	/**
	 * 批量新增 班级和老师关系
	 */
	public void batchSaveClassTeacherRelation(List<ClassTeacherRelationDTO> dtos);

	/**
	 * 更新 班级和老师关系
	 */
	public int updateClassTeacherRelation(ClassTeacherRelationDTO classTeacherRelationDTO);

	/**
	 * 批量 班级和老师关系
	 */
	public void batchUpdateClassTeacherRelation(List<ClassTeacherRelationDTO> dtos);

	/**
	 * 删除 班级和老师关系
	 */
	public int deleteClassTeacherRelation(long id);

	/**
	 * 根据主键获取 班级和老师关系
	 */
	public ClassTeacherRelationDTO getClassTeacherRelation(long id);

	/**
	 * 根据条件获取一条 班级和老师关系
	 */
	public ClassTeacherRelationDTO getClassTeacherRelation(ClassTeacherRelationDTO classTeacherRelationDTO);

	/**
	 * 查询满足条件的 班级和老师关系 列表(单表)
	 */
	public List<ClassTeacherRelationDTO> getClassTeacherRelationList(ClassTeacherRelationDTO classTeacherRelationDTO);

	/**
	 * 查询满足条件的 班级和老师关系 列表(分页)(单表)
	 */
	public PageList<ClassTeacherRelationDTO> getClassTeacherRelationListForPage(ClassTeacherRelationDTO classTeacherRelationDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 班级和老师关系 列表(分页)(单表)
	 */
	public PageList<ClassTeacherRelationDTO> getClassTeacherRelationListForPage(QueryParam queryParam);

	/**
	 * 班级和老师关系DTO 转换成 Entity
	 */
	public ClassTeacherRelationEntity toClassTeacherRelationEntity(ClassTeacherRelationDTO classTeacherRelationDTO);

	/**
	 * 班级和老师关系DTOs 转换成 Entities
	 */
	public List<ClassTeacherRelationEntity> toClassTeacherRelationEntities(List<ClassTeacherRelationDTO> dtoes);

	/**
	 * 新增 幼儿园信息
	 */
	public Long saveKindergarten(KindergartenDTO kindergartenDTO);

	/**
	 * 批量新增 幼儿园信息
	 */
	public void batchSaveKindergarten(List<KindergartenDTO> dtos);

	/**
	 * 更新 幼儿园信息
	 */
	public int updateKindergarten(KindergartenDTO kindergartenDTO);

	/**
	 * 批量 幼儿园信息
	 */
	public void batchUpdateKindergarten(List<KindergartenDTO> dtos);

	/**
	 * 删除 幼儿园信息
	 */
	public int deleteKindergarten(long schoolId);

	/**
	 * 根据主键获取 幼儿园信息
	 */
	public KindergartenDTO getKindergarten(long schoolId);

	/**
	 * 根据条件获取一条 幼儿园信息
	 */
	public KindergartenDTO getKindergarten(KindergartenDTO kindergartenDTO);

	/**
	 * 查询满足条件的 幼儿园信息 列表(单表)
	 */
	public List<KindergartenDTO> getKindergartenList(KindergartenDTO kindergartenDTO);

	/**
	 * 查询满足条件的 幼儿园信息 列表(分页)(单表)
	 */
	public PageList<KindergartenDTO> getKindergartenListForPage(KindergartenDTO kindergartenDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 幼儿园信息 列表(分页)(单表)
	 */
	public PageList<KindergartenDTO> getKindergartenListForPage(QueryParam queryParam);

	/**
	 * 幼儿园信息DTO 转换成 Entity
	 */
	public KindergartenEntity toKindergartenEntity(KindergartenDTO kindergartenDTO);

	/**
	 * 幼儿园信息DTOs 转换成 Entities
	 */
	public List<KindergartenEntity> toKindergartenEntities(List<KindergartenDTO> dtoes);

	/**
	 * 新增 父母自评信息
	 */
	public Long saveParentAssessment(ParentAssessmentDTO parentAssessmentDTO);

	/**
	 * 批量新增 父母自评信息
	 */
	public void batchSaveParentAssessment(List<ParentAssessmentDTO> dtos);

	/**
	 * 更新 父母自评信息
	 */
	public int updateParentAssessment(ParentAssessmentDTO parentAssessmentDTO);

	/**
	 * 批量 父母自评信息
	 */
	public void batchUpdateParentAssessment(List<ParentAssessmentDTO> dtos);

	/**
	 * 删除 父母自评信息
	 */
	public int deleteParentAssessment(long infoId);

	/**
	 * 根据主键获取 父母自评信息
	 */
	public ParentAssessmentDTO getParentAssessment(long infoId);

	/**
	 * 根据条件获取一条 父母自评信息
	 */
	public ParentAssessmentDTO getParentAssessment(ParentAssessmentDTO parentAssessmentDTO);

	/**
	 * 查询满足条件的 父母自评信息 列表(单表)
	 */
	public List<ParentAssessmentDTO> getParentAssessmentList(ParentAssessmentDTO parentAssessmentDTO);

	/**
	 * 查询满足条件的 父母自评信息 列表(分页)(单表)
	 */
	public PageList<ParentAssessmentDTO> getParentAssessmentListForPage(ParentAssessmentDTO parentAssessmentDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 父母自评信息 列表(分页)(单表)
	 */
	public PageList<ParentAssessmentDTO> getParentAssessmentListForPage(QueryParam queryParam);

	/**
	 * 父母自评信息DTO 转换成 Entity
	 */
	public ParentAssessmentEntity toParentAssessmentEntity(ParentAssessmentDTO parentAssessmentDTO);

	/**
	 * 父母自评信息DTOs 转换成 Entities
	 */
	public List<ParentAssessmentEntity> toParentAssessmentEntities(List<ParentAssessmentDTO> dtoes);

	/**
	 * 新增 父母信息
	 */
	public Long saveParent(ParentDTO parentDTO);

	/**
	 * 批量新增 父母信息
	 */
	public void batchSaveParent(List<ParentDTO> dtos);

	/**
	 * 更新 父母信息
	 */
	public int updateParent(ParentDTO parentDTO);

	/**
	 * 批量 父母信息
	 */
	public void batchUpdateParent(List<ParentDTO> dtos);

	/**
	 * 删除 父母信息
	 */
	public int deleteParent(long parentId);

	/**
	 * 根据主键获取 父母信息
	 */
	public ParentDTO getParent(long parentId);

	/**
	 * 根据条件获取一条 父母信息
	 */
	public ParentDTO getParent(ParentDTO parentDTO);

	/**
	 * 查询满足条件的 父母信息 列表(单表)
	 */
	public List<ParentDTO> getParentList(ParentDTO parentDTO);

	/**
	 * 查询满足条件的 父母信息 列表(分页)(单表)
	 */
	public PageList<ParentDTO> getParentListForPage(ParentDTO parentDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 父母信息 列表(分页)(单表)
	 */
	public PageList<ParentDTO> getParentListForPage(QueryParam queryParam);

	/**
	 * 父母信息DTO 转换成 Entity
	 */
	public ParentEntity toParentEntity(ParentDTO parentDTO);

	/**
	 * 父母信息DTOs 转换成 Entities
	 */
	public List<ParentEntity> toParentEntities(List<ParentDTO> dtoes);

	/**
	 * 新增 体测信息
	 */
	public Long savePhysicalData(PhysicalDataDTO physicalDataDTO);

	/**
	 * 批量新增 体测信息
	 */
	public void batchSavePhysicalData(List<PhysicalDataDTO> dtos);

	/**
	 * 更新 体测信息
	 */
	public int updatePhysicalData(PhysicalDataDTO physicalDataDTO);

	/**
	 * 批量 体测信息
	 */
	public void batchUpdatePhysicalData(List<PhysicalDataDTO> dtos);

	/**
	 * 删除 体测信息
	 */
	public int deletePhysicalData(long id);

	/**
	 * 根据主键获取 体测信息
	 */
	public PhysicalDataDTO getPhysicalData(long id);

	/**
	 * 根据条件获取一条 体测信息
	 */
	public PhysicalDataDTO getPhysicalData(PhysicalDataDTO physicalDataDTO);

	/**
	 * 查询满足条件的 体测信息 列表(单表)
	 */
	public List<PhysicalDataDTO> getPhysicalDataList(PhysicalDataDTO physicalDataDTO);

	/**
	 * 查询满足条件的 体测信息 列表(分页)(单表)
	 */
	public PageList<PhysicalDataDTO> getPhysicalDataListForPage(PhysicalDataDTO physicalDataDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 体测信息 列表(分页)(单表)
	 */
	public PageList<PhysicalDataDTO> getPhysicalDataListForPage(QueryParam queryParam);

	/**
	 * 体测信息DTO 转换成 Entity
	 */
	public PhysicalDataEntity toPhysicalDataEntity(PhysicalDataDTO physicalDataDTO);

	/**
	 * 体测信息DTOs 转换成 Entities
	 */
	public List<PhysicalDataEntity> toPhysicalDataEntities(List<PhysicalDataDTO> dtoes);

	/**
	 * 新增 幼儿园学生信息
	 */
	public Long saveStudent(StudentDTO studentDTO);

	/**
	 * 批量新增 幼儿园学生信息
	 */
	public void batchSaveStudent(List<StudentDTO> dtos);

	/**
	 * 更新 幼儿园学生信息
	 */
	public int updateStudent(StudentDTO studentDTO);

	/**
	 * 批量 幼儿园学生信息
	 */
	public void batchUpdateStudent(List<StudentDTO> dtos);

	/**
	 * 删除 幼儿园学生信息
	 */
	public int deleteStudent(long studentId);

	/**
	 * 根据主键获取 幼儿园学生信息
	 */
	public StudentDTO getStudent(long studentId);

	/**
	 * 根据条件获取一条 幼儿园学生信息
	 */
	public StudentDTO getStudent(StudentDTO studentDTO);

	/**
	 * 查询满足条件的 幼儿园学生信息 列表(单表)
	 */
	public List<StudentDTO> getStudentList(StudentDTO studentDTO);

	/**
	 * 查询满足条件的 幼儿园学生信息 列表(分页)(单表)
	 */
	public PageList<StudentDTO> getStudentListForPage(StudentDTO studentDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 幼儿园学生信息 列表(分页)(单表)
	 */
	public PageList<StudentDTO> getStudentListForPage(QueryParam queryParam);

	/**
	 * 幼儿园学生信息DTO 转换成 Entity
	 */
	public StudentEntity toStudentEntity(StudentDTO studentDTO);

	/**
	 * 幼儿园学生信息DTOs 转换成 Entities
	 */
	public List<StudentEntity> toStudentEntities(List<StudentDTO> dtoes);

	/**
	 * 新增 老师信息
	 */
	public Long saveTeacher(TeacherDTO teacherDTO);

	/**
	 * 批量新增 老师信息
	 */
	public void batchSaveTeacher(List<TeacherDTO> dtos);

	/**
	 * 更新 老师信息
	 */
	public int updateTeacher(TeacherDTO teacherDTO);

	/**
	 * 批量 老师信息
	 */
	public void batchUpdateTeacher(List<TeacherDTO> dtos);

	/**
	 * 删除 老师信息
	 */
	public int deleteTeacher(long teacherId);

	/**
	 * 根据主键获取 老师信息
	 */
	public TeacherDTO getTeacher(long teacherId);

	/**
	 * 根据条件获取一条 老师信息
	 */
	public TeacherDTO getTeacher(TeacherDTO teacherDTO);

	/**
	 * 查询满足条件的 老师信息 列表(单表)
	 */
	public List<TeacherDTO> getTeacherList(TeacherDTO teacherDTO);

	/**
	 * 查询满足条件的 老师信息 列表(分页)(单表)
	 */
	public PageList<TeacherDTO> getTeacherListForPage(TeacherDTO teacherDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 老师信息 列表(分页)(单表)
	 */
	public PageList<TeacherDTO> getTeacherListForPage(QueryParam queryParam);

	/**
	 * 老师信息DTO 转换成 Entity
	 */
	public TeacherEntity toTeacherEntity(TeacherDTO teacherDTO);

	/**
	 * 老师信息DTOs 转换成 Entities
	 */
	public List<TeacherEntity> toTeacherEntities(List<TeacherDTO> dtoes);

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

	public void updateBySql(KindergartenDTO dto);

	public void updateBySql(ClassDTO dto);

	public void updateBySql(TeacherDTO dto);

	public void updateBySql(StudentDTO dto);

	public void deleteClassTeacherRelationByClassId(Long classId);

	public void deleteClassTeacherRelationByTeacherId(Long teacherId);

	public void updateBySql(PhysicalDataDTO dto);

	public void deletePhysicalDataBySql(PhysicalDataDTO params);

	/**
	 * 新增 学生和长辈的关系
	 */
	public Long saveStudentParentRelation(StudentParentRelationDTO studentParentRelationDTO);

	/**
	 * 批量新增 学生和长辈的关系
	 */
	public void batchSaveStudentParentRelation(List<StudentParentRelationDTO> dtos);

	/**
	 * 更新 学生和长辈的关系
	 */
	public int updateStudentParentRelation(StudentParentRelationDTO studentParentRelationDTO);

	/**
	 * 批量 学生和长辈的关系
	 */
	public void batchUpdateStudentParentRelation(List<StudentParentRelationDTO> dtos);

	/**
	 * 删除 学生和长辈的关系
	 */
	public int deleteStudentParentRelation(long id);

	/**
	 * 根据主键获取 学生和长辈的关系
	 */
	public StudentParentRelationDTO getStudentParentRelation(long id);

	/**
	 * 根据条件获取一条 学生和长辈的关系
	 */
	public StudentParentRelationDTO getStudentParentRelation(StudentParentRelationDTO studentParentRelationDTO);

	/**
	 * 查询满足条件的 学生和长辈的关系 列表(单表)
	 */
	public List<StudentParentRelationDTO> getStudentParentRelationList(StudentParentRelationDTO studentParentRelationDTO);

	/**
	 * 查询满足条件的 学生和长辈的关系 列表(分页)(单表)
	 */
	public PageList<StudentParentRelationDTO> getStudentParentRelationListForPage(StudentParentRelationDTO studentParentRelationDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 学生和长辈的关系 列表(分页)(单表)
	 */
	public PageList<StudentParentRelationDTO> getStudentParentRelationListForPage(QueryParam queryParam);

	/**
	 * 学生和长辈的关系DTO 转换成 Entity
	 */
	public StudentParentRelationEntity toStudentParentRelationEntity(StudentParentRelationDTO studentParentRelationDTO);

	/**
	 * 学生和长辈的关系DTOs 转换成 Entities
	 */
	public List<StudentParentRelationEntity> toStudentParentRelationEntities(List<StudentParentRelationDTO> dtoes);
	
	/**
	 * 新增 班级和教练关系
	 */
	public Long saveClassCoachRelation(ClassCoachRelationDTO classCoachRelationDTO);

	/**
	 * 批量新增 班级和教练关系
	 */
	public void batchSaveClassCoachRelation(List<ClassCoachRelationDTO> dtos);

	/**
	 * 更新 班级和教练关系
	 */
	public int updateClassCoachRelation(ClassCoachRelationDTO classCoachRelationDTO);

	/**
	 * 批量 班级和教练关系
	 */
	public void batchUpdateClassCoachRelation(List<ClassCoachRelationDTO> dtos);

	/**
	 * 删除 班级和教练关系
	 */
	public int deleteClassCoachRelation(long id);

	/**
	 * 根据主键获取 班级和教练关系
	 */
	public ClassCoachRelationDTO getClassCoachRelation(long id);

	/**
	 * 根据条件获取一条 班级和教练关系
	 */
	public ClassCoachRelationDTO getClassCoachRelation(ClassCoachRelationDTO classCoachRelationDTO);

	/**
	 * 查询满足条件的 班级和教练关系 列表(单表)
	 */
	public List<ClassCoachRelationDTO> getClassCoachRelationList(ClassCoachRelationDTO classCoachRelationDTO);

	/**
	 * 查询满足条件的 班级和教练关系 列表(分页)(单表)
	 */
	public PageList<ClassCoachRelationDTO> getClassCoachRelationListForPage(ClassCoachRelationDTO classCoachRelationDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 班级和教练关系 列表(分页)(单表)
	 */
	public PageList<ClassCoachRelationDTO> getClassCoachRelationListForPage(QueryParam queryParam);

	/**
	 * 班级和教练关系DTO 转换成 Entity
	 */
	public ClassCoachRelationEntity toClassCoachRelationEntity(ClassCoachRelationDTO classCoachRelationDTO);

	/**
	 * 班级和教练关系DTOs 转换成 Entities
	 */
	public List<ClassCoachRelationEntity> toClassCoachRelationEntities(List<ClassCoachRelationDTO> dtoes);
	
	/**
	 * 新增 教练信息
	 */
	public Long saveCoach(CoachDTO coachDTO);

	/**
	 * 批量新增 教练信息
	 */
	public void batchSaveCoach(List<CoachDTO> dtos);

	/**
	 * 更新 教练信息
	 */
	public int updateCoach(CoachDTO coachDTO);

	/**
	 * 批量 教练信息
	 */
	public void batchUpdateCoach(List<CoachDTO> dtos);

	/**
	 * 删除 教练信息
	 */
	public int deleteCoach(long coachId);

	/**
	 * 根据主键获取 教练信息
	 */
	public CoachDTO getCoach(long coachId);

	/**
	 * 根据条件获取一条 教练信息
	 */
	public CoachDTO getCoach(CoachDTO coachDTO);

	/**
	 * 查询满足条件的 教练信息 列表(单表)
	 */
	public List<CoachDTO> getCoachList(CoachDTO coachDTO);

	/**
	 * 查询满足条件的 教练信息 列表(分页)(单表)
	 */
	public PageList<CoachDTO> getCoachListForPage(CoachDTO coachDTO, int pageNumber, int pageSize);

	/**
	 * 查询满足条件的 教练信息 列表(分页)(单表)
	 */
	public PageList<CoachDTO> getCoachListForPage(QueryParam queryParam);

	/**
	 * 教练信息DTO 转换成 Entity
	 */
	public CoachEntity toCoachEntity(CoachDTO coachDTO);

	/**
	 * 教练信息DTOs 转换成 Entities
	 */
	public List<CoachEntity> toCoachEntities(List<CoachDTO> dtoes);

	public void deleteClassCoachRelationByClassId(Long classId);

	public void deleteClassCoachRelationByCoachId(Long coachId);

	public CoachDTO updateBySql(CoachDTO dto);

  public List<PhysicalDataDTO> getPhysicalDataList(PhysicalDataDTO physicalDataDTO, Integer num);
}