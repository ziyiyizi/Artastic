package com.javaee.artastic.Artastic.domain;

import java.util.List;

public class ArtworksList {

	private List<ArtWorkDetails> posts;
	
	private ArtWorkDetails post;
	
	private ChartData chartdata;
	
	private List<UserDetails> members;
	
	private UserDetails member;
	
	private List<Notification> notification;
	
	private List<String> values;
	
	private int notifyNum;
	
	private Weekly weekly;
	
	private boolean error;
	
	private String errorMsg;
	
	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Weekly getWeekly() {
		return weekly;
	}

	public void setWeekly(Weekly weekly) {
		this.weekly = weekly;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public int getNotifyNum() {
		return notifyNum;
	}

	public void setNotifyNum(int notifyNum) {
		this.notifyNum = notifyNum;
	}

	public List<Notification> getNotification() {
		return notification;
	}

	public void setNotification(List<Notification> notification) {
		this.notification = notification;
	}

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
