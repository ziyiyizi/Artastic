package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;

@Entity
@IdClass(ArtdataPK.class)
public class Artdata {
	private int artdataId;
    private int artworkId;
    private String artdata;

    @Id
    @Column(name = "Artwork_ID")
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Id
    @Column(name = "Artdata_ID")
    public int getArtdataId() {
        return artdataId;
    }

    public void setArtdataId(int artdataId) {
        this.artdataId = artdataId;
    }

    @Basic
    @Column(name = "Artdata")
    public String getArtdata() {
        return artdata;
    }

    public void setArtdata(String artdata) {
        this.artdata = artdata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artdata artdata1 = (Artdata) o;

        if (artworkId != artdata1.artworkId) return false;
        if (artdataId != artdata1.artdataId) return false;
        if (artdata!= null ? !artdata.equals(artdata1.artdata) : artdata1.artdata != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + artdataId;
        result = 31 * result + (artdata != null ? artdata.hashCode() : 0);
        return result;
    }
}
