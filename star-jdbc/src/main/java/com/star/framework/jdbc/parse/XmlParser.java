/**create by liuhua at 2016年6月7日 下午3:29:27**/
package com.star.framework.jdbc.parse;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import com.star.framework.jdbc.JDBCException;
import com.star.framework.jdbc.parse.bean.SqlBean;

public class XmlParser {
	private static Logger logger = LoggerFactory.getLogger(XmlParser.class);
	private static XmlParser parser = new XmlParser();

	private Map<String, SqlBean> sqlMapResult = new HashMap<>();

	public static XmlParser getInstance() {
		return parser;
	}

	public synchronized void parse(Resource[] resources, Map<String, SqlBean> sqlContainer) {
		parseDocuments(createDocuments(resources));
		sqlContainer.putAll(this.sqlMapResult);
	}

	private Map<String, Document> createDocuments(Resource[] resources) {
		Map<String, Document> documents = new HashMap<>();

		if ((resources != null) && (resources.length > 0)) {
			SAXReader saxReader = new SAXReader();
			for (Resource resource : resources) {
				try {
					String fileName = resource.getFilename();
					InputStream reader = resource.getInputStream();
					Document doc = saxReader.read(reader);
					documents.put(fileName, doc);
				} catch (Exception e) {
					logger.error("SAXReader parse sqlMap xml error!");
					throw new JDBCException(e);
				}
			}
		}
		return documents;
	}

	private void parseDocuments(Map<String, Document> documents) {
		try {
			Iterator<Entry<String, Document>> it = documents.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Document> entry = it.next();
				logger.debug("Loadding sqlMap.xml :" + (String) entry.getKey());
				parseDocument((String) entry.getKey(), ((Document) entry.getValue()).getRootElement());
			}
		} catch (Exception e) {
			throw new JDBCException(e);
		}
	}

	private void parseDocument(String fileName, Element rootElement) {
		String namespace;
		String rootIsRead;
		String rootDsName;
		if (rootElement != null) {
			namespace = rootElement.attributeValue("namespace");
			rootIsRead = rootElement.attributeValue("isRead");
			rootDsName = rootElement.attributeValue("dsName");
			if ((namespace == null) || ("".equals(namespace))) {
				logger.debug("SqlMap Element must have namespace : " + fileName);
				throw new JDBCException("SqlMap has not namespace : " + fileName);
			}
			List<Element> sqlElements = rootElement.elements();
			for (Element element : sqlElements) {
				String id = element.attributeValue("id");
				String isRead = element.attributeValue("isRead");
				String dsName = element.attributeValue("dsName");
				if ((isRead == null) || ("".equals(isRead))) {
					isRead = rootIsRead;
				}
				if ((dsName == null) || ("".equals(dsName))) {
					dsName = rootDsName;
				}
				if ((id == null) || ("".equals(id))) {
					logger.debug("Sql Element must have id : " + fileName);
					throw new JDBCException("Sql Element must have id : " + fileName);
				}
				String sql = element.getTextTrim();
				this.sqlMapResult.put(appendSqlId(namespace, id), new SqlBean(id, isRead, sql, dsName));
			}
		}
	}

	private String appendSqlId(String namespace, String id) {
		return namespace + "." + id;
	}
}
