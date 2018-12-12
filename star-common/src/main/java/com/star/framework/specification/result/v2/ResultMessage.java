/**create by liuhua at 2017年6月1日 下午4:57:34**/
package com.star.framework.specification.result.v2;

import java.util.Date;

public abstract class ResultMessage {

	/**
	 * 接口返回SUCCESS 为成功
	 * 接口返回ERROR 为失败
	 * 如果为ERROR，则查看errorCode和 errorMessage 了解具体的失败原因 
	 */
	private String status;
	
	private Date systemTime = new Date();
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSystemTime() {
		return systemTime;
	}

	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}
}
