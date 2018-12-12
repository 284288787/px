/**create by liuhua at 2018年6月19日 上午10:56:11**/
package com.booting.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.booting.business.conf.BusinessConst.BusinessPictureType;
import com.booting.business.conf.BusinessConst.MemberIdentity;
import com.booting.business.dto.BusinessPictureDTO;
import com.booting.business.dto.CoachDTO;
import com.booting.business.dto.MemberDTO;
import com.booting.business.dto.SalesmanDTO;
import com.booting.business.facade.BusinessFacade;
import com.booting.common.CommonConstants.UserType;
import com.booting.system.dto.UserAccountDTO;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("salesmanWebService")
public class SalesmanWebService extends BaseWebService{

  @Autowired
  private BusinessFacade businessFacade;
  
  public PageList<SalesmanDTO> getListForPageSalesman(QueryParam queryParam, Class<SalesmanDTO> class1) {
    return businessFacade.getSalesmanListForPage(queryParam);
  }

  public SalesmanDTO getSalesman(Long memberId) {
    MemberDTO member = businessFacade.getMember(memberId);
    SalesmanDTO salesman = businessFacade.getSalesman(memberId);
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
    salesman.setHeadPic(picsMap.get(BusinessPictureType.head.getPictureType()));
    salesman.setCertificatePics(picsMap.get(BusinessPictureType.certificate.getPictureType()));
    move(member, salesman);
    salesman.setHeadPath(null != salesman.getHeadPic() && ! salesman.getHeadPic().isEmpty() ? salesman.getHeadPic().get(0).getPicPath() : "");
    return salesman;
  }

  private void move(MemberDTO member, SalesmanDTO salesman){
    if (null == member || null == salesman) {
      return ;
    }
    salesman.setMobile(member.getMobile());
    salesman.setName(member.getName());
    salesman.setIdentity(member.getIdentity());
    salesman.setEnabled(member.getEnabled());
    salesman.setDeleted(member.getDeleted());
    salesman.setBeforeDeletedMobile(member.getBeforeDeletedMobile());
    salesman.setCreateTime(member.getCreateTime());
    salesman.setUpdateTime(member.getUpdateTime());
  }
  
  public void updateSalesman(SalesmanDTO salesmanDTO) throws ArgsException {
    if (null == salesmanDTO || null == salesmanDTO.getMemberId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO member = businessFacade.getMember(salesmanDTO.getMemberId());
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "会员信息不存在.");
    }
    SalesmanDTO salesman = businessFacade.getSalesman(salesmanDTO.getMemberId());
    if (null == salesman) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "会员信息不存在。");
    }
    if (StringUtils.isNotBlank(salesmanDTO.getMobile())) {
      MemberDTO param = new MemberDTO();
      param.setMobile(salesmanDTO.getMobile());
      List<MemberDTO> memberDTOs = this.businessFacade.getMemberList(param);
      if (null != memberDTOs && ! memberDTOs.isEmpty()) {
        if (memberDTOs.size() > 1) {
          throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的教练员已经存在。");
        }else{
          MemberDTO memberDTO = memberDTOs.get(0);
          if (memberDTO.getMemberId() != salesmanDTO.getMemberId().longValue()) {
            throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的教练员已经存在."); 
          }
        }
      }
    }
    if (StringUtils.isNotBlank(salesmanDTO.getMobile()) && StringUtils.isNotBlank(salesmanDTO.getName())) {
      if (StringUtils.isNotBlank(member.getMobile())) {
        salesmanDTO.setBeforeDeletedMobile(member.getMobile());
      }
      salesmanDTO.setUpdateTime(new Date());
      this.businessFacade.updateMember(salesmanDTO);
    }
    if (salesmanDTO.checkSaveData()) {
      businessFacade.updateSalesman(salesmanDTO);
      UserAccountDTO userAccountDTO = getUserAccount(salesmanDTO.getMemberId());
      if (null != userAccountDTO && null != userAccountDTO.getUserId() && (! salesmanDTO.getMobile().equals(userAccountDTO.getMobile()) || ! salesmanDTO.getName().equals(userAccountDTO.getName()) || ! salesmanDTO.getAddress().equals(userAccountDTO.getAddress()))) {
        updateUserAccount(salesmanDTO.getMemberId(), salesmanDTO.getName(), salesmanDTO.getMobile(), salesmanDTO.getAddress(), UserType.salesman.getUserType());
      }
      if (null != salesmanDTO.getHeadPic() && ! salesmanDTO.getHeadPic().isEmpty()) {
        this.businessFacade.deleteBusinessPictureBy(salesman.getMemberId(), BusinessPictureType.head.getPictureType());
        List<BusinessPictureDTO> pics = salesmanDTO.getHeadPic();
        for (BusinessPictureDTO businessPictureDTO : pics) {
          businessPictureDTO.setMemberId(salesman.getMemberId());
        }
        this.businessFacade.batchSaveBusinessPicture(pics);
      }
      if (null != salesmanDTO.getCertificatePics() && ! salesmanDTO.getCertificatePics().isEmpty()) {
        this.businessFacade.deleteBusinessPictureBy(salesman.getMemberId(), BusinessPictureType.certificate.getPictureType());
        List<BusinessPictureDTO> pics = salesmanDTO.getCertificatePics();
        for (BusinessPictureDTO businessPictureDTO : pics) {
          businessPictureDTO.setMemberId(salesman.getMemberId());
        }
        this.businessFacade.batchSaveBusinessPicture(pics);
      }
    }
  }

  public void saveSalesman(SalesmanDTO salesmanDTO) throws ArgsException {
    if (null == salesmanDTO || ! salesmanDTO.checkSaveData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO param = new MemberDTO();
    param.setMobile(salesmanDTO.getMobile());
    List<MemberDTO> memberDTOs = this.businessFacade.getMemberList(param);
    if (null != memberDTOs && memberDTOs.size() > 0) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的人员已经存在");
    }
    Long userId = null;
    UserAccountDTO userAccountDTO = getUserAccount(salesmanDTO.getMobile());
    if (null == userAccountDTO || null == userAccountDTO.getUserId()) {
      userId = insertUserAccount(salesmanDTO.getMobile(), salesmanDTO.getName(), salesmanDTO.getAddress(), UserType.salesman.getUserType());
    } else {
      userId = userAccountDTO.getUserId();
      updateUserAccount(userId, salesmanDTO.getMobile(), salesmanDTO.getName(), salesmanDTO.getAddress(), UserType.salesman.getUserType());
    }
    CoachDTO old = businessFacade.getCoach(userId);
    salesmanDTO.setMemberId(userId);
    salesmanDTO.setIdentity(MemberIdentity.salesman.getIdentity());
    salesmanDTO.setEnabled(1);
    salesmanDTO.setDeleted(0);
    salesmanDTO.setCreateTime(new Date());
    salesmanDTO.setUpdateTime(salesmanDTO.getCreateTime());
    if (null != old) {
      updateSalesman(salesmanDTO);
    } else {
      salesmanDTO.setBeforeDeletedMobile(salesmanDTO.getMobile());
      Long memberId = this.businessFacade.saveMember(salesmanDTO);
      salesmanDTO.setMemberId(memberId);
      this.businessFacade.saveSalesman(salesmanDTO);
      List<BusinessPictureDTO> pictures = new ArrayList<>();
      pictures.addAll(salesmanDTO.getHeadPic());
      pictures.addAll(salesmanDTO.getCertificatePics());
      for (BusinessPictureDTO businessPictureDTO : pictures) {
        businessPictureDTO.setMemberId(memberId);
      }
      this.businessFacade.batchSaveBusinessPicture(pictures);
    }
  }

  public void enabledSalesman(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setEnabled(1);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
  }

  public void disabledSalesman(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setEnabled(0);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
  }

  public void deleteSalesman(String ids) throws ArgsException {
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
