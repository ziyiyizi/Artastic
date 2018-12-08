package com.javaee.artastic.Artastic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Likes;

@Component
public interface LikesDao extends JpaRepository<Likes, Long>{
	public List<Likes> findByUserId(int userId);
	public List<Likes> findByArtworkId(int artworkId);
	
	@Query("select new map(u.userName as userName, u.userIcon as userIcon, ru.liketime as liketime) from Users u, Likes ru where u.userId = ru.userId and ru.artworkId = :artworkId order by liketime desc")
	public List< Map<String, Object> > findLikesList(@Param("artworkId")int artworkId);
	
	@Query("select count(*) as likeNums from Likes where artworkId = :artworkId")
	public int countLikes(@Param("artworkId")int artworkId);
	
	public Likes findByUserIdAndArtworkId(int userId, int artworkId);
}
