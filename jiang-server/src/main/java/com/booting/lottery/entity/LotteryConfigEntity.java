/**create by liuhua at 2018年3月1日 下午2:25:54**/
package com.booting.lottery.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.star.framework.aop.annotation.Description;

@Description(name = "配置信息")
@Entity(name = "lottery_config")
public class LotteryConfigEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long configId;
	private String key;
	private String value;
	private String desc;
	
	@Id
	@Column(name = "configId")
	public Long getConfigId() {
		return configId;
	}
	@Column(name = "key")
	public String getKey() {
		return key;
	}
	@Column(name = "value")
	public String getValue() {
		return value;
	}
	@Column(name = "desc")
	public String getDesc() {
		return desc;
	}
	
	public void setConfigId(Long configId) {
		this.configId = configId;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
