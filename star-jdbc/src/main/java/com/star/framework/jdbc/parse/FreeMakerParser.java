/**create by liuhua at 2016年6月8日 上午11:27:46**/
package com.star.framework.jdbc.parse;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.star.framework.jdbc.JDBCException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMakerParser {
	private static final String DEFAULT_TEMPLATE_KEY = "default_template_key";
	private static final String DEFAULT_TEMPLATE_EXPRESSION = "default_template_expression";
	private static final Configuration CONFIGURER = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

	private static Map<String, Template> templateCache = new HashMap<>();

	private static Map<String, Template> expressionCache = new HashMap<>();

	public static String process(String expression, Map<String, Object> root) {
		StringReader reader = null;
		StringWriter out = null;
		Template template = null;
		try {
			if (expressionCache.get(expression) != null) {
				template = (Template) expressionCache.get(expression);
			}
			if (template == null) {
				template = createTemplate(DEFAULT_TEMPLATE_EXPRESSION, new StringReader(expression));
				expressionCache.put(expression, template);
			}
			out = new StringWriter();
			template.process(root, out);
			return out.toString();
		} catch (Exception e) {
			throw new JDBCException(e);
		} finally {
			if (reader != null)
				reader.close();
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				return null;
			}
		}
	}

	private static Template createTemplate(String templateKey, StringReader reader) throws IOException {
		Template template = new Template(DEFAULT_TEMPLATE_KEY, reader, CONFIGURER);
		template.setNumberFormat("#");
		return template;
	}

	public static String process(Map<String, Object> root, String sql, String sqlId) {
		StringReader reader = null;
		StringWriter out = null;
		Template template = null;
		try {
			if (templateCache.get(sqlId) != null) {
				template = (Template) templateCache.get(sqlId);
			}
			if (template == null) {
				reader = new StringReader(sql);
				template = createTemplate(DEFAULT_TEMPLATE_KEY, reader);
				templateCache.put(sqlId, template);
			}
			out = new StringWriter();
			template.process(root, out);
			return out.toString();
		} catch (Exception e) {
			throw new JDBCException(e);
		} finally {
			if (reader != null)
				reader.close();
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				return null;
			}
		}
	}

	static {
		CONFIGURER.setClassicCompatible(true);
	}
}