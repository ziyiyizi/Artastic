package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Follow;

@Component
public interface FollowDao extends JpaRepository<Follow, Long>{

	public Follow findByArtistIdAndFollowerId(int artistId, int followerId);
	
	@Query("select count(*) from Follow where artistId = :artistId")
	public int countFollows(@Param("artistId")int artistId);
}
