package com.javaee.artastic.Artastic.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaee.artastic.Artastic.domain.ArtWorkDetails;
import com.javaee.artastic.Artastic.domain.ArtWorkLikes;
import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.domain.Clicks;
import com.javaee.artastic.Artastic.domain.Comments;
import com.javaee.artastic.Artastic.domain.Likes;

public interface ArtworksService {
	//找出作家的所有投稿
	public List<Artworks> findByArtistId(int artistId);
	//找出特定作品
	public Artworks findByArtworkId(int artworkId);
	//找出特定名字的作品
	public List<Artworks> findByArtworkName(String artworkName);
	//找出作品的名字
	public String findNameByworkId(int workId);
	//找出作品的喜爱者列表
	public List<Map<String, Object>> findLikesList(int artworkId);
	//找出作品的喜爱者列表，分页
	public Page<Map<String, Object>> findLikesList(int artworkId, Pageable pageable);
	//作品评论列表
	public List<Map<String, Object>> findCommentList(int artworkId);
	//作品tag列表
	public List<String> findTagList(int artworkId);
	//一定时间内最受欢迎的tag
	public List<String> findTagListPopular(String start, String end);
	//一定时间内最受喜欢的作品
	public Integer findAllArtworkIdLikeSort(String start, String end);
	//一定时间内点击量最高的作品
	public Object[] findArtworkWeekly(String start, String end);
	//获取所有作品
	public List<Artworks> findAll();
	//获取所有作品，分页，无排序
	public Page<Artworks> findAll(Pageable pageable);
	//获取关注作家的作品，分页
	public Page<Integer> findFollowArtworks(int clientId, Pageable pageable);
	//获取所有作品，分页，上传时间排序
	public Page<Integer> findAllTimeSort(Pageable pageable);
	////获取所有作品，分页，评论数排序
	public Page<Integer> findAllCommentSort(Pageable pageable);
	//获取所有作品，随机排序
	public List<Integer> findAllRandSort();
	//获取所有作品，分页，随机排序
	public Page<Integer> findAllRandSort(Pageable pageable);
	//获取所有作品，分页，喜爱数排序
	public Page<Integer> findAllLikeSort(Pageable pageable);
	//按关键字查询，模糊搜索
	public Page<Integer> findBySearchKey(String key, Pageable pageable);
	//按关键字查询，模糊搜索
	public Page<Integer> findBySearchAll(String key, Pageable pageable);
	//按作品名字查询，模糊搜索
	public Page<Integer> findByWorkName(String artworkName, Pageable pageable);
	//按作家名字查询，模糊搜索
	public Page<Integer> findByArtistName(String userName, Pageable pageable);
	//按作家名字查询，精确搜索
	public Page<Integer> findByArtistNameEX(String userName, Pageable pageable);
	//按tag查询，模糊搜索
	public Page<Integer> findByTagName(String tagName, Pageable pageable);
	//按tag查询，精确搜索
	public Page<Integer> findByTagNameEX(String tagName, Pageable pageable);
	//按tag和作家查询，模糊搜索
	public Page<Integer> findByUserNameAndTag(String userName, String tagName, Pageable pageable);
	//按作家和作品名查询，模糊搜索
	public Page<Integer> findByUserNameAndWorkName(String userName, String artworkName, Pageable pageable);
	//按tag，作品名查询，模糊搜索
	public Page<Integer> findByWorkNameAndTag(String artworkName, String tagName, Pageable pageable);
	//按tag，作家，作品名查询，模糊搜索
	public Page<Integer> findByAllKey(String userName, String artworkName, String tagName, Pageable pageable);
	//获取喜爱的作品
	public Page<Integer> findUserLikes(int userId, Pageable pageable);
	
	//获取作品详细信息
	public ArtWorkDetails getArtworkDetails(int artworkId, int clientId);
	//获取作品详细信息
	public ArtWorkDetails getArtworkDetails(int artworkId);
	//获取作品详细信息
	public ArtWorkDetails getArtworkDetails(Artworks artworks);
	//获取作品喜爱，评论列表
	public ArtWorkLikes getArtworkLikes(int artworkId);
	
	//是否喜欢
	public boolean isLike(int userId, int artworkId);
	
	//更新作品
	public Artworks saveArtwork(Artworks artworks);
	//更新点击
	public Clicks saveClick(Clicks clicks);
	//更新喜爱作品
	public Likes saveLike(Likes likes);
	//更新评论
	public Comments saveComment(Comments comments);
	
	//获取每月点击量
	public List<Object[]> countClicksPerMonth(int artworkId);
	//统计点击者性别比例
	public List<Object[]> countClicksBySex(int artworkId);
	//作品的喜爱数
	public int countLikes(int artworkId);
	
	public List<String> findSimilarTag(String key);
	public List<String> findSimilarTagEX(String key);

}
