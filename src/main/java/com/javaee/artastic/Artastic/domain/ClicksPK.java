package com.javaee.artastic.Artastic.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class ClicksPK implements Serializable {
    private int userId;
    private int artworkId;
    private Timestamp clicktime;

    @Column(name = "User_ID")
    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Column(name = "Artwork_ID")
    @Id
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

        ClicksPK clicksPK = (ClicksPK) o;

        if (userId != clicksPK.userId) return false;
        if (artworkId != clicksPK.artworkId) return false;
        if (clicktime != null ? !clicktime.equals(clicksPK.clicktime) : clicksPK.clicktime != null) return false;
        
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
