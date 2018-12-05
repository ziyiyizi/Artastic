package com.javaee.artastic.Artastic.domain;

import java.sql.Timestamp;
import java.util.List;

public class ArtWorkDetails {
	private int artistId;
	private String artistName;
	private int artworkId;
	private String artworkName;
	private Timestamp date;
	private List<String> likes;
	private int frenzy;
	private List<String> comments;
	private List<String> tags;
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
	public List<String> getLikes() {
		return likes;
	}
	public void setLikes(List<String> likes) {
		this.likes = likes;
	}
	public int getFrenzy() {
		return frenzy;
	}
	public void setFrenzy(int frenzy) {
		this.frenzy = frenzy;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	
}
