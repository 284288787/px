/** create by auto at 2018-06-12 09:48:38 **/
package com.booting.business.facade;

import java.io.Serializable;
import java.util.List;
import com.booting.business.dto.BusinessPictureDTO;
import com.booting.business.dto.CoachDTO;
import com.booting.business.dto.CustomerDTO;
import com.booting.business.dto.FranchiseeDTO;
import com.booting.business.dto.MemberDTO;
import com.booting.business.dto.SalesmanDTO;
import com.booting.business.entity.BusinessPictureEntity;
import com.booting.business.entity.CoachEntity;
import com.booting.business.entity.CustomerEntity;
import com.booting.business.entity.FranchiseeEntity;
import com.booting.business.entity.MemberEntity;
import com.booting.business.entity.SalesmanEntity;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;

public interface BusinessFacade extends Serializable {

  /**
   * 新增 图片
   */
  public Long saveBusinessPicture(BusinessPictureDTO businessPictureDTO);

  /**
   * 批量新增 图片
   */
  public void batchSaveBusinessPicture(List<BusinessPictureDTO> dtos);

  /**
   * 更新 图片
   */
  public int updateBusinessPicture(BusinessPictureDTO businessPictureDTO);

  /**
   * 批量 图片
   */
  public void batchUpdateBusinessPicture(List<BusinessPictureDTO> dtos);

  /**
   * 删除 图片
   */
  public int deleteBusinessPicture(long id);

  /**
   * 根据主键获取 图片
   */
  public BusinessPictureDTO getBusinessPicture(long id);

  /**
   * 根据条件获取一条 图片
   */
  public BusinessPictureDTO getBusinessPicture(BusinessPictureDTO businessPictureDTO);

  /**
   * 查询满足条件的 图片 列表(单表)
   */
  public List<BusinessPictureDTO> getBusinessPictureList(BusinessPictureDTO businessPictureDTO);

  /**
   * 查询满足条件的 图片 列表(分页)(单表)
   */
  public PageList<BusinessPictureDTO> getBusinessPictureListForPage(BusinessPictureDTO businessPictureDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 图片 列表(分页)(单表)
   */
  public PageList<BusinessPictureDTO> getBusinessPictureListForPage(QueryParam queryParam);

  /**
   * 图片DTO 转换成 Entity
   */
  public BusinessPictureEntity toBusinessPictureEntity(BusinessPictureDTO businessPictureDTO);

  /**
   * 图片DTOs 转换成 Entities
   */
  public List<BusinessPictureEntity> toBusinessPictureEntities(List<BusinessPictureDTO> dtoes);

  /**
   * 新增 教练
   */
  public Long saveCoach(CoachDTO coachDTO);

  /**
   * 批量新增 教练
   */
  public void batchSaveCoach(List<CoachDTO> dtos);

  /**
   * 更新 教练
   */
  public int updateCoach(CoachDTO coachDTO);

  /**
   * 批量 教练
   */
  public void batchUpdateCoach(List<CoachDTO> dtos);

  /**
   * 删除 教练
   */
  public int deleteCoach(long memberId);

  /**
   * 根据主键获取 教练
   */
  public CoachDTO getCoach(long memberId);

  /**
   * 根据条件获取一条 教练
   */
  public CoachDTO getCoach(CoachDTO coachDTO);

  /**
   * 查询满足条件的 教练 列表(单表)
   */
  public List<CoachDTO> getCoachList(CoachDTO coachDTO);

  /**
   * 查询满足条件的 教练 列表(分页)(单表)
   */
  public PageList<CoachDTO> getCoachListForPage(CoachDTO coachDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 教练 列表(分页)(单表)
   */
  public PageList<CoachDTO> getCoachListForPage(QueryParam queryParam);

  /**
   * 教练DTO 转换成 Entity
   */
  public CoachEntity toCoachEntity(CoachDTO coachDTO);

  /**
   * 教练DTOs 转换成 Entities
   */
  public List<CoachEntity> toCoachEntities(List<CoachDTO> dtoes);

  /**
   * 新增 会员信息
   */
  public Long saveMember(MemberDTO memberDTO);

  /**
   * 批量新增 会员信息
   */
  public void batchSaveMember(List<MemberDTO> dtos);

  /**
   * 更新 会员信息
   */
  public int updateMember(MemberDTO memberDTO);

  /**
   * 批量 会员信息
   */
  public void batchUpdateMember(List<MemberDTO> dtos);

  /**
   * 删除 会员信息
   */
  public int deleteMember(long memberId);

  /**
   * 根据主键获取 会员信息
   */
  public MemberDTO getMember(long memberId);

  /**
   * 根据条件获取一条 会员信息
   */
  public MemberDTO getMember(MemberDTO memberDTO);

  /**
   * 查询满足条件的 会员信息 列表(单表)
   */
  public List<MemberDTO> getMemberList(MemberDTO memberDTO);

  /**
   * 查询满足条件的 会员信息 列表(分页)(单表)
   */
  public PageList<MemberDTO> getMemberListForPage(MemberDTO memberDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 会员信息 列表(分页)(单表)
   */
  public PageList<MemberDTO> getMemberListForPage(QueryParam queryParam);

  /**
   * 会员信息DTO 转换成 Entity
   */
  public MemberEntity toMemberEntity(MemberDTO memberDTO);

  /**
   * 会员信息DTOs 转换成 Entities
   */
  public List<MemberEntity> toMemberEntities(List<MemberDTO> dtoes);

  /**
   * 新增 业务员
   */
  public Long saveSalesman(SalesmanDTO salesmanDTO);

  /**
   * 批量新增 业务员
   */
  public void batchSaveSalesman(List<SalesmanDTO> dtos);

  /**
   * 更新 业务员
   */
  public int updateSalesman(SalesmanDTO salesmanDTO);

  /**
   * 批量 业务员
   */
  public void batchUpdateSalesman(List<SalesmanDTO> dtos);

  /**
   * 删除 业务员
   */
  public int deleteSalesman(long memberId);

  /**
   * 根据主键获取 业务员
   */
  public SalesmanDTO getSalesman(long memberId);

  /**
   * 根据条件获取一条 业务员
   */
  public SalesmanDTO getSalesman(SalesmanDTO salesmanDTO);

  /**
   * 查询满足条件的 业务员 列表(单表)
   */
  public List<SalesmanDTO> getSalesmanList(SalesmanDTO salesmanDTO);

  /**
   * 查询满足条件的 业务员 列表(分页)(单表)
   */
  public PageList<SalesmanDTO> getSalesmanListForPage(SalesmanDTO salesmanDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 业务员 列表(分页)(单表)
   */
  public PageList<SalesmanDTO> getSalesmanListForPage(QueryParam queryParam);

  /**
   * 业务员DTO 转换成 Entity
   */
  public SalesmanEntity toSalesmanEntity(SalesmanDTO salesmanDTO);

  /**
   * 业务员DTOs 转换成 Entities
   */
  public List<SalesmanEntity> toSalesmanEntities(List<SalesmanDTO> dtoes);

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

  public void deleteBusinessPictureBy(Long memberId, int type);

  public void updateBySql(MemberDTO dto);

  /**
   * 新增 客户
   */
  public Long saveCustomer(CustomerDTO customerDTO);

  /**
   * 批量新增 客户
   */
  public void batchSaveCustomer(List<CustomerDTO> dtos);

  /**
   * 更新 客户
   */
  public int updateCustomer(CustomerDTO customerDTO);

  /**
   * 批量 客户
   */
  public void batchUpdateCustomer(List<CustomerDTO> dtos);

  /**
   * 删除 客户
   */
  public int deleteCustomer(long memberId);

  /**
   * 根据主键获取 客户
   */
  public CustomerDTO getCustomer(long memberId);

  /**
   * 根据条件获取一条 客户
   */
  public CustomerDTO getCustomer(CustomerDTO customerDTO);

  /**
   * 查询满足条件的 客户 列表(单表)
   */
  public List<CustomerDTO> getCustomerList(CustomerDTO customerDTO);

  /**
   * 查询满足条件的 客户 列表(分页)(单表)
   */
  public PageList<CustomerDTO> getCustomerListForPage(CustomerDTO customerDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 客户 列表(分页)(单表)
   */
  public PageList<CustomerDTO> getCustomerListForPage(QueryParam queryParam);

  /**
   * 客户DTO 转换成 Entity
   */
  public CustomerEntity toCustomerEntity(CustomerDTO customerDTO);

  /**
   * 客户DTOs 转换成 Entities
   */
  public List<CustomerEntity> toCustomerEntities(List<CustomerDTO> dtoes);

  /**
   * 新增 加盟商
   */
  public Long saveFranchisee(FranchiseeDTO franchiseeDTO);

  /**
   * 批量新增 加盟商
   */
  public void batchSaveFranchisee(List<FranchiseeDTO> dtos);

  /**
   * 更新 加盟商
   */
  public int updateFranchisee(FranchiseeDTO franchiseeDTO);

  /**
   * 批量 加盟商
   */
  public void batchUpdateFranchisee(List<FranchiseeDTO> dtos);

  /**
   * 删除 加盟商
   */
  public int deleteFranchisee(long memberId);

  /**
   * 根据主键获取 加盟商
   */
  public FranchiseeDTO getFranchisee(long memberId);

  /**
   * 根据条件获取一条 加盟商
   */
  public FranchiseeDTO getFranchisee(FranchiseeDTO franchiseeDTO);

  /**
   * 查询满足条件的 加盟商 列表(单表)
   */
  public List<FranchiseeDTO> getFranchiseeList(FranchiseeDTO franchiseeDTO);

  /**
   * 查询满足条件的 加盟商 列表(分页)(单表)
   */
  public PageList<FranchiseeDTO> getFranchiseeListForPage(FranchiseeDTO franchiseeDTO, int pageNumber, int pageSize);

  /**
   * 查询满足条件的 加盟商 列表(分页)(单表)
   */
  public PageList<FranchiseeDTO> getFranchiseeListForPage(QueryParam queryParam);

  /**
   * 加盟商DTO 转换成 Entity
   */
  public FranchiseeEntity toFranchiseeEntity(FranchiseeDTO franchiseeDTO);

  /**
   * 加盟商DTOs 转换成 Entities
   */
  public List<FranchiseeEntity> toFranchiseeEntities(List<FranchiseeDTO> dtoes);

}
