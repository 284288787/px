/**create by liuhua at 2017年6月2日 上午11:18:33**/
package com.star.framework.specification.result.v2;

import java.io.Serializable;

import com.star.framework.specification.result.PageInfo;

public class ApiResult implements Serializable {
	private static final long serialVersionUID = -8749990128598020005L;
	
	private Object data;
	private PageInfo pageInfo;
	
	public ApiResult(){
		
	}
	
	public ApiResult(Object data){
		this.data = data;
	}
	
	public ApiResult(Object data, PageInfo pageInfo){
		this.data = data;
		this.pageInfo = pageInfo;
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
