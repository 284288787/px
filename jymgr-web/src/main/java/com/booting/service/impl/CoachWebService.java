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
import com.booting.business.conf.BusinessConst.BusinessPictureType;
import com.booting.business.conf.BusinessConst.MemberIdentity;
import com.booting.business.dto.BusinessPictureDTO;
import com.booting.business.dto.CoachDTO;
import com.booting.business.dto.MemberDTO;
import com.booting.business.facade.BusinessFacade;
import com.booting.common.CommonConstants.UserType;
import com.booting.system.dto.UserAccountDTO;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

@Service("coachWebService")
public class CoachWebService extends BaseWebService{

  @Autowired
  private BusinessFacade businessFacade;
  
  public PageList<CoachDTO> getListForPageCoach(QueryParam queryParam, Class<CoachDTO> class1) {
    return businessFacade.getCoachListForPage(queryParam);
  }

  public CoachDTO getCoach(Long memberId) {
    MemberDTO member = businessFacade.getMember(memberId);
    CoachDTO coach = businessFacade.getCoach(memberId);
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
    coach.setHeadPic(picsMap.get(BusinessPictureType.head.getPictureType()));
    coach.setCertificatePics(picsMap.get(BusinessPictureType.certificate.getPictureType()));
    coach.setEduPics(picsMap.get(BusinessPictureType.edu.getPictureType()));
    coach.setTeachingCertPics(picsMap.get(BusinessPictureType.teachingCert.getPictureType()));
    move(member, coach);
    coach.setHeadPath(null != coach.getHeadPic() && ! coach.getHeadPic().isEmpty() ? coach.getHeadPic().get(0).getPicPath() : "");
    return coach;
  }

  private void move(MemberDTO member, CoachDTO coach){
    if (null == member || null == coach) {
      return ;
    }
    coach.setMobile(member.getMobile());
    coach.setName(member.getName());
    coach.setIdentity(member.getIdentity());
    coach.setEnabled(member.getEnabled());
    coach.setDeleted(member.getDeleted());
    coach.setBeforeDeletedMobile(member.getBeforeDeletedMobile());
    coach.setCreateTime(member.getCreateTime());
    coach.setUpdateTime(member.getUpdateTime());
  }

  public void saveCoach(CoachDTO coachDTO) throws ArgsException {
    if (null == coachDTO || ! coachDTO.checkSaveData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO param = new MemberDTO();
    param.setMobile(coachDTO.getMobile());
    List<MemberDTO> memberDTOs = this.businessFacade.getMemberList(param);
    if (null != memberDTOs && memberDTOs.size() > 0) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的人员已经存在");
    }
    Long userId = null;
    UserAccountDTO userAccountDTO = getUserAccount(coachDTO.getMobile());
    if (null == userAccountDTO || null == userAccountDTO.getUserId()) {
      userId = insertUserAccount(coachDTO.getMobile(), coachDTO.getName(), coachDTO.getAddress(), UserType.coach.getUserType());
    } else {
      userId = userAccountDTO.getUserId();
      updateUserAccount(userId, coachDTO.getMobile(), coachDTO.getName(), coachDTO.getAddress(), UserType.coach.getUserType());
    }
    CoachDTO old = businessFacade.getCoach(userId);
    coachDTO.setMemberId(userId);
    coachDTO.setIdentity(MemberIdentity.coach.getIdentity());
    coachDTO.setEnabled(1);
    coachDTO.setDeleted(0);
    coachDTO.setCreateTime(new Date());
    coachDTO.setUpdateTime(coachDTO.getCreateTime());
    if (null != old) {
      updateCoach(coachDTO);
    } else {
      coachDTO.setBeforeDeletedMobile(coachDTO.getMobile());
      Long memberId = this.businessFacade.saveMember(coachDTO);
      coachDTO.setMemberId(memberId);
      this.businessFacade.saveCoach(coachDTO);
      List<BusinessPictureDTO> pictures = new ArrayList<>();
      pictures.addAll(coachDTO.getHeadPic());
      pictures.addAll(coachDTO.getCertificatePics());
      pictures.addAll(coachDTO.getEduPics());
      pictures.addAll(coachDTO.getTeachingCertPics());
      for (BusinessPictureDTO businessPictureDTO : pictures) {
        businessPictureDTO.setMemberId(memberId);
      }
      this.businessFacade.batchSaveBusinessPicture(pictures);
    }
  }

  public void updateCoach(CoachDTO coachDTO) throws ArgsException {
    if (null == coachDTO || null == coachDTO.getMemberId() || ! coachDTO.checkSaveData()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO member = businessFacade.getMember(coachDTO.getMemberId());
    if (null == member) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "会员信息不存在.");
    }
    CoachDTO coach = businessFacade.getCoach(coachDTO.getMemberId());
    if (null == coach) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "会员信息不存在。");
    }
    if (StringUtils.isNotBlank(coachDTO.getMobile())) {
      MemberDTO param = new MemberDTO();
      param.setMobile(coachDTO.getMobile());
      List<MemberDTO> memberDTOs = this.businessFacade.getMemberList(param);
      if (null != memberDTOs && ! memberDTOs.isEmpty()) {
        if (memberDTOs.size() > 1) {
          throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的教练员已经存在。");
        }else{
          MemberDTO memberDTO = memberDTOs.get(0);
          if (memberDTO.getMemberId() != coachDTO.getMemberId().longValue()) {
            throw new ArgsException(FailureCode.ERR_002.getCode(), "使用该手机号的教练员已经存在."); 
          }
        }
      }
    }
    if (StringUtils.isNotBlank(coachDTO.getMobile()) && StringUtils.isNotBlank(coachDTO.getName())) {
      if (StringUtils.isNotBlank(member.getMobile())) {
        coachDTO.setBeforeDeletedMobile(member.getMobile());
      }
      coachDTO.setUpdateTime(new Date());
      this.businessFacade.updateMember(coachDTO);
    }
    if (coachDTO.checkSaveData()) {
      businessFacade.updateCoach(coachDTO);
      UserAccountDTO userAccountDTO = getUserAccount(coachDTO.getMemberId());
      if (null != userAccountDTO && null != userAccountDTO.getUserId() && (! coachDTO.getMobile().equals(userAccountDTO.getMobile()) || ! coachDTO.getName().equals(userAccountDTO.getName()) || ! coachDTO.getAddress().equals(userAccountDTO.getAddress()))) {
        updateUserAccount(coachDTO.getMemberId(), coachDTO.getMobile(), coachDTO.getName(), coachDTO.getAddress(), UserType.coach.getUserType());
      }
      if (null != coachDTO.getHeadPic() && ! coachDTO.getHeadPic().isEmpty()) {
        this.businessFacade.deleteBusinessPictureBy(coach.getMemberId(), BusinessPictureType.head.getPictureType());
        List<BusinessPictureDTO> pics = coachDTO.getHeadPic();
        for (BusinessPictureDTO businessPictureDTO : pics) {
          businessPictureDTO.setMemberId(coach.getMemberId());
        }
        this.businessFacade.batchSaveBusinessPicture(pics);
      }
      if (null != coachDTO.getCertificatePics() && ! coachDTO.getCertificatePics().isEmpty()) {
        this.businessFacade.deleteBusinessPictureBy(coach.getMemberId(), BusinessPictureType.certificate.getPictureType());
        List<BusinessPictureDTO> pics = coachDTO.getCertificatePics();
        for (BusinessPictureDTO businessPictureDTO : pics) {
          businessPictureDTO.setMemberId(coach.getMemberId());
        }
        this.businessFacade.batchSaveBusinessPicture(pics);
      }
      if (null != coachDTO.getEduPics() && ! coachDTO.getEduPics().isEmpty()) {
        this.businessFacade.deleteBusinessPictureBy(coach.getMemberId(), BusinessPictureType.edu.getPictureType());
        List<BusinessPictureDTO> pics = coachDTO.getEduPics();
        for (BusinessPictureDTO businessPictureDTO : pics) {
          businessPictureDTO.setMemberId(coach.getMemberId());
        }
        this.businessFacade.batchSaveBusinessPicture(pics);
      }
      if (null != coachDTO.getTeachingCertPics() && ! coachDTO.getTeachingCertPics().isEmpty()) {
        this.businessFacade.deleteBusinessPictureBy(coach.getMemberId(), BusinessPictureType.teachingCert.getPictureType());
        List<BusinessPictureDTO> pics = coachDTO.getTeachingCertPics();
        for (BusinessPictureDTO businessPictureDTO : pics) {
          businessPictureDTO.setMemberId(coach.getMemberId());
        }
        this.businessFacade.batchSaveBusinessPicture(pics);
      }
    }
  }

  public void enabledCoach(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setEnabled(1);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
  }

  public void disabledCoach(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO dto = new MemberDTO();
    dto.setEnabled(0);
    dto.setMemberIds(ids);
    businessFacade.updateBySql(dto);
  }

  public void deleteCoach(String ids) throws ArgsException {
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
