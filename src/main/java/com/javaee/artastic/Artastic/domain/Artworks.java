package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;

import org.springframework.context.support.StaticApplicationContext;

import java.lang.Thread.State;
import java.sql.Timestamp;

@Entity
public class Artworks {
    private int artworkId;
    private int artistId;
    private String artworkName;
    private String artworkDescription;
    private String artworkDir;
    private Timestamp uploadtime;

    @Id@GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "Artwork_ID")
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Basic
    @Column(name = "Artist_ID")
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "Artwork_name")
    public String getArtworkName() {
        return artworkName;
    }

    public void setArtworkName(String artworkName) {
        this.artworkName = artworkName;
    }

    @Basic
    @Column(name = "Artwork_description")
    public String getArtworkDescription() {
        return artworkDescription;
    }

    public void setArtworkDescription(String artworkDescription) {
        this.artworkDescription = artworkDescription;
    }

    @Basic
    @Column(name = "Artwork_dir")
    public String getArtworkDir() {
        return artworkDir;
    }

    public void setArtworkDir(String artworkDir) {
        this.artworkDir = artworkDir;
    }

    @Basic
    @Column(name = "Uploadtime")
    public Timestamp getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Timestamp uploadtime) {
        this.uploadtime = uploadtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Artworks artworks = (Artworks) o;

        if (artworkId != artworks.artworkId) return false;
        if (artistId != artworks.artistId) return false;
        if (artworkName != null ? !artworkName.equals(artworks.artworkName) : artworks.artworkName != null)
            return false;
        if (artworkDescription != null ? !artworkDescription.equals(artworks.artworkDescription) : artworks.artworkDescription != null)
            return false;
        if (artworkDir != null ? !artworkDir.equals(artworks.artworkDir) : artworks.artworkDir != null) return false;
        if (uploadtime != null ? !uploadtime.equals(artworks.uploadtime) : artworks.uploadtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + artistId;
        result = 31 * result + (artworkName != null ? artworkName.hashCode() : 0);
        result = 31 * result + (artworkDescription != null ? artworkDescription.hashCode() : 0);
        result = 31 * result + (artworkDir != null ? artworkDir.hashCode() : 0);
        result = 31 * result + (uploadtime != null ? uploadtime.hashCode() : 0);
        return result;
    }

}
