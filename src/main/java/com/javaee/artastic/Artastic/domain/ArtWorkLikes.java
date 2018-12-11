package com.javaee.artastic.Artastic.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtWorkLikes {
	private List<Map<String, String>> likerslist;
	private List<Map<String, String>> comments;
	
	public List<Map<String, String>> getComments() {
		return comments;
	}

	public void setComments(List<Map<String, Object>> comments) {
		
		List<Map<String, String>> CommentList = new ArrayList<>();
		for(Map<String, Object> map1 : comments) {
			Map<String, String> map2 = new HashMap<>();
			for(String key : map1.keySet()) {
				Object value = map1.get(key);
				if(value != null) {
					map2.put(key, value.toString());
				}

			}
			CommentList.add(map2);
		}
		
		this.comments = CommentList;
	}

	public List<Map<String, String>> getLikerslist() {
		return likerslist;
	}
	
	public void setLikerslist(List<Map<String, Object>> likerslist) {
		List<Map<String, String>> likeList = new ArrayList<>();
		for(Map<String, Object> map1 : likerslist) {
			Map<String, String> map2 = new HashMap<>();
			for(String key : map1.keySet()) {
				Object value = map1.get(key);
				if(value != null) {
					map2.put(key, value.toString());
				}

			}
			likeList.add(map2);
		}
		this.likerslist = likeList;
	}
}
