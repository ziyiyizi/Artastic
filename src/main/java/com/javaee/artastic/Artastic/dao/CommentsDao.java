package com.javaee.artastic.Artastic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import com.javaee.artastic.Artastic.domain.Comments;

@Component
public interface CommentsDao extends JpaRepository<Comments, Long>{

	public List<Comments> findByArtworkId(int artworkId);
	
	@Query("select new map(ru.userName as userName, ru.comment as comment, ru.commentTime as commentTime, ru.commentorName as commentorName, u.userIcon as userIcon) from Users u, Comments ru where u.userName = ru.commentorName and ru.artworkId = :artworkId order by commentTime desc")
	public List<Map<String, Object>> findCommentList(@Param("artworkId")int artworkId);
	
//	@Query("select new map(u.userName as userName, ru.comment as comment, ru.commentTime as commentTime) from Users u, Comments ru where u.userId = ru.userId and ru.artworkId = :artworkId")
//	public List<Map<String, Object>> findCommentList(@Param("artworkId")int artworkId);
}
