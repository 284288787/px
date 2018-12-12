/**create by liuhua at 2016年6月17日 上午10:59:05**/
package com.star.framework.jdbc.dao.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageList<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**当前页码**/
	private int pageNo = 1;
	/**一页最大记录数**/
	private int pageSize = 10;
	/**总页数**/
	private int totalPage;
	/**总记录数**/
	private int totalRecord;
	/**当前页的数据**/
	private List<T> dataList = new ArrayList<>();
	/**是否最后一页**/
	private boolean isLastPage = true;
	/**页面参数保留**/
	private String urlParam;
	
	public PageList() {
		super();
	}

	public PageList(List<T> dataList) {
      this();
      this.dataList = dataList;
  }

	
	public PageList(int pageNo, int pageSize, int totalRecord, int totalPage, List<T> dataList) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.dataList = dataList;
		this.totalPage = totalPage;
		isLastPage = pageNo == totalPage;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public boolean isLastPage() {
		return isLastPage;
	}

	public String getUrlParam() {
		return urlParam;
	}

	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}
}
