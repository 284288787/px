/**create by liuhua at 2017年12月21日 下午2:08:41**/
package com.booting.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.common.CouponBusinessType;
import com.booting.common.PxConstants.ApplyStatus;
import com.booting.coupon.dto.CouponRelationDTO;
import com.booting.coupon.facade.CouponFacade;
import com.booting.member.dto.MemberDTO;
import com.booting.pub.dto.SmsIdentityDTO;
import com.booting.pub.service.SmsIdentityService;
import com.booting.training.dto.ApplyDetailDTO;
import com.booting.training.dto.ApplyInfoDTO;
import com.booting.training.dto.ApplyItemDTO;
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

  public PageList<TrainingItemDTO> getListForPage(QueryParam queryParam, Class<TrainingItemDTO> class1) {
    return trainingFacade.getTrainingItemListForPage(queryParam);
  }

  public TrainingItemDTO getTrainingItem(Long itemId) {
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

  public Long saveApplyInfo(ApplyInfoDTO applyInfoDTO) throws ArgsException {
    if (null == applyInfoDTO || null == applyInfoDTO.getItemId() || StringUtils.isBlank(applyInfoDTO.getChildName()) || null == applyInfoDTO.getChildAge() || null == applyInfoDTO.getChildSex() || StringUtils.isBlank(applyInfoDTO.getOpenId()) || StringUtils.isBlank(applyInfoDTO.getMobile())) {
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
    // ApplyInfoDTO params = new ApplyInfoDTO();
    // params.setType(applyInfoDTO.getType());
    // params.setMobile(applyInfoDTO.getMobile());
    // List<ApplyInfoDTO> list = this.trainingFacade.getApplyInfoList(params);
    // if (null != list && !list.isEmpty()) {
    // throw new ArgsException(FailureCode.ERR_702);
    // }
    applyInfoDTO.setCreateTime(new Date());
    if (null == itemDTO.getPrice() || 0 == itemDTO.getPrice()) {
      applyInfoDTO.setStatus(ApplyStatus.yzf.getStatus());
    } else {
      applyInfoDTO.setStatus(ApplyStatus.dfk.getStatus());
    }
    applyInfoDTO.setUserId(memberDTO.getMemberId());
    Long applyId = this.trainingFacade.saveApplyInfo(applyInfoDTO);
    if (null != applyInfoDTO.getDetails() && applyInfoDTO.getType() == 4) {
      this.trainingFacade.batchSaveApplyDetail(applyInfoDTO.getDetails());
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
      String[] titles = new String[] { "订单号", "交易单号", "推广员", "推广员手机", "家长姓名", "家长手机", "报名时间" };
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
        record[6] = sdf.format(info.getCreateTime());
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
}
