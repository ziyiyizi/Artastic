package com.javaee.artastic.Artastic.domain;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class ArtWorkDetails {
	private int artistId;
	private String artistName;
	private int artworkId;
	private String artworkName;
	private Timestamp date;
	private List<Map<String, Object>> likes;
	private int frenzy;
	private List<Map<String, Object>> comments;
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
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
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
	public List<Map<String, Object>> getLikes() {
		return likes;
	}
	public void setLikes(List<Map<String, Object>> likes) {
		this.likes = likes;
	}
	public List<Map<String, Object>> getComments() {
		return comments;
	}
	public void setComments(List<Map<String, Object>> comments) {
		this.comments = comments;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
