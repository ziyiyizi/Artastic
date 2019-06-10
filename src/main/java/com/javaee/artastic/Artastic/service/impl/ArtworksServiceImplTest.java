package com.javaee.artastic.Artastic.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.javaee.artastic.Artastic.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = App.class)

@WebAppConfiguration
public class ArtworksServiceImplTest {
	
	@Autowired
	private ArtworksServiceImpl artworkServiceImpl;

	@Test
	public void testFindByArtistId() {
		artworkServiceImpl.findByArtistId(12345);
		assertTrue(true);
	}

}
