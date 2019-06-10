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

@Component
public interface ArtworksDao extends JpaRepository<Artworks, Long>, JpaSpecificationExecutor<Artworks>{
	public List<Artworks> findByArtistId(int artistId);
	public Artworks findByArtworkId(int artworkId);
	public List<Artworks> findByArtworkName(String artworkName);
	
	@Query("select artworkName from Artworks where artworkId=:artworkId")
	public String findNameByworkId(@Param("artworkId")int workId);
	
	public Page<Artworks> findAll(Pageable pageable); 
	
	@Query("select u.artworkId from Artworks u, Follow lu where lu.followerId = :clientId and lu.artistId = u.artistId order by u.uploadtime desc")
	public Page<Integer> findFollowArtworks(@Param("clientId")int clientId, Pageable pageable);
	
	@Query("select artworkId from Artworks order by uploadTime desc")
	public  Page<Integer> findAllArtworkIdTimeSort(Pageable pageable);
	
	//tweet排序
	@Query("select artworkId from Comments group by artworkId order by count(*) desc")
	public Page<Integer> findAllArtworkIdCommentSort(Pageable pageable);
	
	@Query("select artworkId from Likes group by artworkId order by count(*) desc")
	public Page<Integer> findAllArtworkIdLikeSort(Pageable pageable);
	
	//一定时间内like最多
	@Query(value="select Artwork_ID from likes where date(liketime) between ?1 and ?2 group by Artwork_ID order by count(*) desc limit 5", nativeQuery=true)
	public List<Integer> findAllArtworkIdLikeSort(String start, String end);
	
	@Query(value="select u.Artwork_ID,u.Artwork_Name,u.Artist_ID,ru.Artdata,count(*) as num from artworks u,artdata ru,clicks lu where u.Artwork_ID=ru.Artwork_ID and u.Artwork_ID=lu.Artwork_ID and lu.Clicktime between ?1 and ?2 group by u.Artwork_ID order by num desc limit 1", nativeQuery=true)
	public List<Object[]> findArtworkMaxClicks(String start, String end);
	
	@Query(value="SELECT artwork_ID FROM artworks WHERE artwork_ID >= ((SELECT MAX(artwork_ID) FROM artworks)-(SELECT MIN(artwork_ID) FROM artworks)) * RAND() + (SELECT MIN(artwork_ID) FROM artworks) ORDER BY ?#{#pageable}", nativeQuery=true)
	public Page<Integer> findAllArtworkIdRandSort(Pageable pageable);
	
	@Query(value="SELECT artwork_ID FROM artworks WHERE artwork_ID >= ((SELECT MAX(artwork_ID) FROM artworks)-(SELECT MIN(artwork_ID) FROM artworks)) * RAND() + (SELECT MIN(artwork_ID) FROM artworks) limit 10", nativeQuery=true)
	public List<Integer> findAllArtworkIdRandSort();
	
	@Query(value="(select distinct u.Artwork_ID from artworks u,users lu where u.Artist_ID=lu.User_ID and (lu.User_Name like CONCAT('%',?1,'%') or u.Artwork_Name like CONCAT('%',?1,'%'))) union (select distinct s.Artwork_ID from artworks s,tags rs where s.Artwork_ID=rs.Artwork_ID and rs.Tag_name like CONCAT('%',?1,'%')) ORDER BY ?#{#pageable}", nativeQuery=true)
	public Page<Integer> findBySearchAll(String key, Pageable pageable);
	
	@Query("select artworkId from Artworks where artworkId in (select distinct u.artworkId from Artworks u,Users lu where u.artistId=lu.userId and (lu.userName like %:key% or u.artworkName like %:key%)) or artworkId in (select distinct u.artworkId from Artworks u,Tags ru where u.artworkId=ru.artworkId and ru.tagName like %:key%)")
	public Page<Integer> findBySearchKey(@Param("key")String key, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu,Tags ru where u.artistId=lu.userId and u.artworkId=ru.artworkId and (ru.tagName like %:tagName% and lu.userName like %:userName%)")
	public Page<Integer> findByUserNameAndTag(@Param("userName")String userName, @Param("tagName")String tagName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu where u.artistId=lu.userId and (lu.userName like %:userName% and u.artworkName like %:workName%)")
	public Page<Integer> findByUserNameAndWorkName(@Param("userName")String userName, @Param("workName")String artworkName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu,Tags ru where u.artistId=lu.userId and u.artworkId=ru.artworkId and (ru.tagName like %:tagName% and u.artworkName like %:workName%)")
	public Page<Integer> findByWorkNameAndTag(@Param("workName")String artworkName, @Param("tagName")String tagName, Pageable pageable);
	
	@Query("select artworkId from Artworks where artworkName like %:artworkName%")
	public Page<Integer> findByWorkName(@Param("artworkName")String artworkName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu where u.artistId=lu.userId and lu.userName like %:userName%")
	public Page<Integer> findByArtistName(@Param("userName")String userName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu where u.artistId=lu.userId and lu.userName = :userName order by u.uploadtime desc")
	public Page<Integer> findByArtistNameEX(@Param("userName")String userName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Tags ru where u.artworkId=ru.artworkId and ru.tagName like %:tagName%")
	public Page<Integer> findByTagName(@Param("tagName")String tagName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Tags ru where u.artworkId=ru.artworkId and ru.tagName = :tagName")
	public Page<Integer> findByTagNameEX(@Param("tagName")String tagName, Pageable pageable);
	
	@Query("select distinct u.artworkId from Artworks u,Users lu,Tags ru where u.artistId=lu.userId and u.artworkId=ru.artworkId and (ru.tagName like %:tagName% and lu.userName like %:userName% and u.artworkName like %:workName%)")
	public Page<Integer> findByAllKey(@Param("userName")String userName, @Param("workName")String artworkName, @Param("tagName")String tagName, Pageable pageable);

}
