/**create by liuhua at 2018年6月11日 下午5:55:26**/
package com.booting.business.conf;

public class PictureChildType {

  private int childType;
  private String childTypeName;
  
  public PictureChildType(int childType, String childTypeName){
    this.childType = childType;
    this.childTypeName = childTypeName;
  }

  public int getChildType() {
    return childType;
  }

  public String getChildTypeName() {
    return childTypeName;
  }

  public void setChildType(int childType) {
    this.childType = childType;
  }

  public void setChildTypeName(String childTypeName) {
    this.childTypeName = childTypeName;
  }
}
