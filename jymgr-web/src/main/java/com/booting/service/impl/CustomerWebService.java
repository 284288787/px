/**create by liuhua at 2018年6月12日 上午10:17:47**/
package com.booting.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.business.conf.BusinessConst.MemberIdentity;
import com.booting.business.dto.BusinessPictureDTO;
import com.booting.business.dto.CustomerDTO;
import com.booting.business.dto.MemberDTO;
import com.booting.business.facade.BusinessFacade;
import com.booting.common.CommonConstants.UserType;
import com.booting.system.dto.UserAccountDTO;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("customerWebService")
public class CustomerWebService extends BaseWebService{

  @Autowired
  private BusinessFacade businessFacade;
  
  public PageList<CustomerDTO> getListForPageCustomer(QueryParam queryParam, Class<CustomerDTO> class1) {
    return businessFacade.getCustomerListForPage(queryParam);
  }

  public CustomerDTO getCustomer(Long memberId) {
    MemberDTO member = businessFacade.getMember(memberId);
    CustomerDTO customer = businessFacade.getCustomer(memberId);
    BusinessPictureDTO param = new BusinessPictureDTO();
    param.setMemberId(memberId);
    List<BusinessPictureDTO> pictures = businessFacade.getBusinessPictureList(param);
    Map<Integer, List<BusinessPictureDTO>> picsMap = new HashMap<>();
    for (BusinessPictureDTO bpd : pictures) {
      List<BusinessPictureDTO> pics = picsMap.get(bpd.getType());
      if (null == pics) {
        pics = new ArrayList<>();
        picsMap.put(bpd.getType(), pics);
      }
      pics.add(bpd);
    }
    move(member, customer);
    return customer;
  }

  private void move(MemberDTO member, CustomerDTO customer){
    if (null == member || null == customer) {
      return ;
    }
    customer.setMobile(member.getMobile());
    customer.setName(member.getName());
    customer.setIdentity(member.getIdentity());
    customer.setEnabled(member.getEnabled());
    customer.setDeleted(member.getDeleted());
    customer.setBeforeDeletedMobile(member.getBeforeDeletedMobile());
    customer.setCreateTime(member.getCreateTime());
    customer.setUpdateTime(member.getUpdateTime());
  }

  public void saveCustomer(CustomerDTO customerDTO) throws ArgsException {
    if (null == customerDTO || ! customerDTO.checkSaveData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    customerDTO.setName(customerDTO.getOrgName());
    customerDTO.setMobile(customerDTO.getContactPhone());
    MemberDTO param = new MemberDTO();
    param.setMobile(customerDTO.getMobile());
    List<MemberDTO> memberDTOs = this.businessFacade.getMemberList(param);
    if (null != memberDTOs && memberDTOs.size() > 0) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的人员已经存在");
    }
    Long userId = null;
    UserAccountDTO userAccountDTO = getUserAccount(customerDTO.getMobile());
    if (null == userAccountDTO || null == userAccountDTO.getUserId()) {
      userId = insertUserAccount(customerDTO.getMobile(), customerDTO.getName(), customerDTO.getOrgAddress(), UserType.customer.getUserType());
    } else {
      userId = userAccountDTO.getUserId();
      updateUserAccount(userId, customerDTO.getMobile(), customerDTO.getName(), customerDTO.getOrgAddress(), UserType.customer.getUserType());
    }
    CustomerDTO old = businessFacade.getCustomer(userId);
    customerDTO.setMemberId(userId);
    customerDTO.setIdentity(MemberIdentity.customer.getIdentity());
    customerDTO.setEnabled(1);
    customerDTO.setDeleted(0);
    customerDTO.setCreateTime(new Date());
    customerDTO.setUpdateTime(customerDTO.getCreateTime());
    if (null != old) {
      updateCustomer(customerDTO);
    } else {
      customerDTO.setBeforeDeletedMobile(customerDTO.getMobile());
      Long memberId = this.businessFacade.saveMember(customerDTO);
      customerDTO.setMemberId(memberId);
      this.businessFacade.saveCustomer(customerDTO);
    }
  }

  public void updateCustomer(CustomerDTO customerDTO) throws ArgsException {
    if (null == customerDTO || null == customerDTO.getMemberId() || ! customerDTO.checkSaveData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    customerDTO.setName(customerDTO.getOrgName());
    customerDTO.setMobile(customerDTO.getContactPhone());
    MemberDTO member = businessFacade.getMember(customerDTO.getMemberId());
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "会员信息不存在.");
    }
    CustomerDTO customer = businessFacade.getCustomer(customerDTO.getMemberId());
    if (null == customer) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "会员信息不存在。");
    }
    if (StringUtils.isNotBlank(customerDTO.getMobile())) {
      MemberDTO param = new MemberDTO();
      param.setMobile(customerDTO.getMobile());
      List<MemberDTO> memberDTOs = this.businessFacade.getMemberList(param);
      if (null != memberDTOs && ! memberDTOs.isEmpty()) {
        if (memberDTOs.size() > 1) {
          throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的加盟商已经存在。");
        }else{
          MemberDTO memberDTO = memberDTOs.get(0);
          if (memberDTO.getMemberId() != customerDTO.getMemberId().longValue()) {
            throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的加盟商已经存在."); 
          }
        }
      }
    }
    if (StringUtils.isNotBlank(customerDTO.getMobile()) && StringUtils.isNotBlank(customerDTO.getName())) {
      if (StringUtils.isNotBlank(member.getMobile())) {
        customerDTO.setBeforeDeletedMobile(member.getMobile());
      }
      customerDTO.setUpdateTime(new Date());
      this.businessFacade.updateMember(customerDTO);
    }
    if (customerDTO.checkSaveData()) {
      businessFacade.updateCustomer(customerDTO);
      UserAccountDTO userAccountDTO = getUserAccount(customerDTO.getMemberId());
      if (null != userAccountDTO && null != userAccountDTO.getUserId() && (! customerDTO.getMobile().equals(userAccountDTO.getMobile()) || ! customerDTO.getName().equals(userAccountDTO.getName()) || ! customerDTO.getOrgAddress().equals(userAccountDTO.getAddress()))) {
        updateUserAccount(customerDTO.getMemberId(), customerDTO.getMobile(), customerDTO.getName(), customerDTO.getOrgAddress(), UserType.customer.getUserType());
      }
    }
  }

  public void enabledCustomer(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setEnabled(1);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
  }

  public void disabledCustomer(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setEnabled(0);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
  }

  public void deleteCustomer(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setDeleted(1);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
    
    deleteAccount(ids);
  }
}
