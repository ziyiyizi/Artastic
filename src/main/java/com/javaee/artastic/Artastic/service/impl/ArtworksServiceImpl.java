package com.javaee.artastic.Artastic.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaee.artastic.Artastic.dao.ArtdataDao;
import com.javaee.artastic.Artastic.dao.ArtworksDao;
import com.javaee.artastic.Artastic.dao.CommentsDao;
import com.javaee.artastic.Artastic.dao.LikesDao;
import com.javaee.artastic.Artastic.dao.TagsDao;
import com.javaee.artastic.Artastic.dao.UsersDao;
import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.service.ArtworksService;
import com.javaee.artastic.Artastic.service.UsersService;

@Service
public class ArtworksServiceImpl implements ArtworksService{

	@Autowired
	private ArtworksDao artworksDao;
	
	@Autowired
	private LikesDao likesDao;
	
	@Autowired
	private CommentsDao commentDao;
	
	@Autowired
	private TagsDao tagsDao;
	
	@Autowired
	private ArtdataDao artdataDao;
	
	@Autowired
	private UsersDao usersDao;
	
	@Override
	public List<Artworks> findByArtistId(int artistId) {
		// TODO Auto-generated method stub
		return artworksDao.findByArtistId(artistId);
	}

	@Override
	public Artworks findByArtworkId(int artworkId) {
		// TODO Auto-generated method stub
		return artworksDao.findByArtworkId(artworkId);
	}

	@Override
	public List<Artworks> findByArtworkName(String artworkName) {
		// TODO Auto-generated method stub
		return artworksDao.findByArtworkName(artworkName);
	}

	@Override
	public List<Map<String, Object>> findLikesList(int artworkId) {
		// TODO Auto-generated method stub
		return likesDao.findLikesList(artworkId);
	}

	@Override
	public int countLikes(int artworkId) {
		// TODO Auto-generated method stub
		return likesDao.countLikes(artworkId);
	}

	@Override
	public List<Map<String, Object>> findCommentList(int artworkId) {
		// TODO Auto-generated method stub
		return commentDao.findCommentList(artworkId);
	}

	@Override
	public List<String> findTagList(int artworkId) {
		// TODO Auto-generated method stub
		return tagsDao.findTagList(artworkId);
	}

	@Override
	public String findDescriptionByArtworkId(int artworkId) {
		// TODO Auto-generated method stub
		return artworksDao.findDesciptionByArtworkId(artworkId);
	}

	@Override
	public ArtWorkDetails getArtworkDetails(int artworkId) {
		// TODO Auto-generated method stub
		ArtWorkDetails artWorkDetails = new ArtWorkDetails();
		Artworks artworks = findByArtworkId(artworkId);
		int userId = artworks.getArtistId();
		artWorkDetails.setArtworkId(artworkId);
		artWorkDetails.setArtworkName(artworks.getArtworkName());
		artWorkDetails.setArtistId(userId);
		artWorkDetails.setArtistName(usersDao.findUserNameByUserId(userId));
		artWorkDetails.setDate(artworks.getUploadtime());
		artWorkDetails.setComments(findCommentList(artworkId));
		artWorkDetails.setFrenzy(countLikes(artworkId));
		artWorkDetails.setLikes(findLikesList(artworkId));
		artWorkDetails.setTags(findTagList(artworkId));
		artWorkDetails.setDescription(findDescriptionByArtworkId(artworkId));
		artWorkDetails.setFileURL(artdataDao.findUrlByArtworkId(artworkId));
		return artWorkDetails;
	}
	
	@Override
	public ArtWorkDetails getArtworkDetails(Artworks artworks) {
		// TODO Auto-generated method stub
		ArtWorkDetails artWorkDetails = new ArtWorkDetails();
		int artworkId = artworks.getArtworkId();
		int userId = artworks.getArtistId();
		artWorkDetails.setArtworkId(artworkId);
		artWorkDetails.setArtworkName(artworks.getArtworkName());
		artWorkDetails.setArtistId(userId);
		artWorkDetails.setArtistName(usersDao.findUserNameByUserId(userId));
		artWorkDetails.setDate(artworks.getUploadtime());
		artWorkDetails.setComments(findCommentList(artworkId));
		artWorkDetails.setFrenzy(countLikes(artworkId));
		artWorkDetails.setLikes(findLikesList(artworkId));
		artWorkDetails.setTags(findTagList(artworkId));
		artWorkDetails.setDescription(findDescriptionByArtworkId(artworkId));
		artWorkDetails.setFileURL(artdataDao.findUrlByArtworkId(artworkId));
		return artWorkDetails;
	}

	@Override
	public List<Artworks> findAll() {
		// TODO Auto-generated method stub
		return artworksDao.findAll();
	}

	@Override
	public Artworks saveArtwork(Artworks artworks) {
		// TODO Auto-generated method stub
		return artworksDao.save(artworks);
		
	}


	
}
