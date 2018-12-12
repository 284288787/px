/** create by auto at 2018-09-12 16:14:42 **/
package com.booting.bracelet.facade.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.dto.PointLevelDTO;
import com.booting.bracelet.dto.StudentPointDTO;
import com.booting.bracelet.entity.PointLevelEntity;
import com.booting.bracelet.entity.StudentPointEntity;
import com.booting.bracelet.facade.PointFacade;
import com.booting.bracelet.service.PointLevelService;
import com.booting.bracelet.service.StudentPointService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

@Service("pointFacade")
public class PointFacadeImpl implements PointFacade {
  private static final long serialVersionUID = 1L;

  @Autowired
  private PointLevelService pointLevelService;

  @Autowired
  private StudentPointService studentPointService;

  @Override
  public Long savePointLevel(PointLevelDTO pointLevelDTO) {
    if (null == pointLevelDTO) {
      return null;
    }
    PointLevelEntity entity = toPointLevelEntity(pointLevelDTO);
    pointLevelDTO = pointLevelService.save(entity);
    return pointLevelDTO.getId();
  }

  @Override
  public void batchSavePointLevel(List<PointLevelDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<PointLevelEntity> entities = toPointLevelEntities(dtos);
    pointLevelService.batchSave(entities);
  }

  @Override
  public int updatePointLevel(PointLevelDTO pointLevelDTO) {
    pointLevelDTO = pointLevelService.updateBySql(pointLevelDTO);
    return 1;
  }

  @Override
  public void batchUpdatePointLevel(List<PointLevelDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    pointLevelService.batchUpdate(dtos);
  }

  @Override
  public int deletePointLevel(long id) {
    return pointLevelService.delete(id);
  }

  @Override
  public PointLevelDTO getPointLevel(long id) {
    return pointLevelService.get(id);
  }

  @Override
  public PointLevelDTO getPointLevel(PointLevelDTO pointLevelDTO) {
    return pointLevelService.get(pointLevelDTO);
  }

  @Override
  public List<PointLevelDTO> getPointLevelList(PointLevelDTO pointLevelDTO) {
    return pointLevelService.getSimpleList(pointLevelDTO);
  }

  @Override
  public PageList<PointLevelDTO> getPointLevelListForPage(PointLevelDTO pointLevelDTO, int pageNumber, int pageSize) {
    return pointLevelService.getSimpleListForPage(pointLevelDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<PointLevelDTO> getPointLevelListForPage(QueryParam queryParam) {
    return pointLevelService.getSimpleListForPage(queryParam);
  }

  @Override
  public PointLevelEntity toPointLevelEntity(PointLevelDTO dto) {
    PointLevelEntity entity = new PointLevelEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<PointLevelEntity> toPointLevelEntities(List<PointLevelDTO> dtos) {
    List<PointLevelEntity> entities = new ArrayList<>();
    for (PointLevelDTO dto : dtos) {
      entities.add(toPointLevelEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveStudentPoint(StudentPointDTO studentPointDTO) {
    if (null == studentPointDTO) {
      return null;
    }
    StudentPointEntity entity = toStudentPointEntity(studentPointDTO);
    studentPointDTO = studentPointService.save(entity);
    return studentPointDTO.getStudentId();
  }

  @Override
  public void batchSaveStudentPoint(List<StudentPointDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<StudentPointEntity> entities = toStudentPointEntities(dtos);
    studentPointService.batchSave(entities);
  }

  @Override
  public int updateStudentPoint(StudentPointDTO studentPointDTO) {
    studentPointDTO = studentPointService.updateBySql(studentPointDTO);
    return 1;
  }

  @Override
  public void batchUpdateStudentPoint(List<StudentPointDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    studentPointService.batchUpdate(dtos);
  }

  @Override
  public int deleteStudentPoint(long studentId) {
    return studentPointService.delete(studentId);
  }

  @Override
  public StudentPointDTO getStudentPoint(long studentId) {
    return studentPointService.get(studentId);
  }

  @Override
  public StudentPointDTO getStudentPoint(StudentPointDTO studentPointDTO) {
    return studentPointService.get(studentPointDTO);
  }

  @Override
  public List<StudentPointDTO> getStudentPointList(StudentPointDTO studentPointDTO) {
    return studentPointService.getSimpleList(studentPointDTO);
  }

  @Override
  public PageList<StudentPointDTO> getStudentPointListForPage(StudentPointDTO studentPointDTO, int pageNumber, int pageSize) {
    return studentPointService.getSimpleListForPage(studentPointDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<StudentPointDTO> getStudentPointListForPage(QueryParam queryParam) {
    return studentPointService.getSimpleListForPage(queryParam);
  }

  @Override
  public StudentPointEntity toStudentPointEntity(StudentPointDTO dto) {
    StudentPointEntity entity = new StudentPointEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<StudentPointEntity> toStudentPointEntities(List<StudentPointDTO> dtos) {
    List<StudentPointEntity> entities = new ArrayList<>();
    for (StudentPointDTO dto : dtos) {
      entities.add(toStudentPointEntity(dto));
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
  public void batchUpdatePoint(List<BraceletDTO> bracelets) {
    if (null == bracelets || bracelets.isEmpty()) {
      return;
    }
    for (BraceletDTO braceletDTO : bracelets) {
      levelUp(braceletDTO);
    }
  }

  private void levelUp(BraceletDTO braceletDTO){
    StudentPointDTO studentPointDTO = this.studentPointService.get(braceletDTO.getStudentId());
    int caloriePoint = 0;
    int distancePoint = 0;
    int calorie = braceletDTO.getCalorie();
    int distance = braceletDTO.getDistance();
    if (null == studentPointDTO) {
      studentPointDTO = new StudentPointDTO();
      studentPointDTO.setStudentId(braceletDTO.getStudentId());
      studentPointDTO.setLevel(1);
      PointLevelDTO pl = this.pointLevelService.getByLevel(2);
      if (null != pl) {
        caloriePoint = calorie / pl.getCalorieStep() * pl.getPoint();
        distancePoint = distance / pl.getDistanceStep() * pl.getPoint();
        if (caloriePoint + distancePoint > pl.getPointStep() && pl.getLevel() > 0) {
          studentPointDTO.setLevel(pl.getLevel());
        }
      }
      studentPointDTO.setCalorie(calorie);
      studentPointDTO.setCaloriePoint(caloriePoint);
      studentPointDTO.setDistance(distance);
      studentPointDTO.setDistancePoint(distancePoint);
      studentPointDTO.setUpdateTime(new Date());
      saveStudentPoint(studentPointDTO);
    } else {
      PointLevelDTO pl = this.pointLevelService.getByLevel(studentPointDTO.getLevel() + 1);
      if (null != pl) {
        int lateCalorie = studentPointDTO.getCalorie();
        int lateDistance = studentPointDTO.getDistance();
        caloriePoint = studentPointDTO.getCaloriePoint() + ((calorie - lateCalorie) / pl.getCalorieStep() * pl.getPoint());
        distancePoint = studentPointDTO.getDistancePoint() + ((distance - lateDistance) / pl.getDistanceStep() * pl.getPoint());
        if (caloriePoint + distancePoint > pl.getPointStep() && pl.getLevel() > 0) {
          studentPointDTO.setLevel(pl.getLevel());
        }
      }
      studentPointDTO.setCalorie(calorie);
      studentPointDTO.setCaloriePoint(caloriePoint);
      studentPointDTO.setDistance(distance);
      studentPointDTO.setDistancePoint(distancePoint);
      studentPointDTO.setUpdateTime(new Date());
      updateStudentPoint(studentPointDTO);
    }
  }

  @Override
  public PointLevelDTO getByLevel(Integer level) {
    return this.pointLevelService.getByLevel(level);
  }
}
