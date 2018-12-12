package com.javaee.artastic.Artastic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javaee.artastic.Artastic.dao.ArtdataDao;
import com.javaee.artastic.Artastic.dao.ArtworksDao;
import com.javaee.artastic.Artastic.dao.ClicksDao;
import com.javaee.artastic.Artastic.dao.CommentsDao;
import com.javaee.artastic.Artastic.dao.LikesDao;
import com.javaee.artastic.Artastic.dao.TagsDao;
import com.javaee.artastic.Artastic.dao.UsersDao;
import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.domain.Clicks;
import com.javaee.artastic.Artastic.domain.Comments;
import com.javaee.artastic.Artastic.domain.Likes;
import com.javaee.artastic.Artastic.domain.Tags;
import com.javaee.artastic.Artastic.service.ArtworksService;

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
	
	@Autowired
	private ClicksDao clicksDao;
	
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
		
		Map<String, Object> userNameAndIcon = usersDao.findNameAndIconByUserId(userId);
		artWorkDetails.setArtistName(userNameAndIcon.get("name").toString());
		artWorkDetails.setIconURL(userNameAndIcon.get("icon").toString());
		//获取头像
		artWorkDetails.setDate(artworks.getUploadtime().toString());
		artWorkDetails.setFrenzy(countLikes(artworkId));
		artWorkDetails.setTags(findTagList(artworkId));
		if(artWorkDetails.getTags().get(0).equals("")) {
			artWorkDetails.getTags().clear();
		}
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
		
		Map<String, Object> userNameAndIcon = usersDao.findNameAndIconByUserId(userId);
		artWorkDetails.setArtistName(userNameAndIcon.get("name").toString());
		artWorkDetails.setIconURL(userNameAndIcon.get("icon").toString());
		//获取头像
		artWorkDetails.setDate(artworks.getUploadtime().toString());
		artWorkDetails.setFrenzy(countLikes(artworkId));
		artWorkDetails.setTags(findTagList(artworkId));
		if(artWorkDetails.getTags().get(0).equals("")) {
			artWorkDetails.getTags().clear();
		}
		artWorkDetails.setDescription(findDescriptionByArtworkId(artworkId));
		artWorkDetails.setFileURL(artdataDao.findUrlByArtworkId(artworkId));
		return artWorkDetails;
	}

	@Override
	public ArtWorkLikes getArtworkLikes(int artworkId) {
		// TODO Auto-generated method stub
		ArtWorkLikes artWorkLikes = new ArtWorkLikes();
		artWorkLikes.setLikerslist(findLikesList(artworkId));
		artWorkLikes.setComments(commentDao.findCommentList(artworkId));
		return artWorkLikes;
	}

	@Override
	public Clicks saveClick(Clicks clicks) {
		// TODO Auto-generated method stub
		return clicksDao.save(clicks);
	}

	@Override
	public Likes saveLike(Likes likes) {
		// TODO Auto-generated method stub
		return likesDao.save(likes);
	}

	@Override
	public boolean isLike(int userId, int artworkId) {
		// TODO Auto-generated method stub
		if(likesDao.findByUserIdAndArtworkId(userId, artworkId) == null) {
			return false;
		}else {
			return true;
		}
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

	@Override
	public Page<Artworks> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findAll(pageable);
	}

	@Override
	public Page<Integer> findAllTimeSort(Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findAllArtworkIdTimeSort(pageable);
	}

	@Override
	public List<Integer> findAllRandSort() {
		// TODO Auto-generated method stub
		return artworksDao.findAllArtworkIdRandSort();
	}

	@Override
	public Page<Integer> findAllLikeSort(Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findAllArtworkIdLikeSort(pageable);
	}

	@Override
	public Comments saveComment(Comments comments) {
		// TODO Auto-generated method stub
		return commentDao.save(comments);
	}

	@Override
	public Page<Integer> findBySearchKey(String key, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findBySearchKey(key, pageable);
	}


	
}
