/**create by liuhua at 2017年6月2日 上午9:07:49**/
package com.booting.pub.dto;

import java.io.Serializable;
import java.util.Date;

public class SmsIdentityDTO implements Serializable {
	private static final long serialVersionUID = -3445802670837769407L;
	
	private Long id;                  //id
	private String phone;             //接收短信号码
	private Integer tag;               //本次短信用途 com.booting.common.CommonConstants.SmsTag
	private String code;              //验证码
	private String content;           //短信内容
	private Date createTime;          //发送时间
	
	
	private long time = 60 * 1000;
	/**
	 * 是否有效
	 * @author liuhua
	 *
	 * @return true 有效 false无效
	 */
	public boolean isValid(){
		if (null != createTime && createTime.getTime() + time > System.currentTimeMillis()) {
			return true;
		}
		return false;
	}
	public Long getId() {
		return id;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public Integer getTag() {
		return tag;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
