package com.javaee.artastic.Artastic.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.javaee.artastic.Artastic.domain.Artworks;

@Component
public interface ArtworksDao extends JpaRepository<Artworks, Long>{

}
