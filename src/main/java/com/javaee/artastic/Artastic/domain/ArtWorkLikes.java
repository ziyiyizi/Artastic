package com.javaee.artastic.Artastic.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtWorkLikes {
	private List<Map<String, String>> likerslist;
	
	public List<Map<String, String>> getLikerslist() {
		return likerslist;
	}
	
	public void setLikerslist(List<Map<String, Object>> likerslist) {
		List<Map<String, String>> likeList = new ArrayList<>();
		for(Map<String, Object> map1 : likerslist) {
			Map<String, String> map2 = new HashMap<>();
			for(String key : map1.keySet()) {
				
				map2.put(key, map1.get(key).toString());
				
			}
			map2.put("userIcon", null);
			likeList.add(map2);
		}
		this.likerslist = likeList;
	}
}
