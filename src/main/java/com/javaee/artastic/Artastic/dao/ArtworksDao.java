package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Artworks;

@Component
public interface ArtworksDao extends JpaRepository<Artworks, Long>{
	public List<Artworks> findByArtistId(int artistId);
	public Artworks findByArtworkId(int artworkId);
	public List<Artworks> findByArtworkName(String artworkName);
	
	@Query("select artworkDescription from Artworks where artworkId = :artworkId")
	public String findDesciptionByArtworkId(@Param("artworkId")int artworkId);
}
