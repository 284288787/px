/**create by liuhua at 2016年6月8日 上午11:03:33**/
package com.star.framework.jdbc.resource;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

import com.star.framework.jdbc.JDBCException;
import com.star.framework.jdbc.parse.XmlParser;
import com.star.framework.jdbc.parse.bean.SqlBean;

public class XmlResource implements InitializingBean{
	private Map<String, SqlBean> sqlContainer = new HashMap<>();
	private Resource[] resources;

	private synchronized Resource[] getResources() {
		return this.resources;
	}

	public synchronized void setResources(Resource[] resources) {
		this.resources = resources;
	}

	protected void parseResource() {
		XmlParser.getInstance().parse(getResources(), this.sqlContainer);
	}

	public SqlBean getSQL(String sqlId) {
		SqlBean sqlBean = (SqlBean) this.sqlContainer.get(sqlId);
		if ((sqlBean == null) || (sqlBean.getContent() == null) || ("".equals(sqlBean.getContent()))) {
			throw new JDBCException("SQL must be not null.");
		}
		return sqlBean;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		parseResource();
	}
}