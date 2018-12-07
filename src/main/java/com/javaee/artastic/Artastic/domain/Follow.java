package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(FollowPK.class)
public class Follow {
    private int followerId;
    private int artistId;
    private byte specialfollow;
    private Timestamp followtime;

    @Id
    @Column(name = "Follower_ID")
    public int getFollowerId() {
        return followerId;
    }

    public void setFollowerId(int followerId) {
        this.followerId = followerId;
    }

    @Id
    @Column(name = "Artist_ID")
    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    @Basic
    @Column(name = "Specialfollow")
    public byte getSpecialfollow() {
        return specialfollow;
    }

    public void setSpecialfollow(byte specialfollow) {
        this.specialfollow = specialfollow;
    }

    @Basic
    @Column(name = "followtime")
    public Timestamp getFollowtime() {
        return followtime;
    }

    public void setFollowtime(Timestamp followtime) {
        this.followtime = followtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Follow follow = (Follow) o;

        if (followerId != follow.followerId) return false;
        if (artistId != follow.artistId) return false;
        if (specialfollow != follow.specialfollow) return false;
        if (followtime != null ? !followtime.equals(follow.followtime) : follow.followtime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = followerId;
        result = 31 * result + artistId;
        result = 31 * result + (int) specialfollow;
        result = 31 * result + (followtime != null ? followtime.hashCode() : 0);
        return result;
    }
}
