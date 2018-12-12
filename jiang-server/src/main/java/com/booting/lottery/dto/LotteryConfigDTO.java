/**create by liuhua at 2018年3月1日 下午2:25:54**/
package com.booting.lottery.dto;

import java.io.Serializable;

import com.star.framework.aop.annotation.Description;

@Description(name = "配置信息")
public class LotteryConfigDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long configId;
	private String key;
	private String value;
	private String desc;
	
		
	public Long getConfigId() {
		return configId;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
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
