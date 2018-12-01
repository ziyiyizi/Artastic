package com.javaee.artastic.Artastic.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class FollowPK implements Serializable {
    private int followerId;
    private int artistId;

    @Column(name = "Follower_ID")
    @Id
    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
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

        FollowPK followPK = (FollowPK) o;

        if (followerId != followPK.followerId) return false;
        if (artistId != followPK.artistId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = followerId;
        result = 31 * result + artistId;
        return result;
    }
}
