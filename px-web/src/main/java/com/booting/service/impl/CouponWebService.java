/**create by liuhua at 2018年5月7日 上午10:05:06**/
package com.booting.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booting.coupon.dto.CouponRelationDTO;
import com.booting.coupon.facade.CouponFacade;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.framework.httpapi.Card;
import com.star.framework.httpapi.CardList;
import com.star.framework.httpapi.WeiXinUtil;
import com.star.framework.httpapi.WxCardSign;
import com.star.framework.jdbc.dao.result.PageList;
import com.star.framework.jdbc.dao.result.QueryParam;
import com.star.framework.specification.result.PageInfo;
import com.star.framework.specification.result.v2.ApiResult;

@Service("couponWebService")
public class CouponWebService {

	@Autowired
	private CouponFacade couponFacade;
	@Autowired
	private WeiXinUtil weiXinUtil;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public ApiResult searchCards(String openId, QueryParam queryParam) throws IOException {
		PageList<CouponRelationDTO> pageList = this.couponFacade.getCouponRelationListForPage(queryParam);
		List<CouponRelationDTO> list = pageList.getDataList();
		List<Map<String, Object>> res = new ArrayList<>();
		CardList cardList = null;
		if (StringUtils.isNotBlank(openId)) {
			cardList = WeiXinUtil.getCardList(openId);
		}
		for (CouponRelationDTO couponRelationDTO : list) {
			Map<String, Object> map = mapper.readValue(mapper.writeValueAsString(couponRelationDTO), new TypeReference<Map<String, Object>>() {});
			if (null != cardList) {
				Integer num = cardList.getNum(couponRelationDTO.getCardId());
				map.put("getNum", num);
			}
			res.add(map);
		}
		ApiResult apiResult = new ApiResult();
		apiResult.setData(res);
		PageInfo pageInfo = new PageInfo(pageList.getPageNo(), pageList.getPageSize(), pageList.getTotalRecord());
		apiResult.setPageInfo(pageInfo);
		return apiResult;
	}

	public List<Map<String, Object>> mineCards(String openId, String businessType, Long businessId) throws IOException {
		List<Map<String, Object>> res = new ArrayList<>();
		CardList cardList = WeiXinUtil.getCardList(openId);
		if (null != businessId && StringUtils.isNotBlank(businessType)) {
			CouponRelationDTO couponRelationDTO = new CouponRelationDTO();
			couponRelationDTO.setBusinessType(businessType);
			couponRelationDTO.setBusinessId(businessId);
			List<CouponRelationDTO> relations = this.couponFacade.getCouponRelationList(couponRelationDTO);
			for (CouponRelationDTO crd : relations) {
				Integer num = cardList.getNum(crd.getCardId());
				if (null == num || num == 0) {
					continue;
				}
				Map<String, Object> c = WeiXinUtil.getCardDetail(crd.getCardId());
				c.put("cardId", crd.getCardId());
				c.put("code", cardList.getCode(crd.getCardId()));
				res.add(c);
			}
		} else {
			List<Card> cards = cardList.getCard_list();
			if(null != cards && !cards.isEmpty())
  			for (Card card : cards) {
  				Map<String, Object> c = WeiXinUtil.getCardDetail(card.getCard_id());
  				c.put("cardId", card.getCard_id());
  				c.put("code", card.getCode());
  				res.add(c);
  			}
		}
		return res;
	}

	public Map<String, Object> getSignature(String cardId) throws IOException {
		long timestamp = System.currentTimeMillis() / 1000;
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		String apiTicket = weiXinUtil.getApiTicket();
		WxCardSign signer = new WxCardSign();
		signer.AddData(cardId);
		signer.AddData(timestamp);
		signer.AddData(nonceStr);
		signer.AddData(apiTicket);
		String sign = signer.GetSignature();
		Map<String, Object> map = new HashMap<>();
		map.put("cardId", cardId);
		map.put("timestamp", timestamp);
		map.put("nonceStr", nonceStr);
		map.put("apiTicket", apiTicket);
		map.put("sign", sign);
		return map;
	}

}
