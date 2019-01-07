/** create by liuhua at 2016年5月23日 下午1:43:07 **/
package com.star.framework.specification.utils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.framework.specification.Constants;
import com.star.framework.specification.result.v2.ResultMessage;
import com.star.framework.utils.DesUtil;

/**
 * 简化取参数的过程
 * 
 * @author liuhua
 *
 */
public class ParamHandler {

  public static String staticPath = "/home/tomcat/web/booting-web/static/js";
  // public static Gson gson = new GsonBuilder().serializeNulls().registerTypeAdapter(Integer.class,
  // new JsonSerializer<Integer>() {
  // @Override
  // public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
  // if (null == src) {
  // return null;
  // }
  // return new JsonPrimitive(src + "");
  // }
  //
  // })/*.registerTypeAdapter(Double.class, new JsonSerializer<Double>() {
  // @Override
  // public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
  // if (null == src) {
  // return null;
  // }
  // if (src.intValue() != src) {
  // return new JsonPrimitive(src);
  // }
  // return new JsonPrimitive(src.intValue() + "");
  // }
  //
  // })*/.setLongSerializationPolicy(LongSerializationPolicy.STRING).setDateFormat("yyyy-MM-dd
  // HH:mm:ss").create();
  public static ObjectMapper objectMapper = new ObjectMapper();

  static {
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
  public static String ymdhms = "yyyy-MM-dd HH:mm:ss";
  public static String ymd = "yyyy-MM-dd";
  private Map<String, Object> map;
  
  public static <T> T strToObj(String str, Class<T> clazz){
    try {
      return objectMapper.readValue(str, clazz);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <T> T strToObj(String str, JavaType javaType){
    try {
      return objectMapper.readValue(str, javaType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <T> T strToObj(String str, TypeReference<T> typeReference){
    try {
      return objectMapper.readValue(str, typeReference);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static <K, V> Map<K, V> strToMap(String str){
    try {
      return objectMapper.readValue(str, new TypeReference<Map<K, V>>() {});
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static <V> List<V> strToList(String str){
    try {
      return objectMapper.readValue(str, new TypeReference<List<V>>() {});
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  public static String objToString(Object object){
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  /**
   * 把已经封装在map里的参数 直接赋值过来。
   * 
   * @param map
   */
  public ParamHandler(Map<String, Object> map) {
    this.map = map;
  }

  /**
   * 将一个bean 转换成map
   * 
   * @param object
   */
  public ParamHandler(Object object) {
    try {
      this.map = objectMapper.readValue(objectMapper.writeValueAsString(object), new TypeReference<Map<String, Object>>() {});
    } catch (Exception e) {
      e.printStackTrace();
      this.map = new HashMap<>();
    }
  }

  public ParamHandler(String params) {
    try {
      if (StringUtils.isNotBlank(params)) {
        // if (DesUtil.encrypt_args) {
        // params = DesUtil.decrypt(params);
        // }
        this.map = strToMap(params);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 加密
   * 
   * @param resultMessage
   * @return
   */
  public String encrypt(ResultMessage resultMessage) {
    String json = objToString(resultMessage);
    String enc = DesUtil.encrypt(json);
    return enc;
  }

  /**
   * 讲 从客户端传递过来的参数 找到，并封装在map里。
   * 
   * @param request
   */
  @SuppressWarnings("unchecked")
  public ParamHandler(HttpServletRequest request) {
    this.map = new HashMap<String, Object>();
    Enumeration<String> names = request.getParameterNames();
    while (names.hasMoreElements()) {
      String name = names.nextElement();
      String value = request.getParameter(name);
      if (StringUtils.isNotBlank(value)) {
        map.put(name, value);
      }
    }
    if (map.isEmpty()) {
      try {
        ServletInputStream inputStream = request.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        StringBuffer temp = new StringBuffer();
        while ((len = inputStream.read(b)) != -1) {
          temp.append(new String(b, 0, len));
        }
        if (temp.length() > 0) {
          map = strToMap(temp.toString());
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public Double getDouble(String name) {
    Object object = getObject(name);
    if (null == object) {
      return null;
    }
    try {
      return Double.parseDouble(object.toString());
    } catch (Exception e) {

    }
    return null;
  }

  public Date getDate(String name, String pattern) {
    Object object = getObject(name);
    if (null == object) {
      return null;
    }
    try {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.parse(object.toString());
    } catch (Exception e) {

    }
    return null;
  }

  public Long getLong(String name) {
    Object object = getObject(name);
    if (null == object) {
      return null;
    }
    try {
      return Long.parseLong(object.toString());
    } catch (Exception e) {

    }
    return null;
  }

  public Integer getInteger(String name) {
    Object object = getObject(name);
    if (null == object) {
      return null;
    }
    try {
      return Integer.parseInt(object.toString());
    } catch (Exception e) {

    }
    return null;
  }

  public Boolean getBoolean(String name) {
    Object object = getObject(name);
    try {
      return Boolean.parseBoolean(object.toString());
    } catch (Exception e) {

    }
    return null;
  }

  public String getString(String name) {
    Object object = getObject(name);
    if (null == object) {
      return null;
    }
    return object.toString().trim();
  }

  public Object getObject(String name) {
    if (null == map) {
      return null;
    }
    return map.get(name);
  }

  /**
   * 可以把map里的参数 封装成希望的bean @author liuhua
   *
   * @param classOfT @return @throws Exception @throws
   */
  public <T> T getDTO(Class<T> classOfT) throws Exception {
    T t = strToObj(objToString(map), classOfT);
    if (null == t) {
      t = classOfT.newInstance();
    }
    return t;
  }

  public <T> T getDTO(Type type) {
    return strToObj(objToString(map), objectMapper.getTypeFactory().constructType(type));
  }

  /**
   * 签名校验
   * 
   * @author liuhua
   *
   * @param signKey 如果key没有，则使用默认的key
   * @return
   */
  public boolean checkParam(String signKey) {
    if (null != map) {
      String sign = getString("sign");
      if (null == sign || sign.length() == 0) {
        return false;
      }
      Map<String, Object> newMap = new HashMap<String, Object>();
      newMap.putAll(map);
      newMap.remove("sign");
      if (null == signKey || signKey.length() == 0) {
        signKey = Constants.SIGN_KEY;
      }
      String tempSign = SignUtils.createSign(newMap, signKey);
      if (sign.equals(tempSign)) {
        return true;
      }
    }
    return false;
  }

  public void addParam(String key, Object value) {
    if (null == map) {
      map = new HashMap<>();
    }
    map.put(key, value);
  }

  public Map<String, Object> getMap() {
    return map;
  }

  public static String changeName(String name) {
    if (StringUtils.isNotBlank(name)) {
      int idx = name.indexOf("_");
      if (idx != -1) {
        if (idx + 1 == name.length()) {
          name = name.substring(0, name.length() - 1);
        } else {
          String temp = name.substring(idx + 1, idx + 2);
          name = name.replaceFirst("_" + temp, temp.toUpperCase());
          return changeName(name);
        }
      }
    }
    return name;
  }

  public enum FormatTypes {
    yMd("yyyy-MM-dd"), yMdhms("yyyy-MM-dd HH:mm:ss");

    private String format;

    private FormatTypes(String format) {
      this.format = format;
    }

    public String getFormat() {
      return format;
    }
  }

  public static String getDateString(Date date, String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(date);
  }

  public static String getDateString(Date date) {
    return getDateString(date, "yyyy-MM-dd HH:mm:ss");
  }

  public static String getDateStringOfNow(String format) {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    return sdf.format(new Date());
  }

  public static int getMinute(Date time) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(time);
    int h = calendar.get(Calendar.HOUR_OF_DAY);
    int m = calendar.get(Calendar.MINUTE);
    return h * 60 + m;
  }

  public static Date getNewDate(Date time, int key, int value) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(time);
    calendar.add(key, value);
    return calendar.getTime();
  }

  public static Date toDate(String dateString) {
    return toDate(dateString, "yyyy-MM-dd HH:mm:ss");
  }

  public static Date toDate(String dateString, String pattern) {
    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    try {
      return sdf.parse(dateString);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }
}
