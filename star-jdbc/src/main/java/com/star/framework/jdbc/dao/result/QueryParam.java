/**create by liuhua at 2016年6月17日 下午2:16:29**/
package com.star.framework.jdbc.dao.result;

import java.io.Serializable;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

/**
 * 查询参数
 * @author liuhua
 *
 */
public class QueryParam implements Serializable{
	private static final long serialVersionUID = -5736789970571629445L;
	
	Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setLongSerializationPolicy(LongSerializationPolicy.STRING).create();
	
	/**当前页码**/
	private int pageNo = 1;
	/**一页最大记录数**/
	private int pageSize = 10;
	/**排序列表 name desc, id**/
	private String orderBy;
	private String orderType;
	/**查询条件 一般位map或者bean**/
	private Map<String, Object> param;
	/**页面参数保留**/
	private String urlParam;
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Map<String, Object> getParam() {
		return param;
	}
	@SuppressWarnings("unchecked")
	public <T> void setParam(T param) {
		if (null == param) {
			return;
		}
		if (param instanceof Map) {
			if (null == this.param) {
				this.param = (Map<String, Object>)param;
			}else{
				this.param.putAll((Map<String, Object>)param);
			}
		}else{
			try{
				this.param = gson.fromJson(gson.toJson(param), Map.class);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 解析url后面的字符串作为分页查询的参数
	 * -s 排序的字段 并且升序
	 * -d 排序字段降序
	 * -k 关键字查询
	 * -数字 当前页码
	 * -p数字 一页最大记录数
	 * @author liuhua
	 *
	 * @param args
	 * @return
	 */
	public static QueryParam createParam(String args){
		QueryParam queryParam = new QueryParam();
		try{
			Map<String, Object> map = new HashMap<>();
			String urlParam = "";
			if (null != args) {
				Pattern p = Pattern.compile("-([^-&?]+)", Pattern.CASE_INSENSITIVE);
				Matcher m = p.matcher(args);
				while (m.find()) {
					String a = m.group(1);
					if (a.startsWith("s")) {
						urlParam += "-" + a;
						a = a.substring(1);
						queryParam.setOrderBy(a);
						queryParam.setOrderType("asc");
					}else if (a.startsWith("d")) {
						urlParam += "-" + a;
						a = a.substring(1);
						queryParam.setOrderType("desc");
					}else if (a.startsWith("k")) {
						urlParam += "-" + a;
						a = a.substring(1);
						String k = URLDecoder.decode(a, "UTF-8");
						map.put("keyword", k);
					}else if (a.startsWith("p")) {
						urlParam += "-" + a;
						a = a.substring(1);
						queryParam.setPageSize(Integer.parseInt(a));
					}else {
						queryParam.setPageNo(Integer.parseInt(a));
					}
				}
			}
			queryParam.setParam(map);
			queryParam.setUrlParam(urlParam);
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryParam;
	}
	public String getUrlParam() {
		return urlParam;
	}
	public void setUrlParam(String urlParam) {
		this.urlParam = urlParam;
	}
}
