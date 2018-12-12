/**create by liuhua at 2017年6月7日 上午9:20:13**/
package com.booting.order.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "微信回调数据")
@Entity(name = "order_weixin")
public class WeixinPayDetailEntity implements Serializable {
	private static final long serialVersionUID = -2617070785755775044L;
	
	private Long id;
	//协议层
    private String return_code;
    private String return_msg;

    //协议返回的具体数据（以下字段在return_code 为SUCCESS 的时候有返回）
    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String result_code; // SUCCESS/FAIL 
    private String err_code;
    private String err_code_des;

    private String device_info;

    //业务返回的具体数据（以下字段在return_code 和result_code 都为SUCCESS 的时候有返回）
    private String openid;
    private String is_subscribe;
    private String trade_type;
    private String bank_type;
    private Long total_fee;
    private String coupon_fee;
    private Integer coupon_count;
    private String fee_type;
    private String transaction_id;
    private String out_trade_no;
    private String attach;
    private String time_end;
    private Long cash_fee;
    private String cash_fee_type;
    
    @Id
	@Column(name = "id")
    public Long getId() {
		return id;
	}
    @Column(name = "return_code")
	public String getReturn_code() {
		return return_code;
	}
    @Column(name = "return_msg")
	public String getReturn_msg() {
		return return_msg;
	}
    @Column(name = "appid")
	public String getAppid() {
		return appid;
	}
    @Column(name = "mch_id")
	public String getMch_id() {
		return mch_id;
	}
    @Column(name = "nonce_str")
	public String getNonce_str() {
		return nonce_str;
	}
    @Column(name = "sign")
	public String getSign() {
		return sign;
	}
    @Column(name = "result_code")
	public String getResult_code() {
		return result_code;
	}
    @Column(name = "err_code")
	public String getErr_code() {
		return err_code;
	}
    @Column(name = "err_code_des")
	public String getErr_code_des() {
		return err_code_des;
	}
    @Column(name = "device_info")
	public String getDevice_info() {
		return device_info;
	}
    @Column(name = "openid")
	public String getOpenid() {
		return openid;
	}
    @Column(name = "is_subscribe")
	public String getIs_subscribe() {
		return is_subscribe;
	}
    @Column(name = "trade_type")
	public String getTrade_type() {
		return trade_type;
	}
    @Column(name = "bank_type")
	public String getBank_type() {
		return bank_type;
	}
    @Column(name = "total_fee")
	public Long getTotal_fee() {
		return total_fee;
	}
    @Column(name = "coupon_fee")
	public String getCoupon_fee() {
		return coupon_fee;
	}
    @Column(name = "coupon_count")
	public Integer getCoupon_count() {
		return coupon_count;
	}
    @Column(name = "fee_type")
	public String getFee_type() {
		return fee_type;
	}
    @Column(name = "transaction_id")
	public String getTransaction_id() {
		return transaction_id;
	}
    @Column(name = "out_trade_no")
	public String getOut_trade_no() {
		return out_trade_no;
	}
    @Column(name = "attach")
	public String getAttach() {
		return attach;
	}
    @Column(name = "time_end")
	public String getTime_end() {
		return time_end;
	}
    @Column(name = "cash_fee")
	public Long getCash_fee() {
		return cash_fee;
	}
    @Column(name = "cash_fee_type")
	public String getCash_fee_type() {
		return cash_fee_type;
	}
    
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public void setTotal_fee(Long total_fee) {
		this.total_fee = total_fee;
	}
	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = coupon_fee;
	}
	public void setCoupon_count(Integer coupon_count) {
		this.coupon_count = coupon_count;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	public void setCash_fee(Long cash_fee) {
		this.cash_fee = cash_fee;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
