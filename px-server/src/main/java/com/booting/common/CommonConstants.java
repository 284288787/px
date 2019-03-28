/**create by liuhua at 2017年6月2日 上午9:26:40**/
package com.booting.common;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommonConstants {

  /** 是否赠送保险券 **/
  public static boolean zbxq = true;

  public static void main(String[] args) {
    int i = standard.get("3_1").getScore("weight", 0.24);
    System.out.println(i);
  }

  public static Map<String, Physical> standard = new HashMap<String, Physical>() {
    private static final long serialVersionUID = 1L;
    {
      /** 3岁男 **/
      Physical physical31 = new Physical();
      physical31.add("run10", new Temp(1000.00, 12.80, 1), new Temp(12.80, 10.20, 2), new Temp(10.20, 09.00, 3), new Temp(09.00, 08.00, 4), new Temp(08.00, -100.00, 5));
      physical31.add("jump", new Temp(-100.00, 30.00, 1), new Temp(30.00, 43.00, 2), new Temp(43.00, 59.00, 3), new Temp(59.00, 76.00, 4), new Temp(76.00, 1000.00, 5));
      physical31.add("throwTennis", new Temp(-100.00, 02.00, 1), new Temp(02.00, 03.00, 2), new Temp(03.00, 04.00, 3), new Temp(04.00, 05.50, 4), new Temp(05.50, 1000.00, 5));
      physical31.add("doubleJump", new Temp(1000.00, 20.00, 1), new Temp(20.00, 13.40, 2), new Temp(13.40, 09.70, 3), new Temp(09.70, 07.10, 4), new Temp(07.10, -100.00, 5));
      physical31.add("sitReach", new Temp(-100.00, 04.90, 1), new Temp(04.90, 08.60, 2), new Temp(08.60, 11.70, 3), new Temp(11.70, 14.90, 4), new Temp(14.90, 1000.00, 5));
      physical31.add("balance", new Temp(1000.00, 30.00, 1), new Temp(30.00, 16.80, 2), new Temp(16.80, 10.50, 3), new Temp(10.50, 06.60, 4), new Temp(06.60, -100.00, 5));
      physical31.add("stature", new Temp(-100.00, 91.2, 1), new Temp(91.2, 95.5, 2), new Temp(95.5, 99.4, 3), new Temp(99.4, 104.2, 4), new Temp(104.2, 1000.00, 5));
      physical31.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("3_1", physical31);
      /** 3岁女 **/
      Physical physical30 = new Physical();
      physical30.add("run10", new Temp(1000.00, 13.40, 1), new Temp(13.40, 10.50, 2), new Temp(10.50, 09.30, 3), new Temp(09.30, 08.20, 4), new Temp(08.20, -100.00, 5));
      physical30.add("jump", new Temp(-100.00, 29.00, 1), new Temp(29.00, 40.00, 2), new Temp(40.00, 55.00, 3), new Temp(55.00, 71.00, 4), new Temp(71.00, 1000.00, 5));
      physical30.add("throwTennis", new Temp(-100.00, 01.50, 1), new Temp(01.50, 02.50, 2), new Temp(02.50, 03.50, 3), new Temp(03.50, 05.00, 4), new Temp(05.00, 1000.00, 5));
      physical30.add("doubleJump", new Temp(1000.00, 20.00, 1), new Temp(20.00, 13.40, 2), new Temp(13.40, 09.70, 3), new Temp(09.70, 07.10, 4), new Temp(07.10, -100.00, 5));
      physical30.add("sitReach", new Temp(-100.00, 06.30, 1), new Temp(06.30, 10.00, 2), new Temp(10.00, 13.00, 3), new Temp(13.00, 15.90, 4), new Temp(15.90, 1000.00, 5));
      physical30.add("balance", new Temp(1000.00, 32.40, 1), new Temp(32.40, 17.30, 2), new Temp(17.30, 10.70, 3), new Temp(10.70, 06.90, 4), new Temp(06.90, -100.00, 5));
      physical30.add("stature", new Temp(-100.00, 90, 1), new Temp(90, 94.7, 2), new Temp(94.7, 98.1, 3), new Temp(98.1, 103.0, 4), new Temp(103.0, 1000.00, 5));
      physical30.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("3_0", physical30);

      /** 3.5岁男 **/
      Physical physical351 = new Physical();
      physical351.add("run10", new Temp(1000.00, 11.30, 1), new Temp(11.30, 09.40, 2), new Temp(09.40, 08.30, 3), new Temp(08.30, 07.50, 4), new Temp(07.50, -100.00, 5));
      physical351.add("jump", new Temp(-100.00, 35.00, 1), new Temp(35.00, 53.00, 2), new Temp(53.00, 70.00, 3), new Temp(70.00, 84.00, 4), new Temp(84.00, 1000.00, 5));
      physical351.add("throwTennis", new Temp(-100.00, 02.00, 1), new Temp(02.00, 03.00, 2), new Temp(03.00, 04.50, 3), new Temp(04.50, 05.50, 4), new Temp(05.50, 1000.00, 5));
      physical351.add("doubleJump", new Temp(1000.00, 16.90, 1), new Temp(16.90, 11.10, 2), new Temp(11.10, 08.20, 3), new Temp(08.20, 06.10, 4), new Temp(06.10, -100.00, 5));
      physical351.add("sitReach", new Temp(-100.00, 04.70, 1), new Temp(04.70, 08.50, 2), new Temp(08.50, 11.60, 3), new Temp(11.60, 14.90, 4), new Temp(14.90, 1000.00, 5));
      physical351.add("balance", new Temp(1000.00, 27.00, 1), new Temp(27.00, 15.00, 2), new Temp(15.00, 09.30, 3), new Temp(09.30, 05.90, 4), new Temp(05.90, -100.00, 5));
      physical351.add("stature", new Temp(-100.00, 94.1, 1), new Temp(94.1, 98.3, 2), new Temp(98.3, 102.1, 3), new Temp(102.1, 106.0, 4), new Temp(106.0, 1000.00, 5));
      physical351.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("35_1", physical351);
      /** 3.5岁女 **/
      Physical physical350 = new Physical();
      physical350.add("run10", new Temp(1000.00, 12.00, 1), new Temp(12.00, 09.70, 2), new Temp(09.70, 08.60, 3), new Temp(08.60, 07.70, 4), new Temp(07.70, -100.00, 5));
      physical350.add("jump", new Temp(-100.00, 03.40, 1), new Temp(03.40, 50.00, 2), new Temp(50.00, 65.00, 3), new Temp(65.00, 81.00, 4), new Temp(81.00, 1000.00, 5));
      physical350.add("throwTennis", new Temp(-100.00, 02.00, 1), new Temp(02.00, 03.00, 2), new Temp(03.00, 04.00, 3), new Temp(04.00, 05.00, 4), new Temp(05.00, 1000.00, 5));
      physical350.add("doubleJump", new Temp(1000.00, 17.00, 1), new Temp(17.00, 11.20, 2), new Temp(11.20, 08.40, 3), new Temp(08.40, 06.20, 4), new Temp(06.20, -100.00, 5));
      physical350.add("sitReach", new Temp(-100.00, 06.30, 1), new Temp(06.30, 10.00, 2), new Temp(10.00, 13.00, 3), new Temp(13.00, 15.90, 4), new Temp(15.90, 1000.00, 5));
      physical350.add("balance", new Temp(1000.00, 27.40, 1), new Temp(27.40, 15.00, 2), new Temp(15.00, 09.60, 3), new Temp(09.60, 06.10, 4), new Temp(06.10, -100.00, 5));
      physical350.add("stature", new Temp(-100.00, 93.0, 1), new Temp(93, 97.6, 2), new Temp(97.6, 101.2, 3), new Temp(101.2, 105.5, 4), new Temp(105.5, 1000.00, 5));
      physical350.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("35_0", physical350);

      /** 4岁男 **/
      Physical physical41 = new Physical();
      physical41.add("run10", new Temp(1000.00, 10.10, 1), new Temp(10.10, 08.50, 2), new Temp(08.50, 07.60, 3), new Temp(07.60, 06.90, 4), new Temp(06.90, -100.00, 5));
      physical41.add("jump", new Temp(-100.00, 47.00, 1), new Temp(47.00, 65.00, 2), new Temp(65.00, 80.00, 3), new Temp(80.00, 95.00, 4), new Temp(95.00, 1000.00, 5));
      physical41.add("throwTennis", new Temp(-100.00, 03.00, 1), new Temp(03.00, 04.00, 2), new Temp(04.00, 05.00, 3), new Temp(05.00, 06.00, 4), new Temp(06.00, 1000.00, 5));
      physical41.add("doubleJump", new Temp(1000.00, 13.10, 1), new Temp(13.10, 09.10, 2), new Temp(09.10, 07.00, 3), new Temp(07.00, 05.60, 4), new Temp(05.60, -100.00, 5));
      physical41.add("sitReach", new Temp(-100.00, 04.50, 1), new Temp(04.50, 08.50, 2), new Temp(08.50, 11.50, 3), new Temp(11.50, 14.90, 4), new Temp(14.90, 1000.00, 5));
      physical41.add("balance", new Temp(1000.00, 21.50, 1), new Temp(21.50, 11.50, 2), new Temp(11.50, 07.30, 3), new Temp(07.30, 04.90, 4), new Temp(04.90, -100.00, 5));
      physical41.add("stature", new Temp(-100.00, 97.5, 1), new Temp(97.5, 102, 2), new Temp(102, 105.5, 3), new Temp(105.5, 110.4, 4), new Temp(110.4, 1000.00, 5));
      physical41.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("4_1", physical41);
      /** 4岁女 **/
      Physical physical40 = new Physical();
      physical40.add("run10", new Temp(1000.00, 10.80, 1), new Temp(10.80, 09.00, 2), new Temp(09.00, 08.00, 3), new Temp(08.00, 07.20, 4), new Temp(07.20, -100.00, 5));
      physical40.add("jump", new Temp(-100.00, 44.00, 1), new Temp(44.00, 60.00, 2), new Temp(60.00, 74.00, 3), new Temp(74.00, 89.00, 4), new Temp(89.00, 1000.00, 5));
      physical40.add("throwTennis", new Temp(-100.00, 02.50, 1), new Temp(02.50, 03.50, 2), new Temp(03.50, 04.50, 3), new Temp(04.50, 05.00, 4), new Temp(05.00, 1000.00, 5));
      physical40.add("doubleJump", new Temp(1000.00, 13.40, 1), new Temp(13.40, 09.50, 2), new Temp(09.50, 07.30, 3), new Temp(07.30, 05.90, 4), new Temp(05.90, -100.00, 5));
      physical40.add("sitReach", new Temp(-100.00, 06.00, 1), new Temp(06.00, 10.00, 2), new Temp(10.00, 13.00, 3), new Temp(13.00, 15.90, 4), new Temp(15.90, 1000.00, 5));
      physical40.add("balance", new Temp(1000.00, 22.50, 1), new Temp(22.50, 12.20, 2), new Temp(12.20, 08.10, 3), new Temp(08.10, 05.30, 4), new Temp(05.30, -100.00, 5));
      physical40.add("stature", new Temp(-100.00, 96.6, 1), new Temp(96.6, 101, 2), new Temp(101, 104.5, 3), new Temp(104.5, 108.9, 4), new Temp(108.9, 1000.00, 5));
      physical40.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("4_0", physical40);

      /** 4.5岁男 **/
      Physical physical451 = new Physical();
      physical451.add("run10", new Temp(1000.00, 09.70, 1), new Temp(09.70, 08.00, 2), new Temp(08.00, 07.20, 3), new Temp(07.20, 06.70, 4), new Temp(06.70, -100.00, 5));
      physical451.add("jump", new Temp(-100.00, 55.00, 1), new Temp(55.00, 73.00, 2), new Temp(73.00, 89.00, 3), new Temp(89.00, 102.0, 4), new Temp(102.0, 1000.00, 5));
      physical451.add("throwTennis", new Temp(-100.00, 03.00, 1), new Temp(03.00, 04.50, 2), new Temp(04.50, 06.50, 3), new Temp(06.50, 08.00, 4), new Temp(08.00, 1000.00, 5));
      physical451.add("doubleJump", new Temp(1000.00, 11.20, 1), new Temp(11.20, 08.10, 2), new Temp(08.10, 06.40, 3), new Temp(06.40, 05.30, 4), new Temp(05.30, -100.00, 5));
      physical451.add("sitReach", new Temp(-100.00, 04.20, 1), new Temp(04.20, 08.00, 2), new Temp(08.00, 11.00, 3), new Temp(11.00, 14.40, 4), new Temp(14.40, 1000.00, 5));
      physical451.add("balance", new Temp(1000.00, 17.80, 1), new Temp(17.80, 09.60, 2), new Temp(09.60, 06.20, 3), new Temp(06.20, 04.30, 4), new Temp(04.30, -100.00, 5));
      physical451.add("stature", new Temp(-100.00, 100, 1), new Temp(100, 104.7, 2), new Temp(104.7, 108.5, 3), new Temp(108.5, 113.1, 4), new Temp(113.1, 1000.00, 5));
      physical451.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("45_1", physical451);
      /** 4.5岁女 **/
      Physical physical450 = new Physical();
      physical450.add("run10", new Temp(1000.00, 10.20, 1), new Temp(10.20, 08.50, 2), new Temp(08.50, 07.60, 3), new Temp(07.60, 07.00, 4), new Temp(07.00, -100.00, 5));
      physical450.add("jump", new Temp(-100.00, 50.00, 1), new Temp(50.00, 68.00, 2), new Temp(68.00, 81.00, 3), new Temp(81.00, 96.00, 4), new Temp(96.00, 1000.00, 5));
      physical450.add("throwTennis", new Temp(-100.00, 02.50, 1), new Temp(02.50, 03.50, 2), new Temp(03.50, 04.50, 3), new Temp(04.50, 05.50, 4), new Temp(05.50, 1000.00, 5));
      physical450.add("doubleJump", new Temp(1000.00, 11.90, 1), new Temp(11.90, 08.50, 2), new Temp(08.50, 06.70, 3), new Temp(06.70, 05.50, 4), new Temp(05.50, -100.00, 5));
      physical450.add("sitReach", new Temp(-100.00, 06.00, 1), new Temp(06.00, 10.00, 2), new Temp(10.00, 13.00, 3), new Temp(13.00, 16.00, 4), new Temp(16.00, 1000.00, 5));
      physical450.add("balance", new Temp(1000.00, 18.60, 1), new Temp(18.60, 10.10, 2), new Temp(10.10, 06.90, 3), new Temp(06.90, 04.70, 4), new Temp(04.70, -100.00, 5));
      physical450.add("stature", new Temp(-100.00, 99, 1), new Temp(99, 103.7, 2), new Temp(103.7, 107.4, 3), new Temp(107.4, 111.9, 4), new Temp(111.9, 1000.00, 5));
      physical450.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("45_0", physical450);

      /** 5岁男 **/
      Physical physical51 = new Physical();
      physical51.add("run10", new Temp(1000.00, 08.90, 1), new Temp(08.90, 07.60, 2), new Temp(07.60, 06.90, 3), new Temp(06.90, 06.40, 4), new Temp(06.40, -100.00, 5));
      physical51.add("jump", new Temp(-100.00, 65.00, 1), new Temp(65.00, 80.00, 2), new Temp(80.00, 96.00, 3), new Temp(96.00, 100.0, 4), new Temp(100.0, 1000.00, 5));
      physical51.add("throwTennis", new Temp(-100.00, 04.00, 1), new Temp(04.00, 05.50, 2), new Temp(05.50, 07.50, 3), new Temp(07.50, 09.00, 4), new Temp(09.00, 1000.00, 5));
      physical51.add("doubleJump", new Temp(1000.00, 09.80, 1), new Temp(09.80, 07.20, 2), new Temp(07.20, 05.90, 3), new Temp(05.90, 03.70, 4), new Temp(03.70, -100.00, 5));
      physical51.add("sitReach", new Temp(-100.00, 03.50, 1), new Temp(03.50, 06.70, 2), new Temp(06.70, 11.00, 3), new Temp(11.00, 14.40, 4), new Temp(14.40, 1000.00, 5));
      physical51.add("balance", new Temp(1000.00, 14.00, 1), new Temp(14.00, 07.80, 2), new Temp(07.80, 05.20, 3), new Temp(05.20, 03.70, 4), new Temp(03.70, -100.00, 5));
      physical51.add("stature", new Temp(-100.00, 103.1, 1), new Temp(103.1, 107.9, 2), new Temp(107.9, 112, 3), new Temp(112, 116.9, 4), new Temp(116.9, 1000.00, 5));
      physical51.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("5_1", physical51);
      /** 5岁女 **/
      Physical physical50 = new Physical();
      physical50.add("run10", new Temp(1000.00, 09.60, 1), new Temp(09.60, 08.00, 2), new Temp(08.00, 07.20, 3), new Temp(07.20, 06.70, 4), new Temp(06.70, -100.00, 5));
      physical50.add("jump", new Temp(-100.00, 60.00, 1), new Temp(60.00, 75.00, 2), new Temp(75.00, 89.00, 3), new Temp(89.00, 102.0, 4), new Temp(102.0, 1000.00, 5));
      physical50.add("throwTennis", new Temp(-100.00, 03.50, 1), new Temp(03.50, 04.50, 2), new Temp(04.50, 06.00, 3), new Temp(06.00, 08.50, 4), new Temp(08.50, 1000.00, 5));
      physical50.add("doubleJump", new Temp(1000.00, 10.00, 1), new Temp(10.00, 07.50, 2), new Temp(07.50, 06.10, 3), new Temp(06.10, 05.20, 4), new Temp(05.20, -100.00, 5));
      physical50.add("sitReach", new Temp(-100.00, 05.50, 1), new Temp(05.50, 09.70, 2), new Temp(09.70, 13.20, 3), new Temp(13.20, 16.60, 4), new Temp(16.60, 1000.00, 5));
      physical50.add("balance", new Temp(1000.00, 14.00, 1), new Temp(14.00, 08.20, 2), new Temp(08.20, 05.70, 3), new Temp(05.70, 04.10, 4), new Temp(04.10, -100.00, 5));
      physical50.add("stature", new Temp(-100.00, 102.0, 1), new Temp(102., 106.6, 2), new Temp(106.6, 110.5, 3), new Temp(110.5, 115.4, 4), new Temp(115.4, 1000.00, 5));
      physical50.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("5_0", physical50);

      /** 5.5岁男 **/
      Physical physical551 = new Physical();
      physical551.add("run10", new Temp(1000.00, 08.50, 1), new Temp(08.50, 07.30, 2), new Temp(07.30, 06.70, 3), new Temp(06.70, 06.20, 4), new Temp(06.20, -100.00, 5));
      physical551.add("jump", new Temp(-100.00, 70.00, 1), new Temp(70.00, 90.00, 2), new Temp(90.00, 103.0, 3), new Temp(103.0, 119.0, 4), new Temp(119.0, 1000.00, 5));
      physical551.add("throwTennis", new Temp(-100.00, 04.00, 1), new Temp(04.00, 06.00, 2), new Temp(06.00, 08.00, 3), new Temp(08.00, 10.00, 4), new Temp(10.00, 1000.00, 5));
      physical551.add("doubleJump", new Temp(1000.00, 09.30, 1), new Temp(09.30, 06.80, 2), new Temp(06.80, 05.60, 3), new Temp(05.60, 04.90, 4), new Temp(04.90, -100.00, 5));
      physical551.add("sitReach", new Temp(-100.00, 03.30, 1), new Temp(03.30, 07.60, 2), new Temp(07.60, 11.00, 3), new Temp(11.00, 14.40, 4), new Temp(14.40, 1000.00, 5));
      physical551.add("balance", new Temp(1000.00, 12.00, 1), new Temp(12.00, 06.70, 2), new Temp(06.70, 04.50, 3), new Temp(04.50, 03.30, 4), new Temp(03.30, -100.00, 5));
      physical551.add("stature", new Temp(-100.00, 104.6, 1), new Temp(104.6, 110.2, 2), new Temp(110.2, 114.7, 3), new Temp(114.7, 119.7, 4), new Temp(119.7, 1000.00, 5));
      physical551.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("55_1", physical551);
      /** 5.5岁女 **/
      Physical physical550 = new Physical();
      physical550.add("run10", new Temp(1000.00, 09.00, 1), new Temp(09.00, 07.60, 2), new Temp(07.60, 06.90, 3), new Temp(06.90, 06.40, 4), new Temp(06.40, -100.00, 5));
      physical550.add("jump", new Temp(-100.00, 66.00, 1), new Temp(66.00, 82.00, 2), new Temp(82.00, 96.00, 3), new Temp(96.00, 109.0, 4), new Temp(109.0, 1000.00, 5));
      physical550.add("throwTennis", new Temp(-100.00, 03.50, 1), new Temp(03.50, 05.00, 2), new Temp(05.00, 06.50, 3), new Temp(06.50, 08.50, 4), new Temp(08.50, 1000.00, 5));
      physical550.add("doubleJump", new Temp(1000.00, 09.30, 1), new Temp(09.30, 06.80, 2), new Temp(06.80, 05.60, 3), new Temp(05.60, 04.90, 4), new Temp(04.90, -100.00, 5));
      physical550.add("sitReach", new Temp(-100.00, 03.30, 1), new Temp(03.30, 07.80, 2), new Temp(07.80, 11.00, 3), new Temp(11.00, 14.40, 4), new Temp(14.40, 1000.00, 5));
      physical550.add("balance", new Temp(1000.00, 12.00, 1), new Temp(12.00, 06.70, 2), new Temp(06.70, 04.50, 3), new Temp(04.50, 03.30, 4), new Temp(03.30, -100.00, 5));
      physical550.add("stature", new Temp(-100.00, 104.5, 1), new Temp(104.5, 109.3, 2), new Temp(109.3, 113.5, 3), new Temp(113.5, 118.4, 4), new Temp(118.4, 1000.00, 5));
      physical550.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("55_0", physical550);

      /** 6岁男 **/
      Physical physical61 = new Physical();
      physical61.add("run10", new Temp(1000.00, 07.90, 1), new Temp(07.90, 06.80, 2), new Temp(06.80, 06.20, 3), new Temp(06.20, 05.80, 4), new Temp(05.80, -100.00, 5));
      physical61.add("jump", new Temp(-100.00, 78.00, 1), new Temp(78.00, 95.00, 2), new Temp(95.00, 111.0, 3), new Temp(111.0, 127.0, 4), new Temp(127.0, 1000.00, 5));
      physical61.add("throwTennis", new Temp(-100.00, 04.50, 1), new Temp(04.50, 07.00, 2), new Temp(07.00, 09.50, 3), new Temp(09.50, 12.00, 4), new Temp(12.00, 1000.00, 5));
      physical61.add("doubleJump", new Temp(1000.00, 08.20, 1), new Temp(08.20, 06.10, 2), new Temp(06.10, 05.10, 3), new Temp(05.10, 04.40, 4), new Temp(04.40, -100.00, 5));
      physical61.add("sitReach", new Temp(-100.00, 03.20, 1), new Temp(03.20, 07.10, 2), new Temp(07.10, 10.50, 3), new Temp(10.50, 14.40, 4), new Temp(14.40, 1000.00, 5));
      physical61.add("balance", new Temp(1000.00, 09.30, 1), new Temp(09.30, 05.30, 2), new Temp(05.30, 03.70, 3), new Temp(03.70, 02.70, 4), new Temp(02.70, -100.00, 5));
      physical61.add("stature", new Temp(-100.00, 108.2, 1), new Temp(108.2, 113.3, 2), new Temp(113.3, 117.8, 3), new Temp(117.8, 123.0, 4), new Temp(123, 1000.00, 5));
      physical61.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("6_1", physical61);
      /** 6岁女 **/
      Physical physical60 = new Physical();
      physical60.add("run10", new Temp(1000.00, 08.50, 1), new Temp(08.50, 07.20, 2), new Temp(07.20, 06.50, 3), new Temp(06.50, 06.10, 4), new Temp(06.10, -100.00, 5));
      physical60.add("jump", new Temp(-100.00, 71.00, 1), new Temp(71.00, 87.00, 2), new Temp(87.00, 101.0, 3), new Temp(101.0, 116.0, 4), new Temp(116.0, 1000.00, 5));
      physical60.add("throwTennis", new Temp(-100.00, 03.50, 1), new Temp(03.50, 05.00, 2), new Temp(05.00, 06.50, 3), new Temp(06.50, 08.00, 4), new Temp(08.00, 1000.00, 5));
      physical60.add("doubleJump", new Temp(1000.00, 08.30, 1), new Temp(08.30, 06.20, 2), new Temp(06.20, 05.20, 3), new Temp(05.20, 04.60, 4), new Temp(04.60, -100.00, 5));
      physical60.add("sitReach", new Temp(-100.00, 05.40, 1), new Temp(05.40, 09.60, 2), new Temp(09.60, 13.00, 3), new Temp(13.00, 16.70, 4), new Temp(16.70, 1000.00, 5));
      physical60.add("balance", new Temp(1000.00, 10.70, 1), new Temp(10.70, 06.10, 2), new Temp(06.10, 42.00, 3), new Temp(42.00, 03.00, 4), new Temp(03.00, -100.00, 5));
      physical60.add("stature", new Temp(-100.00, 107, 1), new Temp(107, 112, 2), new Temp(112, 116.7, 3), new Temp(116.7, 121.7, 4), new Temp(121.7, 1000.00, 5));
      physical60.add("weight", new Temp(1, 0.50, 1), new Temp(0.50, 0.30, 2), new Temp(0.30, 0.20, 3), new Temp(0.20, 0.10, 4), new Temp(.10, 0., 5));
      put("6_0", physical60);
    }
  };

  public static String getAge(Date birth) {
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    calendar.setTime(birth);
    int yearBirth = calendar.get(Calendar.YEAR);
    int monthBirth = calendar.get(Calendar.MONTH);
    int dayBirth = calendar.get(Calendar.DAY_OF_MONTH);
    int age = year - yearBirth;
    String temp = "";
    if (month <= monthBirth) {
      if (month == monthBirth) {
        if (day < dayBirth) {
          age--;
          temp = "5";
        }
      } else {
        if (monthBirth + 6 - 12 >= month) {
          if (monthBirth + 6 - 12 == month) {
            if (day <= dayBirth) {
              temp = "5";
            }
          } else {
            temp = "5";
          }
        }
        age--;
      }
    }
    //三岁以下的，按三岁算
    if (age < 3) {
      age = 3;
    }
    return age + temp;
  }

  public static Map<String, PushInfo> smsNotes = new HashMap<String, PushInfo>() {
    private static final long serialVersionUID = 1L;
    {
      put("code_sms", new PushInfo("SMS_77245049", "{\"code\":\"%s\"}"));
      put("bind_mobile", new PushInfo(1, MessageType.system, "messageDetail", "messageId", "绑定手机成功", "恭喜，你已成功绑定手机。"));
      put("apply_success", new PushInfo(1, MessageType.system, "messageDetail", "messageId", "报名成功", "恭喜你，%s，报名成功。"));
      put("invite_success", new PushInfo(1, MessageType.system, "messageDetail", "messageId", "被邀请查看幼儿信息", "你被邀请查看%s的体智能信息。"));
    }
  };

  public static String getUserIdentity(Integer identity) {
    if (null == identity) {
      return null;
    }
    UserIdentity[] identities = UserIdentity.values();
    for (UserIdentity userIdentity : identities) {
      if (userIdentity.getIdentity().intValue() == identity) {
        return userIdentity.getCaption();
      }
    }
    return null;
  }

  public enum CouponIds {
    insurance_ticket_z(1000l, "赠送保险券");

    private Long couponId;
    private String caption;

    private CouponIds(Long couponId, String caption) {
      this.couponId = couponId;
      this.caption = caption;
    }

    public Long getCouponId() {
      return couponId;
    }

    public String getCaption() {
      return caption;
    }

  }

  public enum UserService {
    uniform_ticket(1000l, "球服券"), ball_ticket(1001l, "约球券"), text_popularize_ticket(1002l, "文化推广券(图文)"), video_popularize_ticket(1003l, "文化推广券(视频)"), coach_ticket(1004l, "教练券"), insurance_ticket(1005l, "保险券"), insurance_ticket_z(9999l, "保险券[赠]");

    private Long serviceId;
    private String caption;

    private UserService(Long serviceId, String caption) {
      this.serviceId = serviceId;
      this.caption = caption;
    }

    public Long getServiceId() {
      return serviceId;
    }

    public String getCaption() {
      return caption;
    }
  }

  public enum MessageType {
    system(1, "系统消息"), game(2, "约球消息"), team(3, "球队消息"), court(4, "球场消息");

    private Integer type;
    private String caption;

    private MessageType(Integer type, String caption) {
      this.caption = caption;
      this.type = type;
    }

    public Integer getType() {
      return this.type;
    }

    public String getCaption() {
      return this.caption;
    }
  }

  public enum DocumentType {
    banner(1, "轮播图"), article(2, "文章");

    private Integer type;
    private String caption;

    private DocumentType(Integer type, String caption) {
      this.type = type;
      this.caption = caption;
    }

    public Integer getType() {
      return type;
    }

    public String getCaption() {
      return caption;
    }
  }

  public enum UserIdentity {
    background(0, "后台人员"), member(1, "会员");

    private Integer identity;
    private String caption;

    private UserIdentity(Integer identity, String caption) {
      this.identity = identity;
      this.caption = caption;
    }

    public Integer getIdentity() {
      return this.identity;
    }

    public String getCaption() {
      return this.caption;
    }
  }

  public enum SmsTag {
    registration(-1, "注册"), findpassword(-2, "找回密码"), bindMobile(1, "绑定手机"), apply(2, "培训项目报名"), coachLogin(3, "教练登录");

    private Integer tag;
    private String caption;

    private SmsTag(Integer tag, String caption) {
      this.tag = tag;
      this.caption = caption;
    }

    public Integer getTag() {
      return this.tag;
    }

    public String getCaption() {
      return this.caption;
    }
  }

  public static Map<Integer, String> week = new HashMap<Integer, String>() {
    private static final long serialVersionUID = 1L;
    {
      put(1, "周一");
      put(2, "周二");
      put(3, "周三");
      put(4, "周四");
      put(5, "周五");
      put(6, "周六");
      put(7, "周日");
    }
  };

  public static ParentType geParentType(int type) {
    ParentType[] types = ParentType.values();
    for (ParentType parentType : types) {
      if (parentType.getType() == type) {
        return parentType;
      }
    }
    return null;
  }

  public enum ParentType {
    father(1, "爸爸", "儿子", "女儿"), monther(2, "妈妈", "儿子", "女儿"), grandpa(3, "爷爷", "孙子", "孙女"), grandma(4, "奶奶", "孙子", "孙女"), grandfather(5, "外公", "外孙", "外孙女"), grandmother(6, "外婆", "外孙", "外孙女"), other(7, "其他", "长辈", "长辈");

    private int type;
    private String name;
    private String relation1;
    private String relation2;

    private ParentType(int type, String name, String relation1, String relation2) {
      this.type = type;
      this.name = name;
      this.relation1 = relation1;
      this.relation2 = relation2;
    }

    public int getType() {
      return type;
    }

    public String getName() {
      return name;
    }

    public String getRelation1() {
      return relation1;
    }

    public String getRelation2() {
      return relation2;
    }
  }
}
