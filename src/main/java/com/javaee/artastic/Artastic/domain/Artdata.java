package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@IdClass(ArtdataPK.class)
public class Artdata {
    private int artworkId;
    private int artdataId;
    private byte[] artdata;

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
    public byte[] getArtdata() {
        return artdata;
    }

    public void setArtdata(byte[] artdata) {
        this.artdata = artdata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artdata artdata1 = (Artdata) o;

        if (artworkId != artdata1.artworkId) return false;
        if (artdataId != artdata1.artdataId) return false;
        if (!Arrays.equals(artdata, artdata1.artdata)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + artdataId;
        result = 31 * result + Arrays.hashCode(artdata);
        return result;
    }
}
