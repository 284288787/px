/**create by liuhua at 2018年1月20日 上午9:30:50**/
package com.star.framework.importdata;

import java.io.Serializable;
import java.util.List;

public class ImportResult implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String[] heads;
	private List<List<FieldValue>> errorRecords;
	private List<List<FieldValue>> successRecords;
	private boolean empty;
	
	public boolean isEmpty() {
		return empty;
	}

	public ImportResult(boolean empty, String[] heads, List<List<FieldValue>> errorRecords, List<List<FieldValue>> successRecords) {
		super();
		this.heads = heads;
		this.empty = empty;
		this.errorRecords = errorRecords;
		this.successRecords = successRecords;
	}
	
	public String[] getHeads() {
		return heads;
	}
	public List<List<FieldValue>> getErrorRecords() {
		return errorRecords;
	}
	public List<List<FieldValue>> getSuccessRecords() {
		return successRecords;
	}
	public void setHeads(String[] heads) {
		this.heads = heads;
	}
	public void setErrorRecords(List<List<FieldValue>> errorRecords) {
		this.errorRecords = errorRecords;
	}
	public void setSuccessRecords(List<List<FieldValue>> successRecords) {
		this.successRecords = successRecords;
	}
}
