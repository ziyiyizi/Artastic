package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Clicks;

@Component
public interface ClicksDao extends JpaRepository<Clicks, Long>{
	public List<Clicks> findByUserId(int userId);
	public List<Clicks> findByArtworkId(int artworkId);
	
	@Query(value="select count(*) as clicknum,month(Clicktime) as clickmonth from clicks where Artwork_ID=?1 group by month(Clicktime) order by clickmonth asc", nativeQuery=true)
	public List<Object[]> countClicksPerMonth(int artworkId);
	
	@Query(value="select count(*) as clicknum,lu.User_sex as sex from clicks u,users lu where u.User_ID=lu.User_ID and Artwork_ID=?1 group by sex", nativeQuery=true)
	public List<Object[]> countClicksBySex(int artworkId);
	
}
