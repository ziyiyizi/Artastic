package com.javaee.artastic.Artastic.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public Page<Map<String, Object>> findLikesList(int artworkId, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return likesDao.findLikesList(artworkId, pageable);
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
	public ArtWorkDetails getArtworkDetails(int artworkId, int clientId) {
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
		artWorkDetails.setDescription(artworks.getArtworkDescription());
		artWorkDetails.setFileURL(artdataDao.findUrlByArtworkId(artworkId));
		
		artWorkDetails.setLike(isLike(clientId, artworkId));
		return artWorkDetails;
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
		artWorkDetails.setDescription(artworks.getArtworkDescription());
		artWorkDetails.setFileURL(artdataDao.findUrlByArtworkId(artworkId));
		artWorkDetails.setLike(false);
		return artWorkDetails;
	}
	
	@Override
	public ArtWorkDetails getArtworkDetails(Artworks artworks) {
		// TODO Auto-generated method stub
		
		ArtWorkDetails artWorkDetails = new ArtWorkDetails();
		if(artworks == null) {
			return artWorkDetails;
		}
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
		artWorkDetails.setDescription(artworks.getArtworkDescription());
		artWorkDetails.setFileURL(artdataDao.findUrlByArtworkId(artworkId));
		artWorkDetails.setLike(false);
		return artWorkDetails;
	}

	@Override
	public ArtWorkLikes getArtworkLikes(int artworkId) {
		// TODO Auto-generated method stub
		ArtWorkLikes artWorkLikes = new ArtWorkLikes();
		Pageable pageable = new PageRequest(0, 10);
		artWorkLikes.setLikerslist(findLikesList(artworkId,pageable).getContent());
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
	public Page<Integer> findFollowArtworks(int clientId, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findFollowArtworks(clientId, pageable);
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

	//时间排序-最近七天 time>=? 传入time参数
	@Override
	public Page<Integer> findAllTimeSort(Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findAllArtworkIdTimeSort(pageable);
	}

	//随机排序
	@Override
	public List<Integer> findAllRandSort() {
		// TODO Auto-generated method stub
		return artworksDao.findAllArtworkIdRandSort();
	}
	
	@Override
	public Page<Integer> findAllCommentSort(Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findAllArtworkIdCommentSort(pageable);
	}

	//热度排序
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

	@Override
	public Page<Integer> findAllRandSort(Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findAllArtworkIdRandSort(pageable);
	}

	@Override
	public Page<Integer> findBySearchAll(String key, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findBySearchAll(key, pageable);
	}

	@Override
	public Page<Integer> findByUserNameAndTag(String userName, String tagName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByUserNameAndTag(userName, tagName, pageable);
	}

	@Override
	public Page<Integer> findByUserNameAndWorkName(String userName, String artworkName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByUserNameAndWorkName(userName, artworkName, pageable);
	}

	@Override
	public Page<Integer> findByWorkNameAndTag(String artworkName, String tagName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByWorkNameAndTag(artworkName, tagName, pageable);
	}

	@Override
	public Page<Integer> findByAllKey(String userName, String artworkName, String tagName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByAllKey(userName, artworkName, tagName, pageable);
	}

	@Override
	public Page<Integer> findUserLikes(int userId, Pageable pageable) {
		// TODO Auto-generated method stub
		return likesDao.findUserLikes(userId, pageable);
	}

	@Override
	public List<Object[]> countClicksPerMonth(int artworkId) {
		// TODO Auto-generated method stub
		return clicksDao.countClicksPerMonth(artworkId);
	}

	@Override
	public List<Object[]> countClicksBySex(int artworkId) {
		// TODO Auto-generated method stub
		return clicksDao.countClicksBySex(artworkId);
	}

	@Override
	public Page<Integer> findByWorkName(String artworkName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByWorkName(artworkName, pageable);
	}

	@Override
	public Page<Integer> findByArtistName(String userName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByArtistName(userName, pageable);
	}

	@Override
	public Page<Integer> findByArtistNameEX(String userName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByArtistNameEX(userName, pageable);
	}

	@Override
	public Page<Integer> findByTagName(String tagName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByTagName(tagName, pageable);
	}

	@Override
	public Page<Integer> findByTagNameEX(String tagName, Pageable pageable) {
		// TODO Auto-generated method stub
		return artworksDao.findByTagNameEX(tagName, pageable);
	}

	@Override
	public String findNameByworkId(int workId) {
		// TODO Auto-generated method stub
		return artworksDao.findNameByworkId(workId);
	}

	@Override
	public List<String> findTagListPopular(String start, String end) {
		// TODO Auto-generated method stub
		return tagsDao.findTagListPopular(start, end);
	}

	@Override
	public Integer findAllArtworkIdLikeSort(String start, String end) {
		// TODO Auto-generated method stub
		return artworksDao.findAllArtworkIdLikeSort(start, end).get(0);
	}

	@Override
	public Object[] findArtworkWeekly(String start, String end) {
		// TODO Auto-generated method stub
		List<Object[]> objects = artworksDao.findArtworkMaxClicks(start, end);
		if(objects.isEmpty() == true) {
			return null;
		} else {
			return objects.get(0);
		}
		
	}

	@Override
	public List<String> findSimilarTag(String key) {
		// TODO Auto-generated method stub
		return tagsDao.findSimilarTag(key);
	}

	@Override
	public List<String> findSimilarTagEX(String key) {
		// TODO Auto-generated method stub
		return tagsDao.findSimilarTagEX(key);
	}

	
}
