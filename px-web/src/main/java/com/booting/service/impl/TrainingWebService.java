/**create by liuhua at 2017年12月21日 下午2:08:41**/
package com.booting.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.common.CouponBusinessType;
import com.booting.common.PxConstants.ApplyStatus;
import com.booting.coupon.dto.CouponRelationDTO;
import com.booting.coupon.facade.CouponFacade;
import com.booting.kindergarten.dto.StudentDTO;
import com.booting.member.dto.MemberDTO;
import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.service.SmsIdentityService;
import com.booting.training.dto.ApplyDetailDTO;
import com.booting.training.dto.ApplyInfoDTO;
import com.booting.training.dto.ApplyItemDTO;
import com.booting.training.dto.KickbackDetailDTO;
import com.booting.training.dto.PhysicalClassDTO;
import com.booting.training.dto.PromoterDTO;
import com.booting.training.dto.TrainingItemDTO;
import com.booting.training.dto.TrainingItemPictureDTO;
import com.booting.training.dto.TrainingItemPriceDTO;
import com.booting.training.facade.TrainingFacade;
import com.star.framework.httpapi.WeiXinUtil;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;
import com.star.framework.utils.DataExportUtil;

@Service("trainingWebService")
public class TrainingWebService extends BaseWebService {

  @Autowired
  private TrainingFacade trainingFacade;
  @Autowired
  private SmsIdentityService smsIdentityService;
  @Autowired
  private CouponFacade couponFacade;
  @Autowired
  private KindergartenWebService kindergartenWebService;

  public PageList<TrainingItemDTO> getListForPage(QueryParam queryParam, Class<TrainingItemDTO> class1) {
    return trainingFacade.getTrainingItemListForPage(queryParam);
  }

  public TrainingItemDTO getTrainingItem(Long itemId) {
    if (null == itemId) {
      throw new ArgsException("itemId 必填");
    }
    TrainingItemDTO params = new TrainingItemDTO();
    params.setItemId(itemId);
    TrainingItemDTO trainingItemDTO = this.trainingFacade.getTrainingItem(params);
    if (null != trainingItemDTO) {
      List<TrainingItemPriceDTO> prices = getApplyItems().stream().map(item -> {
          TrainingItemPriceDTO trainingItemPriceDTO = new TrainingItemPriceDTO();
          trainingItemPriceDTO.setItemId(itemId);
          trainingItemPriceDTO.setApplyItemId(item.getApplyItemId());
          TrainingItemPriceDTO titem = this.trainingFacade.getTrainingItemPrice(trainingItemPriceDTO);
          TrainingItemPriceDTO price = new TrainingItemPriceDTO();
          price.setApplyItemId(item.getApplyItemId());
          price.setItemName(item.getItemName());
          if (null != titem) {
            price.setPrice(titem.getPrice());
          }
          return price;
        }).collect(Collectors.toList());
      trainingItemDTO.setPrices(prices);
      TrainingItemPictureDTO trainingItemPictureDTO = new TrainingItemPictureDTO();
      trainingItemPictureDTO.setItemId(itemId);
      List<TrainingItemPictureDTO> pictures = this.trainingFacade.getTrainingItemPictureList(trainingItemPictureDTO);
      trainingItemDTO.setPictures(pictures);
      CouponRelationDTO crd = new CouponRelationDTO();
      crd.setBusinessType(CouponBusinessType.training.name());
      crd.setBusinessId(itemId);
      List<CouponRelationDTO> coupons = this.couponFacade.getCouponRelationList(crd);
      if (null != coupons && !coupons.isEmpty()) {
        trainingItemDTO.setCoupons(coupons);
        String cardIds = "";
        for (CouponRelationDTO couponRelationDTO : coupons) {
          cardIds += "," + couponRelationDTO.getCardId();
        }
        if (cardIds.length() > 0) {
          trainingItemDTO.setCardIds(cardIds.substring(1));
        }
      }
      if (trainingItemDTO.getSubType() == 2) {
        PhysicalClassDTO physicalClassDTO = new PhysicalClassDTO();
        physicalClassDTO.setPhysicalClassId(trainingItemDTO.getPhysicalClassId());
        physicalClassDTO = this.trainingFacade.getPhysicalClass(physicalClassDTO);
        trainingItemDTO.setPhysicalClass(physicalClassDTO);
      }
    }
    return trainingItemDTO;
  }

  public List<ApplyItemDTO> getApplyItems() {
    ApplyItemDTO applyItemDTO = new ApplyItemDTO();
    applyItemDTO.setDeleted(0);
    applyItemDTO.setEnabled(1);
    List<ApplyItemDTO> items = this.trainingFacade.getApplyItemList(applyItemDTO);
    return items;
  }
  
  public void saveTrainingItem(TrainingItemDTO trainingItemDTO) throws ArgsException, IOException {
    if (null == trainingItemDTO || StringUtils.isBlank(trainingItemDTO.getTitle()) || null == trainingItemDTO.getPictures() || trainingItemDTO.getPictures().isEmpty()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    trainingItemDTO.setCreateTime(new Date());
    trainingItemDTO.setEnabled(1);
    trainingItemDTO.setDeleted(0);
    Long itemId = this.trainingFacade.saveTrainingItem(trainingItemDTO);

    List<TrainingItemPriceDTO> prices = trainingItemDTO.getPrices();
    if (null != prices && !prices.isEmpty()) {
      for (TrainingItemPriceDTO trainingItemPriceDTO : prices) {
        trainingItemPriceDTO.setItemId(itemId);
      }
      this.trainingFacade.batchSaveTrainingItemPrice(prices);
    }

    List<TrainingItemPictureDTO> pictures = trainingItemDTO.getPictures();
    for (TrainingItemPictureDTO trainingItemPictureDTO : pictures) {
      trainingItemPictureDTO.setItemId(itemId);
    }
    this.trainingFacade.batchSaveTrainingItemPicture(pictures);
    if (StringUtils.isNotBlank(trainingItemDTO.getCardIds())) {
      List<CouponRelationDTO> crds = new ArrayList<>();
      String[] cardIds = trainingItemDTO.getCardIds().split(",");
      for (String cardId : cardIds) {
        CouponRelationDTO crd = new CouponRelationDTO();
        crd.setBusinessId(itemId);
        crd.setBusinessType(CouponBusinessType.training.name());
        crd.setCardId(cardId);
        Map<String, Object> card = WeiXinUtil.getCardDetail(cardId);
        crd.setCardType(card.get("cardType") + "");
        crd.setCardName(card.get("title") + "");
        crds.add(crd);
      }
      this.couponFacade.batchSaveCouponRelation(crds);
    }
  }

  public void updateTrainingItem(TrainingItemDTO trainingItemDTO) throws ArgsException, IOException {
    if (null == trainingItemDTO || null == trainingItemDTO.getItemId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    TrainingItemDTO olItem = this.trainingFacade.getTrainingItem(trainingItemDTO.getItemId());
    if (null != olItem) {
      trainingFacade.updateTrainingItem(trainingItemDTO);
      trainingFacade.deleteTrainingItemPriceByItemId(trainingItemDTO.getItemId());
      List<TrainingItemPriceDTO> prices = trainingItemDTO.getPrices();
      if (null != prices && !prices.isEmpty()) {
        for (TrainingItemPriceDTO trainingItemPriceDTO : prices) {
          trainingItemPriceDTO.setItemId(trainingItemDTO.getItemId());
        }
        this.trainingFacade.batchSaveTrainingItemPrice(prices);
      }
      trainingFacade.deleteTrainingItemPictureByItemId(trainingItemDTO.getItemId());
      List<TrainingItemPictureDTO> pictures = trainingItemDTO.getPictures();
      if (null != pictures && !pictures.isEmpty()) {
        for (TrainingItemPictureDTO trainingItemPictureDTO : pictures) {
          trainingItemPictureDTO.setItemId(trainingItemDTO.getItemId());
        }
        this.trainingFacade.batchSaveTrainingItemPicture(pictures);
      }
      this.couponFacade.deleteCouponRelationByBusiness(CouponBusinessType.training.name(), trainingItemDTO.getItemId());
      if (StringUtils.isNotBlank(trainingItemDTO.getCardIds())) {
        List<CouponRelationDTO> crds = new ArrayList<>();
        String[] cardIds = trainingItemDTO.getCardIds().split(",");
        if (cardIds.length > 0) {
          for (String cardId : cardIds) {
            CouponRelationDTO crd = new CouponRelationDTO();
            crd.setBusinessId(trainingItemDTO.getItemId());
            crd.setBusinessType(CouponBusinessType.training.name());
            crd.setCardId(cardId);
            Map<String, Object> card = WeiXinUtil.getCardDetail(cardId);
            crd.setCardType(card.get("cardType") + "");
            crd.setCardName(card.get("title") + "");
            crd.setGetLimit(Integer.parseInt(card.get("getLimit") + ""));
            crds.add(crd);
          }
          this.couponFacade.batchSaveCouponRelation(crds);
        }
      }
    }
  }

  public void enabledTrainingItem(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    TrainingItemDTO dto = new TrainingItemDTO();
    dto.setEnabled(1);
    dto.setItemIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void disabledTrainingItem(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    TrainingItemDTO dto = new TrainingItemDTO();
    dto.setEnabled(0);
    dto.setItemIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void deleteTrainingItem(String ids) throws ArgsException {
    if (StringUtils.isBlank(ids)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    TrainingItemDTO dto = new TrainingItemDTO();
    dto.setDeleted(1);
    dto.setItemIds(ids);
    trainingFacade.updateBySql(dto);
  }

  public void deleteTrainingApply(String ids) throws ArgsException {
    // TODO Auto-generated method stub

  }

  public PageList<ApplyInfoDTO> getListForPageApplyInfo(QueryParam queryParam, Class<ApplyInfoDTO> class1) {
    return trainingFacade.getApplyInfoListForPage(queryParam);
  }

  public ApplyInfoDTO getApplyInfo(Long applyId) {
    ApplyInfoDTO applyInfoDTO = this.trainingFacade.getApplyInfo(applyId);
    ApplyDetailDTO params = new ApplyDetailDTO();
    params.setApplyId(applyInfoDTO.getApplyId());
    List<ApplyDetailDTO> details = this.trainingFacade.getApplyDetailList(params);
    applyInfoDTO.setDetails(details);
    applyInfoDTO.setTrainingItem(getTrainingItem(applyInfoDTO.getItemId()));
    return applyInfoDTO;
  }

  public void updateApplyInfo(ApplyInfoDTO applyInfoDTO) throws ArgsException {
    if (null == applyInfoDTO || null == applyInfoDTO.getApplyId()) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    this.trainingFacade.updateApplyInfo(applyInfoDTO);
  }

  public SmsIdentityDTO getSmsIdentity(String phone, Integer tag) {
    SmsIdentityDTO dto = new SmsIdentityDTO();
    dto.setPhone(phone);
    dto.setTag(tag);
    List<SmsIdentityDTO> list = smsIdentityService.getSimpleList(dto);
    if (null != list && list.size() > 0) {
      return list.get(0);
    }
    return null;
  }

  public Long saveApplyInfo3(ApplyInfoDTO applyInfoDTO) throws ArgsException {
    if (null == applyInfoDTO || StringUtils.isBlank(applyInfoDTO.getChildName()) || 
        null == applyInfoDTO.getChildAge() || null == applyInfoDTO.getChildSex() || StringUtils.isBlank(applyInfoDTO.getOpenId()) || 
        StringUtils.isBlank(applyInfoDTO.getMobile())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    applyInfoDTO.setItemId(1L);
    applyInfoDTO.setType(5);
    
    String openId = applyInfoDTO.getOpenId();
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setOpenId(openId);
    memberDTO = memberFacade.getMember(memberDTO);
    if (null == memberDTO || null == memberDTO.getMemberId()) {
      memberDTO = new MemberDTO();
      memberDTO.setOpenId(openId);
      memberDTO.setMobile(applyInfoDTO.getMobile());
      memberDTO.setCreateTime(new Date());
      memberFacade.saveMember(memberDTO);
    } else {
      if (StringUtils.isBlank(memberDTO.getMobile())) {
        memberDTO.setMobile(applyInfoDTO.getMobile());
        memberFacade.updateMember(memberDTO);
      }
    }
    TrainingItemDTO itemDTO = this.trainingFacade.getTrainingItem(applyInfoDTO.getItemId());
    if (null == itemDTO) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "项目不存在");
    }
    if (itemDTO.getSubType() == 1) {
      TrainingItemPriceDTO trainingItemPriceDTO = new TrainingItemPriceDTO();
      trainingItemPriceDTO.setItemId(itemDTO.getItemId());
      trainingItemPriceDTO.setApplyItemId(applyInfoDTO.getApplyItemId());
      TrainingItemPriceDTO price = this.trainingFacade.getTrainingItemPrice(trainingItemPriceDTO);
      if (null == price || 0 == price.getPrice()) {
        applyInfoDTO.setStatus(ApplyStatus.yzf.getStatus());
      } else {
        applyInfoDTO.setStatus(ApplyStatus.dfk.getStatus());
      }
    }
    
    applyInfoDTO.setCreateTime(new Date());
    applyInfoDTO.setUserId(memberDTO.getMemberId());
    Long applyId = this.trainingFacade.saveApplyInfo(applyInfoDTO);
    if (null != applyInfoDTO.getDetails() && applyInfoDTO.getType() == 4) {
      this.trainingFacade.batchSaveApplyDetail(applyInfoDTO.getDetails());
    }
    if (itemDTO.getSubType() == 2) {
      StudentDTO studentDTO = new StudentDTO();
      studentDTO.setBirth(Date.from(LocalDate.now().minusYears(applyInfoDTO.getChildAge()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
      studentDTO.setGuardianMobile(applyInfoDTO.getMobile());
      studentDTO.setGuardianName(applyInfoDTO.getName());
      studentDTO.setName(applyInfoDTO.getChildName());
      studentDTO.setSex(applyInfoDTO.getChildSex());
      studentDTO.setType(2);
      studentDTO.setPhysicalClassId(itemDTO.getPhysicalClassId());
      kindergartenWebService.saveStudent(studentDTO);
    }
    return applyId;
  }
  
  public Long saveApplyInfo(ApplyInfoDTO applyInfoDTO) throws ArgsException {
    if (null == applyInfoDTO || null == applyInfoDTO.getItemId() || StringUtils.isBlank(applyInfoDTO.getChildName()) || 
        null == applyInfoDTO.getChildAge() || null == applyInfoDTO.getChildSex() || StringUtils.isBlank(applyInfoDTO.getOpenId()) || 
        StringUtils.isBlank(applyInfoDTO.getMobile())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    applyInfoDTO.setType(5);

    String openId = applyInfoDTO.getOpenId();
    MemberDTO memberDTO = new MemberDTO();
    memberDTO.setOpenId(openId);
    memberDTO = memberFacade.getMember(memberDTO);
    if (null == memberDTO || null == memberDTO.getMemberId()) {
      memberDTO = new MemberDTO();
      memberDTO.setOpenId(openId);
      memberDTO.setMobile(applyInfoDTO.getMobile());
      memberDTO.setCreateTime(new Date());
      memberFacade.saveMember(memberDTO);
    } else {
      if (StringUtils.isBlank(memberDTO.getMobile())) {
        memberDTO.setMobile(applyInfoDTO.getMobile());
        memberFacade.updateMember(memberDTO);
      }
    }
    TrainingItemDTO itemDTO = this.trainingFacade.getTrainingItem(applyInfoDTO.getItemId());
    if (null == itemDTO) {
      throw new ArgsException(FailureCode.ERR_002.getCode(), "项目不存在");
    }
    if (itemDTO.getSubType() == 1) {
      TrainingItemPriceDTO trainingItemPriceDTO = new TrainingItemPriceDTO();
      trainingItemPriceDTO.setItemId(itemDTO.getItemId());
      trainingItemPriceDTO.setApplyItemId(applyInfoDTO.getApplyItemId());
      TrainingItemPriceDTO price = this.trainingFacade.getTrainingItemPrice(trainingItemPriceDTO);
      if (null == price || 0 == price.getPrice()) {
        applyInfoDTO.setStatus(ApplyStatus.yzf.getStatus());
      } else {
        applyInfoDTO.setStatus(ApplyStatus.dfk.getStatus());
      }
    }else {
      PhysicalClassDTO physicalClass = this.trainingFacade.getPhysicalClass(itemDTO.getPhysicalClassId());
      if (null != physicalClass && null != physicalClass.getPrice() && physicalClass.getPrice() > 0) {
        applyInfoDTO.setStatus(ApplyStatus.dfk.getStatus());
      }else {
        applyInfoDTO.setStatus(ApplyStatus.yzf.getStatus());
      }
    }
    
    // ApplyInfoDTO params = new ApplyInfoDTO();
    // params.setType(applyInfoDTO.getType());
    // params.setMobile(applyInfoDTO.getMobile());
    // List<ApplyInfoDTO> list = this.trainingFacade.getApplyInfoList(params);
    // if (null != list && !list.isEmpty()) {
    // throw new ArgsException(FailureCode.ERR_702);
    // }
    applyInfoDTO.setCreateTime(new Date());
    applyInfoDTO.setUserId(memberDTO.getMemberId());
    Long applyId = this.trainingFacade.saveApplyInfo(applyInfoDTO);
    if (null != applyInfoDTO.getDetails() && applyInfoDTO.getType() == 4) {
      this.trainingFacade.batchSaveApplyDetail(applyInfoDTO.getDetails());
    }
    if (itemDTO.getSubType() == 2) {
      StudentDTO studentDTO = new StudentDTO();
      studentDTO.setBirth(Date.from(LocalDate.now().minusYears(applyInfoDTO.getChildAge()).atStartOfDay(ZoneId.systemDefault()).toInstant()));
      studentDTO.setGuardianMobile(applyInfoDTO.getMobile());
      studentDTO.setGuardianName(applyInfoDTO.getName());
      studentDTO.setName(applyInfoDTO.getChildName());
      studentDTO.setSex(applyInfoDTO.getChildSex());
      studentDTO.setType(2);
      studentDTO.setPhysicalClassId(itemDTO.getPhysicalClassId());
      kindergartenWebService.saveStudent(studentDTO);
    }
    return applyId;
  }

  public ApiResult searchTrainingItems(String openId, QueryParam queryParam) throws ArgsException {
    PageList<TrainingItemDTO> pageList = this.trainingFacade.getTrainingItemListForPage(queryParam);
    List<TrainingItemDTO> list = pageList.getDataList();
    if (StringUtils.isNotBlank(openId)) {
      MemberDTO member = getMember(openId);
      if (null == member || StringUtils.isBlank(member.getMobile())) {
        for (TrainingItemDTO trainingItemDTO : list) {
          ApplyInfoDTO applyInfo = getApplyInfo(member.getMemberId(), trainingItemDTO.getItemId());
          if (null != applyInfo) {
            trainingItemDTO.setApplied(true);
          }
        }
      }
    }
    ApiResult apiResult = new ApiResult();
    apiResult.setData(list);
    PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
    apiResult.setPageInfo(pageInfo);
    return apiResult;
  }

  public ApplyInfoDTO getApplyInfo(Long userId, Long itemId) throws ArgsException {
    if (null == userId || null == itemId) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    TrainingItemDTO item = getTrainingItem(itemId);
    if (null == item) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    ApplyInfoDTO aid = new ApplyInfoDTO();
    aid.setUserId(userId);
    aid.setItemId(itemId);
    aid.setStatus(2);
    ApplyInfoDTO applyInfo = this.trainingFacade.getApplyInfo(aid);
    ApplyDetailDTO params = new ApplyDetailDTO();
    params.setApplyId(applyInfo.getApplyId());
    List<ApplyDetailDTO> details = this.trainingFacade.getApplyDetailList(params);
    applyInfo.setDetails(details);
    applyInfo.setTrainingItem(item);
    return applyInfo;
  }

  public ApplyInfoDTO getApplyInfo(Long itemId, String openId) throws ArgsException {
    MemberDTO member = getMember(openId);
    if (null == member || StringUtils.isBlank(member.getMobile())) {
      return getApplyInfo(member.getMemberId(), itemId);
    }
    return null;
  }

  public ApiResult searchApplyInfos(QueryParam queryParam) throws ArgsException {
    String openId = queryParam.getParam("openId");
    if (StringUtils.isBlank(openId)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    MemberDTO member = getMember(openId);
    if (null == member || StringUtils.isBlank(member.getMobile())) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    queryParam.addParam("userId", member.getMemberId());
    PageList<ApplyInfoDTO> pageList = this.trainingFacade.getApplyInfoListForPage(queryParam);
    List<ApplyInfoDTO> list = pageList.getDataList();
    ApplyDetailDTO params = new ApplyDetailDTO();
    for (ApplyInfoDTO applyInfoDTO : list) {
      applyInfoDTO.setTrainingItem(getTrainingItem(applyInfoDTO.getItemId()));
      params.setApplyId(applyInfoDTO.getApplyId());
      List<ApplyDetailDTO> details = this.trainingFacade.getApplyDetailList(params);
      applyInfoDTO.setDetails(details);
    }
    ApiResult apiResult = new ApiResult();
    apiResult.setData(list);
    PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
    apiResult.setPageInfo(pageInfo);
    return apiResult;
  }

  public void exportRecord(HttpServletResponse response) {
    OutputStream os = null;
    try {
      os = response.getOutputStream();
      response.setContentType("application/vnd.ms-excel");
      String filename = "已付款报名信息.xlsx";
      filename = URLEncoder.encode(filename, "UTF-8");
      response.setHeader("Content-Disposition", "attachment; filename=" + filename);
      String[] titles = new String[] { "订单号", "交易单号", "推广员", "推广员手机", "家长姓名", "家长手机", "孩子姓名", "孩子性别", "报名时间" };
      ApplyInfoDTO applyInfoDTO = new ApplyInfoDTO();
      List<ApplyInfoDTO> list = trainingFacade.getApplyInfoList(applyInfoDTO);
      List<String[]> datas = list.stream().map(info -> {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String[] record = new String[titles.length];
        record[0] = info.getOrderNo();
        record[1] = info.getTransactionId();
        record[2] = info.getPromoter();
        record[3] = info.getPromoterMobile();
        record[4] = info.getName();
        record[5] = info.getMobile();
        record[6] = info.getChildName();
        record[7] = info.getChildSex() == 1 ? "男" : "女";
        record[8] = sdf.format(info.getCreateTime());
        return record;
      }).collect(Collectors.toList());
      DataExportUtil.createXlsxExcelFile2(os, "已付款报名信息", titles, datas);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (null != os) {
        try {
          os.close();
          os = null;
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public Integer getTrainingItemPrice(Long itemId, Long applyItemId) {
    TrainingItemPriceDTO trainingItemPriceDTO = new TrainingItemPriceDTO();
    trainingItemPriceDTO.setApplyItemId(applyItemId);
    trainingItemPriceDTO.setItemId(itemId);
    TrainingItemPriceDTO priceDTO = this.trainingFacade.getTrainingItemPrice(trainingItemPriceDTO);
    if(null == priceDTO) {
      return 0;
    }
    return priceDTO.getPrice();
  }

  public Map<String, Object> applyTotal() {
    Map<String, Object> map = new HashMap<>();
    QueryParam queryParam = new QueryParam();
    queryParam.setPageNo(1);
    queryParam.setPageSize(10);
    queryParam.setOrderBy("createTime");
    queryParam.setOrderType("desc");
    queryParam.addParam("status", 2);
    PageList<ApplyInfoDTO> pageList = this.trainingFacade.getApplyInfoListForPage(queryParam);
    map.put("infos", pageList.getDataList());
    map.put("totalNum", pageList.getTotalRecord());
    return map;
  }

  public PromoterDTO applyForPromoter(String openId) {
    if(StringUtils.isBlank(openId)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    String mobile = getMobile(openId);
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_002, "请先绑定手机号");
    }
    PromoterDTO promoterDTO = new PromoterDTO();
    promoterDTO.setMobile(mobile);
    promoterDTO = this.trainingFacade.getPromoter(promoterDTO);
    if (null == promoterDTO) {
      throw new ArgsException(FailureCode.ERR_002, "你不是推广员");
    }
    return promoterDTO;
  }

  public void applyKickback(String openId, String wxNumber) {
    if (StringUtils.isBlank(openId) || StringUtils.isBlank(wxNumber)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    String mobile = getMobile(openId);
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_002, "请先绑定手机号");
    }
    PromoterDTO promoter = new PromoterDTO();
    promoter.setMobile(mobile);
    promoter = this.trainingFacade.getPromoter(promoter);
    if (null == promoter) {
      throw new ArgsException(FailureCode.ERR_002, "你不是推广员");
    }
    //得到他已经提现的金额和最后一次提现的时间
    KickbackDetailDTO kickbackDetail = this.trainingFacade.getLatestKickbackDetail(promoter.getPromoterId());
    Date beginDatePoint = DateUtils.parseDate("2019-01-01", new String[] {"yyyy-MM-dd"});
    if (null != kickbackDetail && null != kickbackDetail.getPointEndTime()) {
      beginDatePoint = kickbackDetail.getPointEndTime();
    }
    Map<String, Object> kickback = this.trainingFacade.totalMoneyByPromoter(promoter.getPromoterId(), beginDatePoint);
    Object m = kickback.get("money");
    Object t = kickback.get("time");
    if(null == m || null == t) {
      throw new ArgsException(FailureCode.ERR_002, "暂无可提现的金额");
    }
    Double money = Double.parseDouble(m.toString());
    if (money < 200 * 100 * 0) {
      throw new ArgsException(FailureCode.ERR_002, "暂无可提现的金额");
    }
    Date time = (Date) t;
    KickbackDetailDTO kickbackDetailDTO = new KickbackDetailDTO();
    kickbackDetailDTO.setCreateTime(new Date());
    kickbackDetailDTO.setCreateUser(promoter.getName());
    kickbackDetailDTO.setMoney(money.intValue());
    kickbackDetailDTO.setPointBeginTime(beginDatePoint);
    kickbackDetailDTO.setPointEndTime(time);
    kickbackDetailDTO.setPromoterId(promoter.getPromoterId());
    kickbackDetailDTO.setState(1);
    kickbackDetailDTO.setUpdateTime(kickbackDetailDTO.getCreateTime());
    kickbackDetailDTO.setWxNumber(wxNumber);
    this.trainingFacade.saveKickbackDetail(kickbackDetailDTO);
  }

  public ApiResult kickbackList(String openId, QueryParam queryParam) {
    if (StringUtils.isBlank(openId)) {
      throw new ArgsException(FailureCode.ERR_002);
    }
    String mobile = getMobile(openId);
    if (StringUtils.isBlank(mobile)) {
      throw new ArgsException(FailureCode.ERR_002, "请先绑定手机号");
    }
    PromoterDTO promoter = new PromoterDTO();
    promoter.setMobile(mobile);
    promoter = this.trainingFacade.getPromoter(promoter);
    if (null == promoter) {
      throw new ArgsException(FailureCode.ERR_002, "你不是推广员");
    }
    queryParam.addParam("promoterId", promoter.getPromoterId());
    PageList<KickbackDetailDTO> pageList = this.trainingFacade.getKickbackDetailListForPage(queryParam);
    List<KickbackDetailDTO> list = pageList.getDataList();
    ApiResult apiResult = new ApiResult();
    apiResult.setData(list);
    PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
    apiResult.setPageInfo(pageInfo);
    return apiResult;
  }
}
