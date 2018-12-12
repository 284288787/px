/** create by auto at 2018-09-12 16:14:42 **/
package com.booting.bracelet.facade;

import java.io.Serializable;
import java.util.List;
import com.booting.bracelet.dto.BraceletDTO;
import com.booting.bracelet.dto.PointLevelDTO;
import com.booting.bracelet.dto.StudentPointDTO;
import com.booting.bracelet.entity.PointLevelEntity;
import com.booting.bracelet.entity.StudentPointEntity;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

public interface PointFacade extends Serializable {

  /**
   * 新增 级别配置
   */
  public Long savePointLevel(PointLevelDTO pointLevelDTO);

  /**
   * 批量新增 级别配置
   */
  public void batchSavePointLevel(List<PointLevelDTO> dtos);

  /**
   * 更新 级别配置
   */
  public int updatePointLevel(PointLevelDTO pointLevelDTO);

  /**
   * 批量 级别配置
   */
  public void batchUpdatePointLevel(List<PointLevelDTO> dtos);

  /**
   * 删除 级别配置
   */
  public int deletePointLevel(long id);

  /**
   * 根据主键获取 级别配置
   */
  public PointLevelDTO getPointLevel(long id);

  /**
   * 根据条件获取一条 级别配置
   */
  public PointLevelDTO getPointLevel(PointLevelDTO pointLevelDTO);

  /**
   * 查询满足条件的 级别配置 列表(单表)
   */
  public List<PointLevelDTO> getPointLevelList(PointLevelDTO pointLevelDTO);

  /**
   * 查询满足条件的 级别配置 列表(分页)(单表)
   */
  public PageList<PointLevelDTO> getPointLevelListForPage(PointLevelDTO pointLevelDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 级别配置 列表(分页)(单表)
   */
  public PageList<PointLevelDTO> getPointLevelListForPage(QueryParam queryParam);

  /**
   * 级别配置DTO 转换成 Entity
   */
  public PointLevelEntity toPointLevelEntity(PointLevelDTO pointLevelDTO);

  /**
   * 级别配置DTOs 转换成 Entities
   */
  public List<PointLevelEntity> toPointLevelEntities(List<PointLevelDTO> dtoes);

  /**
   * 新增 学生级别
   */
  public Long saveStudentPoint(StudentPointDTO studentPointDTO);

  /**
   * 批量新增 学生级别
   */
  public void batchSaveStudentPoint(List<StudentPointDTO> dtos);

  /**
   * 更新 学生级别
   */
  public int updateStudentPoint(StudentPointDTO studentPointDTO);

  /**
   * 批量 学生级别
   */
  public void batchUpdateStudentPoint(List<StudentPointDTO> dtos);

  /**
   * 删除 学生级别
   */
  public int deleteStudentPoint(long studentId);

  /**
   * 根据主键获取 学生级别
   */
  public StudentPointDTO getStudentPoint(long studentId);

  /**
   * 根据条件获取一条 学生级别
   */
  public StudentPointDTO getStudentPoint(StudentPointDTO studentPointDTO);

  /**
   * 查询满足条件的 学生级别 列表(单表)
   */
  public List<StudentPointDTO> getStudentPointList(StudentPointDTO studentPointDTO);

  /**
   * 查询满足条件的 学生级别 列表(分页)(单表)
   */
  public PageList<StudentPointDTO> getStudentPointListForPage(StudentPointDTO studentPointDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 学生级别 列表(分页)(单表)
   */
  public PageList<StudentPointDTO> getStudentPointListForPage(QueryParam queryParam);

  /**
   * 学生级别DTO 转换成 Entity
   */
  public StudentPointEntity toStudentPointEntity(StudentPointDTO studentPointDTO);

  /**
   * 学生级别DTOs 转换成 Entities
   */
  public List<StudentPointEntity> toStudentPointEntities(List<StudentPointDTO> dtoes);

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

  public void batchUpdatePoint(List<BraceletDTO> bracelets);

  public PointLevelDTO getByLevel(Integer level);

}
