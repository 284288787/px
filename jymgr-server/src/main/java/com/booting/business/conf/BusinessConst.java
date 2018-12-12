/**create by liuhua at 2018年6月11日 下午4:55:55**/
package com.booting.business.conf;

import java.util.ArrayList;
import java.util.List;

public class BusinessConst {

  public enum MemberIdentity{
    coach(1, "教练"), salesman(2, "业务员"), franchisee(3, "加盟商"), customer(4, "客户");
    
    private int identity;
    private String caption;
    
    private MemberIdentity(int identity, String caption){
      this.identity = identity;
      this.caption = caption;
    }

    public int getIdentity() {
      return identity;
    }

    public String getCaption() {
      return caption;
    }
  }
  
  public static PictureChildType[] createTeachingCerts(){
    List<PictureChildType> types = new ArrayList<>();
    types.add(new PictureChildType(1, "执教证书1"));
    types.add(new PictureChildType(2, "执教证书2"));
    types.add(new PictureChildType(3, "执教证书3"));
    types.add(new PictureChildType(4, "执教证书4"));
    types.add(new PictureChildType(5, "执教证书5"));
    return types.toArray(new PictureChildType[]{});
  }
  
  public enum BusinessPictureType{
    head         (1, "头像", new PictureChildType(1, "头像")), 
    certificate  (2, "证件", new PictureChildType(1, "正面"), new PictureChildType(2, "反面")), 
    edu          (3, "学历", new PictureChildType(1, "学历证书")), 
    teachingCert (4, "执教证书", createTeachingCerts());
    
    private int pictureType;
    private String caption;
    private PictureChildType[] childTypes;
    
    private BusinessPictureType(int pictureType, String caption, PictureChildType ...childTypes){
      this.pictureType = pictureType;
      this.caption = caption;
      this.childTypes = childTypes;
    }

    public String getCaption() {
      return caption;
    }

    public int getPictureType() {
      return pictureType;
    }
    
    public PictureChildType[] geChildTypes(){
      return childTypes;
    }
  }
}
