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
import com.booting.business.dto.FranchiseeDTO;
import com.booting.business.dto.MemberDTO;
import com.booting.business.facade.BusinessFacade;
import com.booting.common.CommonConstants.UserType;
import com.booting.system.dto.UserAccountDTO;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("franchiseeWebService")
public class FranchiseeWebService extends BaseWebService{

  @Autowired
  private BusinessFacade businessFacade;
  
  public PageList<FranchiseeDTO> getListForPageFranchisee(QueryParam queryParam, Class<FranchiseeDTO> class1) {
    return businessFacade.getFranchiseeListForPage(queryParam);
  }

  public FranchiseeDTO getFranchisee(Long memberId) {
    MemberDTO member = businessFacade.getMember(memberId);
    FranchiseeDTO franchisee = businessFacade.getFranchisee(memberId);
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
    move(member, franchisee);
    return franchisee;
  }

  private void move(MemberDTO member, FranchiseeDTO franchisee){
    if (null == member || null == franchisee) {
      return ;
    }
    franchisee.setMobile(member.getMobile());
    franchisee.setName(member.getName());
    franchisee.setIdentity(member.getIdentity());
    franchisee.setEnabled(member.getEnabled());
    franchisee.setDeleted(member.getDeleted());
    franchisee.setBeforeDeletedMobile(member.getBeforeDeletedMobile());
    franchisee.setCreateTime(member.getCreateTime());
    franchisee.setUpdateTime(member.getUpdateTime());
  }

  public void saveFranchisee(FranchiseeDTO franchiseeDTO) throws ArgsException {
    if (null == franchiseeDTO || ! franchiseeDTO.checkSaveData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    franchiseeDTO.setName(franchiseeDTO.getCompanyName());
    franchiseeDTO.setMobile(franchiseeDTO.getContactPhone());
    franchiseeDTO.setLevel(1);
    MemberDTO param = new MemberDTO();
    param.setMobile(franchiseeDTO.getMobile());
    List<MemberDTO> memberDTOs = this.businessFacade.getMemberList(param);
    if (null != memberDTOs && memberDTOs.size() > 0) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的人员已经存在");
    }
    Long userId = null;
    UserAccountDTO userAccountDTO = getUserAccount(franchiseeDTO.getMobile());
    if (null == userAccountDTO || null == userAccountDTO.getUserId()) {
      userId = insertUserAccount(franchiseeDTO.getMobile(), franchiseeDTO.getName(), franchiseeDTO.getCompanyAddress(), UserType.franchisee.getUserType());
    } else {
      userId = userAccountDTO.getUserId();
      updateUserAccount(userId, franchiseeDTO.getMobile(), franchiseeDTO.getName(), franchiseeDTO.getCompanyAddress(), UserType.franchisee.getUserType());
    }
    FranchiseeDTO old = businessFacade.getFranchisee(userId);
    franchiseeDTO.setMemberId(userId);
    franchiseeDTO.setIdentity(MemberIdentity.franchisee.getIdentity());
    franchiseeDTO.setEnabled(1);
    franchiseeDTO.setDeleted(0);
    franchiseeDTO.setCreateTime(new Date());
    franchiseeDTO.setUpdateTime(franchiseeDTO.getCreateTime());
    if (null != old) {
      updateFranchisee(franchiseeDTO);
    } else {
      franchiseeDTO.setBeforeDeletedMobile(franchiseeDTO.getMobile());
      Long memberId = this.businessFacade.saveMember(franchiseeDTO);
      franchiseeDTO.setMemberId(memberId);
      this.businessFacade.saveFranchisee(franchiseeDTO);
    }
  }

  public void updateFranchisee(FranchiseeDTO franchiseeDTO) throws ArgsException {
    if (null == franchiseeDTO || null == franchiseeDTO.getMemberId() || ! franchiseeDTO.checkSaveData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    franchiseeDTO.setName(franchiseeDTO.getCompanyName());
    franchiseeDTO.setMobile(franchiseeDTO.getContactPhone());
    MemberDTO member = businessFacade.getMember(franchiseeDTO.getMemberId());
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "会员信息不存在.");
    }
    FranchiseeDTO franchisee = businessFacade.getFranchisee(franchiseeDTO.getMemberId());
    if (null == franchisee) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "会员信息不存在。");
    }
    if (StringUtils.isNotBlank(franchiseeDTO.getMobile())) {
      MemberDTO param = new MemberDTO();
      param.setMobile(franchiseeDTO.getMobile());
      List<MemberDTO> memberDTOs = this.businessFacade.getMemberList(param);
      if (null != memberDTOs && ! memberDTOs.isEmpty()) {
        if (memberDTOs.size() > 1) {
          throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的加盟商已经存在。");
        }else{
          MemberDTO memberDTO = memberDTOs.get(0);
          if (memberDTO.getMemberId() != franchiseeDTO.getMemberId().longValue()) {
            throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的加盟商已经存在."); 
          }
        }
      }
    }
    if (StringUtils.isNotBlank(franchiseeDTO.getMobile()) && StringUtils.isNotBlank(franchiseeDTO.getName())) {
      if (StringUtils.isNotBlank(member.getMobile())) {
        franchiseeDTO.setBeforeDeletedMobile(member.getMobile());
      }
      franchiseeDTO.setUpdateTime(new Date());
      this.businessFacade.updateMember(franchiseeDTO);
    }
    if (franchiseeDTO.checkSaveData()) {
      businessFacade.updateFranchisee(franchiseeDTO);
      UserAccountDTO userAccountDTO = getUserAccount(franchiseeDTO.getMemberId());
      if (null != userAccountDTO && null != userAccountDTO.getUserId() && (! franchiseeDTO.getMobile().equals(userAccountDTO.getMobile()) || ! franchiseeDTO.getName().equals(userAccountDTO.getName()) || ! franchiseeDTO.getCompanyAddress().equals(userAccountDTO.getAddress()))) {
        updateUserAccount(franchiseeDTO.getMemberId(), franchiseeDTO.getMobile(), franchiseeDTO.getName(), franchiseeDTO.getCompanyAddress(), UserType.franchisee.getUserType());
      }
    }
  }

  public void enabledFranchisee(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setEnabled(1);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
  }

  public void disabledFranchisee(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setEnabled(0);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
  }

  public void deleteFranchisee(String ids) throws ArgsException {
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
