package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(ClicksPK.class)
public class Clicks {
    private int userId;
    private int artworkId;
    private Timestamp clicktime;

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

    @Column(name = "Clicktime")
    @Id
    public Timestamp getClicktime() {
        return clicktime;
    }

    public void setClicktime(Timestamp clicktime) {
        this.clicktime = clicktime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clicks clicks = (Clicks) o;

        if (userId != clicks.userId) return false;
        if (artworkId != clicks.artworkId) return false;
        if (clicktime != null ? !clicktime.equals(clicks.clicktime) : clicks.clicktime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + artworkId;
        result = 31 * result + (clicktime != null ? clicktime.hashCode() : 0);
        return result;
    }
}
