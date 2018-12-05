package com.javaee.artastic.Artastic.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.service.ArtworksService;

@Service
public class ArtworksServiceImpl implements ArtworksService{

	@Override
	public List<Artworks> findByArtistId(int artistId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Artworks findByArtworkId(int artworkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Artworks> findByArtworkName(String artworkName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findLikesList(int artworkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countLikes(int artworkId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> findCommentList(int artworkId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findTagList(int artworkId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
