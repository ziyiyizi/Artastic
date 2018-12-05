package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Likes;

@Component
public interface LikesDao extends JpaRepository<Likes, Long>{
	public List<Likes> findByUserId(int userId);
	public List<Likes> findByArtworkId(int artworkId);
}
