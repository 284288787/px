package com.booting.insurance.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


public class MD5Util {

    private static final String HEX_CHARS = "0123456789abcdef";
    /** 日志 */
   
    private MD5Util() {}

   /**
     * 返回 MessageDigest MD5
     */
   private static MessageDigest getDigest() {
	   try {
           return MessageDigest.getInstance("MD5");
       } catch (NoSuchAlgorithmException e) {
           throw new RuntimeException(e);
       }
    }
   
   /**
    * 返回 MessageDigest MD5
    */
  private static MessageDigest getDigestBySha() {
	   try {
          return MessageDigest.getInstance("SHA-256");
      } catch (NoSuchAlgorithmException e) {
          throw new RuntimeException(e);
      }
   }

    /**
     * MD5加密，并返回作为一个十六进制字节
     */
    public static byte[] md5(byte[] data) {
        return getDigest().digest(data);
    }

    /**
     * SHA-256加密，并返回作为一个十六进制字节
     */
    public static byte[] sha256(byte[] data) {
        return getDigestBySha().digest(data);
    }

    /**
     * MD5加密，并返回作为一个十六进制字节
     * <code>byte[]</code>.
     * 
     * @param data
     *            Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(String data) {
    	byte[] bytes = null;
        try {
        	bytes = md5(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			
		}
		return bytes;
    }

    /**
     * MD5加密，并返回一个32字符的十六进制值
     */
    public static String md5Hex(byte[] data) {
        return toHexString(md5(data));
    }

    /**
     * MD5加密，并返回一个32字符的十六进制值
     */
    public static String md5Hex(String data) {
        return toHexString(md5(data));
    }
    /**
     * SHA256加密
     */
    public static String sha256Hex(String data) {
        try {
			return toHexString(sha256(data.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			
			return null;
		}
    }
    
    private static String toHexString(byte[] b) {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
        	stringbuffer.append(HEX_CHARS.charAt(b[i] >>> 4 & 0x0F));
        	stringbuffer.append(HEX_CHARS.charAt(b[i] & 0x0F));
        }
        return stringbuffer.toString();
    }
//    public static void main(String[] args) {   
//    	String dataSource="137";
//    	String customerName="zs";
//    	String businessNo="05250019000143135043";
//    	String currencyNo="RMB";
//    	String amount="30.00";
//    	String documentNo="50569001900309425607";
//    	String KEY="P_JIAHE";// OPENAPI
//    	String data=dataSource + customerName + businessNo + currencyNo + amount + documentNo + KEY;
//    	System.out.println("密文为："+MD5Util1.sha256Hex(data));
//    }
    public static String getmsg(Map<String,String> map){
    	String data = "";
    	String dataSource=map.get("dataSource");
    	String customerName=map.get("customerName");
    	String businessNo=map.get("businessNo");
    	String currencyNo=map.get("currencyNo");
    	String amount=map.get("amount");
    	String documentNo=map.get("documentNo");
    	String KEY="P_JIAHE";// OPENAPI
    	data = dataSource + customerName + businessNo + currencyNo + amount + documentNo + KEY;
    	data = sha256Hex(data);
    	return data;
    }
    
    public static String getAppPayMsg(Map<String,String> map){
    	String data = "";
    	String dataSource=map.get("dataSource");
    	String customerName=map.get("customerName");
    	String businessNo=map.get("businessNo");
    	String currencyNo=map.get("currencyNo");
    	String amount=map.get("amount");
    	String KEY="P_JIAHE";// OPENAPI
    	data = dataSource + customerName + businessNo + currencyNo + amount  + KEY;
    	data = sha256Hex(data);
    	return data;
    }
    
//    public static void main(String[] args) {
//    	Map map = new HashMap();
//    	map.put("dataSource", "137");
//    	map.put("customerName", "水电费的是否等");
//    	map.put("businessNo", "12100019000143176964");
//    	map.put("currencyNo", "RMB");
//    	map.put("amount", "3.00");
//    	map.put("KEY", "P_JIAHE");
//		System.out.println(getmsg(map));
//	}
    	
}



