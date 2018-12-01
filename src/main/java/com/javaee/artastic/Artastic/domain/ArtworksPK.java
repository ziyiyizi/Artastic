package com.javaee.artastic.Artastic.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class ArtworksPK implements Serializable {
    private int artworkId;
    private int artistId;

    @Column(name = "Artwork_ID")
    @Id
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Column(name = "Artist_ID")
    @Id
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArtworksPK that = (ArtworksPK) o;

        if (artworkId != that.artworkId) return false;
        if (artistId != that.artistId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + artistId;
        return result;
    }
}
