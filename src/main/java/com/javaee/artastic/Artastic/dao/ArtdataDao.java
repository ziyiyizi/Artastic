package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Artdata;

@Component
public interface ArtdataDao extends JpaRepository<Artdata, Long>{
	public List<Artdata> findByArtworkId(int artworkId);
	public List<Artdata> findByArtdata(String artdata);
	
	@Query("select artdata from Artdata where artworkId = :artworkId")
	public String findUrlByArtworkId(@Param("artworkId")int artworkId);
}
