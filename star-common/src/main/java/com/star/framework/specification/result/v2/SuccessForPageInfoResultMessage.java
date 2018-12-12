/**create by liuhua at 2017年6月1日 下午4:58:45**/
package com.star.framework.specification.result.v2;

import com.star.framework.specification.Constants;
import com.star.framework.specification.result.PageInfo;

public class SuccessForPageInfoResultMessage extends ResultMessage {

	/**
	 * 接口要返回的数据
	 */
	private Object data;
	/**
	 * 分页信息
	 */
	private PageInfo pageInfo;
	
	public SuccessForPageInfoResultMessage(Object data, PageInfo pageInfo){
		this.setStatus(Constants.RESULT_SUCCESS);
		this.pageInfo = pageInfo;
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	public PageInfo getPageInfo() {
		return pageInfo;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}
