/** create by auto at 2018-06-12 09:48:38 **/
package com.booting.business.facade.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import com.booting.business.facade.BusinessFacade;
import com.booting.business.service.BusinessPictureService;
import com.booting.business.service.CoachService;
import com.booting.business.service.CustomerService;
import com.booting.business.service.FranchiseeService;
import com.booting.business.service.MemberService;
import com.booting.business.service.SalesmanService;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.utils.CglibBeanUtils;

@Service("businessFacade")
public class BusinessFacadeImpl implements BusinessFacade {
  private static final long serialVersionUID = 1L;

  @Autowired
  private BusinessPictureService businessPictureService;

  @Autowired
  private CoachService coachService;

  @Autowired
  private MemberService memberService;

  @Autowired
  private SalesmanService salesmanService;

  @Autowired
  private CustomerService customerService;

  @Autowired
  private FranchiseeService franchiseeService;
  
  @Override
  public Long saveBusinessPicture(BusinessPictureDTO businessPictureDTO) {
    if (null == businessPictureDTO) {
      return null;
    }
    BusinessPictureEntity entity = toBusinessPictureEntity(businessPictureDTO);
    businessPictureDTO = businessPictureService.save(entity);
    return businessPictureDTO.getId();
  }

  @Override
  public void batchSaveBusinessPicture(List<BusinessPictureDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<BusinessPictureEntity> entities = toBusinessPictureEntities(dtos);
    businessPictureService.batchSave(entities);
  }

  @Override
  public int updateBusinessPicture(BusinessPictureDTO businessPictureDTO) {
    businessPictureDTO = businessPictureService.updateBySql(businessPictureDTO);
    return 1;
  }

  @Override
  public void batchUpdateBusinessPicture(List<BusinessPictureDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    businessPictureService.batchUpdate(dtos);
  }

  @Override
  public int deleteBusinessPicture(long id) {
    return businessPictureService.delete(id);
  }

  @Override
  public BusinessPictureDTO getBusinessPicture(long id) {
    return businessPictureService.get(id);
  }

  @Override
  public BusinessPictureDTO getBusinessPicture(BusinessPictureDTO businessPictureDTO) {
    return businessPictureService.get(businessPictureDTO);
  }

  @Override
  public List<BusinessPictureDTO> getBusinessPictureList(BusinessPictureDTO businessPictureDTO) {
    return businessPictureService.getSimpleList(businessPictureDTO);
  }

  @Override
  public PageList<BusinessPictureDTO> getBusinessPictureListForPage(BusinessPictureDTO businessPictureDTO, int pageNumber, int pageSize) {
    return businessPictureService.getSimpleListForPage(businessPictureDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<BusinessPictureDTO> getBusinessPictureListForPage(QueryParam queryParam) {
    return businessPictureService.getSimpleListForPage(queryParam);
  }

  @Override
  public BusinessPictureEntity toBusinessPictureEntity(BusinessPictureDTO dto) {
    BusinessPictureEntity entity = new BusinessPictureEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<BusinessPictureEntity> toBusinessPictureEntities(List<BusinessPictureDTO> dtos) {
    List<BusinessPictureEntity> entities = new ArrayList<>();
    for (BusinessPictureDTO dto : dtos) {
      entities.add(toBusinessPictureEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveCoach(CoachDTO coachDTO) {
    if (null == coachDTO) {
      return null;
    }
    CoachEntity entity = toCoachEntity(coachDTO);
    coachDTO = coachService.save(entity);
    return coachDTO.getMemberId();
  }

  @Override
  public void batchSaveCoach(List<CoachDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<CoachEntity> entities = toCoachEntities(dtos);
    coachService.batchSave(entities);
  }

  @Override
  public int updateCoach(CoachDTO coachDTO) {
    coachDTO = coachService.updateBySql(coachDTO);
    return 1;
  }

  @Override
  public void batchUpdateCoach(List<CoachDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    coachService.batchUpdate(dtos);
  }

  @Override
  public int deleteCoach(long memberId) {
    return coachService.delete(memberId);
  }

  @Override
  public CoachDTO getCoach(long memberId) {
    return coachService.get(memberId);
  }

  @Override
  public CoachDTO getCoach(CoachDTO coachDTO) {
    return coachService.get(coachDTO);
  }

  @Override
  public List<CoachDTO> getCoachList(CoachDTO coachDTO) {
    return coachService.getSimpleList(coachDTO);
  }

  @Override
  public PageList<CoachDTO> getCoachListForPage(CoachDTO coachDTO, int pageNumber, int pageSize) {
    return coachService.getSimpleListForPage(coachDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<CoachDTO> getCoachListForPage(QueryParam queryParam) {
    return coachService.getSimpleListForPage(queryParam);
  }

  @Override
  public CoachEntity toCoachEntity(CoachDTO dto) {
    CoachEntity entity = new CoachEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<CoachEntity> toCoachEntities(List<CoachDTO> dtos) {
    List<CoachEntity> entities = new ArrayList<>();
    for (CoachDTO dto : dtos) {
      entities.add(toCoachEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveMember(MemberDTO memberDTO) {
    if (null == memberDTO) {
      return null;
    }
    MemberEntity entity = toMemberEntity(memberDTO);
    memberDTO = memberService.save(entity);
    return memberDTO.getMemberId();
  }

  @Override
  public void batchSaveMember(List<MemberDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<MemberEntity> entities = toMemberEntities(dtos);
    memberService.batchSave(entities);
  }

  @Override
  public int updateMember(MemberDTO memberDTO) {
    memberDTO = memberService.updateBySql(memberDTO);
    return 1;
  }

  @Override
  public void batchUpdateMember(List<MemberDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    memberService.batchUpdate(dtos);
  }

  @Override
  public int deleteMember(long memberId) {
    return memberService.delete(memberId);
  }

  @Override
  public MemberDTO getMember(long memberId) {
    return memberService.get(memberId);
  }

  @Override
  public MemberDTO getMember(MemberDTO memberDTO) {
    return memberService.get(memberDTO);
  }

  @Override
  public List<MemberDTO> getMemberList(MemberDTO memberDTO) {
    return memberService.getSimpleList(memberDTO);
  }

  @Override
  public PageList<MemberDTO> getMemberListForPage(MemberDTO memberDTO, int pageNumber, int pageSize) {
    return memberService.getSimpleListForPage(memberDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<MemberDTO> getMemberListForPage(QueryParam queryParam) {
    return memberService.getSimpleListForPage(queryParam);
  }

  @Override
  public MemberEntity toMemberEntity(MemberDTO dto) {
    MemberEntity entity = new MemberEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<MemberEntity> toMemberEntities(List<MemberDTO> dtos) {
    List<MemberEntity> entities = new ArrayList<>();
    for (MemberDTO dto : dtos) {
      entities.add(toMemberEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveSalesman(SalesmanDTO salesmanDTO) {
    if (null == salesmanDTO) {
      return null;
    }
    SalesmanEntity entity = toSalesmanEntity(salesmanDTO);
    salesmanDTO = salesmanService.save(entity);
    return salesmanDTO.getMemberId();
  }

  @Override
  public void batchSaveSalesman(List<SalesmanDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<SalesmanEntity> entities = toSalesmanEntities(dtos);
    salesmanService.batchSave(entities);
  }

  @Override
  public int updateSalesman(SalesmanDTO salesmanDTO) {
    salesmanDTO = salesmanService.updateBySql(salesmanDTO);
    return 1;
  }

  @Override
  public void batchUpdateSalesman(List<SalesmanDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    salesmanService.batchUpdate(dtos);
  }

  @Override
  public int deleteSalesman(long memberId) {
    return salesmanService.delete(memberId);
  }

  @Override
  public SalesmanDTO getSalesman(long memberId) {
    return salesmanService.get(memberId);
  }

  @Override
  public SalesmanDTO getSalesman(SalesmanDTO salesmanDTO) {
    return salesmanService.get(salesmanDTO);
  }

  @Override
  public List<SalesmanDTO> getSalesmanList(SalesmanDTO salesmanDTO) {
    return salesmanService.getSimpleList(salesmanDTO);
  }

  @Override
  public PageList<SalesmanDTO> getSalesmanListForPage(SalesmanDTO salesmanDTO, int pageNumber, int pageSize) {
    return salesmanService.getSimpleListForPage(salesmanDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<SalesmanDTO> getSalesmanListForPage(QueryParam queryParam) {
    return salesmanService.getSimpleListForPage(queryParam);
  }

  @Override
  public SalesmanEntity toSalesmanEntity(SalesmanDTO dto) {
    SalesmanEntity entity = new SalesmanEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<SalesmanEntity> toSalesmanEntities(List<SalesmanDTO> dtos) {
    List<SalesmanEntity> entities = new ArrayList<>();
    for (SalesmanDTO dto : dtos) {
      entities.add(toSalesmanEntity(dto));
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
  public void deleteBusinessPictureBy(Long memberId, int type) {
    Map<String, Object> param = new HashMap<>();
    param.put("memberId", memberId);
    param.put("type", type);
    this.businessPictureService.deleteBySql(param);
  }

  @Override
  public void updateBySql(MemberDTO dto) {
    this.memberService.updateBySql(dto);
  }

  @Override
  public Long saveCustomer(CustomerDTO customerDTO) {
    if (null == customerDTO) {
      return null;
    }
    CustomerEntity entity = toCustomerEntity(customerDTO);
    customerDTO = customerService.save(entity);
    return customerDTO.getMemberId();
  }

  @Override
  public void batchSaveCustomer(List<CustomerDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<CustomerEntity> entities = toCustomerEntities(dtos);
    customerService.batchSave(entities);
  }

  @Override
  public int updateCustomer(CustomerDTO customerDTO) {
    customerDTO = customerService.updateBySql(customerDTO);
    return 1;
  }

  @Override
  public void batchUpdateCustomer(List<CustomerDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    customerService.batchUpdate(dtos);
  }

  @Override
  public int deleteCustomer(long memberId) {
    return customerService.delete(memberId);
  }

  @Override
  public CustomerDTO getCustomer(long memberId) {
    return customerService.get(memberId);
  }

  @Override
  public CustomerDTO getCustomer(CustomerDTO customerDTO) {
    return customerService.get(customerDTO);
  }

  @Override
  public List<CustomerDTO> getCustomerList(CustomerDTO customerDTO) {
    return customerService.getSimpleList(customerDTO);
  }

  @Override
  public PageList<CustomerDTO> getCustomerListForPage(CustomerDTO customerDTO, int pageNumber, int pageSize) {
    return customerService.getSimpleListForPage(customerDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<CustomerDTO> getCustomerListForPage(QueryParam queryParam) {
    return customerService.getSimpleListForPage(queryParam);
  }

  @Override
  public CustomerEntity toCustomerEntity(CustomerDTO dto) {
    CustomerEntity entity = new CustomerEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<CustomerEntity> toCustomerEntities(List<CustomerDTO> dtos) {
    List<CustomerEntity> entities = new ArrayList<>();
    for (CustomerDTO dto : dtos) {
      entities.add(toCustomerEntity(dto));
    }
    return entities;
  }

  @Override
  public Long saveFranchisee(FranchiseeDTO franchiseeDTO) {
    if (null == franchiseeDTO) {
      return null;
    }
    FranchiseeEntity entity = toFranchiseeEntity(franchiseeDTO);
    franchiseeDTO = franchiseeService.save(entity);
    return franchiseeDTO.getMemberId();
  }

  @Override
  public void batchSaveFranchisee(List<FranchiseeDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    List<FranchiseeEntity> entities = toFranchiseeEntities(dtos);
    franchiseeService.batchSave(entities);
  }

  @Override
  public int updateFranchisee(FranchiseeDTO franchiseeDTO) {
    franchiseeDTO = franchiseeService.updateBySql(franchiseeDTO);
    return 1;
  }

  @Override
  public void batchUpdateFranchisee(List<FranchiseeDTO> dtos) {
    if (null == dtos || dtos.isEmpty()) {
      return;
    }
    franchiseeService.batchUpdate(dtos);
  }

  @Override
  public int deleteFranchisee(long memberId) {
    return franchiseeService.delete(memberId);
  }

  @Override
  public FranchiseeDTO getFranchisee(long memberId) {
    return franchiseeService.get(memberId);
  }

  @Override
  public FranchiseeDTO getFranchisee(FranchiseeDTO franchiseeDTO) {
    return franchiseeService.get(franchiseeDTO);
  }

  @Override
  public List<FranchiseeDTO> getFranchiseeList(FranchiseeDTO franchiseeDTO) {
    return franchiseeService.getSimpleList(franchiseeDTO);
  }

  @Override
  public PageList<FranchiseeDTO> getFranchiseeListForPage(FranchiseeDTO franchiseeDTO, int pageNumber, int pageSize) {
    return franchiseeService.getSimpleListForPage(franchiseeDTO, pageNumber, pageSize);
  }

  @Override
  public PageList<FranchiseeDTO> getFranchiseeListForPage(QueryParam queryParam) {
    return franchiseeService.getSimpleListForPage(queryParam);
  }

  @Override
  public FranchiseeEntity toFranchiseeEntity(FranchiseeDTO dto) {
    FranchiseeEntity entity = new FranchiseeEntity();
    CglibBeanUtils.copy(dto, entity);
    return entity;
  }

  @Override
  public List<FranchiseeEntity> toFranchiseeEntities(List<FranchiseeDTO> dtos) {
    List<FranchiseeEntity> entities = new ArrayList<>();
    for (FranchiseeDTO dto : dtos) {
      entities.add(toFranchiseeEntity(dto));
    }
    return entities;
  }

}
