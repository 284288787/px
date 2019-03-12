/**create by liuhua at 2019年3月12日 下午3:46:54**/
package com.booting.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplyItem {

  public static List<Map<String, Object>> items = new ArrayList<Map<String, Object>>() {
    private static final long serialVersionUID = 1L;
    {
      add(new HashMap<String, Object>() {
        private static final long serialVersionUID = 1L;
        {
          put("itemId", 1);
          put("itemName", "体质检测");
        }
      });
      add(new HashMap<String, Object>() {
        private static final long serialVersionUID = 1L;
        {
          put("itemId", 2);
          put("itemName", "2节体验课");
        }
      });

    }
  };
  
  public static String getItemName(Long applyItemId) {
    if(null == applyItemId) return "";
    return items.stream().filter(map -> Long.parseLong(map.get("itemId").toString()) == applyItemId).map(map -> (String) map.get("itemName")).findFirst().get();
  }
}
