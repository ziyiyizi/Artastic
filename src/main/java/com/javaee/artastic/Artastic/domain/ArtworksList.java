package com.javaee.artastic.Artastic.domain;

import java.util.List;

public class ArtworksList {

	private List<ArtWorkDetails> posts;
	
	private ArtWorkDetails post;
	
	private ChartData chartdata;
	
	private List<UserDetails> members;
	
	private UserDetails member;
	
	public UserDetails getMember() {
		return member;
	}

	public void setMember(UserDetails member) {
		this.member = member;
	}

	public List<UserDetails> getMembers() {
		return members;
	}

	public void setMembers(List<UserDetails> members) {
		this.members = members;
	}

	public ChartData getChartdata() {
		return chartdata;
	}

	public void setChartdata(ChartData chartdata) {
		this.chartdata = chartdata;
	}

	public ArtWorkDetails getPost() {
		return post;
	}

	public void setPost(ArtWorkDetails post) {
		this.post = post;
	}

	public List<ArtWorkDetails> getPosts() {
		return posts;
	}

	public void setPosts(List<ArtWorkDetails> posts) {
		this.posts = posts;
	}
	
}
