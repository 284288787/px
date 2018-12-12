package com.booting.insurance.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class HttpRequestUtil {

	    /**
	     * 向指定URL发送GET方法的请求
	     * 
	     * @param url
	     *            发送请求的URL
	     * @param param
	     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	     * @return URL 所代表远程资源的响应结果
	     */
	    public static String sendGet(String url, String param) {
	        String result = "";
	        BufferedReader in = null;
	        try {
	            String urlNameString = url + "?" + param;
	            
	            System.out.println(urlNameString);
	            URL realUrl = new URL(urlNameString);
	            System.out.println(realUrl);
	            // 打开和URL之间的连接
	            URLConnection connection = realUrl.openConnection();
	            
	            // 设置通用的请求属性
	            connection.setRequestProperty("accept", "*/*");
	            connection.setRequestProperty("connection", "Keep-Alive");
	            connection.setRequestProperty("Content-type", "application/json;charset=utf-8");
	           // connection.setRequestProperty("Charset", "utf-8");
	            connection.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 建立实际的连接
	            connection.connect();
	            // 获取所有响应头字段
	            Map<String, List<String>> map = connection.getHeaderFields();
	            // 遍历所有的响应头字段
	            for (String key : map.keySet()) {
	                System.out.println(key + "--->" + map.get(key));
	            }
	            // 定义 BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(new InputStreamReader(
	                    connection.getInputStream(),"utf-8"));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送GET请求出现异常！" + e);
	            e.printStackTrace();
	        }
	        // 使用finally块来关闭输入流
	        finally {
	            try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (Exception e2) {
	                e2.printStackTrace();
	            }
	        }
	        return result;
	    }

	    /**
	     * 向指定 URL 发送POST方法的请求
	     * 
	     * @param url
	     *            发送请求的 URL
	     * @param param
	     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	     * @return 所代表远程资源的响应结果
	     */
	    public static String sendPost(String url, String param) {
	        PrintWriter out = null;
	        BufferedReader in = null;
	        String result = "";
	        //HttpURLConnection conn = null;
	        URLConnection conn = null;
	        try {
	            URL realUrl = new URL(url);
	            // 打开和URL之间的连接
	            //conn = Proxy1.getConnection(realUrl);
	            conn = realUrl.openConnection();
	            // 设置通用的请求属性
	            conn.addRequestProperty("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	            conn.setRequestProperty("accept", "*/*");
	            conn.setRequestProperty("connection", "Keep-Alive");
	            //conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	            conn.setRequestProperty("user-agent",
	                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	            // 发送POST请求必须设置如下两行
	            conn.setDoOutput(true);
	            conn.setDoInput(true);
	            // 获取URLConnection对象对应的输出流
	            out = new PrintWriter(conn.getOutputStream());
	            // 发送请求参数
	            out.print(param);
	            // flush输出流的缓冲
	            out.flush();
	            // 定义BufferedReader输入流来读取URL的响应
	            in = new BufferedReader(
	                    new InputStreamReader(conn.getInputStream()));
	            String line;
	            while ((line = in.readLine()) != null) {
	                result += line;
	            }
	        } catch (Exception e) {
	            System.out.println("发送 POST 请求出现异常！"+e);
	            e.printStackTrace();
	        }
	        //使用finally块来关闭输出流、输入流
	        finally{
	            try{
	                if(out!=null){
	                    out.close();
	                }
	                if(in!=null){
	                    in.close();
	                }
	            }
	            catch(IOException ex){
	                ex.printStackTrace();
	            }
	        }
	        return result;
	    }    
	
	    public static void main(String[] args) {
	        //发送 GET 请求
	        //String s=HttpRequestUtil.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
	        //System.out.println(s);
	        
	        //发送 POST 请求
	    	//params.rowIdx = idx;
	    	//S_ID:"tb_qccriterions_dim_taobiao.xlsx",
			//day:"2015",
			//daycap:"2015年",
			//op:"setValidate",
			//page:0,
			//paraDef:"str",
			//reportid:"tb_qccriterions_dim_taobiao.xlsx",
			//rmlpar:"2015",
			//rowIdx:7,
			//section:0,
	    	/*Map  parameter = new HashMap();
	    	parameter.put("ajax","1");
	    	parameter.put("S_ID","tb_tax_taobiao.xlsx");
	    	parameter.put("taxTypeId","DECTAX160804000002");
	    	parameter.put("reportTableId","DETNO160804000001");
	    	parameter.put("op","setValidate");
	    	parameter.put("type","tb_tax_taobiao.xlsx");*/
	    	
	    	/*String ajax = String.valueOf(parameter.get("ajax"));
	    	String SID = String.valueOf(parameter.get("S_ID"));
	    	String taxTypeId = String.valueOf(parameter.get("taxTypeId"));
	    	String reportTableId = String.valueOf(parameter.get("reportTableId"));
	    	String op = String.valueOf(parameter.get("op"));
	    	String type = String.valueOf(parameter.get("type"));*/
	    	/*JSONObject json = new JSONObject();
	    	json.put("ajax","1");
	    	json.put("S_ID","tb_tax_taobiao.xlsx");
	    	json.put("taxTypeId","DECTAX160804000002");
	    	json.put("reportTableId","DETNO160804000001");
	    	json.put("op","setValidate");
	    	json.put("type","tb_tax_taobiao.xlsx");*/
	    	
	    	
	    	//需要提前知道一张套表有几张申报表
	    	/*int rowCount = 1;
	    	for (int i = 0; i < rowCount; i++) {
	    		String param = "ajax=1&S_ID="+SID+"&taxTypeId="+taxTypeId
	    				+"&reportTableId="+reportTableId+"&op=setValidate&type="+type
	    				+"&rowIdx="+i+"&section=0";
	    		String sr=HttpRequestUtil.sendPost("http://localhost:8086/bicenterfin/RegularReport", param);
		        		"ajax=1&S_ID=tb_qccriterions_dim_taobiao.xlsx&day=2015&rowIdx=0&op=setValidate&type=tb_qccriterions_dim_taobiao.xlsx");
		        System.out.println(sr);
	    		try {
					JSONObject a = new JSONObject(sr);
					JSONObject b= (JSONObject) a.get("DataInfo");
					String result = String.valueOf(b.get("result"));
					//String msg = String.valueOf(b.get("msg"));
					System.out.println(result);
					//System.out.println(msg);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		       
			}*/
	    	
	        
	    }
}
