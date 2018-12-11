package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Artworks> findAll(Pageable pageable); 
	
	@Query("select artworkId from Artworks order by uploadTime desc")
	public Page<Integer> findAllArtworkIdTimeSort(Pageable pageable);
	
	@Query("select artworkId from Likes group by artworkId order by count(*)")
	public Page<Integer> findAllArtworkIdLikeSort(Pageable pageable);
	
	@Query(value="SELECT artwork_ID FROM artworks WHERE artwork_ID >= ((SELECT MAX(artwork_ID) FROM artworks)-(SELECT MIN(artwork_ID) FROM artworks)) * RAND() + (SELECT MIN(artwork_ID) FROM artworks) limit 10", nativeQuery=true)
	public List<Integer> findAllArtworkIdRandSort();
}
