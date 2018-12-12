/**create by liuhua at 2016年6月7日 下午1:42:38**/
package com.star.framework.jdbc;

public class JDBCException extends RuntimeException {
	private static final long serialVersionUID = -5924283532740215584L;

	public JDBCException(){
		super();
	}
	
	public JDBCException(String msg){
		super(msg);
	}
	
	public JDBCException(Throwable throwable){
		super(throwable);
	}
	
	public JDBCException(String msg, Throwable throwable){
		super(msg, throwable);
	}
}
