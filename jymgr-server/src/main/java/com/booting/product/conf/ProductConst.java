/**create by liuhua at 2018年6月21日 下午10:13:39**/
package com.booting.product.conf;

public class ProductConst {
  
  //1活动 2设备 3课程
  public enum ProductType{
    activity(1, "活动"), equipment(2, "设备"), curriculum(3, "课程");
    
    private int business;
    private String caption;
    
    private ProductType(int business, String caption){
      this.business = business;
      this.caption = caption;
    }

    public int getBusiness() {
      return business;
    }

    public String getCaption() {
      return caption;
    }
  }
}
