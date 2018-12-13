package com.javaee.artastic.Artastic.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtWorkDetails {
	private int artistId;
	private String artistName;
	private int artworkId;
	private String artworkName;
	private String date;
	private int frenzy;
	private List<String> tags;
	private String description;
	private String fileURL;
	private String iconURL;
	private List<Map<String, String>> likerslist;
	private List<Map<String, String>> comments;
	private boolean isLike;

	public boolean isLike() {
		return isLike;
	}

	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}

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
	
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public String getFileURL() {
		return fileURL;
	}
	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public int getArtworkId() {
		return artworkId;
	}
	public void setArtworkId(int artworkId) {
		this.artworkId = artworkId;
	}
	public String getArtworkName() {
		return artworkName;
	}
	public void setArtworkName(String artworkName) {
		this.artworkName = artworkName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getFrenzy() {
		return frenzy;
	}
	public void setFrenzy(int frenzy) {
		this.frenzy = frenzy;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
