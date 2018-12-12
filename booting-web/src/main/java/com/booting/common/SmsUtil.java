/**create by liuhua at 2017年6月3日 上午9:04:30**/
package com.booting.common;

import java.util.Random;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.star.framework.specification.FailureCode;
import com.star.framework.specification.exception.ArgsException;

public class SmsUtil {

    private static final String product = "Dysmsapi";
    private static final String domain = "dysmsapi.aliyuncs.com";

    private static final String accessKeyId = "LTAIskNr1fYUc85E";
    private static final String accessKeySecret = "yRklHcegQw0jaVKs6vio8XJiBMihZV";
	
    private static SendSmsResponse sendSms(String mobiles, String templateCode, String templateParam) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobiles);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("精茵体育");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        System.out.println("sms param:" + templateParam);
        request.setTemplateParam(templateParam);
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("star");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    
    private static String buildCode(int len){
    	Random random = new Random();
    	String code = "";
    	for (int i = 0; i < len; i++) {
			code += random.nextInt(10);
		}
    	return code;
    }
    
	public static String sendSms(String mobile, PushInfo pushInfo) throws ArgsException{
		try {
//			String code = "1111";
			String code = buildCode(4);
			pushInfo.fillTemplateParam(code);
			sendSms(mobile, pushInfo.getTemplateCode(), pushInfo.getTemplateParam2());
			return code;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ArgsException(FailureCode.ERR_000.getCode(), "发送验证码失败");
		}
	}
	
	public static void sendNote(String mobiles, String templateCode, String templateParam){
		try {
			System.out.println("即将发短信：" + mobiles + " " + templateCode + " " + templateParam);
			SendSmsResponse ssr = sendSms(mobiles, templateCode, templateParam);
			System.out.println(ssr.getCode() + " " + ssr.getRequestId() + " " + ssr.getMessage());
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
