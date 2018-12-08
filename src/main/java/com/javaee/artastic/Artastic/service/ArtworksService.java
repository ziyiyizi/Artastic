package com.javaee.artastic.Artastic.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
import com.javaee.artastic.Artastic.domain.Artworks;

public interface ArtworksService {
	public List<Artworks> findByArtistId(int artistId);
	public Artworks findByArtworkId(int artworkId);
	public List<Artworks> findByArtworkName(String artworkName);
	
	public List<Map<String, Object>> findLikesList(int artworkId);
	public int countLikes(int artworkId);
	public List<Map<String, Object>> findCommentList(int artworkId);
	public List<String> findTagList(int artworkId);
	public String findDescriptionByArtworkId(int artworkId);
	
	public ArtWorkDetails getArtworkDetails(int artworkId);
	public ArtWorkDetails getArtworkDetails(Artworks artworks);
	public ArtWorkLikes getArtworkLikes(int artworkId);
	public List<Artworks> findAll();
	public Artworks saveArtwork(Artworks artworks);
}
