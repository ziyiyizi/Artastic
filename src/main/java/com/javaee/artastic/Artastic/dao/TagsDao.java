package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Tags;

@Component
public interface TagsDao extends JpaRepository<Tags, Long>{
	public List<Tags> findByArtworkId(int artworkId);
	public List<Tags> findByTagName(String tagName);
	
	@Query("select tagName from Tags where artworkId = :artworkId")
	public List<String> findTagList(@Param("artworkId")int artworkId);
	
	//一定时间内喜爱度最高的作品tag频率
	@Query(value="select ru.Tag_name from likes u,tags ru where u.Artwork_ID=ru.Artwork_ID and date(u.liketime) between ?1 and ?2 group by ru.Tag_name order by count(*) desc limit 5", nativeQuery=true)
	public List<String> findTagListPopular(String start, String end);
	
	@Query(value="select Tag_name from tags where Artwork_ID in " + 
			"(select Artwork_ID from tags where Tag_name like CONCAT('%',?1,'%')) and Tag_name <> ?1 " + 
			"group by Tag_name order by count(*) desc limit 5", nativeQuery=true)
	public List<String> findSimilarTag(String key);
	
	@Query(value="select Tag_name from tags where Artwork_ID in " + 
			"(select Artwork_ID from tags where Tag_name = ?1) and Tag_name <> ?1 " + 
			"group by Tag_name order by count(*) desc limit 5", nativeQuery=true)
	public List<String> findSimilarTagEX(String key);
	
}
