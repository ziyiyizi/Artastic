package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Tags;

@Component
public interface TagsDao extends JpaRepository<Tags, Long>{
	public List<Tags> findByTagId(int tagId);
	public List<Tags> findByArtworkId(int artworkId);
	public List<Tags> findByTagName(String tagName);
}
