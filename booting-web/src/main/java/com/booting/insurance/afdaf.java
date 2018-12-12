/**create by liuhua at 2017年9月15日 上午10:39:39**/
package com.booting.insurance;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.booting.insurance.util.DESCoder;
import com.booting.insurance.util.HttpRequestUtil;
import com.booting.insurance.util.MD5Util;
import com.gexin.fastjson.JSONException;
import com.google.gson.Gson;

public class afdaf {
	public static void main(String[] args) throws JSONException, UnsupportedEncodingException {
		// Map<String, String> map1 = System.getenv();

		String key = "JH_21908";
		String partnercode = "000031";
		String action = "createOrder";

		Map<String, Object> map = new HashMap<>();
		List<Map<String, Object>> orderList = new ArrayList<>();
		Map<String, Object> orderMap = new HashMap<>();
		orderMap.put("productPriceId", "59");
		orderMap.put("realPremiumAmt", "600");
		orderMap.put("insuredBgnTime", "2017-09-18 00:00:00");
		orderMap.put("insuredEndTime", "2017-09-18 23:59:59");
		orderMap.put("insuredNum", "2");
		orderMap.put("eachInsuredQuantity", "1");
		orderMap.put("isInvoice", "N");

		Map<String, Object> holderMap = new HashMap<>();
		holderMap.put("holderCorpType", "2");
		// holderMap.put("bornDate", "1987-08-01");
		holderMap.put("holderCorpName", "宝鼎体育");
		holderMap.put("holderName", "小王");
		holderMap.put("idcartNo", "12345678");
		holderMap.put("idcartType", "99");
		// holderMap.put("mail", "123@qq.com");
		// holderMap.put("mobile", "13693504167");
		// holderMap.put("sex", "1");
		orderMap.put("holder", holderMap);

		List<Map<String, Object>> insureds = new ArrayList<>();
		Map<String, Object> insured = new HashMap<>();
		insured.put("bornDate", "");
		insured.put("insuredName", "小li");
		insured.put("idcartNo", "12345678");
		insured.put("idcartType", "99");
		// insured.put("mail", "123@qq.com");
		// insured.put("mobile", "13693504167");
		// insured.put("sex", "1");
		Map<String, Object> insured2 = new HashMap<>();
		insured2.put("bornDate", "");
		insured2.put("insuredName", "小wang");
		insured2.put("idcartNo", "3158459");
		insured2.put("idcartType", "99");
		insureds.add(insured);
		insureds.add(insured2);

		orderMap.put("insureds", insureds);

		/*
		 * Map invoices = new HashMap();
		 * 
		 * invoices.put("isSendInvoice", "1"); invoices.put("invoiceOwner",
		 * "北京足坛"); invoices.put("taxPayerTel", "093247095743");
		 * invoices.put("taxPayerAddress", "北京");
		 * invoices.put("taxPayerBankName", "");
		 * invoices.put("taxPayerBankAccount", ""); invoices.put("taxPayerNO",
		 * ""); orderMap.put("invoices", invoices);
		 */

		orderList.add(orderMap);
		map.put("orderList", orderList);

		try {
			Gson gson = new Gson();
			String req = gson.toJson(map);
			System.out.println(req);

			String reqhash = URLEncoder.encode(DESCoder.getEncryptToken(MD5Util.md5(req + key)), "UTF-8");
			String param = "partnercode=" + partnercode + "&action=" + action + "&req=" + req + "&reqhash=" + reqhash;
//			String res = HttpRequestUtil.sendPost("http://118.190.78.29:8080/Services/api/orderService/", param);
			String res = HttpRequestUtil.sendPost("http://118.190.76.173:8080/Services/api/orderService/", param);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
