package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Artworks;
import com.javaee.artastic.Artastic.domain.Users;

@Component
public interface ArtworksDao extends JpaRepository<Artworks, Long>, JpaSpecificationExecutor<Artworks>{
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
	
	@Query("select distinct u.artworkId from Artworks u,Users lu,Tags ru where u.artistId=lu.userId and u.artworkId=ru.artworkId and (ru.tagName like %:key% or lu.userName like %:key% or u.artworkName like %:key%)")
	public Page<Integer> findBySearchKey(@Param("key")String key, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu,Tags ru where u.artistId=lu.userId and u.artworkId=ru.artworkId and (ru.tagName like %:tagName% and lu.userName like %:userName%)")
	public Page<Integer> findByUserNameAndTag(@Param("userName")String userName, @Param("tagName")String tagName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu,Tags ru where u.artistId=lu.userId and u.artworkId=ru.artworkId and (lu.userName like %:userName% and u.artworkName like %:workName%)")
	public Page<Integer> findByUserNameAndWorkName(@Param("userName")String userName, @Param("workName")String artworkName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu,Tags ru where u.artistId=lu.userId and u.artworkId=ru.artworkId and (ru.tagName like %:tagName% and u.artworkName like %:workName%)")
	public Page<Integer> findByWorkNameAndTag(@Param("workName")String artworkName, @Param("tagName")String tagName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu,Tags ru where u.artistId=lu.userId and u.artworkId=ru.artworkId and (ru.tagName like %:tagName% and lu.userName like %:userName% and u.artworkName like %:workName%)")
	public Page<Integer> findByAllKey(@Param("userName")String userName, @Param("workName")String artworkName, @Param("tagName")String tagName, Pageable pageable);

}
