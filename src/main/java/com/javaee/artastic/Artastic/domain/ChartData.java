package com.javaee.artastic.Artastic.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartData {

	private List<Map<String, String>> data1;
	private List<Map<String, String>> data2;
	public List<Map<String, String>> getData1() {
		return data1;
	}
	public void setData1(List<Object[]> data1) {
		this.data1 = new ArrayList<>();
		for(Object[] objects : data1) {
			Map<String, String> map1 = new HashMap<>();
			for(int i = 0; i < objects.length; i+=2) {
				map1.put("clicknum", String.valueOf(objects[i]));
				map1.put("clickmonth", String.valueOf(objects[i+1]));
			}
			this.data1.add(map1);
		}

	}
	public List<Map<String, String>> getData2() {
		return data2;
	}
	public void setData2(List<Object[]> data2) {
		this.data2 = new ArrayList<>();
		for(Object[] objects : data2) {
			Map<String, String> map2 = new HashMap<>();
			for(int i = 0; i < objects.length; i+=2) {
				map2.put("clicknum", String.valueOf(objects[i]));
				map2.put("sex", String.valueOf(objects[i+1]));
			}
			this.data2.add(map2);
		}
		
	}
	
	
}
