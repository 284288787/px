/**create by liuhua at 2017年9月15日 下午2:16:30**/
package com.booting.insurance;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.booting.competition.dto.CompetitionInsuranceDTO;
import com.booting.insurance.util.DESCoder;
import com.booting.insurance.util.HttpRequestUtil;
import com.booting.insurance.util.MD5Util;
import com.google.gson.Gson;

public class InsuranceUtil {
	private static Gson gson = new Gson();
	
	//生产
	private static final String postUrl = "http://118.190.76.173:8080/Services/api/orderService/";
	//测试
//	private static final String postUrl = "http://118.190.78.29:8080/Services/api/orderService/";
	
	private static final String key = "JH_21908";
	private static final String partnercode = "000031";
	private static final String action = "createOrder";
	private static final String productPriceId = "59";
	private static final String eachInsuredQuantity = "1"; // 买一份
	private static final String isInvoice = "N"; //是否发送电子发票   Y:发送，N：不发送
	
	private static final String holderCorpType = "2"; // 投保人类型，1：投保人，2投保单位
	private static final String holderCorpName = "宝鼎体育控股（深圳）股份有限公司"; // 投保单位名称
	private static final String holderName = "黄旭娟"; // 投保人姓名或者企业联系人姓名
	private static final String holderIdcartType = "99"; // 投保人证件类型（1：身份证，99，其他）
	private static final String holderIdcartNo = "91440300MA5DR8193"; // 投保人 或 投保单位证件号码
	private static final String holderMobile = "18589015052";
	private static final String holderBornDate = ""; // 投保人出生日期(yyyy-MM-dd)
	
	public static void main(String[] args) {
		List<CompetitionInsuranceDTO> people = new ArrayList<>();
		CompetitionInsuranceDTO competitionInsuranceDTO = new CompetitionInsuranceDTO();
		competitionInsuranceDTO.setIdentityNo("123123");
		competitionInsuranceDTO.setUserName("张三");
		people.add(competitionInsuranceDTO);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 4);
		InsuranceResult result = insurance(calendar.getTime(), people);
		System.out.println(result );
	}
	
	public static InsuranceResult insurance(Date date, List<CompetitionInsuranceDTO> people) {
		if (null == people || people.isEmpty()) {
			return null;
		}
		System.out.println(date);
		System.out.println(people.size());
		try {
			String req = buildReq(date, people);
//			System.out.println(req);
			String reqhash = URLEncoder.encode(DESCoder.getEncryptToken(MD5Util.md5(req + key)), "UTF-8");
			String param = "partnercode=" + partnercode + "&action=" + action + "&req=" + req + "&reqhash=" + reqhash;
			String res = HttpRequestUtil.sendPost(postUrl, param);
			System.out.println(res);
			InsuranceResult result = gson.fromJson(res, InsuranceResult.class);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String buildReq(Date date, List<CompetitionInsuranceDTO> people){
		List<Map<String, Object>> orderList = new ArrayList<>();
		orderList.add(buildOrder(date, people));

		Map<String, Object> reqMap = new HashMap<>();
		reqMap.put("orderList", orderList);

		String req = gson.toJson(reqMap);
		return req;
	}
	
	private static Map<String, Object> buildOrder(Date date, List<CompetitionInsuranceDTO> people){
		int num = people.size();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String beginTime = simpleDateFormat.format(date) + " 00:00:00";
		String endTime = simpleDateFormat.format(date) + " 23:59:59";
		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("productPriceId", productPriceId);
		orderMap.put("realPremiumAmt", 300 * num);
		orderMap.put("insuredBgnTime", beginTime);
		orderMap.put("insuredEndTime", endTime);
		orderMap.put("insuredNum", num);
		orderMap.put("eachInsuredQuantity", eachInsuredQuantity);
		orderMap.put("isInvoice", isInvoice);
		orderMap.put("holder", buildHolder());
		orderMap.put("insureds", buildInsureds(people));
		return orderMap;
	}
	
	private static List<Map<String, Object>> buildInsureds(List<CompetitionInsuranceDTO> people) {
		List<Map<String, Object>> insureds = new ArrayList<>();
		for (CompetitionInsuranceDTO cid : people) {
			Map<String, Object> insured = new HashMap<>();
			insured.put("idcartType", "99");
			insured.put("idcartNo", cid.getIdentityNo());
			insured.put("insuredName", cid.getUserName());
			 insured.put("bornDate", "");
			// insured.put("mobile", "13693504167");
			// insured.put("mail", "123@qq.com");
			// insured.put("sex", "1");
			insureds.add(insured);
		}
		return insureds;
	}

	private static Map<String, String> buildHolder() {
		Map<String, String> holder = new HashMap<>();
		holder.put("holderCorpType", holderCorpType);
		holder.put("holderCorpName", holderCorpName);
		holder.put("holderName", holderName);
		holder.put("idcartType", holderIdcartType);
		holder.put("idcartNo", holderIdcartNo);
		holder.put("mobile", holderMobile);
		holder.put("bornDate", holderBornDate);
		// holder.put("mail", "123@qq.com");
		// holder.put("sex", "1");
		return holder;
	}
}
