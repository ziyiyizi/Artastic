package com.javaee.artastic.Artastic.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Follow;

@Component
public interface FollowDao extends JpaRepository<Follow, Long>{

}
