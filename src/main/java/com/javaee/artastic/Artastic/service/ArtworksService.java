package com.javaee.artastic.Artastic.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.domain.Clicks;
import com.javaee.artastic.Artastic.domain.Comments;
import com.javaee.artastic.Artastic.domain.Likes;

public interface ArtworksService {
	public List<Artworks> findByArtistId(int artistId);
	public Artworks findByArtworkId(int artworkId);
	public List<Artworks> findByArtworkName(String artworkName);
	
	public List<Map<String, Object>> findLikesList(int artworkId);
	public int countLikes(int artworkId);
	public List<Map<String, Object>> findCommentList(int artworkId);
	public List<String> findTagList(int artworkId);
	
	public ArtWorkDetails getArtworkDetails(int artworkId, int clientId);
	public ArtWorkDetails getArtworkDetails(int artworkId);
	public ArtWorkDetails getArtworkDetails(Artworks artworks);
	public ArtWorkLikes getArtworkLikes(int artworkId);
	public List<Artworks> findAll();
	public Page<Artworks> findAll(Pageable pageable);
	public Page<Integer> findFollowArtworks(int clientId, Pageable pageable);
	public Page<Integer> findAllTimeSort(Pageable pageable);
	public List<Integer> findAllRandSort();
	public Page<Integer> findAllRandSort(Pageable pageable);
	public Page<Integer> findAllLikeSort(Pageable pageable);
	public Page<Integer> findBySearchKey(String key, Pageable pageable);
	public Page<Integer> findBySearchAll(String key, Pageable pageable);
	public Page<Integer> findByUserNameAndTag(String userName, String tagName, Pageable pageable);
	public Page<Integer> findByUserNameAndWorkName(String userName, String artworkName, Pageable pageable);
	public Page<Integer> findByWorkNameAndTag(String artworkName, String tagName, Pageable pageable);
	public Page<Integer> findByAllKey(String userName, String artworkName, String tagName, Pageable pageable);
	
	public Page<Integer> findUserLikes(int userId, Pageable pageable);
	
	public Artworks saveArtwork(Artworks artworks);
	public Clicks saveClick(Clicks clicks);
	public boolean isLike(int userId, int artworkId);
	public Likes saveLike(Likes likes);
	public Comments saveComment(Comments comments);
	
	public List<Object[]> countClicksPerMonth(int artworkId);
	public List<Object[]> countClicksBySex(int artworkId);
}
