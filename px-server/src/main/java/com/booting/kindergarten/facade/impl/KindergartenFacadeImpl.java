/** create by auto at 2018-01-02 14:44:30**/
package com.booting.kindergarten.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.kindergarten.facade.KindergartenFacade;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;
import com.booting.kindergarten.dto.ClassCoachRelationDTO;
import com.booting.kindergarten.dto.ClassDTO;
import com.booting.kindergarten.entity.ClassCoachRelationEntity;
import com.booting.kindergarten.entity.ClassEntity;
import com.booting.kindergarten.service.ClassCoachRelationService;
import com.booting.kindergarten.service.ClassService;
import com.booting.kindergarten.dto.ClassTeacherRelationDTO;
import com.booting.kindergarten.dto.CoachDTO;
import com.booting.kindergarten.entity.ClassTeacherRelationEntity;
import com.booting.kindergarten.entity.CoachEntity;
import com.booting.kindergarten.service.ClassTeacherRelationService;
import com.booting.kindergarten.service.CoachService;
import com.booting.kindergarten.dto.KindergartenDTO;
import com.booting.kindergarten.entity.KindergartenEntity;
import com.booting.kindergarten.service.KindergartenService;
import com.booting.kindergarten.dto.ParentAssessmentDTO;
import com.booting.kindergarten.entity.ParentAssessmentEntity;
import com.booting.kindergarten.service.ParentAssessmentService;
import com.booting.kindergarten.dto.ParentDTO;
import com.booting.kindergarten.entity.ParentEntity;
import com.booting.kindergarten.service.ParentService;
import com.booting.kindergarten.dto.PhysicalDataDTO;
import com.booting.kindergarten.entity.PhysicalDataEntity;
import com.booting.kindergarten.service.PhysicalDataService;
import com.booting.kindergarten.service.StudentParentRelationService;
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.kindergarten.dto.StudentParentRelationDTO;
import com.booting.kindergarten.entity.StudentEntity;
import com.booting.kindergarten.entity.StudentParentRelationEntity;
import com.booting.kindergarten.service.StudentService;
import com.booting.kindergarten.dto.TeacherDTO;
import com.booting.kindergarten.entity.TeacherEntity;
import com.booting.kindergarten.service.TeacherService;

@Service("kindergartenFacade")
public class KindergartenFacadeImpl implements KindergartenFacade {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ClassService classService;

	@Autowired
	private ClassTeacherRelationService classTeacherRelationService;

	@Autowired
	private KindergartenService kindergartenService;

	@Autowired
	private ParentAssessmentService parentAssessmentService;

	@Autowired
	private ParentService parentService;

	@Autowired
	private PhysicalDataService physicalDataService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentParentRelationService studentParentRelationService;
	
	@Autowired
	private ClassCoachRelationService classCoachRelationService;
	
	@Autowired
	private CoachService coachService;
	
	@Override
	public Long saveClass(ClassDTO classDTO) {
		if (null == classDTO) {
			return null;
		}
		ClassEntity entity = toClassEntity(classDTO);
		classDTO = classService.save(entity);
		return classDTO.getClassId();
	}

	@Override
	public void batchSaveClass(List<ClassDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ClassEntity> entities = toClassEntities(dtos);
		classService.batchSave(entities);
	}

	@Override
	public int updateClass(ClassDTO classDTO) {
		classDTO = classService.updateBySql(classDTO);
		return 1;
	}

	@Override
	public void batchUpdateClass(List<ClassDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		classService.batchUpdate(dtos);
	}

	@Override
	public int deleteClass(long classId) {
		return classService.delete(classId);
	}

	@Override
	public ClassDTO getClass(long classId) {
		return classService.get(classId);
	}

	@Override
	public ClassDTO getClass(ClassDTO classDTO) {
		return classService.get(classDTO);
	}

	@Override
	public List<ClassDTO> getClassList(ClassDTO classDTO) {
		return classService.getSimpleList(classDTO);
	}

	@Override
	public PageList<ClassDTO> getClassListForPage(ClassDTO classDTO, int pageNumber, int pageSize) {
		return classService.getSimpleListForPage(classDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ClassDTO> getClassListForPage(QueryParam queryParam) {
		return classService.getSimpleListForPage(queryParam);
	}

	@Override
	public ClassEntity toClassEntity(ClassDTO dto) {
		ClassEntity entity = new ClassEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ClassEntity> toClassEntities(List<ClassDTO> dtos) {
		List<ClassEntity> entities = new ArrayList<>();
		for (ClassDTO dto : dtos) {
			entities.add(toClassEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveClassTeacherRelation(ClassTeacherRelationDTO classTeacherRelationDTO) {
		if (null == classTeacherRelationDTO) {
			return null;
		}
		ClassTeacherRelationEntity entity = toClassTeacherRelationEntity(classTeacherRelationDTO);
		classTeacherRelationDTO = classTeacherRelationService.save(entity);
		return classTeacherRelationDTO.getId();
	}

	@Override
	public void batchSaveClassTeacherRelation(List<ClassTeacherRelationDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ClassTeacherRelationEntity> entities = toClassTeacherRelationEntities(dtos);
		classTeacherRelationService.batchSave(entities);
	}

	@Override
	public int updateClassTeacherRelation(ClassTeacherRelationDTO classTeacherRelationDTO) {
		classTeacherRelationDTO = classTeacherRelationService.updateBySql(classTeacherRelationDTO);
		return 1;
	}

	@Override
	public void batchUpdateClassTeacherRelation(List<ClassTeacherRelationDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		classTeacherRelationService.batchUpdate(dtos);
	}

	@Override
	public int deleteClassTeacherRelation(long id) {
		return classTeacherRelationService.delete(id);
	}

	@Override
	public ClassTeacherRelationDTO getClassTeacherRelation(long id) {
		return classTeacherRelationService.get(id);
	}

	@Override
	public ClassTeacherRelationDTO getClassTeacherRelation(ClassTeacherRelationDTO classTeacherRelationDTO) {
		return classTeacherRelationService.get(classTeacherRelationDTO);
	}

	@Override
	public List<ClassTeacherRelationDTO> getClassTeacherRelationList(ClassTeacherRelationDTO classTeacherRelationDTO) {
		return classTeacherRelationService.getSimpleList(classTeacherRelationDTO);
	}

	@Override
	public PageList<ClassTeacherRelationDTO> getClassTeacherRelationListForPage(ClassTeacherRelationDTO classTeacherRelationDTO, int pageNumber, int pageSize) {
		return classTeacherRelationService.getSimpleListForPage(classTeacherRelationDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ClassTeacherRelationDTO> getClassTeacherRelationListForPage(QueryParam queryParam) {
		return classTeacherRelationService.getSimpleListForPage(queryParam);
	}

	@Override
	public ClassTeacherRelationEntity toClassTeacherRelationEntity(ClassTeacherRelationDTO dto) {
		ClassTeacherRelationEntity entity = new ClassTeacherRelationEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ClassTeacherRelationEntity> toClassTeacherRelationEntities(List<ClassTeacherRelationDTO> dtos) {
		List<ClassTeacherRelationEntity> entities = new ArrayList<>();
		for (ClassTeacherRelationDTO dto : dtos) {
			entities.add(toClassTeacherRelationEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveKindergarten(KindergartenDTO kindergartenDTO) {
		if (null == kindergartenDTO) {
			return null;
		}
		KindergartenEntity entity = toKindergartenEntity(kindergartenDTO);
		kindergartenDTO = kindergartenService.save(entity);
		return kindergartenDTO.getSchoolId();
	}

	@Override
	public void batchSaveKindergarten(List<KindergartenDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<KindergartenEntity> entities = toKindergartenEntities(dtos);
		kindergartenService.batchSave(entities);
	}

	@Override
	public int updateKindergarten(KindergartenDTO kindergartenDTO) {
		kindergartenDTO = kindergartenService.updateBySql(kindergartenDTO);
		return 1;
	}

	@Override
	public void batchUpdateKindergarten(List<KindergartenDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		kindergartenService.batchUpdate(dtos);
	}

	@Override
	public int deleteKindergarten(long schoolId) {
		return kindergartenService.delete(schoolId);
	}

	@Override
	public KindergartenDTO getKindergarten(long schoolId) {
		return kindergartenService.get(schoolId);
	}

	@Override
	public KindergartenDTO getKindergarten(KindergartenDTO kindergartenDTO) {
		return kindergartenService.get(kindergartenDTO);
	}

	@Override
	public List<KindergartenDTO> getKindergartenList(KindergartenDTO kindergartenDTO) {
		return kindergartenService.getSimpleList(kindergartenDTO);
	}

	@Override
	public PageList<KindergartenDTO> getKindergartenListForPage(KindergartenDTO kindergartenDTO, int pageNumber, int pageSize) {
		return kindergartenService.getSimpleListForPage(kindergartenDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<KindergartenDTO> getKindergartenListForPage(QueryParam queryParam) {
		return kindergartenService.getSimpleListForPage(queryParam);
	}

	@Override
	public KindergartenEntity toKindergartenEntity(KindergartenDTO dto) {
		KindergartenEntity entity = new KindergartenEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<KindergartenEntity> toKindergartenEntities(List<KindergartenDTO> dtos) {
		List<KindergartenEntity> entities = new ArrayList<>();
		for (KindergartenDTO dto : dtos) {
			entities.add(toKindergartenEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveParentAssessment(ParentAssessmentDTO parentAssessmentDTO) {
		if (null == parentAssessmentDTO) {
			return null;
		}
		ParentAssessmentEntity entity = toParentAssessmentEntity(parentAssessmentDTO);
		parentAssessmentDTO = parentAssessmentService.save(entity);
		return parentAssessmentDTO.getInfoId();
	}

	@Override
	public void batchSaveParentAssessment(List<ParentAssessmentDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ParentAssessmentEntity> entities = toParentAssessmentEntities(dtos);
		parentAssessmentService.batchSave(entities);
	}

	@Override
	public int updateParentAssessment(ParentAssessmentDTO parentAssessmentDTO) {
		parentAssessmentDTO = parentAssessmentService.updateBySql(parentAssessmentDTO);
		return 1;
	}

	@Override
	public void batchUpdateParentAssessment(List<ParentAssessmentDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		parentAssessmentService.batchUpdate(dtos);
	}

	@Override
	public int deleteParentAssessment(long infoId) {
		return parentAssessmentService.delete(infoId);
	}

	@Override
	public ParentAssessmentDTO getParentAssessment(long infoId) {
		return parentAssessmentService.get(infoId);
	}

	@Override
	public ParentAssessmentDTO getParentAssessment(ParentAssessmentDTO parentAssessmentDTO) {
		return parentAssessmentService.get(parentAssessmentDTO);
	}

	@Override
	public List<ParentAssessmentDTO> getParentAssessmentList(ParentAssessmentDTO parentAssessmentDTO) {
		return parentAssessmentService.getSimpleList(parentAssessmentDTO);
	}

	@Override
	public PageList<ParentAssessmentDTO> getParentAssessmentListForPage(ParentAssessmentDTO parentAssessmentDTO, int pageNumber, int pageSize) {
		return parentAssessmentService.getSimpleListForPage(parentAssessmentDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ParentAssessmentDTO> getParentAssessmentListForPage(QueryParam queryParam) {
		return parentAssessmentService.getSimpleListForPage(queryParam);
	}

	@Override
	public ParentAssessmentEntity toParentAssessmentEntity(ParentAssessmentDTO dto) {
		ParentAssessmentEntity entity = new ParentAssessmentEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ParentAssessmentEntity> toParentAssessmentEntities(List<ParentAssessmentDTO> dtos) {
		List<ParentAssessmentEntity> entities = new ArrayList<>();
		for (ParentAssessmentDTO dto : dtos) {
			entities.add(toParentAssessmentEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveParent(ParentDTO parentDTO) {
		if (null == parentDTO) {
			return null;
		}
		ParentEntity entity = toParentEntity(parentDTO);
		parentDTO = parentService.save(entity);
		return parentDTO.getParentId();
	}

	@Override
	public void batchSaveParent(List<ParentDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ParentEntity> entities = toParentEntities(dtos);
		parentService.batchSave(entities);
	}

	@Override
	public int updateParent(ParentDTO parentDTO) {
		parentDTO = parentService.updateBySql(parentDTO);
		return 1;
	}

	@Override
	public void batchUpdateParent(List<ParentDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		parentService.batchUpdate(dtos);
	}

	@Override
	public int deleteParent(long parentId) {
		return parentService.delete(parentId);
	}

	@Override
	public ParentDTO getParent(long parentId) {
		return parentService.get(parentId);
	}

	@Override
	public ParentDTO getParent(ParentDTO parentDTO) {
		return parentService.get(parentDTO);
	}

	@Override
	public List<ParentDTO> getParentList(ParentDTO parentDTO) {
		return parentService.getSimpleList(parentDTO);
	}

	@Override
	public PageList<ParentDTO> getParentListForPage(ParentDTO parentDTO, int pageNumber, int pageSize) {
		return parentService.getSimpleListForPage(parentDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ParentDTO> getParentListForPage(QueryParam queryParam) {
		return parentService.getSimpleListForPage(queryParam);
	}

	@Override
	public ParentEntity toParentEntity(ParentDTO dto) {
		ParentEntity entity = new ParentEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ParentEntity> toParentEntities(List<ParentDTO> dtos) {
		List<ParentEntity> entities = new ArrayList<>();
		for (ParentDTO dto : dtos) {
			entities.add(toParentEntity(dto));
		}
		return entities;
	}

	@Override
	public Long savePhysicalData(PhysicalDataDTO physicalDataDTO) {
		if (null == physicalDataDTO) {
			return null;
		}
		PhysicalDataEntity entity = toPhysicalDataEntity(physicalDataDTO);
		physicalDataDTO = physicalDataService.save(entity);
		return physicalDataDTO.getId();
	}

	@Override
	public void batchSavePhysicalData(List<PhysicalDataDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<PhysicalDataEntity> entities = toPhysicalDataEntities(dtos);
		physicalDataService.batchSave(entities);
	}

	@Override
	public int updatePhysicalData(PhysicalDataDTO physicalDataDTO) {
		physicalDataDTO = physicalDataService.updateBySql(physicalDataDTO);
		return 1;
	}

	@Override
	public void batchUpdatePhysicalData(List<PhysicalDataDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		physicalDataService.batchUpdate(dtos);
	}

	@Override
	public int deletePhysicalData(long id) {
		return physicalDataService.delete(id);
	}

	@Override
	public PhysicalDataDTO getPhysicalData(long id) {
		return physicalDataService.get(id);
	}

	@Override
	public PhysicalDataDTO getPhysicalData(PhysicalDataDTO physicalDataDTO) {
		return physicalDataService.get(physicalDataDTO);
	}

	@Override
	public List<PhysicalDataDTO> getPhysicalDataList(PhysicalDataDTO physicalDataDTO) {
		return physicalDataService.getSimpleList(physicalDataDTO);
	}

	@Override
	public PageList<PhysicalDataDTO> getPhysicalDataListForPage(PhysicalDataDTO physicalDataDTO, int pageNumber, int pageSize) {
		return physicalDataService.getSimpleListForPage(physicalDataDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<PhysicalDataDTO> getPhysicalDataListForPage(QueryParam queryParam) {
		return physicalDataService.getSimpleListForPage(queryParam);
	}

	@Override
	public PhysicalDataEntity toPhysicalDataEntity(PhysicalDataDTO dto) {
		PhysicalDataEntity entity = new PhysicalDataEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<PhysicalDataEntity> toPhysicalDataEntities(List<PhysicalDataDTO> dtos) {
		List<PhysicalDataEntity> entities = new ArrayList<>();
		for (PhysicalDataDTO dto : dtos) {
			entities.add(toPhysicalDataEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveStudent(StudentDTO studentDTO) {
		if (null == studentDTO) {
			return null;
		}
		StudentEntity entity = toStudentEntity(studentDTO);
		studentDTO = studentService.save(entity);
		return studentDTO.getStudentId();
	}

	@Override
	public void batchSaveStudent(List<StudentDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<StudentEntity> entities = toStudentEntities(dtos);
		studentService.batchSave(entities);
	}

	@Override
	public int updateStudent(StudentDTO studentDTO) {
		studentDTO = studentService.updateBySql(studentDTO);
		return 1;
	}

	@Override
	public void batchUpdateStudent(List<StudentDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		studentService.batchUpdate(dtos);
	}

	@Override
	public int deleteStudent(long studentId) {
		return studentService.delete(studentId);
	}

	@Override
	public StudentDTO getStudent(long studentId) {
		return studentService.get(studentId);
	}

	@Override
	public StudentDTO getStudent(StudentDTO studentDTO) {
		return studentService.get(studentDTO);
	}

	@Override
	public List<StudentDTO> getStudentList(StudentDTO studentDTO) {
		return studentService.getSimpleList(studentDTO);
	}

	@Override
	public PageList<StudentDTO> getStudentListForPage(StudentDTO studentDTO, int pageNumber, int pageSize) {
		return studentService.getSimpleListForPage(studentDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<StudentDTO> getStudentListForPage(QueryParam queryParam) {
		return studentService.getSimpleListForPage(queryParam);
	}

	@Override
	public StudentEntity toStudentEntity(StudentDTO dto) {
		StudentEntity entity = new StudentEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<StudentEntity> toStudentEntities(List<StudentDTO> dtos) {
		List<StudentEntity> entities = new ArrayList<>();
		for (StudentDTO dto : dtos) {
			entities.add(toStudentEntity(dto));
		}
		return entities;
	}

	@Override
	public Long saveTeacher(TeacherDTO teacherDTO) {
		if (null == teacherDTO) {
			return null;
		}
		TeacherEntity entity = toTeacherEntity(teacherDTO);
		teacherDTO = teacherService.save(entity);
		return teacherDTO.getTeacherId();
	}

	@Override
	public void batchSaveTeacher(List<TeacherDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<TeacherEntity> entities = toTeacherEntities(dtos);
		teacherService.batchSave(entities);
	}

	@Override
	public int updateTeacher(TeacherDTO teacherDTO) {
		teacherDTO = teacherService.updateBySql(teacherDTO);
		return 1;
	}

	@Override
	public void batchUpdateTeacher(List<TeacherDTO> dtos) {
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		teacherService.batchUpdate(dtos);
	}

	@Override
	public int deleteTeacher(long teacherId) {
		return teacherService.delete(teacherId);
	}

	@Override
	public TeacherDTO getTeacher(long teacherId) {
		return teacherService.get(teacherId);
	}

	@Override
	public TeacherDTO getTeacher(TeacherDTO teacherDTO) {
		return teacherService.get(teacherDTO);
	}

	@Override
	public List<TeacherDTO> getTeacherList(TeacherDTO teacherDTO) {
		return teacherService.getSimpleList(teacherDTO);
	}

	@Override
	public PageList<TeacherDTO> getTeacherListForPage(TeacherDTO teacherDTO, int pageNumber, int pageSize) {
		return teacherService.getSimpleListForPage(teacherDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<TeacherDTO> getTeacherListForPage(QueryParam queryParam) {
		return teacherService.getSimpleListForPage(queryParam);
	}

	@Override
	public TeacherEntity toTeacherEntity(TeacherDTO dto) {
		TeacherEntity entity = new TeacherEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<TeacherEntity> toTeacherEntities(List<TeacherDTO> dtos) {
		List<TeacherEntity> entities = new ArrayList<>();
		for (TeacherDTO dto : dtos) {
			entities.add(toTeacherEntity(dto));
		}
		return entities;
	}

	@Override
	public <T> List<T> getList(T dto) {
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(T dto, int pageNumber, int pageSize) {
		return null;
	}

	@Override
	public <T> PageList<T> getListForPage(QueryParam queryParam, Class<T> clazz) {
		return null;
	}

	@Override
	public void updateBySql(KindergartenDTO dto) {
		this.kindergartenService.updateBySql(dto);
	}

	@Override
	public void updateBySql(ClassDTO dto) {
		this.classService.updateBySql(dto);
	}

	@Override
	public void updateBySql(TeacherDTO dto) {
		this.teacherService.updateBySql(dto);
	}

	@Override
	public void updateBySql(StudentDTO dto) {
		this.studentService.updateBySql(dto);
	}

	@Override
	public void deleteClassTeacherRelationByClassId(Long classId) {
		Map<String, Object> params = new HashMap<>();
		params.put("classId", classId);
		this.classTeacherRelationService.deleteClassTeacherRelationByParams(params);
	}

	@Override
	public void deleteClassTeacherRelationByTeacherId(Long teacherId) {
		Map<String, Object> params = new HashMap<>();
		params.put("teacherId", teacherId);
		this.classTeacherRelationService.deleteClassTeacherRelationByParams(params);
	}

	@Override
	public void updateBySql(PhysicalDataDTO dto) {
		this.physicalDataService.updateBySql(dto);
	}

	@Override
	public void deletePhysicalDataBySql(PhysicalDataDTO params) {
		this.physicalDataService.deletePhysicalDataBySql(params);
	}

	@Override
	public Long saveStudentParentRelation(StudentParentRelationDTO studentParentRelationDTO){
		if (null == studentParentRelationDTO) {
			return null;
		}
		StudentParentRelationEntity entity = toStudentParentRelationEntity(studentParentRelationDTO);
		studentParentRelationDTO = studentParentRelationService.save(entity);
		return studentParentRelationDTO.getId();
	}

	@Override
	public void batchSaveStudentParentRelation(List<StudentParentRelationDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<StudentParentRelationEntity> entities = toStudentParentRelationEntities(dtos);
		studentParentRelationService.batchSave(entities);
	}

	@Override
	public int updateStudentParentRelation(StudentParentRelationDTO studentParentRelationDTO){
		studentParentRelationDTO = studentParentRelationService.updateBySql(studentParentRelationDTO);
		return 1;
	}

	@Override
	public void batchUpdateStudentParentRelation(List<StudentParentRelationDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		studentParentRelationService.batchUpdate(dtos);
	}

	@Override
	public int deleteStudentParentRelation(long id){
		return studentParentRelationService.delete(id);
	}

	@Override
	public StudentParentRelationDTO getStudentParentRelation(long id){
		return studentParentRelationService.get(id);
	}

	@Override
	public StudentParentRelationDTO getStudentParentRelation(StudentParentRelationDTO studentParentRelationDTO){
		return studentParentRelationService.get(studentParentRelationDTO);
	}

	@Override
	public List<StudentParentRelationDTO> getStudentParentRelationList(StudentParentRelationDTO studentParentRelationDTO){
		return studentParentRelationService.getSimpleList(studentParentRelationDTO);
	}

	@Override
	public PageList<StudentParentRelationDTO> getStudentParentRelationListForPage(StudentParentRelationDTO studentParentRelationDTO, int pageNumber, int pageSize){
		return studentParentRelationService.getSimpleListForPage(studentParentRelationDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<StudentParentRelationDTO> getStudentParentRelationListForPage(QueryParam queryParam){
		return studentParentRelationService.getSimpleListForPage(queryParam);
	}

	@Override
	public StudentParentRelationEntity toStudentParentRelationEntity(StudentParentRelationDTO dto){
		StudentParentRelationEntity entity = new StudentParentRelationEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<StudentParentRelationEntity> toStudentParentRelationEntities(List<StudentParentRelationDTO> dtos){
		List<StudentParentRelationEntity> entities = new ArrayList<>();
		for(StudentParentRelationDTO dto : dtos){
			entities.add(toStudentParentRelationEntity(dto));
		}
		return entities;
	}
	
	@Override
	public Long saveClassCoachRelation(ClassCoachRelationDTO classCoachRelationDTO){
		if (null == classCoachRelationDTO) {
			return null;
		}
		ClassCoachRelationEntity entity = toClassCoachRelationEntity(classCoachRelationDTO);
		classCoachRelationDTO = classCoachRelationService.save(entity);
		return classCoachRelationDTO.getId();
	}

	@Override
	public void batchSaveClassCoachRelation(List<ClassCoachRelationDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<ClassCoachRelationEntity> entities = toClassCoachRelationEntities(dtos);
		classCoachRelationService.batchSave(entities);
	}

	@Override
	public int updateClassCoachRelation(ClassCoachRelationDTO classCoachRelationDTO){
		classCoachRelationDTO = classCoachRelationService.updateBySql(classCoachRelationDTO);
		return 1;
	}

	@Override
	public void batchUpdateClassCoachRelation(List<ClassCoachRelationDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		classCoachRelationService.batchUpdate(dtos);
	}

	@Override
	public int deleteClassCoachRelation(long id){
		return classCoachRelationService.delete(id);
	}

	@Override
	public ClassCoachRelationDTO getClassCoachRelation(long id){
		return classCoachRelationService.get(id);
	}

	@Override
	public ClassCoachRelationDTO getClassCoachRelation(ClassCoachRelationDTO classCoachRelationDTO){
		return classCoachRelationService.get(classCoachRelationDTO);
	}

	@Override
	public List<ClassCoachRelationDTO> getClassCoachRelationList(ClassCoachRelationDTO classCoachRelationDTO){
		return classCoachRelationService.getSimpleList(classCoachRelationDTO);
	}

	@Override
	public PageList<ClassCoachRelationDTO> getClassCoachRelationListForPage(ClassCoachRelationDTO classCoachRelationDTO, int pageNumber, int pageSize){
		return classCoachRelationService.getSimpleListForPage(classCoachRelationDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<ClassCoachRelationDTO> getClassCoachRelationListForPage(QueryParam queryParam){
		return classCoachRelationService.getSimpleListForPage(queryParam);
	}

	@Override
	public ClassCoachRelationEntity toClassCoachRelationEntity(ClassCoachRelationDTO dto){
		ClassCoachRelationEntity entity = new ClassCoachRelationEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<ClassCoachRelationEntity> toClassCoachRelationEntities(List<ClassCoachRelationDTO> dtos){
		List<ClassCoachRelationEntity> entities = new ArrayList<>();
		for(ClassCoachRelationDTO dto : dtos){
			entities.add(toClassCoachRelationEntity(dto));
		}
		return entities;
	}
	
	@Override
	public Long saveCoach(CoachDTO coachDTO){
		if (null == coachDTO) {
			return null;
		}
		CoachEntity entity = toCoachEntity(coachDTO);
		coachDTO = coachService.save(entity);
		return coachDTO.getCoachId();
	}

	@Override
	public void batchSaveCoach(List<CoachDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		List<CoachEntity> entities = toCoachEntities(dtos);
		coachService.batchSave(entities);
	}

	@Override
	public int updateCoach(CoachDTO coachDTO){
		coachDTO = coachService.updateBySql(coachDTO);
		return 1;
	}

	@Override
	public void batchUpdateCoach(List<CoachDTO> dtos){
		if (null == dtos || dtos.isEmpty()) {
			return;
		}
		coachService.batchUpdate(dtos);
	}

	@Override
	public int deleteCoach(long coachId){
		return coachService.delete(coachId);
	}

	@Override
	public CoachDTO getCoach(long coachId){
		return coachService.get(coachId);
	}

	@Override
	public CoachDTO getCoach(CoachDTO coachDTO){
		return coachService.get(coachDTO);
	}

	@Override
	public List<CoachDTO> getCoachList(CoachDTO coachDTO){
		return coachService.getSimpleList(coachDTO);
	}

	@Override
	public PageList<CoachDTO> getCoachListForPage(CoachDTO coachDTO, int pageNumber, int pageSize){
		return coachService.getSimpleListForPage(coachDTO, pageNumber, pageSize);
	}

	@Override
	public PageList<CoachDTO> getCoachListForPage(QueryParam queryParam){
		return coachService.getSimpleListForPage(queryParam);
	}

	@Override
	public CoachEntity toCoachEntity(CoachDTO dto){
		CoachEntity entity = new CoachEntity();
		CglibBeanUtils.copy(dto, entity);
		return entity;
	}

	@Override
	public List<CoachEntity> toCoachEntities(List<CoachDTO> dtos){
		List<CoachEntity> entities = new ArrayList<>();
		for(CoachDTO dto : dtos){
			entities.add(toCoachEntity(dto));
		}
		return entities;
	}

	@Override
	public void deleteClassCoachRelationByClassId(Long classId) {
		Map<String, Object> params = new HashMap<>();
		params.put("classId", classId);
		this.classCoachRelationService.deleteClassCoachRelationByParams(params);
	}

	@Override
	public void deleteClassCoachRelationByCoachId(Long coachId) {
		Map<String, Object> params = new HashMap<>();
		params.put("coachId", coachId);
		this.classCoachRelationService.deleteClassCoachRelationByParams(params);
	}

	@Override
	public CoachDTO updateBySql(CoachDTO coachDTO) {
		return this.coachService.updateBySql(coachDTO);
	}

  @Override
  public List<PhysicalDataDTO> getPhysicalDataList(PhysicalDataDTO physicalDataDTO, Integer num) {
    return this.physicalDataService.getPhysicalDataList(physicalDataDTO, num);
  }
}