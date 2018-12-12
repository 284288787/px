/**create by liuhua at 2017年6月1日 下午4:58:45**/
package com.star.framework.specification.result.v2;

import com.star.framework.specification.Constants;

public class SuccessResultMessage extends ResultMessage {

	/**
	 * 接口要返回的数据
	 */
	private Object data;
	
	public SuccessResultMessage(Object data){
		this.setStatus(Constants.RESULT_SUCCESS);
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
