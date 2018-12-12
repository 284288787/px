/**create by liuhua at 2017年7月7日 下午2:15:16**/
package com.booting.common;

import java.util.List;

import com.booting.common.CommonConstants.MessageType;

public class PushInfo {

	private Integer to;              //发送给 1单个人 2部分群发 3全部群发
  	private MessageType messageType; //消息类型
	private boolean sendNote;        //是否发短信
	private boolean sendMsg;         //是否发消息
	private boolean pushMsg;         //是否推送
	private String mobiles;          //如果sendNote为true，必须要有
	private Long[] userId;           //推送给
	private String[] clientIds;      //推送给
	private Integer sourceFrom;      //推送用户 1安卓 2ios
	private String title;            //推送标题
	private String content;          //推送内容
	private String content2;         //推送内容
	private String htmlContent;		 //消息中心的内容
	private String htmlContent2;     //消息中心的内容
	private Object[] args;           //内容的参数
	private String templateCode;     //阿里短信模板编号
	private String templateParam;    //阿里短信模板参数格式json
	private String templateParam2;    //阿里短信模板参数格式json 副本
	private Object[] params;         //参数值
	private String method = "messageDetail";           //方法名称
	private String key = "messageId";              //方法参数
	private Object   objectValue;
	
	public PushInfo(Integer to, MessageType messageType, String method, String key, boolean sendNote, String title, String content, String htmlContent, String templateCode, String templacteParam) {
		super();
		this.to = to;
		this.messageType = messageType;
		this.sendNote = sendNote;
		this.title = title;
		this.sendNote = sendNote;
		this.sendMsg = true;
		this.content = content;
		this.htmlContent = htmlContent;
		this.templateCode = templateCode;
		this.templateParam = templacteParam;
		this.method = method;
		this.key = key;
	}
	
	public PushInfo(Integer to, MessageType messageType, String method, String key, String title, String content, String htmlContent) {
		super();
		this.to = to;
		this.messageType = messageType;
		this.sendNote = false;
		this.title = title;
		this.content = content;
		this.htmlContent = htmlContent;
		this.method = method;
		this.key = key;
	}
	
	public PushInfo(Integer to, MessageType messageType, String method, String key, String title, String content, String htmlContent, String templateCode, String templacteParam) {
		super();
		this.to = to;
		this.messageType = messageType;
		this.sendNote = true;
		this.title = title;
		this.content = content;
		this.htmlContent = htmlContent;
		this.templateCode = templateCode;
		this.templateParam = templacteParam;
		this.method = method;
		this.key = key;
	}
	
	public PushInfo(Integer to, MessageType messageType, String method, String key, String title, String htmlContent) {
		super();
		this.to = to;
		this.messageType = messageType;
		this.sendMsg = true;
		this.title = title;
		this.htmlContent = htmlContent;
		this.method = method;
		this.key = key;
	}
	
	public PushInfo(String templateCode, String templacteParam) {
		super();
		this.sendMsg = false;
		this.templateCode = templateCode;
		this.templateParam = templacteParam;
		this.htmlContent = "";
	}
	
	public PushInfo(List<Long> userIds, String mobiles, List<String> clientIds){
		setUserId(userIds);
		setMobiles(mobiles);
		setClientIds(clientIds);
	}
	
	public void fillContent(Object... args){
		this.args = args;
		if (null == args || args.length == 0) {
			this.content2 = content;
			this.htmlContent2 = htmlContent;
		}else{
			this.content2 = String.format(this.content, args);
			this.htmlContent2 = String.format(this.htmlContent, args);
		}
	}
	public void fillTemplateParam(Object... params) {
		this.params = params;
		if (null != this.templateParam) {
			this.templateParam2 = String.format(this.templateParam, params);
		}
	}
	
	public boolean isSendNote() {
		return sendNote;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public void setSendNote(boolean sendNote) {
		this.sendNote = sendNote;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Object[] getArgs() {
		return args;
	}
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public Long[] getUserId() {
		return userId;
	}
	public void setUserId(Long... userId) {
		this.userId = userId;
	}
	public void setUserId(List<Long> userIds) {
		if (null != userIds) {
			this.userId = userIds.toArray(new Long[]{});
		}
	}
	public String[] getClientIds() {
		return clientIds;
	}
	public void setClientIds(String... clientIds) {
		this.clientIds = clientIds;
	}
	public void setClientIds(List<String> clientIds) {
		if (null != clientIds) {
			this.clientIds = clientIds.toArray(new String[]{});
		}
	}
	public Integer getTo() {
		return to;
	}
	public void setTo(Integer to) {
		this.to = to;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public String getTemplateParam() {
		return templateParam;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}
	public String getMobiles() {
		return mobiles;
	}
	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}

	public Object[] getParams() {
		return params;
	}

	public String getTemplateParam2() {
		return templateParam2;
	}

	public void setTemplateParam2(String templateParam2) {
		this.templateParam2 = templateParam2;
	}

	public String getMethod() {
		return method;
	}

	public String getKey() {
		return key;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getSourceFrom() {
		return sourceFrom;
	}

	public void setSourceFrom(Integer sourceFrom) {
		this.sourceFrom = sourceFrom;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public boolean isSendMsg() {
		return sendMsg;
	}

	public void setSendMsg(boolean sendMsg) {
		this.sendMsg = sendMsg;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public String getHtmlContent2() {
		return htmlContent2;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public void setHtmlContent2(String htmlContent2) {
		this.htmlContent2 = htmlContent2;
	}

	public Object getObjectValue() {
		return objectValue;
	}

	public void setObjectValue(Object objectValue) {
		this.objectValue = objectValue;
	}

	public boolean isPushMsg() {
		return pushMsg;
	}

	public void setPushMsg(boolean pushMsg) {
		this.pushMsg = pushMsg;
	}
}
