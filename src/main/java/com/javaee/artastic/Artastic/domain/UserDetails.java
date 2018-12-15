package com.javaee.artastic.Artastic.domain;

import java.util.List;
import java.util.Map;

public class UserDetails {
	private int artistId;
	private String artistName;
	private String iconURL;
	private int frenzy;
	
	private int worknum;
	private String joinyear;
	private String description;
	private List<ArtWorkDetails> works;
	private List<Map<String, Object>> followers;
	private List<Map<String, Object>> following;
	private boolean follow;
	
	public boolean isFollow() {
		return follow;
	}
	public void setFollow(boolean follow) {
		this.follow = follow;
	}
	public String getJoinyear() {
		return joinyear;
	}
	public void setJoinyear(String joinyear) {
		this.joinyear = joinyear;
	}
	public int getArtistId() {
		return artistId;
	}
	public void setArtistId(int artistId) {
		this.artistId = artistId;
	}
	public int getWorknum() {
		return worknum;
	}
	public void setWorknum(int worknum) {
		this.worknum = worknum;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<ArtWorkDetails> getWorks() {
		return works;
	}
	public void setWorks(List<ArtWorkDetails> works) {
		this.works = works;
	}
	public List<Map<String, Object>> getFollowers() {
		return followers;
	}
	public void setFollowers(List<Map<String, Object>> followers) {
		this.followers = followers;
	}
	public List<Map<String, Object>> getFollowing() {
		return following;
	}
	public void setFollowing(List<Map<String, Object>> following) {
		this.following = following;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getIconURL() {
		return iconURL;
	}
	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}
	public int getFrenzy() {
		return frenzy;
	}
	public void setFrenzy(int frenzy) {
		this.frenzy = frenzy;
	}
	
	

}
