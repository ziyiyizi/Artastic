package com.javaee.artastic.Artastic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Artdata;

@Component
public interface ArtdataDao extends JpaRepository<Artdata, Long>{

}
