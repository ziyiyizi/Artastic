package com.javaee.artastic.Artastic.domain;

import java.util.List;

public class ArtworksList {

	private List<ArtWorkDetails> posts;
	
	private ArtWorkDetails post;

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
