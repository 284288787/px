/**create by liuhua at 2018年2月1日 上午11:42:54**/
package com.booting.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Physical {

	private Map<String, List<Temp>> data = new HashMap<>();
	
	public void add(String item, Temp temp1, Temp temp2, Temp temp3, Temp temp4, Temp temp5){
		List<Temp> value = new ArrayList<>();
		value.add(temp1);
		value.add(temp2);
		value.add(temp3);
		value.add(temp4);
		value.add(temp5);
		data.put(item, value);
	}
	
	public int getScore(String item, double value){
		List<Temp> list = data.get(item);
		int s = 0;
		if (null != list) {
			for (Temp temp : list) {
				int v = temp.getScore(value);
				s += v;
			}
		}
		return s;
	}
}
