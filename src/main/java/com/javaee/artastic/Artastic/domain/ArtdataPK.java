package com.javaee.artastic.Artastic.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ArtdataPK implements Serializable {
    
    private int artdataId;
    private int artworkId;

    @Column(name = "Artwork_ID")
    @Id
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Column(name = "Artdata_ID")
    @Id
    public int getArtdataId() {
        return artdataId;
    }

    public void setArtdataId(int artdataId) {
        this.artdataId = artdataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtdataPK artdataPK = (ArtdataPK) o;

        if (artworkId != artdataPK.artworkId) return false;
        if (artdataId != artdataPK.artdataId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + artdataId;
        return result;
    }
}
