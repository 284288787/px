/**create by liuhua at 2017年6月2日 上午9:07:49**/
package com.booting.pub.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "验证短信")
@Entity(name = "sms_identity")
public class SmsIdentityEntity implements Serializable {
	private static final long serialVersionUID = -3445802670837769407L;
	
	private Long id;                  //id
	private String phone;             //接收短信号码
	private Integer tag;               //本次短信用途 com.booting.common.CommonConstants.SmsTag
	private String code;              //验证码
	private String content;           //短信内容
	private Date createTime;          //发送时间
	
	@Id
	@Column(name = "id")
	public Long getId() {
		return id;
	}
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}
	@Column(name = "tag")
	public Integer getTag() {
		return tag;
	}
	@Column(name = "code")
	public String getCode() {
		return code;
	}
	@Column(name = "content")
	public String getContent() {
		return content;
	}
	@Column(name = "createTime")
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
