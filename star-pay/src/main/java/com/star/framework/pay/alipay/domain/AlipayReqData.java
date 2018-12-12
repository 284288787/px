/**create by liuhua at 2017年7月20日 上午10:31:34**/
package com.star.framework.pay.alipay.domain;

import com.star.framework.pay.alipay.AlipayUtil;

public class AlipayReqData {
	
	private String service;
	private String partner;
	private String _input_charset;
	private String notify_url;
	private String out_trade_no;
	private String subject;
	private String payment_type;
	private String seller_id;
	private String total_fee;
	private String body;
	private String extend_params;
	private String sign;
	private String sign_type;
	private String params;
	
	public String getOrderInfo() {
		// 合作者身份ID
		String orderInfo = "partner=" + "\"" + partner + "\"";

		// 卖家支付宝账号
		orderInfo += "&seller_id=" + "\"" + seller_id + "\"";

		// 商户网站唯一订单号
		orderInfo += "&out_trade_no=" + "\"" + out_trade_no + "\"";

		// 商品名称
		orderInfo += "&subject=" + "\"" + subject + "\"";

		// 商品详情
		orderInfo += "&body=" + "\"" + body + "\"";

		// 商品金额
		orderInfo += "&total_fee=" + "\"" + total_fee + "\"";

		// 服务器异步通知页面路径
		orderInfo += "&notify_url=" + "\"" + notify_url + "\"";

		// 接口名称， 固定值
		orderInfo += "&service=\"mobile.securitypay.pay\"";

		// 支付类型， 固定值
		orderInfo += "&payment_type=\"1\"";

		// 参数编码， 固定值
		orderInfo += "&_input_charset=\"utf-8\"";

		// 设置未付款交易的超时时间
		// 默认30分钟，一旦超时，该笔交易就会自动被关闭。
		// 取值范围：1m～15d。
		// m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		orderInfo += "&it_b_pay=\"1d\"";

		// 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
//		orderInfo += "&return_url=\"http://www.tianjiandao.com\"";

		// 调用银行卡支付，需配置此参数，参与签名， 固定值
		// orderInfo += "&paymethod=\"expressGateway\"";

		return orderInfo;
	}
	
	public AlipayReqData(String outTradeNo, String subject, String totalFee, String body, String extendParams) {
		super();
		this.service = "mobile.securitypay.pay";
		this.partner = "2088721362302536";
		this.notify_url = AlipayUtil.notify_url;
		this._input_charset = "UTF-8";
		this.out_trade_no = outTradeNo;
		this.subject = subject;
		this.payment_type = "1";
		this.seller_id = "1669829472@qq.com";
		this.total_fee = totalFee;
		this.body = body;
		this.extend_params = extendParams;
		this.sign_type = "RSA";
	}
	
	public String getService() {
		return service;
	}
	public String getPartner() {
		return partner;
	}
	public String get_input_charset() {
		return _input_charset;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public String getSubject() {
		return subject;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setService(String service) {
		this.service = service;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getBody() {
		return body;
	}
	public String getExtend_params() {
		return extend_params;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public void setExtend_params(String extend_params) {
		this.extend_params = extend_params;
	}

	public String getSign() {
		return sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
}
