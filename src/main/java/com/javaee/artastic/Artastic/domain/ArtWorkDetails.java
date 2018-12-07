package com.javaee.artastic.Artastic.domain;

import java.sql.Timestamp;
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
	private List<Map<String, String>> likes;
	private int frenzy;
	private List<Map<String, String>> comments;
	private List<String> tags;
	private String description;
	private String fileURL;
	
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
	public List<Map<String, String>> getLikes() {
		return likes;
	}
	public void setLikes(List<Map<String, Object>> likes) {
		List<Map<String, String>> likeList = new ArrayList<>();
		for(Map<String, Object> map1 : likes) {
			Map<String, String> map2 = new HashMap<>();
			for(String key : map1.keySet()) {
				
				map2.put(key, map1.get(key).toString());
				
			}
			likeList.add(map2);
		}
		this.likes = likeList;
	}
	public List<Map<String, String>> getComments() {
		return comments;
	}
	public void setComments(List<Map<String, Object>> comments) {
		
		List<Map<String, String>> commentList = new ArrayList<>();
		for(Map<String, Object> map1 : comments) {
			Map<String, String> map2 = new HashMap<>();
			for(String key : map1.keySet()) {
				
				map2.put(key, map1.get(key).toString());
				
			}
			commentList.add(map2);
		}
		this.comments = commentList;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
