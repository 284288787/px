package com.star.framework.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class HttpUtil {

	private static final String DEFAULT_CHARSET = "UTF-8"; // 默认字符集

	private static final String _GET = "GET"; // GET
	private static final String _POST = "POST";// POST

	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 初始化http请求参数
	 * 
	 * @param url
	 * @param method
	 * @param headers
	 * @return
	 * @throws IOException
	 */
	private static HttpURLConnection initHttp(String url, String method, Map<String, Object> headers) throws IOException {
		URL _url = new URL(url);
		HttpURLConnection http = (HttpURLConnection) _url.openConnection();
		// 连接超时
		http.setConnectTimeout(25000);
		// 读取超时 --服务器响应比较慢，增大时间
		http.setReadTimeout(25000);
		http.setRequestMethod(method);
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
		if (null != headers && !headers.isEmpty()) {
			for (Entry<String, Object> entry : headers.entrySet()) {
				http.setRequestProperty(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
		http.setDoOutput(true);
		http.setDoInput(true);
		http.connect();
		return http;
	}

	/**
	 * 初始化http请求参数
	 * 
	 * @param url
	 * @param method
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchProviderException
	 * @throws KeyManagementException
	 */
	private static HttpsURLConnection initHttps(String url, String method, Map<String, Object> headers) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException {
		TrustManager[] tm = { new MyX509TrustManager() };
		SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
		sslContext.init(null, tm, new java.security.SecureRandom());
		// 从上述SSLContext对象中得到SSLSocketFactory对象
		SSLSocketFactory ssf = sslContext.getSocketFactory();
		URL _url = new URL(url);
		HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
		// 设置域名校验
		http.setHostnameVerifier(new HttpUtil().new TrustAnyHostnameVerifier());
		// 连接超时
		http.setConnectTimeout(25000);
		// 读取超时 --服务器响应比较慢，增大时间
		http.setReadTimeout(25000);
		http.setRequestMethod(method);
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.146 Safari/537.36");
		if (null != headers && !headers.isEmpty()) {
			for (Entry<String, Object> entry : headers.entrySet()) {
				http.setRequestProperty(entry.getKey(), String.valueOf(entry.getValue()));
			}
		}
		http.setSSLSocketFactory(ssf);
		http.setDoOutput(true);
		http.setDoInput(true);
		http.connect();
		return http;
	}

	/**
	 * 
	 * @description 功能描述: get 请求
	 * @return 返回类型:
	 */
	public static String get(String url, Map<String, Object> params, Map<String, Object> headers) {
		StringBuffer bufferRes = null;
		try {
			HttpURLConnection http = null;
			if (isHttps(url)) {
				http = initHttps(initParams(url, params), _GET, headers);
			} else {
				http = initHttp(initParams(url, params), _GET, headers);
			}
			System.out.println("请求URL：" + http);
			InputStream in = http.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
			String valueString = null;
			bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			read.close();
			in.close();
			if (http != null) {
				http.disconnect();// 关闭连接
			}
			return bufferRes.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @description 功能描述: get 请求
	 * @return 返回类型:
	 */
	public static String get(String url) {
		return get(url, null);
	}

	/**
	 * 
	 * @description 功能描述: get 请求
	 * @return 返回类型:
	 * @throws UnsupportedEncodingException
	 */
	public static String get(String url, Map<String, Object> params) {
		return get(url, params, null);
	}

	/**
	 * 
	 * @description 功能描述: POST 请求
	 * @return 返回类型:
	 */
	public static String post(String url, String params, Map<String, Object> headers) {
		StringBuffer bufferRes = null;
		try {
			HttpURLConnection http = null;
			if (isHttps(url)) {
				http = initHttps(url, _POST, headers);
			} else {
				http = initHttp(url, _POST, headers);
			}
			OutputStream out = http.getOutputStream();
			if (StringUtils.isNotBlank(params)) {
				out.write(params.getBytes(DEFAULT_CHARSET));
			}
			out.flush();
			out.close();

			InputStream in = http.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
			String valueString = null;
			bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			read.close();
			in.close();
			if (http != null) {
				http.disconnect();// 关闭连接
			}
			return bufferRes.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @description 功能描述: POST 请求
	 * @return 返回类型:
	 */
	public static String postExt(String url, String params, Map<String, Object> headers) throws Exception {
		HttpURLConnection http = null;
		if (isHttps(url)) {
			http = initHttps(url, _POST, headers);
		} else {
			http = initHttp(url, _POST, headers);
		}
		OutputStream out = http.getOutputStream();
		if (StringUtils.isNotBlank(params)) {
			out.write(params.getBytes(DEFAULT_CHARSET));
		}
		out.flush();
		out.close();

		InputStream in = http.getInputStream();
		BufferedReader read = new BufferedReader(new InputStreamReader(in, DEFAULT_CHARSET));
		String valueString = null;
		StringBuffer bufferRes = new StringBuffer();
		while ((valueString = read.readLine()) != null) {
			bufferRes.append(valueString);
		}
		read.close();
		in.close();
		if (http != null) {
			http.disconnect();// 关闭连接
		}
		return bufferRes.toString();
	}

	/**
	 * post map 请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String post(String url, Map<String, Object> params) throws UnsupportedEncodingException {
		return post(url, map2Url(params), null);
	}

	/**
	 * post map 请求
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String postExt(String url, Map<String, Object> params) throws Exception {
		return postExt(url, map2Url(params), null);
	}

	/**
	 * post map 请求,headers请求头
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String post(String url, Map<String, Object> params, Map<String, Object> headers) throws UnsupportedEncodingException {
		return post(url, map2Url(params), headers);
	}

	/**
	 * post map 请求,headers请求头
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String postExt(String url, Map<String, Object> params, Map<String, Object> headers) throws Exception {
		return postExt(url, map2Url(params), headers);
	}

	/**
	 * 
	 * @description 功能描述: 构造请求参数
	 * @return 返回类型:
	 * @throws UnsupportedEncodingException
	 */
	public static String initParams(String url, Map<String, Object> params) throws UnsupportedEncodingException {
		if (null == params || params.isEmpty()) {
			return url;
		}
		StringBuilder sb = new StringBuilder(url);
		if (url.indexOf("?") == -1) {
			sb.append("?");
		}
		sb.append(map2Url(params));
		return sb.toString();
	}

	/**
	 * map构造url
	 * 
	 * @description 功能描述:
	 * @return 返回类型:
	 * @throws UnsupportedEncodingException
	 */
	public static String map2Url(Map<String, Object> paramToMap) throws UnsupportedEncodingException {
		if (null == paramToMap || paramToMap.isEmpty()) {
			return null;
		}
		StringBuffer url = new StringBuffer();
		boolean isfist = true;
		for (Entry<String, Object> entry : paramToMap.entrySet()) {
			if (isfist) {
				isfist = false;
			} else {
				url.append("&");
			}
			url.append(entry.getKey()).append("=");
			String value = String.valueOf(entry.getValue());
			if (StringUtils.isNotEmpty(value)) {
				url.append(URLEncoder.encode(value, DEFAULT_CHARSET));
			}
		}
		return url.toString();
	}

	/**
	 * 检测是否https
	 * 
	 * @param url
	 */
	private static boolean isHttps(String url) {
		return url.startsWith("https");
	}

	/**
	 * https 域名校验
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;// 直接返回true
		}
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
			System.out.println(ip + "-----" + "X-Real-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println(ip + "-----" + "Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println(ip + "-----" + "WL-Proxy-Client-IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println(ip + "-----" + "HTTP_CLIENT_IP");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println(ip + "-----" + "HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			System.out.println(ip + "-----" + "getRemoteAddr");
		}
		if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteHost();
			System.out.println(ip + "-----" + "getRemoteHost");
		}

		System.out.println("ip==>" + ip);
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ip != null && ip.contains(",")) {
			String str[] = ip.split(",");
			ip = str[0];
			/*
			 * if(ip.indexOf(",")>0){ ip = ip.substring(0,ip.indexOf(",")); }
			 */
		}
		return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;

	}

}

// 证书管理
class MyX509TrustManager implements X509TrustManager {
	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
	}
}