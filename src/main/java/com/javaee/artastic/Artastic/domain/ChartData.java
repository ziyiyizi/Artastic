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
		for(Object[] objects : data1) {
			Map<String, Object> map1 = new HashMap<>();
			for(int i = 1; i < 13; i++) {
				map1.put("clicknum", 0);
				map1.put("clickmonth", convertToMonth(String.valueOf(i)));
			}
			for(int i = 0; i < objects.length; i+=2) {
				map1.put("clicknum", Integer.valueOf(String.valueOf(objects[i])));
				map1.put("clickmonth", convertToMonth(String.valueOf(objects[i+1])));
			}
			this.data1.add(map1);
		}

	}
	public List<Map<String, Object>> getData2() {
		return data2;
	}
	public void setData2(List<Object[]> data2) {
		this.data2 = new ArrayList<>();
		for(Object[] objects : data2) {
			Map<String, Object> map2 = new HashMap<>();
			for(int i = 0; i < objects.length; i+=2) {
				map2.put("clicknum", Integer.valueOf(String.valueOf(objects[i])));
				map2.put("sex", String.valueOf(objects[i+1]));
			}
			this.data2.add(map2);
		}
		
	}
	
	
}
