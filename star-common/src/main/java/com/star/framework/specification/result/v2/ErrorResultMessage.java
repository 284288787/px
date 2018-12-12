/**create by liuhua at 2017年6月1日 下午4:58:45**/
package com.star.framework.specification.result.v2;

import com.star.framework.specification.Constants;
import com.star.framework.specification.FailureCode;

public class ErrorResultMessage extends ResultMessage {

	/**
	 * 当接口访问不能获取想要的正确结果，则用errorCode标识详细错误代码
	 * 错误代码参考com.huinong.commerce.specification.FailureCode
	 */
	private String errorCode;
	/**
	 * errorCode有值，则此属性必有值
	 * 参考 com.huinong.commerce.specification.FailureCode
	 */
	private String errorMessage;
	
	public ErrorResultMessage(FailureCode failureCode, Object... args){
		this.setStatus(Constants.RESULT_ERROR);
		this.errorCode = failureCode.getCode();
		this.errorMessage = String.format(failureCode.getMessage(), args);
	}

	/**
	 * 接口调用异常时，应返回错误状态 及 错误状态码
	 * @param message
	 * @param failureCode
	 * @param args
	 */
	public ErrorResultMessage(String errorCode, String errorMessage){
		this.setStatus(Constants.RESULT_ERROR);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
