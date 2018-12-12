/**create by liuhua at 2017年6月8日 下午11:06:59**/
package com.booting.common;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.google.gson.reflect.TypeToken;
import com.star.framework.specification.utils.ParamHandler;


public class PushUtil {

    //定义常量, appId、appKey、masterSecret 采用本文档 "第二步 获取访问凭证 "中获得的应用配置
    private static String appId = "VYNDGdeC1Q6kY2ZEpwyp11";
    private static String appKey = "X4x6SzN0zb9tQtcxBjjs9A";
//    private static String appSecret = "vEFb5oO7GM6pW4N7LNVad6";
    private static String masterSecret = "0z5eYV9Pvh8fpjR4YEo7i4";
    private static String url = "http://sdk.open.api.igexin.com/apiex.htm";
    private static IGtPush push = new IGtPush(url, appKey, masterSecret);
    
    public static void main2(String[] args) throws IOException {
        LinkTemplate template = linkTemplateDemo();
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
        message.setPushNetWorkType(0); 
        Target target = new Target();
        target.setAppId(appId);
        String clientId = "07d2b1a0ec3d04da5e5da3739a3cd53a";
        target.setClientId(clientId);
        //target.setAlias(Alias);
        IPushResult ret = null;
        try {
            ret = push.pushMessageToSingle(message, target);
        } catch (RequestException e) {
            e.printStackTrace();
            ret = push.pushMessageToSingle(message, target, e.getRequestId());
        }
        if (ret != null) {
            System.out.println(ret.getResponse().toString());
        } else {
            System.out.println("服务器响应异常");
        }
    }
    
    public static void main1(String[] args) throws IOException {// 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin_pushList_needDetails", "true");
        // 配置返回每个别名及其对应cid的用户状态，可选
        // System.setProperty("gexin_pushList_needAliasDetails", "true");
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo();
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List<Target> targets = new ArrayList<>();
        Target target1 = new Target();
        Target target2 = new Target();
        target1.setAppId(appId);
        target1.setClientId("07d2b1a0ec3d04da5e5da3739a3cd53a");
   //     target1.setAlias(Alias1);
        target2.setAppId(appId);
        target2.setClientId("19de945a7fdb422c967bc1b4d9650883");
   //     target2.setAlias(Alias2);
        targets.add(target1);
        targets.add(target2);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
    }
    
    public static LinkTemplate linkTemplateDemo() {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl("http://www.baidu.com");
        return template;
    }
    
    public static NotificationTemplate notificationTemplateDemo() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle("请输入通知栏标题");
        style.setText("请输入通知栏内容");
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(2);
        template.setTransmissionContent("请输入您要透传的内容");
        return template;
    }
    
    public static AbstractTemplate notificationTemplate(String title, String content, String param) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
//        style.setLogo("icon.png");
        // 配置通知栏网络图标
//        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        Type type = new TypeToken<Map<String, Object>>() {}.getType();
		Map<String, Object> params = ParamHandler.gson.fromJson(param, type);
		params.put("title", title);
		params.put("content", content);
		param = ParamHandler.gson.toJson(params);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(param);
        return template;
    }

    public static void main(String[] args) {
		String title = "这是一个标题";
		String content = "这是一段内容";
		String param = "{\"type\": \"messageDetail\", \"id\":\"messageId\", \"value\":\"5\"}";
//		pushNote(2, title, content, param, "c84ad63187fb596d675b543ad82d5813");
		pushNote(1, title, content, param, "0f811a25bba493a36f4b088b337a61fd");
	}
    
    public static TransmissionTemplate getTemplate(String content) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionContent(content);
        template.setTransmissionType(2);
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge("+1");
        payload.setContentAvailable(1);
        payload.setSound("default");
        payload.setCategory("$由客户端定义");

        //简单模式APNPayload.SimpleMsg
        payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

        //字典模式使用APNPayload.DictionaryAlertMsg
        //payload.setAlertMsg(getDictionaryAlertMsg());

        // 添加多媒体资源
//        payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
//                    .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
//                    .setOnlyWifi(true));

        template.setAPNInfo(payload);
        return template;
    }
    public static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(){
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setBody("body");
        alertMsg.setActionLocKey("ActionLockey");
        alertMsg.setLocKey("LocKey");
        alertMsg.addLocArg("loc-args");
        alertMsg.setLaunchImage("launch-image");
        // iOS8.2以上版本支持
        alertMsg.setTitle("Title");
        alertMsg.setTitleLocKey("TitleLocKey");
        alertMsg.addTitleLocArg("TitleLocArg");
        return alertMsg;
    }

    
    public static TransmissionTemplate transmissionTemplateDemo(String title, String content, String param) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        Type type = new TypeToken<Map<String, Object>>() {}.getType();
		Map<String, Object> params = ParamHandler.gson.fromJson(param, type);
		params.put("title", title);
		params.put("content", content);
		param = ParamHandler.gson.toJson(params);
		System.out.println(param);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent(param);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }
    
	public static void pushNote(Integer sourceFrom, String title, String content, String param, String... clientIds) {
		System.out.println("push:" + title + " " + param + " " + clientIds);
		System.setProperty("gexin_pushList_needDetails", "true");
		AbstractTemplate template = null;
		if (sourceFrom == 1) {
			template = notificationTemplate(title, content, param);
		}else{
			template = transmissionTemplateDemo(title, content, param);
//			template = notificationTemplate(title, content, param);
			APNPayload payload = new APNPayload();
	        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
	        payload.setAutoBadge("1");
	        payload.setContentAvailable(1);
	        payload.setSound("default");
//	        payload.setCategory("$由客户端定义");
	        if (StringUtils.isNotBlank(param)) {
	        	Type type = new TypeToken<Map<String, Object>>() {}.getType();
				Map<String, Object> params = ParamHandler.gson.fromJson(param, type);
				payload.addCustomMsg("title", title);
				payload.addCustomMsg("content", content);
				for (Iterator<String> iterator = params.keySet().iterator(); iterator.hasNext();) {
					String key = iterator.next();
					Object value = params.get(key);
					payload.addCustomMsg(key, value);
				}
			}
	        //简单模式APNPayload.SimpleMsg
	        payload.setAlertMsg(new APNPayload.SimpleAlertMsg(title));
	        
	        template.setAPNInfo(payload);
		}
        ListMessage message = new ListMessage();
        message.setData(template);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List<Target> targets = getTargets(clientIds);
        if (targets.size() == 0) {
			return ;
		}
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
	}
	
	private static List<Target> getTargets(String... clientIds){
		List<Target> targets = new ArrayList<>();
		if (null != clientIds && clientIds.length > 0) {
			for (String clientid : clientIds) {
				Target target = new Target();
				target.setAppId(appId);
		        target.setClientId(clientid);
		        targets.add(target);
			}
		}
		return targets;
	}

	public static void testPush() {
		String title = "这是一个标题";
		String content = "这是一段内容";
		String param = "{\"type\": \"messageDetail\", \"id\":\"messageId\", \"value\":\"5\"}";
		pushNote(2, title, content, param, "c84ad63187fb596d675b543ad82d5813");
	}
}


