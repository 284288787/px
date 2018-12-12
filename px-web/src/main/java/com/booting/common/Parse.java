/** create by liuhua at 2018年6月1日 上午10:27:19 **/
package com.booting.common;

import java.util.ArrayList;
import java.util.List;
import com.booting.bracelet.dto.BeaconsInfo;
import com.booting.bracelet.dto.BraceletDTO;

public class Parse {

  public static void main(String[] args) {
    String str = "05094253303102010609FF0100D513D37136B20B0A00007C00E308030C4F64";
    BraceletDTO braceletDTO = getBracelet(str);
    System.out.println(braceletDTO);
  }

  public static BraceletDTO getBracelet(String data){
    List<char[]> chars = new ArrayList<>();
    chars.add(new char[46]); //other
    chars.add(new char[4]);  //卡路里
    chars.add(new char[4]);  //距离
    chars.add(new char[4]);  //步数
    chars.add(new char[2]);  //心率
    chars.add(new char[2]);  //电量
    int j = 0;
    int k = 0;
    for (int i = 0; i < data.length(); i++) {
      char[] ch = chars.get(j);
      if (ch.length == k) {
        j ++;
        k = 0;
        ch = chars.get(j);
      }
      ch[k] = data.charAt(i);
      k ++;
    }
    BraceletDTO bracelet = new BraceletDTO();
    bracelet.setOther(new String(chars.get(0)));
    bracelet.setCalorie(Integer.parseInt(rev(new String(chars.get(1))), 16));
    bracelet.setDistance(Integer.parseInt(rev(new String(chars.get(2))), 16));
    bracelet.setStepNum(Integer.parseInt(rev(new String(chars.get(3))), 16));
    bracelet.setHeartRate(Integer.parseInt(rev(new String(chars.get(4))), 16));
    bracelet.setQuantity(new String(chars.get(5)));
    return bracelet;
  }
  
  private static String rev(String ox) {
    byte b[] = ox.getBytes();
    byte result[] = new byte[b.length];
    for (int i = b.length - 1, j = 0; i >= 0; i = i - 2, j = j + 2) {
      result[j] = b[i - 1];
      result[j + 1] = b[i];
    }
    return new String(result);
  }

  public static BraceletDTO getBracelet(BeaconsInfo beaconsInfo) {
    BraceletDTO braceletDTO = getBracelet(beaconsInfo.getData1());
    braceletDTO.setMac(beaconsInfo.getDevicemac());
    braceletDTO.setGatewaymac(beaconsInfo.getGatewaymac());
    braceletDTO.setRssi(beaconsInfo.getRssi());
    braceletDTO.setType(beaconsInfo.getType());
    braceletDTO.setData(beaconsInfo.getData1());
    braceletDTO.setDeviceName(beaconsInfo.getDevicename());
    return braceletDTO;
  }

  /*private static int cal(int hnum) {
    byte byte1 = (byte) (hnum & 0xff);
    byte byte2 = (byte) ((hnum & 0xff00) >> 8);
    int realint = (byte2 & 0xff) << 0 | (byte1 & 0xff) << 8;
    return realint;
  }*/
}
