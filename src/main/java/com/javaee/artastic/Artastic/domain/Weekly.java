package com.javaee.artastic.Artastic.domain;

import java.util.List;

public class Weekly {
	private List<String> tags;
	private String iconURL;
	private String artistName;
	private int frenzy;
	
	private int artworkId;
	private String artworkName;
	private String fileURL;
	private int artworkviews;

	public String getIconURL() {
		return iconURL;
	}

	public void setIconURL(String iconURL) {
		this.iconURL = iconURL;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public int getFrenzy() {
		return frenzy;
	}

	public void setFrenzy(int frenzy) {
		this.frenzy = frenzy;
	}

	public int getArtworkId() {
		return artworkId;
	}

	public void setArtworkId(int artworkId) {
		this.artworkId = artworkId;
	}

	public String getArtworkName() {
		return artworkName;
	}

	public void setArtworkName(String artworkName) {
		this.artworkName = artworkName;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public int getArtworkviews() {
		return artworkviews;
	}

	public void setArtworkviews(int artworkviews) {
		this.artworkviews = artworkviews;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
}
