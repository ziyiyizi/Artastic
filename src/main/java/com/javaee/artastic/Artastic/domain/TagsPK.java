package com.javaee.artastic.Artastic.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TagsPK implements Serializable {
    private int artworkId;
    private String tagName;

    @Column(name = "Artwork_ID")
    @Id
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Column(name = "Tag_name")
    @Id
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagsPK tagsPK = (TagsPK) o;

        if (artworkId != tagsPK.artworkId) return false;
        if (tagName != null ? !tagName.equals(tagsPK.tagName) : tagsPK.tagName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }
}
