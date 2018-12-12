/**create by liuhua at 2016年6月17日 下午3:59:26**/
package com.star.framework.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

@SuppressWarnings("unchecked")
public class ParamViewTag extends TagSupport{
	private static final long serialVersionUID = 6405865795294582070L;
	
	private Map<String, Object> detail;
	
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
	
	private void children(StringBuffer outHtml, List<Map<String, Object>> list, Double paramType){
		if (null != list && list.size() > 0) {
			outHtml.append("<tr>");
			outHtml.append("<td>");
			outHtml.append("</td>");
			outHtml.append("<td>");
			outHtml.append("<table>");
			outHtml.append("<tr>");
			if (paramType == ParameterType.type_list.getType().intValue()) {
				outHtml.append("<td style='white-space: nowrap'>[{</td>");
			}else{
				outHtml.append("<td style='white-space: nowrap'>{</td>");
			}
			outHtml.append("</td>");
			outHtml.append("</tr>");
			for (int i = 0; i < list.size(); i++) {
				outHtml.append("<tr>");
				Map<String, Object> map = list.get(i);
				outHtml.append("<td style='white-space: nowrap'>" + (i + 1) + ". " + map.get("paramName") + "</td>");
				outHtml.append("<td>" + "<div class='typeStype'>" + getType(map.get("paramType")) + "</div>" + map.get("paramDesc") + "</td>");
				outHtml.append("</tr>");
				children(outHtml, (List<Map<String, Object>>)map.get("children"), (Double)map.get("paramType"));
			}
			outHtml.append("<tr>");
			if (paramType == ParameterType.type_list.getType().intValue()) {
				outHtml.append("<td style='white-space: nowrap'>},{...}]</td>");
			}else{
				outHtml.append("<td style='white-space: nowrap'>}</td>");
			}
			outHtml.append("</td>");
			outHtml.append("</tr>");
			outHtml.append("</table>");
			outHtml.append("</td>");
			outHtml.append("</tr>");
		}
	}
	public int doEndTag() throws JspException {
		if (null == this.detail) {
			return super.doEndTag();
		}
		Double rstType = (Double)detail.get("rstType");
		if (null == rstType) {
			return super.doEndTag();
		}
		List<Map<String, Object>> list = (List<Map<String, Object>>) detail.get("params");
		JspWriter out = pageContext.getOut();
		StringBuffer outHtml = new StringBuffer();
		outHtml.append("<table>");
		outHtml.append("<tr>");
		if (rstType == ResultType.list.getType().intValue()) {
			outHtml.append("<td style='white-space: nowrap'>[{</td>");
		}else{
			outHtml.append("<td style='white-space: nowrap'>{</td>");
		}
		outHtml.append("</td>");
		outHtml.append("</tr>");
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			outHtml.append("<tr>");
			outHtml.append("<td style='white-space: nowrap'>" + (i + 1) + ". " + map.get("paramName") + "</td>");
			outHtml.append("<td>" + "<div class='typeStype'>" + getType(map.get("paramType")) + "</div>" + map.get("paramDesc") + "</td>");
			outHtml.append("</tr>");
			children(outHtml, (List<Map<String, Object>>)map.get("children"), (Double)map.get("paramType"));
		}
		outHtml.append("<tr>");
		if (rstType == ResultType.list.getType().intValue()) {
			outHtml.append("<td style='white-space: nowrap'>},{...}]</td>");
		}else{
			outHtml.append("<td style='white-space: nowrap'>}</td>");
		}
		outHtml.append("</td>");
		outHtml.append("</tr>");
		outHtml.append("</table>");
		try {
			out.println(outHtml.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doEndTag();
	}

	private String getType(Object object) {
		if (null != object) {
			Integer type = ((Double) object).intValue();
			for (ParameterType parameterType : ParameterType.values()) {
				if (parameterType.getType().intValue() == type.intValue()) {
					return parameterType.getCaption();
				}
			}
		}
		return null;
	}

	public Map<String, Object> getDetail() {
		return detail;
	}

	public void setDetail(Map<String, Object> detail) {
		this.detail = detail;
	}

	public enum ResultType{
		javabean(1, "javaBean"),
		list(2, "list");
		private Integer type;
		private String caption;
		private ResultType(Integer type, String caption){
			this.type = type;
			this.caption = caption;
		}
		public Integer getType() {
			return type;
		}
		public String getCaption() {
			return caption;
		}
	}
	public enum ParameterType{
		type_string(1, "String"),
		type_byte(2, "byte"),
		type_char(3, "char"),
		type_short(4, "short"),
		type_int(5, "int"),
		type_long(6, "long"),
		type_float(7, "float"),
		type_double(8, "double"),
		type_boolean(9, "boolean"),
		type_date_1(10, "已格式化日期"),
		type_date_2(11, "未格式化日期"),
		type_list(12, "列表");
		private Integer type;
		private String caption;
		private ParameterType(Integer type, String caption){
			this.type = type;
			this.caption = caption;
		}
		public Integer getType() {
			return type;
		}
		public String getCaption(){
			return caption;
		}
	}
}