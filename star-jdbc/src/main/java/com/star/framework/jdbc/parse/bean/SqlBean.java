/**create by liuhua at 2016年6月7日 下午3:24:11**/
package com.star.framework.jdbc.parse.bean;

public class SqlBean {
	private String id;
	private boolean isRead;
	private String content;
	private String dsName;
	
	public SqlBean(String id, String isRead, String content, String dsName) {
		super();
		this.id = id;
		try{
			this.isRead = Boolean.valueOf(isRead).booleanValue();
		}catch(Exception e){
			this.isRead = false;
		}
		this.content = content;
		this.dsName = dsName;
	}

	public SqlBean(String id, boolean isRead, String content, String dsName) {
		super();
		this.id = id;
		this.isRead = isRead;
		this.content = content;
		this.dsName = dsName;
	}

	public String getId() {
		return id;
	}

	public boolean isRead() {
		return isRead;
	}

	public String getContent() {
		return content;
	}

	public String getDsName() {
		return dsName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setDsName(String dsName) {
		this.dsName = dsName;
	}
}
