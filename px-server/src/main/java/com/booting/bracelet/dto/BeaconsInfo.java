/**create by liuhua at 2018年6月15日 上午11:03:25**/
package com.booting.bracelet.dto;

public class BeaconsInfo {
  //[{"devicename":"BS01","type":"ble","devicemac":"D513D37136B2","gatewaymac":"D1122000013F","rssi":-45,"data1":"05094253303102010609FF0100D513D37136B20B0A000030007E03B9044F64","data2":""}]
  private String devicename;
  private String type;
  private String devicemac;
  private String gatewaymac;
  private String rssi;
  private String data1;
  private String data2;
  
  public String getDevicename() {
    return devicename;
  }
  public String getType() {
    return type;
  }
  public String getDevicemac() {
    return devicemac;
  }
  public String getGatewaymac() {
    return gatewaymac;
  }
  public String getRssi() {
    return rssi;
  }
  public String getData1() {
    return data1;
  }
  public String getData2() {
    return data2;
  }
  public void setDevicename(String devicename) {
    this.devicename = devicename;
  }
  public void setType(String type) {
    this.type = type;
  }
  public void setDevicemac(String devicemac) {
    this.devicemac = devicemac;
  }
  public void setGatewaymac(String gatewaymac) {
    this.gatewaymac = gatewaymac;
  }
  public void setRssi(String rssi) {
    this.rssi = rssi;
  }
  public void setData1(String data1) {
    this.data1 = data1;
  }
  public void setData2(String data2) {
    this.data2 = data2;
  }
}
