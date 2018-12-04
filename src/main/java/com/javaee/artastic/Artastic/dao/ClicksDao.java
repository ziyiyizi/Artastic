package com.javaee.artastic.Artastic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Clicks;

@Component
public interface ClicksDao extends JpaRepository<Clicks, Long>{

}
