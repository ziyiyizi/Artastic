package com.javaee.artastic.Artastic.service;

import java.util.List;

import com.javaee.artastic.Artastic.domain.Artworks;

public interface ArtworksService {
	public List<Artworks> findByArtistId(int artistId);
	public Artworks findByArtworkId(int artworkId);
	public List<Artworks> findByArtworkName(String artworkName);
	
	public List<String> findLikesList(int artworkId);
	public int countLikes(int artworkId);
	public List<String> findCommentList(int artworkId);
	public List<String> findTagList(int artworkId);
}
