package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import com.javaee.artastic.Artastic.domain.Comments;

@Component
public interface CommentsDao extends JpaRepository<Comments, Long>{

	public List<Comments> findByArtworkId(int artworkId);
	public List<Comments> findByUserId(int userId);
}
