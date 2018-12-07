package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(LikesPK.class)
public class Likes {
    private int userId;
    private int artworkId;
    private Timestamp liketime;

    @Id
    @Column(name = "User_ID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "Artwork_ID")
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Basic
    @Column(name = "liketime")
    public Timestamp getLiketime() {
        return liketime;
    }

    public void setLiketime(Timestamp liketime) {
        this.liketime = liketime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Likes likes = (Likes) o;

        if (userId != likes.userId) return false;
        if (artworkId != likes.artworkId) return false;
        if (liketime != null ? !liketime.equals(likes.liketime) : likes.liketime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + artworkId;
        result = 31 * result + (liketime != null ? liketime.hashCode() : 0);
        return result;
    }
}
