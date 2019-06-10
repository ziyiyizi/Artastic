package com.javaee.artastic.Artastic.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.javaee.artastic.Artastic.domain.Artworks;

import net.sf.ezmorph.primitive.AbstractIntegerMorpher;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class ArtworksDaoTest {
	@Autowired
	JpaSpecificationExecutor<Artworks> artworksDao;

	@Test
	public void testFindByArtistId() {
		List<Artworks> artlist =  ((ArtworksDao) artworksDao).findByArtistId(123);
		for(Artworks artitem : artlist) {
		System.out.println(artitem.getArtworkName());
		}
		assertTrue(true);
	}

	@Test
	public void testFindByArtworkId() {
		Artworks artlist =  ((ArtworksDao) artworksDao).findByArtworkId(123);
		System.out.println(artlist.getArtworkName());
		assertTrue(true);
	}

	@Test
	public void testFindByArtworkName() {
		List<Artworks> artlist =  ((ArtworksDao) artworksDao).findByArtworkName("jack");
		for(Artworks artitem : artlist) {
		System.out.println(artitem.getArtworkName());
		}
		assertTrue(true);
	}

	@Test
	public void testFindNameByworkId() {
		String artlist =  ((ArtworksDao) artworksDao).findNameByworkId(12345);
		System.out.println(artlist);
		assertTrue(true);
	}

}
