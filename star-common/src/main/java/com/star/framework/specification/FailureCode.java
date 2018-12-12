/**create by liuhua at 2016年7月6日 下午5:33:11**/
package com.star.framework.specification;

/**
 * 失败编码 及 说明
 * @author liuhua
 *
 */
public enum FailureCode {
	/**不知道编码的错误，就用这个**/
	ERR_000("000", "操作失败"),
	/**服务繁忙，请稍后再试**/
	ERR_001("001", "服务繁忙，请稍后再试"),
	/**参数错误**/
	ERR_002("002", "参数错误"),
	/**未登录，请先登录**/
	ERR_003("003", "未登录，请先登录"),
	/**未登录，请先登录**/
	ERR_004("004", "帐号或密码错误"),
	/**图形验证码错误**/
	ERR_018("018", "图形验证码错误"),
	
	ERR_101("101", "没有球队信息"),
	ERR_201("201", "没有球场信息"),
	ERR_202("202", "同用户下不能有同名的球场"),
	ERR_203("203", "球场非本人不能操作"),
	ERR_205("205", "球场不存在"),
	ERR_301("301", "场地不存在"),
	ERR_302("302", "场地正在使用中，暂不能修改场地规格"),
	ERR_303("303", "状态相同"),
	ERR_401("401", "该手机号没有注册"),
	
	ERR_700("700", "验证码未使用一直有效，1分钟后可重新获取新验证码"),
	ERR_701("701", "未绑定手机"),
	ERR_702("702", "已经报名该项目"),
	
	ERR_801("801", "你不是监护人"),
	ERR_802("802", "你不是被邀请的家长"),
	ERR_803("803", "学生不存在"),
	ERR_804("804", "该家长不存在"),
	ERR_805("805", "555");
	
	private String code;
	private String message;
	
	private FailureCode(String code, String message){
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
