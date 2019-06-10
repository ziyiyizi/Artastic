package com.javaee.artastic.Artastic.domain;

import static org.junit.Assert.*;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;

public class ArtdataTest {

	private Artdata artdata = new Artdata();

	@Test
	public void testGetArtworkId() {
		artdata.setArtworkId(123);
		assertTrue(artdata.getArtworkId()==123);
	}

	@Test
	public void testSetArtworkId() {
		artdata.setArtworkId(123);
		assertTrue(artdata.getArtworkId()==123);
	}

	@Test
	public void testGetArtdataId() {
		artdata.setArtdataId(123);
		assertTrue(artdata.getArtdataId()==123);
	}

	@Test
	public void testSetArtdataId() {
		artdata.setArtdataId(123);
		assertTrue(artdata.getArtdataId()==123);
	}

	@Test
	public void testGetArtdata() {
		artdata.setArtdata("abc");
		String data = artdata.getArtdata();
		assertTrue(artdata.getArtdata().equals("abc"));
	}

	@Test
	public void testSetArtdata() {
		artdata.setArtdata("abc");
		String data = artdata.getArtdata();
		assertTrue(artdata.getArtdata().equals("abc"));
	}

	@Test
	public void testEqualsObject() {
		assertTrue(artdata.equals(new Artdata()));
	}

}
