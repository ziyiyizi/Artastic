package com.javaee.artastic.Artastic.domain;

import java.util.List;

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
