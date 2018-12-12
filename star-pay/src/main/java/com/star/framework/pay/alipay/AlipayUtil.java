/**create by liuhua at 2017年7月20日 上午10:28:13**/
package com.star.framework.pay.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.star.framework.pay.alipay.api.AlipayApiException;
import com.star.framework.pay.alipay.api.AlipaySignature;
import com.star.framework.pay.alipay.domain.AlipayReqData;
import com.star.framework.utils.CglibBeanUtils;

public class AlipayUtil {

//	public static final String private_key = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ21zNJ6YWeCfCuO2Mrk2fSlNPSIR4h/OXgSJT9Ixz+laIsvUvr8i3KN3Jre8SAHm47zOdfZGfUafI4zbmFyTsk7tM46vnrVsr/04DUKwgM+GMbeR+4CyJOMlv664YQXbVyexUxHW9XHmazcNQ/ntfn9mhA6gTN35hXp/4EHbhb7AgMBAAECgYAx7vPQ2GqqqUfDStEdPGQ972ja26M6dn2tpKEiblqXrjlxiCRPz1IfjQcCCgByUbZRBnWT/+sWDbLOGz6hn92NnSV+K0rRizGvszwYXlt27yzXKzVyaFyAhR0GYVM3rHPZ8tmvH0Sdy1F41q/YEor5WliNjmxqgvRbSpgGbfOqwQJBAOMnhcKtwpFeRPGZ6lYcO4F6Gb43V9SYD36ZZBuFDLDs1Hpq3qG9dsaqqoTXfaW3cVWSiUN6PVj9uM5iQCsyT5ECQQCxvL2wVgueLhmvNd0FYp/axmxHj7cl79t1Ca0ZezLiATrmy11f5CNXTdtUH+yvj8uIfw16t2YNjPcwi/49J4/LAkB1WLWBHMKm1kxHg3vLgEo9WRZzbaOx79wRSqysis9DlxUtKIhahcYWW5Q7jBccxOeHjWvbsAqBLpGKDLeZmSQBAkAirbPJGxDLPS1vvS+xpEuSURLBZ64RbdpA3LF0flAwPpMygs1mL0oh96Pqpwv0OTc6G6gSoIP98tosE7R4VCa/AkAcq+0WQyyRyHPrpdY8a2ZS7GYtgzvMXE9AmpPxtgpnI0tlktwRkQWJQ0NTDGZecPTVKq35Pi2TOzZYfKooSRAs";
	public static final String private_key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIqzxNkicAXC1G5QBUVqm7YCQNVYW6mIismd9akExTABqLJBOShQCivIGoBc9PkIbgvN/FEluP9Ty1hEIhB39p+RKKB8Mz7Bp3cYoOQWvWYkZP3oJWgai6xBPlBZAIVVCRvgcHXtxGIJ3yXEpApJENUzHOy5ue7wPqkSR1GmLKRfAgMBAAECgYBuSq6f/qtXWWp0YXsfjLtOypHlVAJnV+UMl2etw9ZM6zTKseSScrrzv4WbRs+haxuelWgq2rU1itsqT9tKfyoC4Ukqdso41CCGbHoeL71vhECjNbGUU8FArtmaj/480yQMxyHhbSXll2OFjbY6VVvB8cSxi2k4GIcycnXFjLMeEQJBANBRR9DqijQXYgw+RYnwK3aBqYw/2FiSHRNPp1Czy1AGvr9zgb+L+9kVHzRcYRx+QIpS84S2fh9xif09u/x3LBkCQQCqc0MnKV20dR2G9PJbNTp5dkvnMZ7DchpCQ2bwIveav+tF8h6Getc3+HU75A6HYLzseAm42UZpu5MOS+qvOOM3AkB5x4pcL0Arw5+l2KF+SdCerp4XRxuKa6BjsaT2IKUyWz8XXMb02ziXZymeyoJazdOdCXEinCyO5SjtyjEjHefBAkEAo0L13mvT0kafOqYiAVTRpoe1mkO/8yq2liOjVH3ZtoL/YqbPsDW3MWgtOmlttSPmsYUkosaSmDHPYFE9AAq4LwJAdWL/t4oOEqLfZJigEpqzq0uY2/XGAnBT2MSXIiTKUolHypOeiRfyICHtBDAfRzMtqnZ4GYfHzQ6cS0ygnKQzGg==";
	
	public static final String notify_url = "http://47.94.0.136/api/1.0/alipay";
	
	private static String getParamString(AlipayReqData alipayReqData){
		String temp = "";
		Map<String, Object> map = new HashMap<>();
		CglibBeanUtils.addToMap(alipayReqData, map);
		map.remove("sign");
//		map.remove("sign_type");
		map.remove("params");
		map.remove("extend_params");
		for (Iterator<String> iterator = map.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			String value = (String) map.get(key);
			temp += "&" + key + "=\"" + value + "\"";
		}
		if (temp.length() > 0) {
			temp = temp.substring(1);
		}
		return temp;
	}
	
	public static void getParams2(AlipayReqData alipayReqData){
		try {
			String temp = alipayReqData.getOrderInfo();
			System.out.println(" ... tem :" + temp);
			String sign = URLEncoder.encode(RSA.sign(temp, AlipayUtil.private_key, "UTF-8"), "UTF-8");
			alipayReqData.setSign(sign);
			temp += "&sign=\"" + sign + "\"&sign_type=\"RSA\"";
			alipayReqData.setParams(temp);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	public static String getParams(AlipayReqData alipayReqData){
		try {
			String temp = getParamString(alipayReqData);
			String sign = AlipaySignature.rsaSign(temp, AlipayUtil.private_key, "UTF-8");
			System.out.println(sign);
			String sign2 = RSA.sign(temp, AlipayUtil.private_key, "UTF-8");
			System.out.println(sign2);
			temp += "&sign_type=RSA&sign=" + sign;
			return temp;
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		double f = 123228/ 100.00;
		AlipayReqData alipayReqData = new AlipayReqData("D00001", "test", String.format("%.2f", f), "test", "D1232");
		System.out.println(getParams(alipayReqData));
	}
}
