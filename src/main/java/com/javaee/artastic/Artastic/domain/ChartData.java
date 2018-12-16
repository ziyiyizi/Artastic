package com.javaee.artastic.Artastic.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.bcel.Const;

public class ChartData {

	private List<Map<String, Object>> data1;
	private List<Map<String, Object>> data2;
	
	public String convertToMonth(String number) {
		final String[] months = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sept", "Oct", "Nov", "Dec"};
		return months[Integer.parseInt(number)];
	}
	
	public List<Map<String, Object>> getData1() {
		return data1;
	}
	public void setData1(List<Object[]> data1) {
		this.data1 = new ArrayList<>();
		for(int i = 1; i < 13; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("clicknum", 0);
			map.put("clickmonth", convertToMonth(String.valueOf(i)));
			this.data1.add(map);
		}
		
		for(Object[] objects : data1) {
			Map<String, Object> map1 = new HashMap<>();
			int month = Integer.parseInt(String.valueOf(objects[1]));

			map1.put("clicknum", Integer.valueOf(String.valueOf(objects[0])));
			map1.put("clickmonth", convertToMonth(String.valueOf(objects[1])));

			this.data1.set(month-1, map1);
		}

	}
	public List<Map<String, Object>> getData2() {
		return data2;
	}
	public void setData2(List<Object[]> data2) {
		this.data2 = new ArrayList<>();
		
		Map<String, Object> map = new HashMap<>();
		map.put("clicknum", 0);
		map.put("sex", "boy");
		
		Map<String, Object> map1 = new HashMap<>();
		map1.put("clicknum", 0);
		map1.put("sex", "girl");
		
		this.data2.add(map);
		this.data2.add(map1);
		
		for(Object[] objects : data2) {
			Map<String, Object> map2 = new HashMap<>();
			map2.put("clicknum", Integer.valueOf(String.valueOf(objects[0])));
			map2.put("sex", String.valueOf(objects[1]));
			if(String.valueOf(objects[1]).equals("boy")) {
				this.data2.set(0, map2);
			} else {
				this.data2.set(1, map2);
			}

		}
		
	}
	
	
}
