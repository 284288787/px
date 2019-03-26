/** create by liuhua at 2018年1月2日 下午2:47:33 **/
package com.booting.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.facade.BraceletFacade;
import com.booting.common.CommonConstants;
import com.booting.common.CommonConstants.ParentType;
import com.booting.common.CommonConstants.SmsTag;
import com.booting.common.Physical;
import com.booting.common.PushInfo;
import com.booting.kindergarten.dto.ClassCoachRelationDTO;
import com.booting.kindergarten.dto.ClassDTO;
import com.booting.kindergarten.dto.ClassTeacherRelationDTO;
import com.booting.kindergarten.dto.CoachDTO;
import com.booting.kindergarten.dto.KindergartenDTO;
import com.booting.kindergarten.dto.ParentDTO;
import com.booting.kindergarten.dto.PhysicalDataDTO;
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.kindergarten.dto.StudentParentRelationDTO;
import com.booting.kindergarten.dto.TeacherDTO;
import com.booting.kindergarten.facade.KindergartenFacade;
import com.booting.member.dto.MemberDTO;
import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.service.SmsIdentityService;
import com.booting.task.TaskJob;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.specification.utils.ParamHandler;

@Service("kindergartenWebService")
public class KindergartenWebService extends BaseWebService {
  @Autowired
  private KindergartenFacade kindergartenFacade;
  @Autowired
  private BraceletFacade braceletFacade;
  @Autowired
  private SmsIdentityService smsIdentityService;

  public PageList<KindergartenDTO> getListForPageKindergarten(QueryParam queryParam, Class<KindergartenDTO> class1) {
    return kindergartenFacade.getKindergartenListForPage(queryParam);
  }

  public KindergartenDTO getKindergarten(Long schoolId) {
    KindergartenDTO params = new KindergartenDTO();
    params.setSchoolId(schoolId);
    KindergartenDTO kindergartenDTO = this.kindergartenFacade.getKindergarten(params);
    return kindergartenDTO;
  }

  public void updateKindergarten(KindergartenDTO kindergartenDTO) throws ArgsException {
    if (null == kindergartenDTO || null == kindergartenDTO.getSchoolId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    kindergartenFacade.updateKindergarten(kindergartenDTO);
  }

  public void saveKindergarten(KindergartenDTO kindergartenDTO) throws ArgsException {
    if (null == kindergartenDTO || StringUtils.isBlank(kindergartenDTO.getAccount()) || StringUtils.isBlank(kindergartenDTO.getName()) || StringUtils.isBlank(kindergartenDTO.getMobile())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    kindergartenDTO.setCreateTime(new Date());
    kindergartenDTO.setEnabled(1);
    kindergartenDTO.setDeleted(0);
    this.kindergartenFacade.saveKindergarten(kindergartenDTO);
  }

  public void enabledKindergarten(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    KindergartenDTO dto = new KindergartenDTO();
    dto.setEnabled(1);
    dto.setSchoolIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void disabledKindergarten(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    KindergartenDTO dto = new KindergartenDTO();
    dto.setEnabled(0);
    dto.setSchoolIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void deleteKindergarten(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    KindergartenDTO dto = new KindergartenDTO();
    dto.setDeleted(1);
    dto.setSchoolIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public PageList<ClassDTO> getListForPageClass(QueryParam queryParam, Class<ClassDTO> class1) {
    return kindergartenFacade.getClassListForPage(queryParam);
  }

  public ClassDTO getClass(Long classId) {
    ClassDTO params = new ClassDTO();
    params.setClassId(classId);
    ClassDTO classDTO = this.kindergartenFacade.getClass(params);
    return classDTO;
  }

  public void updateClass(ClassDTO classDTO) throws ArgsException {
    if (null == classDTO || null == classDTO.getClassId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    kindergartenFacade.updateClass(classDTO);
  }

  public void saveClass(ClassDTO classDTO) throws ArgsException {
    if (null == classDTO || StringUtils.isBlank(classDTO.getName())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    classDTO.setCreateTime(new Date());
    classDTO.setEnabled(1);
    classDTO.setDeleted(0);
    kindergartenFacade.saveClass(classDTO);
  }

  public void enabledClass(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ClassDTO dto = new ClassDTO();
    dto.setEnabled(1);
    dto.setClassIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void disabledClass(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ClassDTO dto = new ClassDTO();
    dto.setEnabled(0);
    dto.setClassIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void deleteClass(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ClassDTO dto = new ClassDTO();
    dto.setDeleted(1);
    dto.setClassIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public List<KindergartenDTO> getSchools() {
    KindergartenDTO kindergartenDTO = new KindergartenDTO();
    kindergartenDTO.setEnabled(1);
    kindergartenDTO.setDeleted(0);
    List<KindergartenDTO> list = kindergartenFacade.getKindergartenList(kindergartenDTO);
    return list;
  }

  public PageList<TeacherDTO> getListForPageTeacher(QueryParam queryParam, Class<TeacherDTO> class1) {
    return kindergartenFacade.getTeacherListForPage(queryParam);
  }

  public TeacherDTO getTeacher(Long teacherId) {
    TeacherDTO params = new TeacherDTO();
    params.setTeacherId(teacherId);
    TeacherDTO teacherDTO = this.kindergartenFacade.getTeacher(params);
    return teacherDTO;
  }

  public void updateTeacher(TeacherDTO teacherDTO) throws ArgsException {
    if (null == teacherDTO || null == teacherDTO.getTeacherId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    kindergartenFacade.updateTeacher(teacherDTO);
  }

  public void saveTeacher(TeacherDTO teacherDTO) throws ArgsException {
    if (null == teacherDTO || StringUtils.isBlank(teacherDTO.getTeacherName())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    teacherDTO.setCreateTime(new Date());
    teacherDTO.setEnabled(1);
    teacherDTO.setDeleted(0);
    kindergartenFacade.saveTeacher(teacherDTO);
  }

  public void enabledTeacher(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    TeacherDTO dto = new TeacherDTO();
    dto.setEnabled(1);
    dto.setTeacherIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void disabledTeacher(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    TeacherDTO dto = new TeacherDTO();
    dto.setEnabled(0);
    dto.setTeacherIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void deleteTeacher(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    TeacherDTO dto = new TeacherDTO();
    dto.setDeleted(1);
    dto.setTeacherIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public List<TeacherDTO> getTeachers(Long schoolId) {
    TeacherDTO params = new TeacherDTO();
    params.setSchoolId(schoolId);
    params.setDeleted(0);
    params.setEnabled(1);
    List<TeacherDTO> list = kindergartenFacade.getTeacherList(params);
    return list;
  }

  public PageList<StudentDTO> getListForPageStudent(QueryParam queryParam, Class<StudentDTO> class1) {
    return kindergartenFacade.getStudentListForPage(queryParam);
  }

  public StudentDTO getStudent(Long studentId) {
    if (null == studentId) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    StudentDTO param = new StudentDTO();
    param.setStudentId(studentId);
    StudentDTO studentDTO = this.kindergartenFacade.getStudent(param);
    return studentDTO;
  }

  public void updateStudent(StudentDTO studentDTO) throws ArgsException {
    if (null == studentDTO || null == studentDTO.getStudentId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    kindergartenFacade.updateStudent(studentDTO);
    if (StringUtils.isNotBlank(studentDTO.getGuardianMobile())) {
      ParentDTO parentDTO = getParent(studentDTO.getGuardianMobile());
      Long parentId = null;
      if (null == parentDTO || null == parentDTO.getParentId()) {
        parentDTO = new ParentDTO();
        parentDTO.setCreateTime(new Date());
        parentDTO.setMobile(studentDTO.getGuardianMobile());
        parentDTO.setName(studentDTO.getGuardianName());
        parentId = this.kindergartenFacade.saveParent(parentDTO);
      } else {
        parentId = parentDTO.getParentId();
        if (StringUtils.isNotBlank(studentDTO.getGuardianName()) && ! studentDTO.getGuardianName().equals(parentDTO.getName())) {
          parentDTO.setName(studentDTO.getGuardianName());
          this.kindergartenFacade.updateParent(parentDTO);
        }
      }
      StudentParentRelationDTO studentParentRelationDTO = getStudentParentRelation(parentId, studentDTO.getStudentId());
      if (null == studentParentRelationDTO) {
        studentParentRelationDTO = new StudentParentRelationDTO();
        studentParentRelationDTO.setParentId(parentId);
        studentParentRelationDTO.setType(studentDTO.getGuardianType());
        studentParentRelationDTO.setStudentId(studentDTO.getStudentId());
        this.kindergartenFacade.saveStudentParentRelation(studentParentRelationDTO);
      } else {
        if (studentParentRelationDTO.getType().intValue() != studentDTO.getGuardianType()) {
          studentParentRelationDTO.setType(studentDTO.getGuardianType());
          this.kindergartenFacade.updateStudentParentRelation(studentParentRelationDTO);
        }
      }
    }
  }

  public ParentDTO getParent(String mobile) {
    ParentDTO param = new ParentDTO();
    param.setMobile(mobile);
    ParentDTO parentDTO = this.kindergartenFacade.getParent(param);
    return parentDTO;
  }

  public StudentParentRelationDTO getStudentParentRelation(Long parentId, Long studentId) {
    StudentParentRelationDTO param = new StudentParentRelationDTO();
    param.setParentId(parentId);
    param.setStudentId(studentId);
    StudentParentRelationDTO dto = this.kindergartenFacade.getStudentParentRelation(param);
    return dto;
  }

  public void saveStudent(StudentDTO studentDTO) throws ArgsException {
    if (null == studentDTO || StringUtils.isBlank(studentDTO.getName())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    studentDTO.setCreateTime(new Date());
    studentDTO.setEnabled(1);
    studentDTO.setDeleted(0);
    Long studentId = kindergartenFacade.saveStudent(studentDTO);
    if (StringUtils.isNotBlank(studentDTO.getGuardianMobile())) {
      ParentDTO parentDTO = getParent(studentDTO.getGuardianMobile());
      Long parentId = null;
      if (null == parentDTO || null == parentDTO.getParentId()) {
        parentDTO = new ParentDTO();
        parentDTO.setCreateTime(new Date());
        parentDTO.setMobile(studentDTO.getGuardianMobile());
        parentDTO.setName(studentDTO.getGuardianName());
        parentId = this.kindergartenFacade.saveParent(parentDTO);
      } else {
        parentId = parentDTO.getParentId();
        if (StringUtils.isNotBlank(studentDTO.getGuardianName()) && ! studentDTO.getGuardianName().equals(parentDTO.getName())) {
          parentDTO.setName(studentDTO.getGuardianName());
          this.kindergartenFacade.updateParent(parentDTO);
        }
      }
      StudentParentRelationDTO studentParentRelationDTO = new StudentParentRelationDTO();
      studentParentRelationDTO.setParentId(parentId);
      studentParentRelationDTO.setType(studentDTO.getGuardianType());
      studentParentRelationDTO.setStudentId(studentId);
      this.kindergartenFacade.saveStudentParentRelation(studentParentRelationDTO);
    }
  }

  public void enabledStudent(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    StudentDTO dto = new StudentDTO();
    dto.setEnabled(1);
    dto.setStudentIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void disabledStudent(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    StudentDTO dto = new StudentDTO();
    dto.setEnabled(0);
    dto.setStudentIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void deleteStudent(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    StudentDTO dto = new StudentDTO();
    dto.setDeleted(1);
    dto.setStudentIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public List<ClassDTO> getClasses(Long schoolId) {
    ClassDTO params = new ClassDTO();
    params.setSchoolId(schoolId);
    params.setDeleted(0);
    params.setEnabled(1);
    List<ClassDTO> list = kindergartenFacade.getClassList(params);
    return list;
  }

  public String getClassTeacherIds(Long classId) {
    ClassTeacherRelationDTO classTeacherRelationDTO = new ClassTeacherRelationDTO();
    classTeacherRelationDTO.setClassId(classId);
    List<ClassTeacherRelationDTO> list = kindergartenFacade.getClassTeacherRelationList(classTeacherRelationDTO);
    String ids = "";
    for (ClassTeacherRelationDTO ctrd : list) {
      ids += "," + ctrd.getTeacherId() + ",";
    }
    return ids;
  }

  /**
   * 得到该老师的所有班级ids
   * 
   * @author liuhua
   *
   * @param teacherId
   * @return
   */
  public String getTeacherClassIds(Long teacherId) {
    ClassTeacherRelationDTO classTeacherRelationDTO = new ClassTeacherRelationDTO();
    classTeacherRelationDTO.setTeacherId(teacherId);
    List<ClassTeacherRelationDTO> list = kindergartenFacade.getClassTeacherRelationList(classTeacherRelationDTO);
    String ids = "";
    for (ClassTeacherRelationDTO ctrd : list) {
      ids += "," + ctrd.getClassId() + ",";
    }
    return ids;
  }

  public void saveRelationByClassTeacher(ClassTeacherRelationDTO classTeacherRelationDTO) throws ArgsException {
    if (null == classTeacherRelationDTO || null == classTeacherRelationDTO.getSchoolId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    List<ClassTeacherRelationDTO> data = new ArrayList<>();
    if (StringUtils.isNotBlank(classTeacherRelationDTO.getTeacherIds())) {
      Long classId = classTeacherRelationDTO.getClassId();
      ClassDTO classDTO = getClass(classId);
      String[] teacherIds = classTeacherRelationDTO.getTeacherIds().split(",");
      for (String id : teacherIds) {
        Long teacherId = Long.parseLong(id);
        if (teacherId.longValue() == classDTO.getTeacherId()) {
          continue;
        }
        ClassTeacherRelationDTO relation = new ClassTeacherRelationDTO();
        relation.setClassId(classId);
        relation.setTeacherId(teacherId);
        data.add(relation);
      }
      this.kindergartenFacade.deleteClassTeacherRelationByClassId(classId);
    }
    if (StringUtils.isNotBlank(classTeacherRelationDTO.getClassIds())) {
      Long teacherId = classTeacherRelationDTO.getTeacherId();
      String[] classIds = classTeacherRelationDTO.getClassIds().split(",");
      for (String id : classIds) {
        Long classId = Long.parseLong(id);
        ClassDTO classDTO = getClass(classId);
        if (teacherId.longValue() == classDTO.getTeacherId()) {
          continue;
        }
        ClassTeacherRelationDTO relation = new ClassTeacherRelationDTO();
        relation.setClassId(classId);
        relation.setTeacherId(teacherId);
        data.add(relation);
      }
      this.kindergartenFacade.deleteClassTeacherRelationByTeacherId(teacherId);
    }
    this.kindergartenFacade.batchSaveClassTeacherRelation(data);
  }

  /**
   * 得到该学校该老师的所有班主任班级ids
   * 
   * @author liuhua
   *
   * @param schoolId
   * @param teacherId
   * @return
   */
  public String getHeadTeacherClassIds(Long schoolId, Long teacherId) {
    ClassDTO params = new ClassDTO();
    params.setSchoolId(schoolId);
    params.setTeacherId(teacherId);
    params.setDeleted(0);
    params.setEnabled(1);
    List<ClassDTO> list = kindergartenFacade.getClassList(params);
    String classIds = "";
    for (ClassDTO ctrd : list) {
      classIds += "," + ctrd.getClassId() + ",";
    }
    return classIds;
  }

  public PageList<PhysicalDataDTO> getListForPagePhysicalData(QueryParam queryParam, Class<PhysicalDataDTO> class1) {
    return this.kindergartenFacade.getPhysicalDataListForPage(queryParam);
  }

  public PhysicalDataDTO getPhysicalData(Long id) {
    PhysicalDataDTO params = new PhysicalDataDTO();
    params.setId(id);
    PhysicalDataDTO physicalDataDTO = this.kindergartenFacade.getPhysicalData(params);
    return physicalDataDTO;
  }

  public void updatePhysicalData(PhysicalDataDTO physicalDataDTO) throws ArgsException {
    if (null == physicalDataDTO || null == physicalDataDTO.getId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    setPhysicalDataScore(physicalDataDTO);
    kindergartenFacade.updatePhysicalData(physicalDataDTO);
  }

  public void savePhysicalData(PhysicalDataDTO physicalDataDTO) throws ArgsException {
    if (null == physicalDataDTO || null == physicalDataDTO.getStudentId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    physicalDataDTO.setCreateTime(new Date());
    physicalDataDTO.setEnabled(1);
    physicalDataDTO.setDeleted(0);
    setPhysicalDataScore(physicalDataDTO);
    kindergartenFacade.savePhysicalData(physicalDataDTO);
  }

  public void setPhysicalDataScore(PhysicalDataDTO physicalDataDTO) {
    StudentDTO studentDTO = getStudent(physicalDataDTO.getStudentId());
    if (null == studentDTO || null == studentDTO.getBirth()) {
      return;
    }
    String key = getSexAndAge(studentDTO);
    Physical physical = CommonConstants.standard.get(key);
    int run10 = 0, jump = 0, throwTennis = 0, doubleJump = 0, sitReachScore = 0, balance = 0, statureScore = 0, weightScore = 0;
    if (null != physical) {
      run10 = physical.getScore("run10", physicalDataDTO.getRun10() / 100.0);
      jump = physical.getScore("jump", physicalDataDTO.getJump() / 100.0);
      throwTennis = physical.getScore("throwTennis", physicalDataDTO.getThrowTennis() / 100.0);
      doubleJump = physical.getScore("doubleJump", physicalDataDTO.getDoubleJump() / 100.0);
      sitReachScore = physical.getScore("sitReach", physicalDataDTO.getSitReach() / 100.0);
      balance = physical.getScore("balance", physicalDataDTO.getBalance() / 100.0);
      statureScore = physical.getScore("stature", physicalDataDTO.getStature());
      // 标准体重 男= (身高 - 80) * 0.7
      // 标准体重 女= (身高 - 70) * 0.6
      int arg1 = studentDTO.getSex() == 1 ? 80 : 70;
      double arg2 = studentDTO.getSex() == 1 ? 0.7 : 0.6;
      double bzweight = (physicalDataDTO.getStature() - arg1) * arg2;
      double bl = Math.abs(1 - bzweight / (physicalDataDTO.getWeight() / 100));
      weightScore = physical.getScore("weight", bl);
    }
    physicalDataDTO.setRun10Score(run10);
    physicalDataDTO.setJumpScore(jump);
    physicalDataDTO.setThrowTennisScore(throwTennis);
    physicalDataDTO.setDoubleJumpScore(doubleJump);
    physicalDataDTO.setSitReachScore(sitReachScore);
    physicalDataDTO.setBalanceScore(balance);
    physicalDataDTO.setStatureScore(statureScore);
    physicalDataDTO.setWeightScore(weightScore);
  }

  public String getSexAndAge(StudentDTO studentDTO) {
    Date birth = studentDTO.getBirth();
    String age = CommonConstants.getAge(birth);
    return age + "_" + studentDTO.getSex();
  }

  public void enabledPhysicalData(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PhysicalDataDTO dto = new PhysicalDataDTO();
    dto.setEnabled(1);
    dto.setIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void disabledPhysicalData(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PhysicalDataDTO dto = new PhysicalDataDTO();
    dto.setEnabled(0);
    dto.setIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void deletePhysicalData(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    PhysicalDataDTO dto = new PhysicalDataDTO();
    dto.setDeleted(1);
    dto.setIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public List<StudentDTO> getStudents(Long classId) {
    StudentDTO params = new StudentDTO();
    params.setClassId(classId);
    params.setDeleted(0);
    params.setEnabled(1);
    List<StudentDTO> list = kindergartenFacade.getStudentList(params);
    return list;
  }

  public KindergartenDTO getKindergarten(String schoolName) {
    KindergartenDTO param = new KindergartenDTO();
    param.setEnabled(1);
    param.setDeleted(0);
    param.setName(schoolName);
    KindergartenDTO kindergartenDTO = kindergartenFacade.getKindergarten(param);
    return kindergartenDTO;
  }

  public TeacherDTO getTeacher(Long schoolId, String teacherName) {
    TeacherDTO param = new TeacherDTO();
    param.setSchoolId(schoolId);
    param.setEnabled(1);
    param.setDeleted(0);
    param.setTeacherName(teacherName);
    TeacherDTO teacherDTO = kindergartenFacade.getTeacher(param);
    return teacherDTO;
  }

  public ClassDTO getClass(Long schoolId, String className) {
    ClassDTO param = new ClassDTO();
    param.setEnabled(1);
    param.setDeleted(0);
    param.setSchoolId(schoolId);
    param.setName(className);
    ClassDTO classDTO = kindergartenFacade.getClass(param);
    return classDTO;
  }

  public StudentDTO getStudent(Long classId, String studentName) {
    StudentDTO param = new StudentDTO();
    param.setEnabled(1);
    param.setDeleted(0);
    param.setClassId(classId);
    param.setName(studentName);
    StudentDTO student = kindergartenFacade.getStudent(param);
    return student;
  }

  public void batchSavePhysicalData(List<PhysicalDataDTO> list) {
    this.kindergartenFacade.batchSavePhysicalData(list);
  }

  public void deletePhysicalDataBySql(PhysicalDataDTO params) {
    kindergartenFacade.deletePhysicalDataBySql(params);
  }

  public List<StudentDTO> allChildrenOfMine(String openId) throws ArgsException {
    MemberDTO member = getMember(openId);
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    String mobile = member.getMobile();
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    ParentDTO parent = getParent(mobile);
    if (null == parent) {
      throw new ArgsException(FailureCode.ERR_801);
    }
    StudentDTO param = new StudentDTO();
    param.setGuardianMobile(mobile);
    param.setDeleted(0);
    List<StudentDTO> students = this.kindergartenFacade.getStudentList(param);
    return students;
  }

  public List<Map<String, Object>> allChildren(String openId) throws ArgsException {
    MemberDTO member = getMember(openId);
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    String mobile = member.getMobile();
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    ParentDTO parent = getParent(mobile);
    if (null == parent) {
      throw new ArgsException(FailureCode.ERR_801);
    }
    StudentParentRelationDTO param = new StudentParentRelationDTO();
    param.setParentId(parent.getParentId());
    List<StudentParentRelationDTO> studentParentRelationDTOs = this.kindergartenFacade.getStudentParentRelationList(param);
    List<Map<String, Object>> students = new ArrayList<>();
    for (StudentParentRelationDTO studentParentRelationDTO : studentParentRelationDTOs) {
      StudentDTO temp = getStudent(studentParentRelationDTO.getStudentId());
      Map<String, Object> student = toMap(temp, "yyyy-MM-dd HH:mm:ss");
      student.put("type", studentParentRelationDTO.getType());
      students.add(student);

    }
    return students;
  }

  public void invitePatriarch(ParentDTO parentDTO) throws ArgsException {
    if (null == parentDTO || null == parentDTO.getStudentId() || StringUtils.isBlank(parentDTO.getOpenId()) || StringUtils.isBlank(parentDTO.getMobile()) || null == parentDTO.getType()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO member = getMember(parentDTO.getOpenId());
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    String mobile = member.getMobile();
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    ParentDTO parent = getParent(mobile);
    if (null == parent) {
      throw new ArgsException(FailureCode.ERR_801);
    }
    ParentDTO patriarch = getParent(parentDTO.getMobile());
    Long patriarchId = null;
    if (null == patriarch) {
      patriarch = new ParentDTO();
      patriarch.setCreateTime(new Date());
      patriarch.setMobile(parentDTO.getMobile());
      patriarchId = this.kindergartenFacade.saveParent(patriarch);
    } else {
      patriarchId = patriarch.getParentId();
    }
    StudentParentRelationDTO studentParentRelationDTO = getStudentParentRelation(patriarchId, parentDTO.getStudentId());
    if (null == studentParentRelationDTO) {
      studentParentRelationDTO = new StudentParentRelationDTO();
      studentParentRelationDTO.setParentId(patriarchId);
      studentParentRelationDTO.setType(parentDTO.getType());
      studentParentRelationDTO.setStudentId(parentDTO.getStudentId());
      this.kindergartenFacade.saveStudentParentRelation(studentParentRelationDTO);
    } else {
      if (studentParentRelationDTO.getType().intValue() != parentDTO.getType()) {
        studentParentRelationDTO.setType(parentDTO.getType());
        this.kindergartenFacade.updateStudentParentRelation(studentParentRelationDTO);
      }
    }
    PushInfo push = CommonConstants.smsNotes.get("invite_success");
    push.setUserId(member.getMemberId());
    TaskJob.messages.offer(push);
  }

  public ApiResult lookupBaseData(Integer pageNo, ParentDTO parentDTO) throws ArgsException {
    String openId = parentDTO.getOpenId();
    Long studentId = parentDTO.getStudentId();
    if (null == studentId || StringUtils.isBlank(openId)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    if (null == pageNo) {
      pageNo = 1;
    }
    StudentDTO student = getStudent(studentId);
    if (null == student) {
      throw new ArgsException(FailureCode.ERR_803);
    }
    MemberDTO member = getMember(parentDTO.getOpenId());
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    String mobile = member.getMobile();
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    ParentDTO parent = getParent(mobile);
    if (null == parent) {
      throw new ArgsException(FailureCode.ERR_801);
    }
    StudentParentRelationDTO relation = getStudentParentRelation(parent.getParentId(), studentId);
    if (null == relation) {
      throw new ArgsException(FailureCode.ERR_802);
    }
    QueryParam queryParam = new QueryParam();
    queryParam.setPageNo(pageNo);
    queryParam.setPageSize(10);
    queryParam.addParam("studentId", studentId);
    queryParam.addParam("deleted", 0);
    if (StringUtils.isNotBlank(parentDTO.getBeginTime())) {
      queryParam.addParam("beginTime", parentDTO.getBeginTime() + " 00:00:00");
    }
    if (StringUtils.isNotBlank(parentDTO.getEndTime())) {
      queryParam.addParam("endTime", parentDTO.getEndTime() + " 23:59:59");
    }
    queryParam.setOrderBy("testTime");
    queryParam.setOrderType("desc");
    PageList<PhysicalDataDTO> pageList = this.kindergartenFacade.getPhysicalDataListForPage(queryParam);
    List<Map<String, Object>> data = new ArrayList<>();
    List<PhysicalDataDTO> list = pageList.getDataList();
    for (PhysicalDataDTO physicalDataDTO : list) {
      Map<String, Object> stu = new HashMap<>();
      stu.put("studentId", physicalDataDTO.getStudentId());
      stu.put("testTime", ParamHandler.getDateString(physicalDataDTO.getTestTime(), "yyyy-MM-dd"));
      stu.put("intro", physicalDataDTO.getIntro());
      stu.put("xietiaoxing", (physicalDataDTO.getSitReachScore() + physicalDataDTO.getDoubleJumpScore()) / 2.0);
      stu.put("liliang", (physicalDataDTO.getJumpScore() + physicalDataDTO.getThrowTennisScore()) / 2.0);
      stu.put("naili", physicalDataDTO.getDoubleJumpScore());
      stu.put("sudu", physicalDataDTO.getRun10Score());
      stu.put("linghuodu", (physicalDataDTO.getDoubleJumpScore() + physicalDataDTO.getBalanceScore()) / 2.0);
      stu.put("rourunxing", physicalDataDTO.getSitReachScore());
      stu.put("totalScore", physicalDataDTO.getRun10Score() + physicalDataDTO.getSitReachScore() + physicalDataDTO.getThrowTennisScore() + physicalDataDTO.getJumpScore() + physicalDataDTO.getDoubleJumpScore() + physicalDataDTO.getBalanceScore());
      stu.put("dataDetail", physicalDataDTO);
      stu.put("bmi", physicalDataDTO.getStature() / 100.0 * 2 + physicalDataDTO.getWeight() / 100.0);  //体质指数（BMI）=体重（kg）÷ 身高^2（m）
      data.add(stu);
    }
    ApiResult apiResult = new ApiResult();
    apiResult.setData(data);
    return apiResult;
  }

  public List<Map<String, Object>> inviteeList(String openId, Long studentId) throws ArgsException {
    if (StringUtils.isBlank(openId) || null == studentId) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO member = getMember(openId);
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    String mobile = member.getMobile();
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    ParentDTO parent = getParent(mobile);
    if (null == parent) {
      throw new ArgsException(FailureCode.ERR_801);
    }
    StudentParentRelationDTO param = new StudentParentRelationDTO();
    param.setStudentId(studentId);
    List<StudentParentRelationDTO> list = this.kindergartenFacade.getStudentParentRelationList(param);
    List<Map<String, Object>> parents = new ArrayList<>();
    for (StudentParentRelationDTO studentParentRelationDTO : list) {
      if (studentParentRelationDTO.getParentId().longValue() != parent.getParentId()) {
        ParentDTO parentDTO = kindergartenFacade.getParent(studentParentRelationDTO.getParentId());
        Map<String, Object> temp = toMap(parentDTO, "yyyy-MM-dd HH:mm:ss");
        temp.remove("studentId");
        temp.remove("openId");
        temp.remove("beginTime");
        temp.remove("endTime");
        parents.add(temp);
      }
    }
    return parents;
  }

  public void deletePatriarch(String openId, Long studentId, Long parentId) throws ArgsException {
    if (StringUtils.isBlank(openId) || null == studentId || null == parentId) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO member = getMember(openId);
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    String mobile = member.getMobile();
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    ParentDTO parent = getParent(mobile);
    if (null == parent) {
      throw new ArgsException(FailureCode.ERR_801);
    }
    StudentParentRelationDTO relation = getStudentParentRelation(parent.getParentId(), studentId);
    if (null == relation) {
      throw new ArgsException(FailureCode.ERR_801);
    }
    parent = kindergartenFacade.getParent(parentId);
    if (null == parent) {
      throw new ArgsException(FailureCode.ERR_804);
    }
    relation = getStudentParentRelation(parentId, studentId);
    this.kindergartenFacade.deleteStudentParentRelation(relation.getId());
  }

  public void bindChild(ParentDTO parentDTO) throws ArgsException {
    if (null == parentDTO.getType() || null == parentDTO.getStudentId() || StringUtils.isBlank(parentDTO.getOpenId()) || StringUtils.isBlank(parentDTO.getStudentBirth()) || StringUtils.isBlank(parentDTO.getStudentName()) || StringUtils.isBlank(parentDTO.getName())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    StudentDTO studentDTO = getStudent(parentDTO.getStudentId());
    if (null == studentDTO) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "没有找到编号对应的孩子，请确认编号");
    }
    if (!parentDTO.getStudentName().equals(studentDTO.getName())) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "孩子姓名不正确");
    }
    ParentType parentType = CommonConstants.geParentType(parentDTO.getType());
    if (null == parentType) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "不存在的关系");
    }
    MemberDTO member = getMember(parentDTO.getOpenId());
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "用户不存在");
    }
    if (StringUtils.isBlank(member.getMobile())) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    member.setName(parentDTO.getName());
    this.memberFacade.updateMember(member);
    ParentDTO parent = getParent(member.getMobile());
    Long parentId = null;
    if (null == parent || null == parent.getParentId()) {
      parent = new ParentDTO();
      parent.setCreateTime(new Date());
      parent.setMobile(member.getMobile());
      if (StringUtils.isBlank(parentDTO.getName())) {
        parent.setName(studentDTO.getName() + "的" + parentType.getName());
      } else {
        parent.setName(parentDTO.getName());
      }
      parentId = this.kindergartenFacade.saveParent(parent);
    } else {
      parent.setMobile(member.getMobile());
      if (StringUtils.isBlank(parentDTO.getName())) {
        parent.setName(studentDTO.getName() + "的" + parentType.getName());
      } else {
        parent.setName(parentDTO.getName());
      }
      parentId = parent.getParentId();
      this.kindergartenFacade.updateParent(parent);
    }
    studentDTO.setBirth(ParamHandler.toDate(parentDTO.getStudentBirth(), "yyyy-MM-dd"));
    studentDTO.setGuardianMobile(member.getMobile());
    studentDTO.setGuardianType(parentDTO.getType());
    this.kindergartenFacade.updateStudent(studentDTO);
    PhysicalDataDTO param = new PhysicalDataDTO();
    param.setStudentId(studentDTO.getStudentId());
    List<PhysicalDataDTO> physicalDatas = this.kindergartenFacade.getPhysicalDataList(param);
    if (null != physicalDatas) {
      for (PhysicalDataDTO physicalDataDTO : physicalDatas) {
        setPhysicalDataScore(physicalDataDTO);
        this.kindergartenFacade.updatePhysicalData(physicalDataDTO);
      }
    }
    StudentParentRelationDTO studentParentRelationDTO = getStudentParentRelation(parentId, studentDTO.getStudentId());
    if (null == studentParentRelationDTO) {
      studentParentRelationDTO = new StudentParentRelationDTO();
      studentParentRelationDTO.setParentId(parentId);
      studentParentRelationDTO.setType(studentDTO.getGuardianType());
      studentParentRelationDTO.setStudentId(studentDTO.getStudentId());
      this.kindergartenFacade.saveStudentParentRelation(studentParentRelationDTO);
    } else {
      if (studentParentRelationDTO.getType().intValue() != studentDTO.getGuardianType()) {
        studentParentRelationDTO.setType(studentDTO.getGuardianType());
        this.kindergartenFacade.updateStudentParentRelation(studentParentRelationDTO);
      }
    }
  }

  public void saveRelationByClassCoach(ClassCoachRelationDTO classCoachRelationDTO) throws ArgsException {
    if (null == classCoachRelationDTO) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    List<ClassCoachRelationDTO> data = new ArrayList<>();
    if (StringUtils.isNotBlank(classCoachRelationDTO.getCoachIds())) {
      Long classId = classCoachRelationDTO.getClassId();
      String[] coachIds = classCoachRelationDTO.getCoachIds().split(",");
      for (String id : coachIds) {
        Long coachId = Long.parseLong(id);
        ClassCoachRelationDTO relation = new ClassCoachRelationDTO();
        relation.setClassId(classId);
        relation.setCoachId(coachId);
        data.add(relation);
      }
      this.kindergartenFacade.deleteClassCoachRelationByClassId(classId);
    }
    if (StringUtils.isNotBlank(classCoachRelationDTO.getClassIds())) {
      Long coachId = classCoachRelationDTO.getCoachId();
      String[] classIds = classCoachRelationDTO.getClassIds().split(",");
      for (String id : classIds) {
        Long classId = Long.parseLong(id);
        ClassCoachRelationDTO relation = new ClassCoachRelationDTO();
        relation.setClassId(classId);
        relation.setCoachId(coachId);
        data.add(relation);
      }
      this.kindergartenFacade.deleteClassCoachRelationByCoachId(coachId);
    }
    this.kindergartenFacade.batchSaveClassCoachRelation(data);
  }

  public List<CoachDTO> getCoachs() {
    CoachDTO params = new CoachDTO();
    params.setDeleted(0);
    params.setEnabled(1);
    List<CoachDTO> list = kindergartenFacade.getCoachList(params);
    return list;
  }

  public PageList<CoachDTO> getListForPageCoach(QueryParam queryParam, Class<CoachDTO> class1) {
    return kindergartenFacade.getCoachListForPage(queryParam);
  }

  public CoachDTO getCoach(Long coachId) {
    CoachDTO params = new CoachDTO();
    params.setCoachId(coachId);
    CoachDTO coachDTO = this.kindergartenFacade.getCoach(params);
    if (null != coachDTO) {
      coachDTO.setClassRelations(getCoachClassRelation(coachDTO.getCoachId()));
    }
    return coachDTO;
  }

  public CoachDTO getCoachByMobile(String mobile) {
    CoachDTO params = new CoachDTO();
    params.setMobile(mobile);
    CoachDTO coachDTO = this.kindergartenFacade.getCoach(params);
    if (null != coachDTO) {
      coachDTO.setClassRelations(getCoachClassRelation(coachDTO.getCoachId()));
    }
    return coachDTO;
  }

  public List<ClassCoachRelationDTO> getCoachClassRelation(Long coachId) {
    ClassCoachRelationDTO classCoachRelationDTO = new ClassCoachRelationDTO();
    classCoachRelationDTO.setCoachId(coachId);
    List<ClassCoachRelationDTO> list = this.kindergartenFacade.getClassCoachRelationList(classCoachRelationDTO);
    if (null != list && !list.isEmpty()) {
      for (ClassCoachRelationDTO classCoachRelationDTO2 : list) {
        classCoachRelationDTO2.setClassDTO(getClass(classCoachRelationDTO2.getClassId()));
      }
    }
    return list;
  }

  public CoachDTO getCoachByOpenId(String openId) throws ArgsException {
    MemberDTO member = getMember(openId);
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    String mobile = member.getMobile();
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_701);
    }
    CoachDTO coachDTO = getCoachByMobile(mobile);
    return coachDTO;
  }

  public void updateCoach(CoachDTO coachDTO) throws ArgsException {
    if (null == coachDTO || null == coachDTO.getCoachId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    coachDTO.setUpdateTime(new Date());
    kindergartenFacade.updateCoach(coachDTO);
  }

  public void saveCoach(CoachDTO coachDTO) throws ArgsException {
    if (null == coachDTO || StringUtils.isBlank(coachDTO.getName())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    coachDTO.setCreateTime(new Date());
    coachDTO.setEnabled(1);
    coachDTO.setDeleted(0);
    kindergartenFacade.saveCoach(coachDTO);
  }

  public void enabledCoach(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    CoachDTO dto = new CoachDTO();
    dto.setEnabled(1);
    dto.setCoachIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void disabledCoach(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    CoachDTO dto = new CoachDTO();
    dto.setEnabled(0);
    dto.setCoachIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public void deleteCoach(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    CoachDTO dto = new CoachDTO();
    dto.setDeleted(1);
    dto.setCoachIds(ids);
    kindergartenFacade.updateBySql(dto);
  }

  public String getCoachClassIds(Long coachId) throws ArgsException {
    ClassCoachRelationDTO classCoachRelationDTO = new ClassCoachRelationDTO();
    classCoachRelationDTO.setCoachId(coachId);
    List<ClassCoachRelationDTO> list = this.kindergartenFacade.getClassCoachRelationList(classCoachRelationDTO);
    String ids = "";
    for (ClassCoachRelationDTO classCoachRelationDTO2 : list) {
      ids += "," + classCoachRelationDTO2.getClassId() + ",";
    }
    return ids;
  }

  public void bindBracelet(Long studentId, String studentName, String mac) throws ArgsException {
    StudentDTO studentDTO = new StudentDTO();
    studentDTO.setBraceletMac(mac);
    List<StudentDTO> students = kindergartenFacade.getStudentList(studentDTO);
    if (null != students && !students.isEmpty()) {
      for (StudentDTO s : students) {
        s.setBraceletMac("");
      }
      kindergartenFacade.batchUpdateStudent(students);
    }
    studentDTO = kindergartenFacade.getStudent(studentId);
    if (null == studentDTO) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "学生不存在");
    }
    if (StringUtils.isNotBlank(studentName) && !studentName.equalsIgnoreCase(studentDTO.getName())) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "输入的姓名错误");
    }
    studentDTO.setBraceletMac(mac);
    this.kindergartenFacade.updateStudent(studentDTO);
  }

  public ApiResult bindingStudents(StudentDTO student, Integer pageNo, Integer pageSize) {
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    if (null == student) {
      student = new StudentDTO();
    }
    student.setHasBraceletMac(true);
    queryParam.setParam(student);
    PageList<StudentDTO> pageList = kindergartenFacade.getStudentListForPage(queryParam);
    List<StudentDTO> list = pageList.getDataList();
    if (null != list && !list.isEmpty()) {
      for (StudentDTO studentDTO : list) {
        BraceletDTO braceletDTO = new BraceletDTO();
        braceletDTO.setMac(studentDTO.getBraceletMac());
        braceletDTO.setStudentId(studentDTO.getStudentId());
        List<BraceletDTO> temp = braceletFacade.getBraceletList(braceletDTO, 1);
        if (null != temp && !temp.isEmpty()) {
          studentDTO.setBraceletInfo(temp.get(0));
        }
      }
    }
    ApiResult apiResult = new ApiResult();
    apiResult.setData(list);
    PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
    apiResult.setPageInfo(pageInfo);
    return apiResult;
  }

  public SmsIdentityDTO getSmsIdentity(String phone, Integer tag){
    SmsIdentityDTO dto = new SmsIdentityDTO();
    dto.setPhone(phone);
    dto.setTag(tag);
    List<SmsIdentityDTO> list = smsIdentityService.getSimpleList(dto);
    if (null != list && list.size() > 0) {
        return list.get(0);
    }
    return null;
  }
  
  public CoachDTO login(String mobile, String code) throws ArgsException {
    if (StringUtils.isBlank(mobile) || StringUtils.isBlank(code)) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "参数错误");
    }
    SmsIdentityDTO dto = getSmsIdentity(mobile, SmsTag.coachLogin.getTag());
    if (null == dto || ! code.equals(dto.getCode())) {
        throw new ArgsException("102", "验证码错误");
    }
    CoachDTO coachDTO = this.getCoachByMobile(mobile);
    smsIdentityService.delete(dto.getId());
    return coachDTO;
  }

  public PageList<ClassCoachRelationDTO> classList(Long coachId, Integer pageNo, Integer pageSize) throws ArgsException {
    if (null == coachId) {
      throw new ArgsException(FailureCode.ERR_002.getCode());
    }
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    queryParam.addParam("coachId", coachId);
    PageList<ClassCoachRelationDTO> pageList = kindergartenFacade.getClassCoachRelationListForPage(queryParam);
    List<ClassCoachRelationDTO> list = pageList.getDataList();
    if (null != list && !list.isEmpty()) {
      for (ClassCoachRelationDTO classCoachRelationDTO2 : list) {
        classCoachRelationDTO2.setClassDTO(getClass(classCoachRelationDTO2.getClassId()));
      }
    }
    return pageList;
  }

  public PageList<StudentDTO> studentList(Long classId, Integer pageNo, Integer pageSize) throws ArgsException {
    if (null == classId) {
      throw new ArgsException(FailureCode.ERR_002.getCode());
    }
    QueryParam queryParam = new QueryParam();
    if (null != pageNo) {
      queryParam.setPageNo(pageNo);
    }
    if (null != pageSize) {
      queryParam.setPageSize(pageSize);
    }
    queryParam.addParam("classId", classId);
    PageList<StudentDTO> pageList = kindergartenFacade.getStudentListForPage(queryParam);
    List<StudentDTO> students = pageList.getDataList();
    for (StudentDTO studentDTO : students) {
      BraceletDTO braceletDTO = new BraceletDTO();
      braceletDTO.setStudentId(studentDTO.getStudentId());
      List<BraceletDTO> brs = braceletFacade.getBraceletList(braceletDTO, 1);
      if (null != brs && ! brs.isEmpty()) {
        studentDTO.setBraceletInfo(brs.get(0));
      }
      PhysicalDataDTO physicalDataDTO = new PhysicalDataDTO();
      physicalDataDTO.setStudentId(studentDTO.getStudentId());
      List<PhysicalDataDTO> list = this.kindergartenFacade.getPhysicalDataList(physicalDataDTO, 1);
      if (null != list && ! list.isEmpty()) {
        studentDTO.setPhysicalData(list.get(0));
      }
    }
    return pageList;
  }

  public void unbindBracelet(Long studentId, String studentName) throws ArgsException {
    StudentDTO studentDTO = kindergartenFacade.getStudent(studentId);
    if (null == studentDTO) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "学生不存在");
    }
    if (StringUtils.isNotBlank(studentName) && !studentName.equalsIgnoreCase(studentDTO.getName())) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "输入的姓名错误");
    }
    studentDTO.setBraceletMac("");
    this.kindergartenFacade.updateStudent(studentDTO);
  }
}
